package com.example.testjdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.testjdbc.entities.Property;
import org.springframework.jdbc.core.RowMapper;

public class PropertyRowMapper implements RowMapper<Property>  {

    @Override
    public Property mapRow(ResultSet row, int rowNum) throws SQLException {
        Property property = new Property();
        property.setId(row.getInt("id"));
        property.setAddress(row.getString("address"));
        property.setNumRooms(row.getInt("numRooms"));
        property.setPrice(row.getInt("price"));
        property.setAllowSmoking(row.getBoolean("allowSmoking"));
        property.setMaxGuestNum(row.getInt("maxGuestNum"));
        return property;
    }
}