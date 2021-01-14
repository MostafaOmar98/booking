package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;

import java.sql.SQLException;

public class UpdateHotel {

    private static final HotelDAO hotelDAO = new HotelDAO();

    public static void execute(Integer hotelId, String name, Float latitude, Float longitude, String phone, Integer adminId) throws SQLException, ClassNotFoundException {
        if (name != null && name.isEmpty())
            throw new IllegalArgumentException("Name can't be empty");
        hotelDAO.update(
                hotelId,
                name,
                latitude,
                longitude,
                phone,
                adminId
        );
    }
}
