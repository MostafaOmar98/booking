package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.DTO.ReservationDTO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-reservations")
public class AdminReservationsService extends HttpServlet {

    ClientRoomReservationDAO clientRoomReservationDAO;
    UserDAO userDAO;
    User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        clientRoomReservationDAO = new ClientRoomReservationDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;

        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        if (!Auth.authorizeHotel(request, response, hotelId))
            return;

        List<ClientRoomReservation> clientRoomReservations = clientRoomReservationDAO.findAllByHotelId(hotelId);
        List<ReservationDTO> reservations = new ArrayList<>();
        for (ClientRoomReservation cr : clientRoomReservations) {
            ReservationDTO r = new ReservationDTO();
            User user = userDAO.findById(cr.getClientId());
            r.setClientId(user.getUserId());
            r.setClientEmail(user.getEmail());
            r.setClientName(user.getName());
            r.setClientPhone(user.getPhone());
            r.setRoomId(cr.getRoomId());
            r.setStatus(cr.getStatusName());

            reservations.add(r);
        }
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/WEB-INF/admins-reservations.jsp").forward(request, response);
    }
}
