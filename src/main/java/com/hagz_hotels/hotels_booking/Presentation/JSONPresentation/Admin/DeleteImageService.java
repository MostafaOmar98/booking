package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;

import com.hagz_hotels.hotels_booking.Business.Admin.DeleteImage;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete-image")
public class DeleteImageService extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        try {
            if (!JSONAuth.authorizeHotel(request, response, hotelId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        Integer imageId = Integer.valueOf(request.getParameter("imageId"));

        JsonResponse jsonResponse = new JsonResponse();
        try {
            DeleteImage.execute(hotelId, imageId);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        response.getWriter().println(jsonResponse);
    }

}