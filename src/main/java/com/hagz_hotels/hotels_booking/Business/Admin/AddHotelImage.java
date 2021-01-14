package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Business.conf;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

public class AddHotelImage {
    private static final HotelImageDAO hotelImageDAO = new HotelImageDAO();
    private static final String imagePath = conf.imagePath;
    public static void execute(Integer hotelId, InputStream fileContent) throws SQLException, ClassNotFoundException, IOException {
        Integer hotelImageId = hotelImageDAO.create(hotelId);
        HotelImage hotelImage = new HotelImage();
        hotelImage.setImageId(hotelImageId);
        hotelImage.setHotelId(hotelId);
        String image = imagePath + "/" + hotelImage.getName();
        File file = new File(image);

        Files.copy(fileContent, file.toPath());
    }
}
