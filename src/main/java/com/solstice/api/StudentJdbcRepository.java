package com.solstice.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setWorkPhone(rs.getString("number_work"));
            student.setPersonalPhone(rs.getString("number_personal"));
            System.out.println(student);
            return student;

        }

    }

    public List<Student> findAll() {
        return jdbcTemplate.query("select * from student", new StudentRowMapper());
    }

    public Student findById(long id) {
        return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Student>(Student.class));
    }

    public Student findByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from student where email=?", new Object[] { email },
                new BeanPropertyRowMapper<Student>(Student.class));
    }

    public Student findByWorkNumber(String number) {
        number = Student.formatNumber(number);
        return jdbcTemplate.queryForObject("select * from student where number_work=?", new Object[] { number },
                new StudentRowMapper());
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from student where id=?", new Object[] { id });
    }

    public int insert(Student student) {
        return jdbcTemplate.update("insert into student (id, name, email, number_work, number_personal) " +
                        "values(?,  ?, ?, ?, ?)",
                new Object[] { student.getId(), student.getName(), student.getEmail(),
                        student.getWorkPhone(), student.getPersonalPhone() });
    }

    public int update(Student student) {
        return jdbcTemplate.update("update student " + " set name = ?, email = ? " + " where id = ?",
                new Object[] { student.getName(), student.getEmail(), student.getId() });
    }

}
