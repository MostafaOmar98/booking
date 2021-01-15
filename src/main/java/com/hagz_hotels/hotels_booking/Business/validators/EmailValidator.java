package com.hagz_hotels.hotels_booking.Business.validators;

import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.http.HttpServletRequest;

public class EmailValidator extends MyValidator {
    UserDAO userDAO = new UserDAO();
    public EmailValidator(){
        this.parameter= "email";
    }
    @Override
    public boolean validate(HttpServletRequest request) throws Exception {
        if(parameterIsEmpty(request)){
            throw ValidationExceptionFactory.getEmailException(Status.EMPTY);
        }
        String email = (String) request.getParameter(parameter);
        String type = request.getParameter("type");
        if(isValidEmailAddress(email)){
            User user = userDAO.findByEmailAndType(email, type);
            if(user == null){
                /// fine the email is valid and not used
                return true;
            }
            throw  ValidationExceptionFactory.getEmailException(Status.USED) ;
        }
        throw ValidationExceptionFactory.getEmailException(Status.INVALID);
    }
    static enum Status{
        OK,USED,INVALID,EMPTY
    }
    static public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
