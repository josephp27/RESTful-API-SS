package com.solstice.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
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
            contact.setCompany(rs.getString("company"));
            contact.setWorkPhone(rs.getString("number_work"));
            contact.setPersonalPhone(rs.getString("number_personal"));
            contact.setStreet(rs.getString("street"));
            contact.setState(rs.getString("state"));
            contact.setCity(rs.getString("city"));
            contact.setProfileImage(rs.getString("image"));
            contact.setBd(rs.getString("bd"));
            return contact;

        }

    }

    public List<Contact> findAll() {
        return jdbcTemplate.query("select * from contact", new StudentRowMapper());
    }

    public List<Contact> findAllState(String state) {
        return jdbcTemplate.query("select * from contact where state=?",new Object[] { state }, new StudentRowMapper());
    }

    public List<Contact> findAllCity(String city) {
        return jdbcTemplate.query("select * from contact where city=?",new Object[] { city }, new StudentRowMapper());
    }

    public Contact findById(long id) {
        return jdbcTemplate.queryForObject("select * from contact where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Contact>(Contact.class));
    }

    public Contact findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("select * from contact where email=?", new Object[]{email},
                    new StudentRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    public Contact findByWorkNumber(String number) {
        try {
            return jdbcTemplate.queryForObject("select * from contact where number_work=?", new Object[]{number},
                    new StudentRowMapper());
        }catch(Exception e){
            return null;
        }
    }

    public int deleteByEmail(String email) {
        return jdbcTemplate.update("delete from contact where email=?", new Object[] { email });
    }

    public int insert(Contact contact) {
        Contact c = findByEmail(contact.getEmail());
        if(c != null){
            throw new IllegalArgumentException("That email is already registered to a user!");
        }else{
            return jdbcTemplate.update("insert into contact (id, name, email, number_work, number_personal, company, street, state, city, image, bd) " +
                            "values(?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    new Object[] { contact.getId(), contact.getName(), contact.getEmail(),
                            contact.getWorkPhone(), contact.getPersonalPhone(), contact.getCompany(),
                            contact.getStreet(), contact.getState(), contact.getCity(),
                            contact.getProfileImage(), contact.getBd()});
        }
    }

    public int update(Contact contact) {
        //TODO: Allow for updating only some values and retaining old if value is empty
        return jdbcTemplate.update("update contact " + " set name = IsNull(?, name)," +
                        " number_work = ?, " +
                        " number_personal = ?, " +
                        " company = ?, " +
                        " street = ?, " +
                        " state = ?, " +
                        " city = ?, " +
                        " image = ?, " +
                        " bd = ? " +
                        " where email = ?",
                new Object[] { contact.getName(),
                        contact.getWorkPhone(),
                        contact.getPersonalPhone(),
                        contact.getCompany(),
                        contact.getStreet(),
                        contact.getState(),
                        contact.getCity(),
                        contact.getProfileImage(),
                        contact.getBd(),
                        contact.getEmail() });
    }

}
