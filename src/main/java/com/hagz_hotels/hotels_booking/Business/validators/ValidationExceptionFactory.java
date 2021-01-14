package com.hagz_hotels.hotels_booking.Business.validators;

import com.hagz_hotels.hotels_booking.Business.validators.exceptions.EmailExistException;
import com.hagz_hotels.hotels_booking.Business.validators.exceptions.InvalidEmailException;

public class ValidationExceptionFactory {
    static Exception getEmailException(EmailValidator.Status status){
        switch (status){
            case USED:
                return new EmailExistException("Email already used");
            case INVALID:
                return new InvalidEmailException("Invalid Email");
            default:
                return null;
        }
    }
    static Exception getUserNameException(){
        // TODO: 1/14/21
        return null;
    }
}

