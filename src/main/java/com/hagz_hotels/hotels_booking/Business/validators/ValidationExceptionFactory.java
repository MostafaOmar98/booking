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
    static Exception getPasswordException(PasswordValidator.Status status){
        switch (status){
            case INVALID:
                return new InvalidPasswordException("Invalid Password");
            case TOO_SHORT:
                return new ShortPasswordException("Password must be at least 8 characters");
            case EMPTY:
                return new EmptyException("Password can't be empty");
            default:
                return  new undefinedBehaviorException("we faced internal problem please try later");
        }
    }
    static  Exception getTypeException(TypeValidator.Status status){
        switch (status){
            case Empty:
                return new EmptyException("Password can't be empty");
            case Invalid:
                /// todo extend invalid type exception
                return new InvalidPasswordException("Invalid type");
            default:
                return new undefinedBehaviorException("we faced internal problem");
        }
    }
    public static Exception getIntervalException(DateIntervalValidator.State state){
        switch (state){
            case Empty:
                return new EmptyException("Date can't be empty");
            case StartBeforeToday:
                return new InvalidPasswordException("Check in date can't be before today");
            case StartEqualEnd:
                return new InvalidPasswordException("Check out date can't be the same as check in");
            case EndBeforeStart:
                return new InvalidPasswordException("Check out date can't be before check in");
            default:
                return new undefinedBehaviorException("we faced internal problem");

        }
    }
}

