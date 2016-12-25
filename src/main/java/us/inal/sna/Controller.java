package us.inal.sna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return employeeRepository.recommend(uID);
    }



}
