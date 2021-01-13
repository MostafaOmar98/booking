package com.hagz_hotels.hotels_booking.Controllers;
import com.hagz_hotels.hotels_booking.Model.Entities.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            request.setAttribute("name",user.getName());
            request.setAttribute("phone",user.getPhone());
            request.setAttribute("email",user.getEmail());
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);

        }

    }
}
