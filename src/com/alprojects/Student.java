package com.alprojects;

import org.codehaus.jackson.annotate.JsonProperty;

public class Student {
	private String name;
	private Integer age;
	private Integer id;
	
	public Student()
	{
		
	}
	
	public Student( Integer id, String name, Integer age )
	{
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Student( Student other )
	{
		this.id = other.id;
		this.name = other.name;
		this.age = other.age;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("Age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@JsonProperty("PersonId")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append( id );
		sb.append( "). name: " );
		sb.append( name );
		sb.append( "; age: " );
		sb.append( age );
		return sb.toString();
	}
}

