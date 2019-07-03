package com.example.testjdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.testjdbc.entities.Booking;
import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setId(row.getInt("id"));
        booking.setCheckInDate(row.getString("checkInDate"));
        booking.setCheckOutDate(row.getString("checkOutDate"));
        booking.setTotalPrice(row.getInt("totalPrice"));
        booking.setRemarks(row.getString("remarks"));
        booking.setNumGuest(row.getInt("numGuest"));
        booking.setUserId(row.getInt("userId"));
        booking.setPropertyId(row.getInt("propertyId"));
        return booking;
    }
}