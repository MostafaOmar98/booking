package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/add-room")
public class AddRoomService extends HttpServlet {

    RoomDAO roomDAO;
    User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;

        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        if (!Auth.authorizeHotel(request, response, hotelId))
            return;

        String type = request.getParameter("type");
        // Frontent will validate, bypassing of validation will not affect backend.
        if (!type.equals("")) {
            roomDAO.create(
                    Float.valueOf(request.getParameter("pricePerNight")),
                    request.getParameter("type"),
                    Integer.valueOf(request.getParameter("maxAdults")),
                    Integer.valueOf(request.getParameter("maxChildren")),
                    hotelId,
                    request.getParameter("facilities")
            );
        }
        response.sendRedirect("admin-home");
    }
}
