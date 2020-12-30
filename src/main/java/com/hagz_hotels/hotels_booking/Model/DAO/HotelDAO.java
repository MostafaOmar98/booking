package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDAO {
    public Hotel findByAdminId(Integer adminId) {
        Connection con = DBUtil.getConnection();
        Hotel hotel = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Hotel WHERE AdminId=?");
            stmt.setInt(1, adminId);
            ResultSet set = stmt.executeQuery();
            if (set.next())
                hotel = map(set);
            DBUtil.close(con, stmt, set);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotel;
    }

    private Hotel map(ResultSet set) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setHotelId(set.getInt("HotelId"));
        hotel.setName(set.getString("Name"));
        hotel.setLatitude(set.getFloat("Latitude"));
        hotel.setLongitude(set.getFloat("Longitude"));
        hotel.setAddress(set.getString("Address"));
        hotel.setPhone(set.getString("Phone"));
        hotel.setAdminId(set.getInt("AdminId"));
        return hotel;
    }

    public void create(String name, Integer adminId) {
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Hotel (Name, AdminId) VALUES(?, ?)");
            stmt.setString(1, name);
            stmt.setInt(2, adminId);
            stmt.executeUpdate();
            DBUtil.close(con, stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Integer hotelId, String name, Float latitude, Float longitude, String address, String phone, Integer adminId) {
        if (name != null)
            update(hotelId, "Name", name);
        if (latitude != null)
            update(hotelId, "Latitude", latitude);
        if (longitude != null)
            update(hotelId, "Longitude", longitude);
        if (address != null)
            update(hotelId, "Address", address);
        if (phone != null)
            update(hotelId, "Phone", phone);
        if (adminId != null)
            update(hotelId, "AdminId", adminId);
    }

    private void update(Integer hotelId, String fieldName, Object value) {
        String query = "UPDATE Hotel SET " + fieldName + "=? WHERE HotelId=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setObject(1, value);
            stmt.setInt(2, hotelId);
            stmt.executeUpdate();
            DBUtil.close(con, stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
