package com.alprojects;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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

import com.sun.jersey.api.client.ClientResponse.Status;


// http://www.simplecodestuffs.com/ajax-based-crud-operations-in-jsp-and-servlet-using-jtable-jquery-plug-in/
// http://stackoverflow.com/questions/21415413/access-file-inside-webcontent-from-servlet
// http://localhost:8080/StudentService/crunchify/students

// REST
// http://www.programming-free.com/2013/08/ajax-based-crud-operations-in-java-web.html
// https://github.com/hikalkan/jtable/issues/772
// http://crunchify.com/create-very-simple-jersey-rest-service-and-send-json-data-from-java-client/
// https://www.nabisoft.com/tutorials/java-ee/producing-and-consuming-json-or-xml-in-java-rest-services-with-jersey-and-jackson


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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getall")
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
	
	private static String nonnull( String str )
	{
		return str == null ? "<null>" : str;
	}
	
	private static Integer nonnull( Integer intgr )
	{
		return intgr == null ? 0 : intgr;
	}	
	
	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/append")
	public Response appendStudent( 
			String strstud
			// final StudentAdd stud
			) {
		// System.out.println( "Name is" + nonnull( stud.getName() )  );
		// System.out.println( "Age is" + nonnull( stud.getAge() )  );
		System.out.println( "Object received : " + nonnull(strstud) );
		
		ObjectMapper mapper = new ObjectMapper();
		StudentAdd sa = null;
		try {
			sa = mapper.readValue( strstud, StudentAdd.class );
		} catch (IOException e) {
			System.out.println("Unsuppoted media type");
			e.printStackTrace();
			return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).build();
		}
		
		int id = studDAO.create(sa.getName(), sa.getAge());
		
		System.out.println( "returned value is : " + id );
		
		Student stud = new Student( id, sa.getName(), sa.getAge() );
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String strResponse = ow.writeValueAsString(stud);
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	
	

}

