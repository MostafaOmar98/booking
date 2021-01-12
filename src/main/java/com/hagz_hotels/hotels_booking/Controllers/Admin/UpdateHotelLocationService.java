package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-hotel-location")
public class UpdateHotelLocationService extends HttpServlet {


    User.Type authType = User.Type.ADMIN;
    HotelDAO hotelDAO;
    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        if (!Auth.authorizeHotel(request, response, hotelId))
            return;

        Hotel hotel = hotelDAO.findById(hotelId);
        request.setAttribute("hotel", hotel);
        request.getRequestDispatcher("/WEB-INF/admin/update-hotel-location.jsp").forward(request, response);
    }
}
