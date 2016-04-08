package com.alprojects.mvc_ctrl;

import com.alprojects.data.Student;
import com.alprojects.data.StudentJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alprojects.data.JsonBuilder;

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

    @RequestMapping( value="/getall", method = RequestMethod.POST, produces = "application/json")
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
            method = RequestMethod.POST, produces = "application/json")
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
            method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Student> appendStudent( @RequestBody Student student_in )
    {
        HttpHeaders responseHeader = new HttpHeaders();
        int id = studDAO.create( student_in.getName(), student_in.getAge() );
        Student stud = new Student(id, student_in.getName(), student_in.getAge());
        Map<String,Object> retobj = new JsonBuilder()
                .add( "Result", "OK" )
                .add( "Record", stud )
                .getBuiltObject();

        return new ResponseEntity(retobj, responseHeader, HttpStatus.OK);
    }

    @RequestMapping( value="/update/{id}",
            method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    public  ResponseEntity<Student> updateStudent(
            @PathVariable("id") int id, // id to be updated
            @RequestBody Student student_data // data to be updated
    )
    {
        HttpHeaders responseHeader = new HttpHeaders();
        studDAO.update( id, student_data.getName(), student_data.getAge() );
        Student stud_new = new Student( student_data );
        stud_new.setId( id );

        Map<String, Object> retobj = new JsonBuilder()
                .add("Result", "OK")
                .add("Record", stud_new).getBuiltObject();

        return new ResponseEntity(retobj, responseHeader, HttpStatus.OK);
    }

    @RequestMapping( value="/delete/{id}",
            method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteStudent(@PathVariable("id") int id)
    {
        HttpHeaders responseHeader = new HttpHeaders();
        studDAO.delete(id);
        Map<String, Object> retobj = new JsonBuilder()
                .add("Result", "OK")
                .getBuiltObject();
        return new ResponseEntity(retobj, responseHeader, HttpStatus.OK);
    }
}

