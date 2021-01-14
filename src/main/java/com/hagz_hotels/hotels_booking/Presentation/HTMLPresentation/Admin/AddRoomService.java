package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Admin;


import com.hagz_hotels.hotels_booking.Business.Admin.AddRoom;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-room")
public class AddRoomService extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));

        try {
            if (!HTMLAuth.authorizeHotel(request, response, hotelId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        Float pricePerNight = Float.valueOf(request.getParameter("pricePerNight"));
        String type = request.getParameter("type");
        Integer maxAdults = Integer.valueOf(request.getParameter("maxAdults"));
        Integer maxChildren = Integer.valueOf(request.getParameter("maxChildren"));
        String facilities = request.getParameter("facilities");
        try {
            AddRoom.execute(pricePerNight, type, maxAdults, maxChildren, hotelId, facilities);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        // Frontent will validate, bypassing of validation will not affect backend.
        response.sendRedirect("admin-home");
    }
}
