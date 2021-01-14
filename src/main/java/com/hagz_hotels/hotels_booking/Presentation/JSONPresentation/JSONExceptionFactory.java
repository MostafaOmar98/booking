package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation;

import com.hagz_hotels.hotels_booking.Business.validators.exceptions.*;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import java.sql.SQLException;

public class JSONExceptionFactory {
    static public JsonResponse getJSONResponse(Exception e){
        String className= e.getClass().getName();
        JsonResponse jsonResponse = new JsonResponse();
        e.printStackTrace();
        if( e instanceof EmailExistException){
            jsonResponse.setAttr("status", "Email already exists");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        if( e instanceof UserNameExistException){
            jsonResponse.setAttr("status", "Username already exists");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        if(e instanceof  InvalidEmailException){
            jsonResponse.setAttr("status", "Invalid Email");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        if(e instanceof  InvalidUserNameException){
            jsonResponse.setAttr("status", "Invalid Username");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        if(e instanceof SQLException){
            jsonResponse.setAttr("status", "Mysql failure");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        if(e instanceof ClassNotFoundException){
            jsonResponse.setAttr("status", "ClassNotFoundException");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        if(e instanceof undefinedBehaviorException){
            jsonResponse.setAttr("status", "undefined");
            jsonResponse.setAttr("success", "false");
            return jsonResponse;
        }
        jsonResponse.setAttr("status", "unknown behavior");
        jsonResponse.setAttr("success" , "false");
        return jsonResponse;

    }

}
