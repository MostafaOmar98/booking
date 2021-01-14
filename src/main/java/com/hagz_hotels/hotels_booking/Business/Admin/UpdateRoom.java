package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;

import java.sql.SQLException;

public class UpdateRoom {

    private static final RoomDAO roomDAO = new RoomDAO();

    public static void execute(Integer roomId, Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, String facilities) throws SQLException, ClassNotFoundException {
        if (type.isEmpty())
            throw new IllegalArgumentException("Type can't be empty");
        roomDAO.update(roomId, pricePerNight, type, maxAdults, maxChildren, facilities);
    }
}
