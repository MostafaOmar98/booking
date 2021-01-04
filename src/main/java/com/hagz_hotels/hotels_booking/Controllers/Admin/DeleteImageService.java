package com.hagz_hotels.hotels_booking.Controllers.Admin;

import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete-image")
public class DeleteImageService extends HttpServlet {

    private String imagePath;
    HotelImageDAO hotelImageDAO;
    User.Type authType = User.Type.ADMIN;

    public void init() throws ServletException {
        this.imagePath = conf.imagePath;
        hotelImageDAO = new HotelImageDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        if (!Auth.authenticateJson(request, response, authType))
            return;


        HotelImage hotelImage = new HotelImage();
        hotelImage.setHotelId(Integer.valueOf(request.getParameter("hotelId")));
        hotelImage.setImageId(Integer.valueOf(request.getParameter("imageId")));
        if (!Auth.authorizeJsonHotel(request, response, hotelImage.getHotelId()))
            return;
        File image = new File(imagePath + "/" + hotelImage.getName());
        Files.delete(image.toPath());
        hotelImageDAO.delete(Integer.valueOf(request.getParameter("imageId")));
    }

}