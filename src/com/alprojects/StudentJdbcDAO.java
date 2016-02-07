package com.alprojects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class StudentJdbcDAO {
	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplateObject;
	private SimpleJdbcInsert jdbcInsert;

	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new SimpleJdbcTemplate(dataSource);
		jdbcInsert =   
			new SimpleJdbcInsert(dataSource)  
			.withTableName("Students")  
			.usingGeneratedKeyColumns("id");  		
	}

	/*
	public int create(String name, Integer age) {
		String sql = "insert into Students (name, age) values (?, ?)";
		return jdbcTemplateObject.update(sql, name, age);
	}
	*/
	public int create(String name, Integer age) {
		// String sql = "insert into Students (name, age) values (?, ?)";
		// jdbcTemplateObject
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", name);
		params.put("age", age);
		Number id = jdbcInsert.executeAndReturnKey(params);
		return id.intValue();
	}
	

	public Student getStudent(Integer id) {
		String sql = "select name, age from Students where id = ?";
		return jdbcTemplateObject.queryForObject(sql, new StudentMapper(), id);
	}

	public List<Student> listStudents() {
		String sql = "select * from Students";
		return jdbcTemplateObject.query(sql, new StudentMapper());
	}

	public int delete(Integer id) {
		String sql = "delete from Students where id = ?";
		return jdbcTemplateObject.update(sql, id);
	}

	public int update(Integer id, Integer age) {
		String sql = "update Students set age = ? where id = ?";
		return jdbcTemplateObject.update(sql, age, id);
	}
}
