package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.Auth;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "edit-profile", value = "/profile")
public class Profile extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT, User.Type.ADMIN}; // Added by bekh
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!JSONAuth.authorizeUserType(request,response,authType[0], authType[1])){ // edited by bekh
            return ;
        }
        else{
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);

        }

    }
}
