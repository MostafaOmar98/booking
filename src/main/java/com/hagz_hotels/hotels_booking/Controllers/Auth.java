package com.hagz_hotels.hotels_booking.Controllers;

import com.hagz_hotels.hotels_booking.Model.Entities.User;

public class Auth {
    public enum Status{
        OK, AUTHORIZATION_ERROR, AUTHENTICATION_ERROR
    }

    public static Status isAuth(User user, User.Type authType) {
        if (user == null)
            return Status.AUTHENTICATION_ERROR;
        else if(user.getType() != authType)
            return Status.AUTHORIZATION_ERROR;
        return Status.OK;
    }
}
