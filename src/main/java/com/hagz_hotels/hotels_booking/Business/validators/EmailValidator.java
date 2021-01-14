package com.hagz_hotels.hotels_booking.Business.validators;

import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.http.HttpServletRequest;

public class EmailValidator implements Validator {
    String parameter = "email";
    UserDAO userDAO = new UserDAO();
    @Override
    public boolean validate(HttpServletRequest request) throws Exception {
        if(parameterIsEmpty(request)){
            throw ValidationExceptionFactory.getEmailException(Status.INVALID);
        }
        String email = request.getParameter(parameter);
        if(isValidEmailAddress(email)){
            User user = userDAO.findByEmail(email);
            if(user == null){
                /// fine the email is valid and not used
                return true;
            }
            throw  ValidationExceptionFactory.getEmailException(Status.USED) ;
        }
        throw ValidationExceptionFactory.getEmailException(Status.INVALID);
    }

    @Override
    public boolean parameterIsEmpty(HttpServletRequest request ) {

        return request.getParameter(this.parameter) != null;
    }
    static enum Status{
        OK,USED,INVALID
    }
    static public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
