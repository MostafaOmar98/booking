package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User findByEmailAndPasswordAndType(String email, String password, String type) {
        String query = "SELECT * FROM User WHERE Email=? AND Password=? AND Type=?";
        Connection con = DBUtil.getConnection();
        User user = null;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, type);
            ResultSet set = stmt.executeQuery();
            if (set.next())
                user = map(set);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    private User map(ResultSet set) throws SQLException {
        User user = new User();
        user.setUserId(set.getInt("UserId"));
        user.setName(set.getString("Name"));
        user.setEmail(set.getString("Email"));
        user.setPhone(set.getString("Phone"));
        user.setType(set.getString("Type"));
        user.setPassword(set.getString("Password"));
        return user;
    }
}
