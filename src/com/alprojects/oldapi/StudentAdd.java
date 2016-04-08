package com.alprojects.oldapi;

import org.codehaus.jackson.annotate.JsonProperty;

class StudentAdd {
	private String name;
	private Integer age;
	
	StudentAdd()
	{
		
	}
	
	public StudentAdd( String name, Integer age )
	{
		this.name = name;
		this.age = age;
	}

	@JsonProperty("Name")
	public String getName() 
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	@JsonProperty("Age")
	public Integer getAge()
	{
		return age;
	}
	
	public void setAge( Integer age )
	{
		this.age = age;
	}
}

