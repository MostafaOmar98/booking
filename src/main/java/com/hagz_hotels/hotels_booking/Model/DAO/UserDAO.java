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
            user.setUsername(set.getString("Username"));
            user.setEmail(set.getString("Email"));
            user.setPhone(set.getString("Phone"));
            user.setType(set.getString("Type"));
            user.setPassword(set.getString("Password"));
            return user;
        }
    };


    public User findById(Integer userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE UserId=?";
        return DBUtil.selectOne(query, mapper, userId);
    }

    public void update(String email, String phone, String password, Integer UserId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE User SET  Email=?, Phone=?, Password=? WHERE UserId=?";
        DBUtil.executeUpdate(query, email, phone, password, UserId);
    }

    public Integer create(String email, String password, String username, String type, String phone) throws SQLException, ClassNotFoundException {
        String query = "Insert into User (Email, Password, Username, Type, phone) values (?, ?, ?, ?, ?)";
        return DBUtil.insert(query, email, password, username, type, phone);
    }

    public User findByUserNameAndType(String username, String type) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE Username=? AND Type=?";
        return DBUtil.selectOne(query,mapper,username, type);
    }

    public User findByEmailAndType(String email, String type) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE Username=? AND Type=?";
        return DBUtil.selectOne(query,mapper,email, type);
    }

    public User findByUserNameAndPasswordAndType(String username, String password, String type) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM User WHERE Username=? AND Password=? AND Type=?";
        return DBUtil.selectOne(query, mapper, username, password, type);
    }
}
