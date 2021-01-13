package com.hagz_hotels.hotels_booking.Controllers.Client;

import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "change-password", value = "/change-password")
public class changepassword extends HttpServlet {
//    User.Type[] authType = {User.Type.CLIENT};
    User.Type authType = User.Type.CLIENT;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Auth.authenticate(request,response,authType)){
            return ;
        }
        response.sendRedirect("change-password.jsp");

    }

}
