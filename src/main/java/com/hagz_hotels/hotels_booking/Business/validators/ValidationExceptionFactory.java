package com.hagz_hotels.hotels_booking.Business.validators;

public class ValidationExceptionFactory {
    static Exception getEmailException(EmailValidator.Status status){
        switch (status){
            case USED:
                return new EmailExistException("Email already used");
            case INVALID:
                return new InvalidEmail("Invalid Email");
            default:
                return null;
        }
    }
    static Exception getUserNameException(){
        // TODO: 1/14/21
        return null;
    }
}
class EmailExistException extends Exception{
   EmailExistException(String message){super(message);}
}
class InvalidEmail extends Exception{
    InvalidEmail(String message){
        super(message);
    }
}
