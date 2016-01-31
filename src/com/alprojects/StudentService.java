package com.alprojects;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


// http://www.simplecodestuffs.com/ajax-based-crud-operations-in-jsp-and-servlet-using-jtable-jquery-plug-in/
// http://stackoverflow.com/questions/21415413/access-file-inside-webcontent-from-servlet
// http://localhost:8080/StudentService/crunchify/students


@Path("/students")
public class StudentService {
	private StudentJdbcDAO studDAO;
	
	// @Context
	// ServletContext ctx;

	public StudentService( @Context ServletContext ctx ) {
		
		String strPath = ctx.getRealPath("/config/jdbc.xml");
		ApplicationContext context =
				new FileSystemXmlApplicationContext(strPath);
		studDAO = (StudentJdbcDAO) context.getBean("studentJDBCDAO");
	}

	@POST
	@Produces("application/json")
	public Response getStudents()
	{
		try {
			JtableResponse response = new JtableResponse( "OK", studDAO.listStudents() );
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String strResponse = ow.writeValueAsString(response);
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
}

