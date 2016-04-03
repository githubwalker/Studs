package com.alprojects.spr;

import com.alprojects.Student;
import com.alprojects.StudentJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alprojects.JsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// http://localhost:8080/StudentService/sprrest/getall

// http://www.baeldung.com/2011/10/25/building-a-restful-web-service-with-spring-3-1-and-java-based-configuration-part-2/
// http://stackoverflow.com/questions/35770875/json-serialize-with-different-field-names-on-spring-mvc
// http://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/

// spring jackson object mapper

@RestController
@RequestMapping("/students")
public class SpringApiController {

    @Qualifier("studentJDBCDAO")
    @Autowired
    private StudentJdbcDAO studDAO;

    @RequestMapping( value="/getall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Student>> getStudents()
    {
        HttpHeaders responseHeader = new HttpHeaders();
        Map<String,Object> retobj = new JsonBuilder()
            .add("Result", "OK" )
            .add("Records", studDAO.listStudents())
            .getBuiltObject();


        return new ResponseEntity(retobj, responseHeader, HttpStatus.OK);
    }

    @RequestMapping( value="/getPage/{startIndex}/{numberItems}",
            method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Student>> getPage(
            @PathVariable("startIndex") int startIndex,
            @PathVariable("numberItems") int numberItems)
    {
        HttpHeaders responseHeader = new HttpHeaders();
        Integer nStuds = studDAO.getTotalStudents();
        Map<String,Object> retobj = new JsonBuilder()
                .add("Result", "OK" )
                .add("TotalRecordCount", nStuds )
                .add("Records", studDAO.listStudentsPaged(startIndex, numberItems) )
                .getBuiltObject();

        return new ResponseEntity(retobj, responseHeader, HttpStatus.OK);
    }

    @RequestMapping( value="/append",
            method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Student> appendStudent( @RequestBody final Student sa )
    {
        HttpHeaders responseHeader = new HttpHeaders();
        int id = studDAO.create( sa.getName(), sa.getAge() );
        Student stud = new Student(id, sa.getName(), sa.getAge());
        Map<String,Object> retobj = new JsonBuilder()
                .add( "Result", "OK" )
                .add( "Record", stud )
                .getBuiltObject();

        return new ResponseEntity(retobj, responseHeader, HttpStatus.OK);
    }

    @RequestMapping( value="/fake",
            method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Student> getFakeStud()
    {
        HttpHeaders responseHeader = new HttpHeaders();
        Student stud = new Student(1, "Fake", 15);

        return new ResponseEntity(stud, responseHeader, HttpStatus.OK);
    }

    @RequestMapping( value="/putfake",
            method = RequestMethod.GET, consumes = "text/plain")
    @ResponseBody
    public ResponseEntity putFakeStud(@RequestBody String str)
    {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity(null, responseHeader, HttpStatus.OK);
    }

}

