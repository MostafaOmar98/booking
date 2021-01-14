package com.hagz_hotels.hotels_booking.Business.validators.exceptions;

public class UserNameExistException extends Exception {
    public UserNameExistException(String message) {
        super(message);
    }
}
