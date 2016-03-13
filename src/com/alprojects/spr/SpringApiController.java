package com.alprojects.spr;

import com.alprojects.Student;
import com.alprojects.StudentJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@RestController
public class SpringApiController {

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

    @RequestMapping("/getall")
    @ResponseBody
    public List<Student> getStudents()
    {
        return studDAO.listStudents();
    }

}
