package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;


import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-review")
public class AddReviewService extends HttpServlet {

    ClientHotelReviewDAO clientHotelReviewDAO;
    User.Type authType = User.Type.CLIENT;

    @Override
    public void init() throws ServletException {
        clientHotelReviewDAO = new ClientHotelReviewDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;
        Integer reservationId = Integer.valueOf(request.getParameter("reservationId"));
        if (!Auth.authorizeReservation(request, response, reservationId))
            return;
        Integer stars = Integer.valueOf(request.getParameter("stars"));
        String comment = request.getParameter("comment");
        Integer clientId = ((User)request.getSession().getAttribute("user")).getUserId();
        if (!clientHotelReviewDAO.has(reservationId))
            clientHotelReviewDAO.create(clientId, reservationId, stars, comment);
        response.sendRedirect("client-reservations");
    }
}
