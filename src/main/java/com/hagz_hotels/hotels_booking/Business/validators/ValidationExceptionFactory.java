package com.hagz_hotels.hotels_booking.Business.validators;

import com.hagz_hotels.hotels_booking.Business.validators.exceptions.*;

public class ValidationExceptionFactory {
    static Exception getEmailException(EmailValidator.Status status){
        switch (status){
            case USED:
                return new EmailExistException("Email already used");
            case INVALID:
                return new InvalidEmailException("Invalid Email");
            case EMPTY:
                return new EmptyException("Email can't be empty");
            default:
                return new undefinedBehaviorException("undefined behavior");
        }
    }
    static Exception getUserNameException(NameValidator.Status status){
        switch (status){
            case USED:
                return new UserNameExistException("username already used");
            case INVALID:
                return new InvalidUserNameException("Invalid username");
            case EMPTY:
                return new EmptyException("Username can't be empty");
            default:
                return new undefinedBehaviorException("undefined behavior");
        }
    }
}

