package com.hagz_hotels.hotels_booking.Business.validators.exceptions;

public class ShortPasswordException extends Exception{
    public ShortPasswordException(String message){
        super(message);
    }
}
