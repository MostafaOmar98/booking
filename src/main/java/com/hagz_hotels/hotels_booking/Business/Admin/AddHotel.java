package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Business.conf;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;

import java.io.File;
import java.sql.SQLException;

public class AddHotel {
    private final static HotelDAO hotelDAO = new HotelDAO();
    private final static String imagePath = conf.imagePath;

    public static void execute(Integer userId, String hotelName) throws SQLException, ClassNotFoundException {
        if (hotelName.isEmpty())
            throw new IllegalArgumentException("Hotel Name can't be empty");
        if (hotelDAO.findByAdminId(userId) != null)
            throw new IllegalArgumentException("Admin can't have more than one hotel");
        Integer hotelId = hotelDAO.create(hotelName, userId);
        File hotelDir = new File(imagePath + "/" + hotelId);
        hotelDir.mkdir();
    }
}
