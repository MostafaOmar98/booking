package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;


import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-hotel")
public class UpdateHotelService extends HttpServlet {

    HotelDAO hotelDAO;
    private final User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        if (!Auth.authenticateJson(request, response, authType))
            return;

        // Invalid input format will be validated by frontend. Bypass of frontend will result in status code 500 which is intended.
        String latitudeParam = request.getParameter("latitude"), longitudeParam = request.getParameter("longitude");
        String adminIdParam = request.getParameter("adminId");
        Float latitude = latitudeParam == null ? null : Float.valueOf(latitudeParam);
        Float longitude = longitudeParam == null ? null : Float.valueOf(longitudeParam);
        Integer adminId = adminIdParam == null ? null : Integer.valueOf(adminIdParam);
        String name = request.getParameter("name");
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));

        if (!Auth.authorizeJsonHotel(request, response, hotelId))
            return;

        JsonResponse jsonResponse = new JsonResponse();
        if (name  != null && name.equals(""))
            jsonResponse.setAttr("name_error", "name_empty");
        else {
            hotelDAO.update(
                    hotelId,
                    request.getParameter("name"),
                    latitude,
                    longitude,
                    request.getParameter("phone"),
                    adminId
            );
        }

        response.getWriter().println(jsonResponse);
    }
}
