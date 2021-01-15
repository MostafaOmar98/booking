package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;

import java.sql.SQLException;

public class AddRoom {

    private static final RoomDAO roomDAO = new RoomDAO();

    public static void execute(Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, Integer hotelId, String facilities) throws SQLException, ClassNotFoundException {
        if (type.isEmpty())
            throw new IllegalArgumentException("Type can't be empty");
        if (pricePerNight < 0 || maxAdults < 0 || maxChildren < 0)
            throw new IllegalArgumentException("Number can't be negative");
        roomDAO.create(
                pricePerNight,
                type,
                maxAdults,
                maxChildren,
                hotelId,
                facilities
        );
    }
}
