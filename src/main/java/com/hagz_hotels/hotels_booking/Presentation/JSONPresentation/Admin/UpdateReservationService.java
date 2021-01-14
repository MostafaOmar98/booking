package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;


import com.hagz_hotels.hotels_booking.Util.Auth;
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
import java.io.IOException;

@WebServlet("/update-reservation")
public class UpdateReservationService extends HttpServlet {

    ClientRoomReservationDAO clientRoomReservationDAO;
    UserDAO userDAO;
    RoomDAO roomDAO;
    HotelDAO hotelDAO;
    User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        clientRoomReservationDAO = new ClientRoomReservationDAO();
        userDAO = new UserDAO();
        roomDAO = new RoomDAO();
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticateJson(request, response, authType))
            return;
        Integer reservationId = Integer.valueOf(request.getParameter("reservationId"));
        String state = request.getParameter("state");
        ClientRoomReservation reservation = clientRoomReservationDAO.findById(reservationId);
        if (!Auth.authorizeJsonRoom(request, response,reservation.getRoomId()))
            return;
        JsonResponse jsonResponse = new JsonResponse();
        if (state.equals("CANCELED") && (reservation.getStatusName().equals("CHECKED_IN") || reservation.getStatusName().equals("CHECKED_OUT")))
            jsonResponse.setAttr("error", "invalid_change");
        else if (state.equals("CONFIRMED") && !reservation.getStatusName().equals("PENDING"))
            jsonResponse.setAttr("error", "invalid_change");
        else if (state.equals("CHECKED_IN") && !reservation.getStatusName().equals("CONFIRMED"))
            jsonResponse.setAttr("error", "invalid_change");
        else if (state.equals("CHECKED_OUT") && !reservation.getStatusName().equals("CHECKED_IN"))
            jsonResponse.setAttr("error", "invalid_change");
        else {
            clientRoomReservationDAO.updateStatus(reservationId, state);
            if (state.equals("CANCELED")) {
                User client = userDAO.findById(reservation.getClientId());
                Room room = roomDAO.findById(reservation.getRoomId());
                Hotel hotel = hotelDAO.findById(room.getHotelId());
                MailUtil.sendMail(client.getEmail(), "[Booking App] A recent reservation of yours has been canceled",
                        "Your reservation for room " + room.getRoomId() + " Has been canceled\n" +
                                "Contact the hotel for more info\n" +
                                "Hotel Name: " + hotel.getName() + "\n" +
                                "Hotel Phone: " + hotel.getPhone() + "\n");
            }
        }
        response.getWriter().println(jsonResponse);
    }
}
