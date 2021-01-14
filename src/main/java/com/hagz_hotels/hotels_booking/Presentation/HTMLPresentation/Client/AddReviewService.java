package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;


import com.hagz_hotels.hotels_booking.Business.Client.AddReview;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-review")
public class AddReviewService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer reservationId = Integer.valueOf(request.getParameter("reservationId"));

        try {
            if (!HTMLAuth.authorizeReservation(request, response, reservationId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        Integer stars = Integer.valueOf(request.getParameter("stars"));
        String comment = request.getParameter("comment");
        Integer clientId = ((User)request.getSession().getAttribute("user")).getUserId();

        try {
            AddReview.execute(clientId, reservationId, stars, comment);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("client-reservations");
    }
}
