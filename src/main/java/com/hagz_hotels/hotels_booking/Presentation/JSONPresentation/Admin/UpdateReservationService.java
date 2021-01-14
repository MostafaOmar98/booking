package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;


import com.hagz_hotels.hotels_booking.Business.Public.UpdateReservation;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
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

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

@WebServlet("/update-reservation")
public class UpdateReservationService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer reservationId = Integer.valueOf(request.getParameter("reservationId"));
        try {
            if (!JSONAuth.authorizeAdminReservation(request, response, reservationId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String state = request.getParameter("state");

        JsonResponse jsonResponse = new JsonResponse();
        try {
            UpdateReservation.execute(reservationId, state);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } catch(IllegalArgumentException e) {
            jsonResponse.setAttr("error", "invalid_change");
            e.printStackTrace();
        } catch(GeneralSecurityException | MessagingException e) {
            jsonResponse.setAttr("error", "couldn't send e-mail");
            e.printStackTrace();
        }
        response.getWriter().println(jsonResponse);
    }
}
