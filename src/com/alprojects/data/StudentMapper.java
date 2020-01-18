package com.alprojects.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


class StudentMapper implements RowMapper<Student> {
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Student stud = new Student();
		stud.setAge(rs.getInt("age"));
		stud.setName(rs.getString("name"));
		stud.setId(rs.getInt("id"));
		return stud;
	}
}
