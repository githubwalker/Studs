package com.alprojects;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class JtableResponse {

	private String result;
	private List<Student> studs;
	
	public JtableResponse(
			String result,
			List<Student> studs
			)
	{
		this.result = result;
		this.studs = studs;
	}
	
	@JsonProperty("Result")
	public String getResult() { return result; }
	
	@JsonProperty("Records")
	public List<Student> getRecords() { return studs; }
}

