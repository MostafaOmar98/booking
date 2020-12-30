package com.hagz_hotels.hotels_booking.Controllers;


import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-hotel")
public class UpdateHotelService extends HttpServlet {

    HotelDAO hotelDAO;

    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String latitudeParam = request.getParameter("latitude"), longitudeParam = request.getParameter("longitude");
        String adminIdParam = request.getParameter("adminId");
        Float latitude = latitudeParam == null ? null : Float.valueOf(latitudeParam);
        Float longitude = longitudeParam == null ? null : Float.valueOf(longitudeParam);
        Integer adminId = adminIdParam == null ? null : Integer.valueOf(adminIdParam);
        hotelDAO.update(
                Integer.valueOf(request.getParameter("hotelId")),
                request.getParameter("name"),
                latitude,
                longitude,
                request.getParameter("address"),
                request.getParameter("phone"),
                adminId
        );
        // TODO: Return Error
    }
}
