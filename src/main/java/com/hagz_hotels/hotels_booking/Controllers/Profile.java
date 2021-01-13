package com.hagz_hotels.hotels_booking.Controllers;
import com.hagz_hotels.hotels_booking.Model.Entities.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "edit-profile", value = "/profile")
public class Profile extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT};
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Auth.authenticate(request,response,authType)){
            return ;
        }
        else{
            response.sendRedirect("profile.jsp");

        }

    }
}
