package com.alprojects.spr;

import com.alprojects.Student;
import com.alprojects.StudentJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// http://www.baeldung.com/2011/10/25/building-a-restful-web-service-with-spring-3-1-and-java-based-configuration-part-2/
// http://stackoverflow.com/questions/35770875/json-serialize-with-different-field-names-on-spring-mvc
// http://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/

// spring jackson object mapper

@RestController
public class SpringApiController {

    @Qualifier("studentJDBCDAO")
    @Autowired
    private StudentJdbcDAO studDAO;

    @RequestMapping("/strings")
    @ResponseBody
    public List<String> listArbitraryStrings()
    {
        List<String> ls = new ArrayList<>();
        ls.add( "1" );
        ls.add( "2" );
        ls.add( "3" );

        return ls;
    }

    @RequestMapping( value="/getall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Student>> getStudents()
    {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity(studDAO.listStudents(), responseHeader, HttpStatus.OK);
    }

}

