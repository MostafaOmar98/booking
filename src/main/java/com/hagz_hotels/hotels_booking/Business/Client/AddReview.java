package com.hagz_hotels.hotels_booking.Business.Client;

import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;

import java.sql.SQLException;

public class AddReview {

    public static final ClientHotelReviewDAO clientHotelReviewDAO = new ClientHotelReviewDAO();

    public static void execute(Integer clientId, Integer reservationId, Integer stars, String comment) throws SQLException, ClassNotFoundException {
        if (clientHotelReviewDAO.has(reservationId))
            throw new IllegalArgumentException("Can't add multiple reviews to same reservation with id " + reservationId);
        clientHotelReviewDAO.create(clientId, reservationId, stars, comment);
    }
}
