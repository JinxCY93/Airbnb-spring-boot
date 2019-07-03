package com.example.testjdbc.repositories;

import java.util.List;

import com.example.testjdbc.entities.User;
import com.example.testjdbc.mappers.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRepository
 */

 @Transactional
 @Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM \"user\"";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<User> getUsersByEmail(String email){
        String sql = "SELECT * FROM \"user\" WHERE email = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, new Object[]{email} ,rowMapper); 
    }

    public void addUser(String firstName, String lastName, String email, String phone){
        String sql = "INSERT INTO \"user\" VALUES (?, ?, ?, ?)";
        this.jdbcTemplate.update(sql, firstName, lastName, email, phone);
    }

    public void updateUser(String oldFirstName, String newFirstName){
        String sql = "UPDATE \"user\" SET first_name = ? WHERE first_name = ?";
        this.jdbcTemplate.update(sql, newFirstName, oldFirstName );
    }

    public void deleteUser(int id){
        String sql = "DELETE FROM \"user\" WHERE id = ?";
        this.jdbcTemplate.update(sql, id);
    }

    public void createUser(User user) {
        String sql = "INSERT INTO \"user\" VALUES(?, ?, ?, ?) ";
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String phone = user.getPhone();
        this.jdbcTemplate.update(sql, firstName, lastName, email, phone);
  
        String select = "SELECT id FROM \"user\" WHERE email = ?";
        int userId = this.jdbcTemplate.queryForObject(select, Integer.class, email);
  
        user.setId(userId);
      }


      // update user
      public void updateUserById (User user, int id){
        String sql = "UPDATE \"user\" SET firstName = ?, lastName =?, email =?, phone=? WHERE id = ?";
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String phone = user.getPhone();
        this.jdbcTemplate.update(sql, firstName, lastName, email, phone, id);
      }
}