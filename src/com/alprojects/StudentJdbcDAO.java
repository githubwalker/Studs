package com.alprojects;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class StudentJdbcDAO {
	private DataSource dataSource;
	private SimpleJdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new SimpleJdbcTemplate(dataSource);
	}

	public int create(String name, Integer age) {
		String sql = "insert into Students (name, age) values (?, ?)";
		return jdbcTemplateObject.update(sql, name, age);
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
