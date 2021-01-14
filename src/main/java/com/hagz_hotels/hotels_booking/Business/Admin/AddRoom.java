package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;

import java.sql.SQLException;

public class AddRoom {

    private static final RoomDAO roomDAO = new RoomDAO();

    public static void execute(Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, Integer hotelId, String facilities) throws SQLException, ClassNotFoundException {
        if (type.isEmpty())
            throw new IllegalArgumentException("Type can't be empty");
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
