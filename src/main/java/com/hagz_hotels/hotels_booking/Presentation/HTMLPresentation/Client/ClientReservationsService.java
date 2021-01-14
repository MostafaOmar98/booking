package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;


import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Business.DTO.ReservationDTOForClient;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/client-reservations")
public class ClientReservationsService extends HttpServlet {

    ClientRoomReservationDAO clientRoomReservationDAO;
    ClientHotelReviewDAO clientHotelReviewDAO;
    User.Type authType = User.Type.CLIENT;

    @Override
    public void init() throws ServletException {
        clientRoomReservationDAO = new ClientRoomReservationDAO();
        clientHotelReviewDAO = new ClientHotelReviewDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;

        Integer clientId = ((User)(request.getSession().getAttribute("user"))).getUserId();
        List<ClientRoomReservation> reservations = clientRoomReservationDAO.findAllByClientId(clientId);
        List<ReservationDTOForClient> reservationDTOs = new ArrayList<>();

        for (ClientRoomReservation r : reservations) {
            ReservationDTOForClient d = new ReservationDTOForClient();
            d.setCheckIn(r.getCheckIn());
            d.setCheckOut(r.getCheckOut());
            d.setStatus(r.getStatusName());
            d.setCreatedAt(r.getCreatedAt());
            d.setReservationId(r.getReservationId());
            d.setTotalPrice(r.getTotalPrice());

            d.setRoomId(r.getRoomId());

            d.setReviewable(!clientHotelReviewDAO.has(r.getReservationId()) && r.getStatusName().equals("CHECKED_OUT"));

            reservationDTOs.add(d);
        }
        request.setAttribute("reservations", reservationDTOs);
        request.getRequestDispatcher("/WEB-INF/client/client-reservations.jsp").forward(request, response);
    }
}
