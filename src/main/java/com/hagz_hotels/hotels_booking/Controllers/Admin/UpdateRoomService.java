package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-room")
public class UpdateRoomService extends HttpServlet {

    RoomDAO roomDAO;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer roomId = Integer.valueOf(request.getParameter("roomId")),
                maxAdults = Integer.valueOf(request.getParameter("maxAdults")),
                maxChildren = Integer.valueOf(request.getParameter("maxChildren"));
        Float pricePerNight = Float.valueOf(request.getParameter("pricePerNight"));
        String type = request.getParameter("type"),
                facilities = request.getParameter("facilities");

        roomDAO.update(roomId, pricePerNight, type, maxAdults, maxChildren, facilities);
        // TODO: ERRORS?
    }
}
