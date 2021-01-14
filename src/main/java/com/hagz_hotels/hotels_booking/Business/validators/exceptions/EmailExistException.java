package com.hagz_hotels.hotels_booking.Business.validators.exceptions;

public class EmailExistException extends Exception {
    public EmailExistException(String message) {
        super(message);
    }
}
