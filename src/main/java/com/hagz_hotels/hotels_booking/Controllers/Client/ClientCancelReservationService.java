package com.hagz_hotels.hotels_booking.Controllers.Client;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;
import com.hagz_hotels.hotels_booking.Util.MailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/client-cancel-reservation")
public class ClientCancelReservationService extends HttpServlet {

    ClientRoomReservationDAO clientRoomReservationDAO;
    UserDAO userDAO;
    RoomDAO roomDAO;
    HotelDAO hotelDAO;
    User.Type authType = User.Type.CLIENT;

    @Override
    public void init() throws ServletException {
        clientRoomReservationDAO = new ClientRoomReservationDAO();
        userDAO = new UserDAO();
        roomDAO = new RoomDAO();
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;
        Integer reservationId = Integer.valueOf(request.getParameter("reservationId"));
        if (!Auth.authorizeReservation(request, response, reservationId))
            return;
        ClientRoomReservation reservation = clientRoomReservationDAO.findById(reservationId);
        if (reservation.getStatusName().equals("PENDING") || reservation.getStatusName().equals("CONFIRMED")) {
            clientRoomReservationDAO.updateStatus(reservationId, "CANCELED");
            User client = userDAO.findById(reservation.getClientId());
            Room room = roomDAO.findById(reservation.getRoomId());
            Hotel hotel = hotelDAO.findById(room.getHotelId());
            User admin = userDAO.findById(hotel.getAdminId());
            MailUtil.sendMail(admin.getEmail(), "[Booking App] A recent reservation of your hotel has been canceled",
                    "Reservation for room " + room.getRoomId() + " Has been canceled\n" +
                            "Client Name: " + client.getName() + "\n" +
                            "Client Phone: " + client.getPhone() + "\n" +
                            "Reservation Id: " + reservationId);
        }
        response.sendRedirect("client-reservations");
    }
}
