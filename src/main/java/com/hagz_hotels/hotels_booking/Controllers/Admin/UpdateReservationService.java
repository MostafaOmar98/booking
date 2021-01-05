package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-reservation")
public class UpdateReservationService extends HttpServlet {

    ClientRoomReservationDAO clientRoomReservationDAO;
    User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        clientRoomReservationDAO = new ClientRoomReservationDAO();
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
        else
            clientRoomReservationDAO.updateStatus(reservationId, state);
        response.getWriter().println(jsonResponse);
    }
}
