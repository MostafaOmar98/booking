package com.hagz_hotels.hotels_booking.Business.validators;

import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.http.HttpServletRequest;

public class TypeValidator extends MyValidator{
    public TypeValidator(){
        this.parameter = "type";
    }
    @Override
    public boolean validate(HttpServletRequest request) throws Exception {
        if(parameterIsEmpty(request))
            throw ValidationExceptionFactory.getTypeException(Status.Empty);
        String type = request.getParameter(parameter);
        if(type.equals(User.Type.CLIENT.toString()) || type.equals(User.Type.ADMIN.toString()))
            return true;
        throw ValidationExceptionFactory.getTypeException(Status.Invalid);
    }
    enum Status{
        OK,Invalid,Empty
    }
}
