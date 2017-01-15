package us.inal.sna;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;
import java.util.Map;

@RepositoryRestController
public interface EmployeeRepository extends GraphRepository<Employee> {


    @Query("MATCH (e1:Employee)-[:TAKDIR_ALDI]->(takdir)\n" +
            "RETURN e1.name, count(*) as takdirAlmaSayisi\n")
    List<Map> findEmployeesByTakdirAlma();

    //similarity hesabina gore 40 id li kullaniciya benzer kullanicilari getirir
    @Query("MATCH (e1:Employee {uID:{id}})-[s:SIMILARITY]-(e2:Employee) WITH e2, s.similarity as sim order by sim desc limit 5 return e2.name as neighbor, sim as similarity")
    List<Map> getSimilarEmployees(@Param("id") Integer id);

    //k-nn recommendation
    //e1 uID 40 olana benzerlerin takdir ettiklerini e1'e recommend et ortalama balance ile
    @Query("MATCH (e2:Employee)-[:TAKDIR_ETTI]->(takdir)<-[:TAKDIR_ALDI]-(e3:Employee), (e2)-[s:SIMILARITY]-(e1:Employee {uID:{id}})\n" +
            "WHERE NOT((e1)-[:TAKDIR_ETTI]->(e3))\n" +
            "with e3, s.similarity as cosim, takdir.totalBalance as balance\n" +
            "ORDER BY e3.uID, cosim DESC\n" +
            "WITH \t e3.uID AS emp, COLLECT(balance)[0..3] AS balances\n" +
            "WITH \t emp, REDUCE(s = 0, i IN balances | s + i)*1.0 / SIZE(balances) AS bal\n" +
            "ORDER BY bal DESC\n" +
            "limit 10\n" +
            "RETURN \t emp AS employee, bal AS Recommendation")
    List<Map> knn(@Param("id")Integer id);

    //uid ye gore 2 kullanicinin cosine similartysini verir
    @Query("MATCH  (p1:Employee {uID:{id1}})-[s:SIMILARITY]-(p2:Employee {uID:{id2}})\n" +
            "RETURN s.similarity AS `Cosine Similarity`")
    Double cosineSimilarity(@Param("id1")Integer id1, @Param("id2")Integer id2);

    //employee ve onun degreesi
    @Query("    MATCH  (p1:Employee)-[s:SIMILARITY]-(p2:Employee)\n" +
            "    with p1, count(p2) as d\n" +
            "    order by p1.uID asc\n" +
            "    return p1.uID, p1.degree")
    List<Map> getEmployeesDegree();

    //link prediction 2 employee'nin jaccard index score u
    @Query("MATCH  (p1:Employee {uID:{id1}})-[s:SIMILARITY]-(p2:Employee)\n" +
            "    with p2\n" +
            "    MATCH  (p3:Employee {uID:{id2}})-[s:SIMILARITY]-(p4:Employee)\n" +
            "    with p2, p4\n" +
            "    where p2 = p4\n" +
            "    with count (distinct p2.uID) as kesisim\n" +
            "    MATCH  (p1:Employee {uID:{id1}})-[s:SIMILARITY]-(p2:Employee)\n" +
            "    with p2, kesisim\n" +
            "    MATCH  (p3:Employee {uID:{id2}})-[s:SIMILARITY]-(p4:Employee)\n" +
            "    with p2, p4, kesisim\n" +
            "    where p2 <> p4\n" +
            "    return toFloat(kesisim)/(count(distinct p2.uID)+count(distinct p4.uID)-kesisim) as jaccard")
    Double jaccardScore(@Param("id1")Integer id1, @Param("id2")Integer id2);

    //link prediction common neighbour score
    @Query("MATCH  (p1:Employee)-[s:SIMILARITY]-(p2:Employee)\n" +
            "    with p1, p2\n" +
            "    MATCH  (p3:Employee)-[s:SIMILARITY]-(p4:Employee)\n" +
            "    with p1, p3, p2, p4\n" +
            "    where p2 = p4 and p1 <> p3\n" +
            "    return p1.uID as e1, p3.uID as e2, count(distinct p2.uID) as n\n" +
            "    order by count(distinct p2.uID) desc")
    List<CNS> commonNeighborScore();



}
