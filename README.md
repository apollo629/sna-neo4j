# Ms. Computer Engineering Social Network Analysis Term Project / sna-neo4j

## Recommendation Systems
### Cosine Similarity, K-NN Recommendation, Link Prediction Scoring Analysis

1. First Calculate Cosine Similarity & Create a relation as similarity
    https://en.wikipedia.org/wiki/Cosine_similarity
    ```//totalBalance 0 olmayanlardan e1 in takdir ettiklerini takdir edenler arasida similarity olusturan query
       MATCH (e1:Employee)-[:TAKDIR_ETTI]->(t1:Takdir)<-[:TAKDIR_ALDI]-(e2:Employee)-[:TAKDIR_ETTI]->(t2:Takdir)<-[:TAKDIR_ALDI]-(e3:Employee)
       where t1.totalBalance <> 0 and t2.totalBalance <> 0
       WITH SUM(t1.totalBalance * t2.totalBalance) as tdp,
       SQRT(REDUCE(t1Dot = 0.0, a in COLLECT(t1.totalBalance) | t1Dot + a^2)) as t1L,
       SQRT(REDUCE(t2Dot = 0.0, b in COLLECT(t2.totalBalance) | t2Dot + b^2)) as t2L, e1, e3
       MERGE (e1)-[s:SIMILARITY]-(e3)
       SET s.similarity = tdp / (t1L * t2L) ```

2. Apply K-NN Recommendation By Using Similarity
    ``` //k-nn recommendation
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


