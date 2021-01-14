package com.hagz_hotels.hotels_booking.Business.validators;

import javax.servlet.http.HttpServletRequest;

public abstract class MyValidator {
    String parameter;
    abstract boolean validate(HttpServletRequest request) throws Exception;
    boolean parameterIsEmpty(HttpServletRequest request){
        return request.getParameter(this.parameter) == null;
    }
}
