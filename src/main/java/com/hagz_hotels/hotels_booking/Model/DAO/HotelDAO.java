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

}
