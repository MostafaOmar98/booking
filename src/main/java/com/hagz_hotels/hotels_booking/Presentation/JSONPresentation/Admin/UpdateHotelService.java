package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;


import com.hagz_hotels.hotels_booking.Business.Admin.UpdateHotel;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-hotel")
public class UpdateHotelService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));

        try {
            if (!JSONAuth.authorizeHotel(request, response, hotelId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        // Invalid input format will be validated by frontend. Bypass of frontend will result in status code 500 which is intended.
        String latitudeParam = request.getParameter("latitude"), longitudeParam = request.getParameter("longitude");
        String adminIdParam = request.getParameter("adminId");
        Float latitude = latitudeParam == null ? null : Float.valueOf(latitudeParam);
        Float longitude = longitudeParam == null ? null : Float.valueOf(longitudeParam);
        Integer adminId = adminIdParam == null ? null : Integer.valueOf(adminIdParam);
        String name = request.getParameter("name");

        JsonResponse jsonResponse = new JsonResponse();
        try {
            UpdateHotel.execute(hotelId, name, latitude, longitude, request.getParameter("phone"), adminId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            jsonResponse.setAttr("name_error", "name_empty");
        }

        response.getWriter().println(jsonResponse);
    }
}
