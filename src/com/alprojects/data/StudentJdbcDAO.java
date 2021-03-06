package com.alprojects.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class StudentJdbcDAO {
	private JdbcTemplate jdbcTemplateObject;
	private SimpleJdbcInsert jdbcInsert;

	public void setDataSource(DataSource ds) {
		jdbcTemplateObject = new JdbcTemplate(ds);
		jdbcInsert =   
			new SimpleJdbcInsert(ds)
			.withTableName("Students")
			.usingGeneratedKeyColumns("id");  		
	}

	public int create(String name, Integer age) {
		Map<String,Object> params = new HashMap<>();
		params.put("name", name);
		params.put("age", age);
		Number id = jdbcInsert.executeAndReturnKey(params);
		return id.intValue();
	}
	
	public Student getStudent(Integer id) {
		String sql = "select name, age from Students where id = ?";
		return jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new StudentMapper());
	}

	public List<Student> listStudentsPaged( Integer fromIndex, Integer nItems ) {
		String sql = "select * from Students LIMIT ?, ?";
		return jdbcTemplateObject.query(sql, new StudentMapper(), fromIndex, nItems);
	}

	public Integer getTotalStudents() {
		String sql = "select count(*) from Students";
		return jdbcTemplateObject.queryForObject(sql, Integer.class);
	}

	public List<Student> listStudents() {
		String sql = "select * from Students";
		return jdbcTemplateObject.query(sql, new StudentMapper());
	}

	public int delete(Integer id) {
		String sql = "delete from Students where id = ?";
		return jdbcTemplateObject.update(sql, id);
	}

	public int update(Integer id, String name, Integer age) {
		String sql = "update Students set name = ? , age = ? where id = ?";
		return jdbcTemplateObject.update(sql, name, age, id);
	}
}
