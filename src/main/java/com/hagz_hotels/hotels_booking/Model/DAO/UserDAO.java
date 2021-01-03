package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class UserDAO {

    Function<ResultSet, User> mapper = new Function<ResultSet, User>() {
        @Override
        public User apply(ResultSet set) {

            User user = new User();
            try {
                user.setUserId(set.getInt("UserId"));
                user.setName(set.getString("Name"));
                user.setEmail(set.getString("Email"));
                user.setPhone(set.getString("Phone"));
                user.setType(set.getString("Type"));
                user.setPassword(set.getString("Password"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return user;
        }
    };

    public User findByEmailAndPasswordAndType(String email, String password, String type) {
        String query = "SELECT * FROM User WHERE Email=? AND Password=? AND Type=?";
        return DBUtil.selectOne(query, mapper, email, password, type);
    }
}
