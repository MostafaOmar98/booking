package com.hagz_hotels.hotels_booking.Business.Public;

import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.sql.SQLException;

public class SearchUser {

    private static final UserDAO userDAO = new UserDAO();

    public static User execute(String email, String password, String type) throws SQLException, ClassNotFoundException {

        User user = userDAO.findByEmailAndPasswordAndType(email, password, type);
        if (user == null)
            throw new IllegalArgumentException("User not found");
        return user;
    }
}
