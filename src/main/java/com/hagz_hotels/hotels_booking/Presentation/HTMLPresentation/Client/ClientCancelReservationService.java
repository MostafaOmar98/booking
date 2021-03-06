package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;


import com.hagz_hotels.hotels_booking.Business.Public.UpdateReservation;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.MailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

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
        Integer reservationId = Integer.valueOf(request.getParameter("reservationId"));
        try {
            if (!HTMLAuth.authorizeReservation(request, response, reservationId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            UpdateReservation.execute(reservationId, "CANCELED");
        } catch (SQLException | MessagingException | GeneralSecurityException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("client-reservations");
    }
}
