package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Business.conf;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class DeleteImage {

    private static final HotelImageDAO hotelImageDAO = new HotelImageDAO();
    private static final String imagePath = conf.imagePath;
    public static void execute(Integer hotelId, Integer imageId) throws IOException, SQLException, ClassNotFoundException {
        HotelImage hotelImage = new HotelImage();
        hotelImage.setHotelId(hotelId);
        hotelImage.setImageId(imageId);
        JsonResponse jsonResponse = new JsonResponse();
        File image = new File(imagePath + "/" + hotelImage.getName());
        Files.delete(image.toPath());
        hotelImageDAO.delete(imageId);
        System.out.println(jsonResponse);
    }
}
