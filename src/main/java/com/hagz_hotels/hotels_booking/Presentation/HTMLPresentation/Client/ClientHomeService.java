package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;

import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client-home")
public class ClientHomeService extends HttpServlet {
    User.Type authType = User.Type.CLIENT;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HTMLAuth.authorizeUserType(request, response, authType))
            return;
        request.getRequestDispatcher("/WEB-INF/client/client-home.jsp").forward(request, response);
    }
}
