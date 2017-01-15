package us.inal.sna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alpereninal on 25/12/16.
 */
@RestController
public class Controller {

    @Autowired private EmployeeRepository employeeRepository;


    @RequestMapping(value = "/employee/rating", method = RequestMethod.GET)
    public Iterable<Map> getEmployeeRating(){
        return employeeRepository.findEmployeesByTakdirAlma();
    }

    @RequestMapping(value = "/employee/recommend/{uId}", method = RequestMethod.GET)
    public Iterable<Map> recommend(@PathVariable("uId") Integer uID){
        return employeeRepository.knn(uID);
    }

    @RequestMapping(value = "/employee/similar/{uId}", method = RequestMethod.GET)
    public String similar(@PathVariable("uId") Integer uID){
        return "Most Similar 5 Employee for id: " + uID + "\n" + employeeRepository.getSimilarEmployees(uID).toString();
    }

    @RequestMapping(value = "/employee/cossin/{uId1}/{uId2}", method = RequestMethod.GET)
    public String cosineSimilarity(@PathVariable("uId1") Integer uID1, @PathVariable("uId2") Integer uID2){
        return "Cosine Similarity of Employees with id1: " + uID1 + " id2: " + uID2 + " similarity: " + employeeRepository.cosineSimilarity(uID1, uID2);
    }

    @RequestMapping(value = "/employee/degrees", method = RequestMethod.GET)
    public Iterable<Map> degrees(){
        return employeeRepository.getEmployeesDegree();
    }

    @RequestMapping(value = "/employee/jaccard/{uId1}/{uId2}", method = RequestMethod.GET)
    public String jaccardScore(@PathVariable("uId1") Integer uID1, @PathVariable("uId2") Integer uID2){
        return "JaccardScore of Employees with id1: " + uID1 + " id2: " + uID2 + " score: " + employeeRepository.jaccardScore(uID1, uID2).toString();
    }

    @RequestMapping(value = "/employee/cns", method = RequestMethod.GET)
    public String commonNeighborScore(){
        List<String> neighbors = new ArrayList<>();
        List<CNS> cnsList = employeeRepository.commonNeighborScore();
        for(CNS cns : cnsList){
            neighbors.add(cns.toString());
        }
        return neighbors.toString();
    }





}
