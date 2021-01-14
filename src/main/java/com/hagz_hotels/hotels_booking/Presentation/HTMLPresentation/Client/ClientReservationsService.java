package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;


import com.hagz_hotels.hotels_booking.Business.Client.GetClientReservations;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Business.DTO.ReservationDTOForClient;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/client-reservations")
public class ClientReservationsService extends HttpServlet {

    User.Type authType = User.Type.CLIENT;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HTMLAuth.authorizeUserType(request, response, authType))
            return;

        Integer clientId = ((User)(request.getSession().getAttribute("user"))).getUserId();
        List<ReservationDTOForClient> reservationDTOs = null;
        try {
            reservationDTOs = GetClientReservations.execute(clientId);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("reservations", reservationDTOs);
        request.getRequestDispatcher("/WEB-INF/client/client-reservations.jsp").forward(request, response);
    }
}
