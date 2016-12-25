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

    @Query("MATCH (e1:Employee)-[:TAKDIR_ETTI]->(takdir)<-[:TAKDIR_ALDI]-(e2:Employee)-[:TAKDIR_ALDI]->(t2:Takdir)<-[:TAKDIR_ETTI]-(e3:Employee)\n" +
            "WHERE takdir.fromUserID = {id}\n" +
            "RETURN e3.name, count(*) as count \n" +
            "ORDER BY count DESC")
    List<Map> recommend(@Param("id") Integer id);



}
