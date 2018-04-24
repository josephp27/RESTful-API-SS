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
public class ContactJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class StudentRowMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setName(rs.getString("name"));
            contact.setEmail(rs.getString("email"));
            contact.setWorkPhone(rs.getString("number_work"));
            contact.setPersonalPhone(rs.getString("number_personal"));
            return contact;

        }

    }

    public List<Contact> findAll() {
        return jdbcTemplate.query("select * from contact", new StudentRowMapper());
    }

    public Contact findById(long id) {
        return jdbcTemplate.queryForObject("select * from contact where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Contact>(Contact.class));
    }

    public Contact findByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from contact where email=?", new Object[] { email },
                new StudentRowMapper());
    }

    public Contact findByWorkNumber(String number) {
        number = Validatior.formatNumber(number);
        return jdbcTemplate.queryForObject("select * from contact where number_work=?", new Object[] { number },
                new StudentRowMapper());
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from contact where id=?", new Object[] { id });
    }

    public int insert(Contact contact) {
        return jdbcTemplate.update("insert into contact (id, name, email, number_work, number_personal, company) " +
                        "values(?,  ?, ?, ?, ?, ?)",
                new Object[] { contact.getId(), contact.getName(), contact.getEmail(),
                        contact.getWorkPhone(), contact.getPersonalPhone(), contact.getCompany() });
    }

    public int update(Contact contact) {
        return jdbcTemplate.update("update contact " + " set name = ?, email = ? " + " where id = ?",
                new Object[] { contact.getName(), contact.getEmail(), contact.getId() });
    }

}
