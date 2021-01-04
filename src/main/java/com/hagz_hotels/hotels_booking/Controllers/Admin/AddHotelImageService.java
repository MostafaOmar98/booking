package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;

@MultipartConfig
@WebServlet("/add-hotel-image")
public class AddHotelImageService extends HttpServlet {

    HotelImageDAO hotelImageDAO;
    User.Type authType = User.Type.ADMIN;
    String imagePath;

    @Override
    public void init() throws ServletException {
        hotelImageDAO = new HotelImageDAO();
        imagePath = conf.imagePath;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;

        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        if (!Auth.authorizeHotel(request, response, hotelId))
            return;

        Integer hotelImageId = hotelImageDAO.create(hotelId);
        HotelImage hotelImage = new HotelImage();
        hotelImage.setImageId(hotelImageId);
        hotelImage.setHotelId(hotelId);
        String image = imagePath + "/" + hotelImage.getName();
        File file = new File(image);

        Part filePart = request.getPart("image");
        InputStream fileContent = filePart.getInputStream();
        Files.copy(fileContent, file.toPath());
        response.sendRedirect("admin-home");
    }
}


