package com.hagz_hotels.hotels_booking.Controllers;

import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Auth {

    public enum Status{
        OK, AUTHORIZATION_ERROR, AUTHENTICATION_ERROR
    }

    public static Status isAuth(User user, User.Type authType) {
        if (user == null)
            return Status.AUTHENTICATION_ERROR;
        else if(user.getType() != authType)
            return Status.AUTHORIZATION_ERROR;
        return Status.OK;
    }

    public static boolean authenticate(HttpServletRequest request, HttpServletResponse response, User.Type authType) throws IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null || user.getType() != authType)
        {
            session.invalidate();
            response.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }

    public static boolean authenticateJson(HttpServletRequest request, HttpServletResponse response, User.Type authType) throws IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null || user.getType() != authType)
        {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setAttr("error", "not_authenticated");
            response.getWriter().println(jsonResponse);
            session.invalidate();
            return false;
        }
        return true;
    }
}
