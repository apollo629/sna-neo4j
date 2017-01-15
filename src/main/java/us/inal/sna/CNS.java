package us.inal.sna;

import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by alpereninal on 15/01/17.
 */
@QueryResult
public class CNS {
    Integer e1, e2, n;

    @Override
    public String toString() {
        return "{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", cns=" + n +
                '}';
    }

    //
//    public CNS(Integer e1, Integer e2, Integer n) {
//        this.e1 = e1;
//        this.e2 = e2;
//        this.n = n;
//    }
//
//    @Override
//    public String toString() {
//        return "CNS{" +
//                "e1=" + e1 +
//                ", e2=" + e2 +
//                ", n=" + n +
//                '}';
//    }
//
//    public Integer getE1() {
//        return e1;
//    }
//
//    public void setE1(Integer e1) {
//        this.e1 = e1;
//    }
//
//    public Integer getE2() {
//        return e2;
//    }
//
//    public void setE2(Integer e2) {
//        this.e2 = e2;
//    }
//
//    public Integer getN() {
//        return n;
//    }
//
//    public void setN(Integer n) {
//        this.n = n;
//    }
}
