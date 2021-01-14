package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Admin;


import com.hagz_hotels.hotels_booking.Business.Admin.AddHotel;
import com.hagz_hotels.hotels_booking.Business.conf;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-hotel")
public class AddHotelService extends HttpServlet {

    private final User.Type authType = User.Type.ADMIN;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (!HTMLAuth.authorizeUserType(request, response, authType))
           return;

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String name = request.getParameter("name");

        try {
            AddHotel.execute(user.getUserId(), name);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("admin-home");
    }
}
