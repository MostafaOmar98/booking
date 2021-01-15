package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public;
import com.hagz_hotels.hotels_booking.Model.Entities.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "register", value = "/sign-up")
public class Register extends HttpServlet {
    User.Type[] authType = {}; // Added by bekh
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * if useR's signed in then we redirect him to index
         * admin can register admin or client
         * Guest can only register client
         * client redirected to index
         */
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null){   // Hanafy  1/14/21
            if(user.getType() == User.Type.ADMIN){
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/register.jsp").forward(request, response);

        }

    }
}
