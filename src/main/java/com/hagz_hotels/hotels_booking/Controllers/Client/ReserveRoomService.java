package com.hagz_hotels.hotels_booking.Controllers.Client;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/reserve-room")
public class ReserveRoomService extends HttpServlet {

    RoomDAO roomDAO;
    ClientRoomReservationDAO clientRoomReservationDAO;
    User.Type authType = User.Type.CLIENT;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
        clientRoomReservationDAO = new ClientRoomReservationDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;
        Integer roomId = Integer.valueOf(request.getParameter("roomId"));
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"), dtf);
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"), dtf);
        if (roomDAO.isAvaialble(roomId, checkIn, checkOut)) {
            Float pricePerNight = roomDAO.findById(roomId).getPricePerNight();
            User client = (User)request.getSession().getAttribute("user");
            long nights = Util.getNights(checkIn, checkOut);
            if (nights > 0)
                clientRoomReservationDAO.create(client.getUserId(), roomId, checkIn, checkOut, pricePerNight * nights);
        }
        response.sendRedirect("client-reservations");
    }
}
