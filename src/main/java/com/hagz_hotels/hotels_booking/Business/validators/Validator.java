package com.hagz_hotels.hotels_booking.Business.validators;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    boolean validate(HttpServletRequest request);
    boolean parameterIsEmpty(HttpServletRequest request);



}
