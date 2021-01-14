package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    IMapper<ResultSet, User> mapper = new IMapper<ResultSet, User>() {
        @Override
        public User apply(ResultSet set) throws SQLException {
            User user = new User();
            user.setUserId(set.getInt("UserId"));
            user.setName(set.getString("Name"));
            user.setEmail(set.getString("Email"));
            user.setPhone(set.getString("Phone"));
            user.setType(set.getString("Type"));
            user.setPassword(set.getString("Password"));
            return user;
        }
    };

    public User findByEmailAndPasswordAndType(String email, String password, String type) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE Email=? AND Password=? AND Type=?";
        return DBUtil.selectOne(query, mapper, email, password, type);
    }

    public User findById(Integer userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE UserId=?";
        return DBUtil.selectOne(query, mapper, userId);
    }

    public void update(String Name, String email, String phoneNumber, String password, Integer UserId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE User SET Name=?, Email=?, Phone=?, Password=? WHERE UserId=?";
        DBUtil.executeUpdate(query, Name, email, phoneNumber, password, UserId);
    }
}
