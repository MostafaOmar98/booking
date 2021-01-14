package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;


import com.hagz_hotels.hotels_booking.Business.Client.ReserveRoom;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/reserve-room")
public class ReserveRoomService extends HttpServlet {

    User.Type authType = User.Type.CLIENT;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HTMLAuth.authorizeUserType(request, response, authType))
            return;
        Integer roomId = Integer.valueOf(request.getParameter("roomId"));
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"), dtf);
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"), dtf);
        Integer clientId = ((User)request.getSession().getAttribute("user")).getUserId();
        try {
            ReserveRoom.execute(clientId, roomId, checkIn, checkOut);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("client-reservations");
    }
}
