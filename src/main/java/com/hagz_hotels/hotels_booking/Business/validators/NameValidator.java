package com.hagz_hotels.hotels_booking.Business.validators;

import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.http.HttpServletRequest;

public class NameValidator extends MyValidator{
    UserDAO userDAO = new UserDAO();
    public NameValidator(){
        this.parameter = "username";
    }
    @Override
    public boolean validate(HttpServletRequest request) throws Exception {
        if(parameterIsEmpty(request))
            throw ValidationExceptionFactory.getUserNameException(Status.EMPTY);
        String userName = request.getParameter("username");
        String type = request.getParameter("type");
        if(validUserName(userName)){
            User user =userDAO.findByUserNameAndType(userName, type);
            if(user == null){
                return true;
            }
            throw ValidationExceptionFactory.getUserNameException(Status.USED);
        }
        throw ValidationExceptionFactory.getUserNameException(Status.INVALID);
    }
    enum Status{
        USED,INVALID,EMPTY
    }
    Boolean validUserName(String username){
        return !username.isEmpty();
    }
}
