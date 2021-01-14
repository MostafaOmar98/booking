package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Admin;


import com.hagz_hotels.hotels_booking.Business.Admin.AddHotelImage;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;

@MultipartConfig
@WebServlet("/add-hotel-image")
public class AddHotelImageService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));

        try {
            if (!HTMLAuth.authorizeHotel(request, response, hotelId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        Part filePart = request.getPart("image");
        InputStream fileContent = filePart.getInputStream();
        try {
            AddHotelImage.execute(hotelId, fileContent);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.sendRedirect("admin-home");
    }
}


