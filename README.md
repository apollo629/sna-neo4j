# Ms. Computer Engineering Social Network Analysis Term Project / sna-neo4j

## Recommendation Systems
### Cosine Similarity, K-NN Recommendation, Link Prediction Scoring Analysis

1. First Calculate Cosine Similarity & Create a relation as similarity
    * https://en.wikipedia.org/wiki/Cosine_similarity
    * ```//totalBalance 0 olmayanlardan e1 in takdir ettiklerinin takdir ettikleri ile e1 arasida similarity olusturan query
       MATCH (e1:Employee)-[:TAKDIR_ETTI]->(t1:Takdir)<-[:TAKDIR_ALDI]-(e2:Employee)-[:TAKDIR_ETTI]->(t2:Takdir)<-[:TAKDIR_ALDI]-(e3:Employee)
       where t1.totalBalance <> 0 and t2.totalBalance <> 0
       WITH SUM(t1.totalBalance * t2.totalBalance) as tdp,
       SQRT(REDUCE(t1Dot = 0.0, a in COLLECT(t1.totalBalance) | t1Dot + a^2)) as t1L,
       SQRT(REDUCE(t2Dot = 0.0, b in COLLECT(t2.totalBalance) | t2Dot + b^2)) as t2L, e1, e3
       MERGE (e1)-[s:SIMILARITY]-(e3)
       SET s.similarity = tdp / (t1L * t2L) ```

2. Get Similarity Score of 2 Employee
    * Use this endpoint to get similarity of 2 employee
    * http://localhost:8080/employee/cossin/40/1185
    * ``` //uid ye gore 2 kullanicinin cosine similartysini verir
        MATCH  (p1:Employee {uID:40})-[s:SIMILARITY]-(p2:Employee {uID:1185})
        RETURN s.similarity AS `Cosine Similarity` ```

3. Get Most Similar 5 Employee
    * Use this endpoint to get most 5 similar
    * http://localhost:8080/employee/similar/40
    * ```//similarity hesabina gore 40 id li kullaniciya benzer kullanicilari getirir
           MATCH (e1:Employee {uID:40})-[s:SIMILARITY]-(e2:Employee)
           WITH e2, s.similarity as sim
           order by sim desc limit 5 return e2.name as neighbor, sim as similarity ```


4. Apply K-NN Recommendation By Using Similarity
    * Use this endpoint to see recommendations for employee
    * http://localhost:8080/employee/recommend/40
    * ``` //k-nn recommendation
            //e1 uID 40 olana benzerlerin takdir ettiklerini e1'e recommend et ortalama balance ile
            MATCH (e2:Employee)-[:TAKDIR_ETTI]->(takdir)<-[:TAKDIR_ALDI]-(e3:Employee), (e2)-[s:SIMILARITY]-(e1:Employee {uID:40})
            WHERE NOT((e1)-[:TAKDIR_ETTI]->(e3))
            with e3, s.similarity as cosim, takdir.totalBalance as balance
            ORDER BY e3.uID, cosim DESC
            WITH 	 e3.uID AS emp, COLLECT(balance)[0..3] AS balances
            WITH 	 emp, REDUCE(s = 0, i IN balances | s + i)*1.0 / SIZE(balances) AS bal
            ORDER BY bal DESC
            limit 10
            RETURN 	 emp AS employee, bal AS Recommendation ```

5. Calculate Degrees of each Employee Node
    * ``` //set degree, number similar employees (neighbours) = degree of this employee
    MATCH  (p1:Employee)-[s:SIMILARITY]-(p2:Employee)
    with p1, count(p2) as d
    set p1.degree = d
    return p1.uID, p1.degree ```

6. Get Employe Degree
    * Use this endpoint to get degree of all employees
    * http://localhost:8080/employee/degrees
    * ```//employee ve onun degreesi
           MATCH  (p1:Employee)-[s:SIMILARITY]-(p2:Employee)
           with p1, count(p2) as d
           order by p1.uID asc
           return p1.uID, p1.degree ```

7. Calculate Jaccard Score of given 2 employee for Link Prediction
    * Use this endpoint to get jaccard score
    * http://localhost:8080/employee/jaccard/40/1171
    * ``` //link prediction 2 employee'nin jaccard index score u
        MATCH  (p1:Employee {uID:40})-[s:SIMILARITY]-(p2:Employee)
        with p2
        MATCH  (p3:Employee {uID:1171})-[s:SIMILARITY]-(p4:Employee)
        with p2, p4
        where p2 = p4
        with count (distinct p2.uID) as kesisim
        MATCH  (p1:Employee {uID:40})-[s:SIMILARITY]-(p2:Employee)
        with p2, kesisim
        MATCH  (p3:Employee {uID:1171})-[s:SIMILARITY]-(p4:Employee)
        with p2, p4, kesisim
        where p2 <> p4
        return toFloat(kesisim)/(count(distinct p2.uID)+count(distinct p4.uID)-kesisim) as jaccard ```

8. Calculate Common Neighbor Score of given all employees for Link Prediction
   * Use this endpoint to get jaccard score
   * http://localhost:8080/employee/cns
   * ``` //link prediction common neighbour score
           MATCH  (p1:Employee)-[s:SIMILARITY]-(p2:Employee)
           with p1, p2
           MATCH  (p3:Employee)-[s:SIMILARITY]-(p4:Employee)
           with p1, p3, p2, p4
           where p2 = p4 and p1 <> p3
           return p1.uID, p3.uID, count(distinct p2.uID)
           order by count(distinct p2.uID) desc ```



