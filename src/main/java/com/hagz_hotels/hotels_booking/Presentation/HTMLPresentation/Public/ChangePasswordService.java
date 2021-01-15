package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public;

import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "change-password", value = "/change-password")
public class ChangePasswordService extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT, User.Type.ADMIN};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HTMLAuth.authorizeUserType(request, response, authType[0], authType[1]))
            return;
        response.sendRedirect("change-password.jsp");
    }

}
