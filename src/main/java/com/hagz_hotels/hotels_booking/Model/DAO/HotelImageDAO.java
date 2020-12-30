package com.hagz_hotels.hotels_booking.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HotelImageDAO {
    public void create(String URL, Integer hotelId) {
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO HotelImage (URL, HotelId) VALUES(?, ?)");
            stmt.setString(1, URL);
            stmt.setInt(2, hotelId);
            stmt.executeUpdate();
            DBUtil.close(con, stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
