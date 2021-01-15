package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Admin;


import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-hotel-location")
public class UpdateHotelLocationService extends HttpServlet {


    HotelDAO hotelDAO;

    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        try {
            if (!HTMLAuth.authorizeHotel(request, response, hotelId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        Hotel hotel = null;
        try {
            hotel = hotelDAO.findById(hotelId);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("hotel", hotel);
        request.getRequestDispatcher("/WEB-INF/admin/update-hotel-location.jsp").forward(request, response);
    }
}
