package com.example.testjdbc.repositories;

import java.util.List;

import com.example.testjdbc.entities.Property;
import com.example.testjdbc.entities.Review;
import com.example.testjdbc.mappers.PropertyRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PropertyRepository
 */

 @Transactional
 @Repository
public class PropertyRepository {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PropertyRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Property> getAllPropertys(){
        String sql = "SELECT * FROM property";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    //    search by address, numRooms, price
    public List<Property> searchProperties(String address, int numRooms, int price){
        String sql = "SELECT * FROM property WHERE address LIKE ? AND numRooms = ? AND price = ? ";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql,new Object[] {"%" + address + "%", numRooms, price} , rowMapper);
    }

    public List<Property> getBookedProperties(){
        String sql = "SELECT DISTINCT property.* FROM property JOIN booking ON booking.propertyId = property.id";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Property> getReviewedProperties(){
        String sql = "SELECT DISTINCT property.* FROM property JOIN review ON review.propertyId = property.id";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public void createProperty(Property property){
        String sql = "INSERT INTO property VALUES (?,?,?,?,?)";
        this.jdbcTemplate.update(sql, property.getAddress(), property.getNumRooms(), property.getPrice(), property.getAllowSmoking(), property.getMaxGuestNum());

        sql = "SELECT id FROM property WHERE address = ?";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress());

        property.setId(id);
    }

    public void createReviewForProperty (Review review, int id){
        String sql = "INSERT INTO review VALUES (?,?,?)";
        int rating = review.getRating();
        String remark = review.getRemark();
        this.jdbcTemplate.update(sql, rating, remark, id);

        String select = "Select id from review where propertyId = ?";
        int reviewId = this.jdbcTemplate.queryForObject(select, Integer.class, id);
        review.setId(reviewId);
    }
}