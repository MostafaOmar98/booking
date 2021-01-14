package com.hagz_hotels.hotels_booking.Business.validators;

import javax.servlet.http.HttpServletRequest;

public class PasswordValidator extends MyValidator{

    PasswordValidator(){
        this.parameter = "password";
    }
    @Override
    boolean validate(HttpServletRequest request) throws Exception {
        if(parameterIsEmpty(request))
           throw ValidationExceptionFactory.getPasswordException(Status.EMPTY);
        String password = request.getParameter(parameter);
        if(password.length() <8){
            throw ValidationExceptionFactory.getPasswordException(Status.TOO_SHORT);
        }
        return true;
    }
    enum Status{
        OK,INVALID,TOO_SHORT,EMPTY
    }
}
