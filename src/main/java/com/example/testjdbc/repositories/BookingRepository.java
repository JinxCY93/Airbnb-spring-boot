package com.example.testjdbc.repositories;

import java.util.List;

import com.example.testjdbc.entities.Booking;
import com.example.testjdbc.mappers.BookingRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookingRepository
 */

 @Transactional
 @Repository
public class BookingRepository {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Booking> getAllBookings(){
        String sql = "SELECT * FROM booking";
        RowMapper<Booking> rowMapper = new BookingRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Booking> getBookingsByPropertyId(int id){
        String sql = "SELECT * FROM booking WHERE propertyId = ?";
        RowMapper<Booking> rowMapper = new BookingRowMapper();
        return this.jdbcTemplate.query(sql, new Object[]{id},rowMapper);
    }

    public List<Booking> getPaymentByBookingId(int id){
        String sql = "SELECT DISTINCT booking.* FROM booking JOIN payment ON booking.id = payment.bookingId where payment.bookingId = ? ";
        RowMapper<Booking> rowMapper = new BookingRowMapper();
        return this.jdbcTemplate.query(sql, new Object[]{id},rowMapper);
    }

    public void updateTotalPrice(Booking booking, int id){
        String sql = "UPDATE booking SET totalPrice = ? WHERE id = ?";
        int totalPrice = booking.getTotalPrice();
        this.jdbcTemplate.update(sql, totalPrice, id);
    }
}