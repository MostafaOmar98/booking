package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;

import com.hagz_hotels.hotels_booking.Business.Client.SearchHotels;
import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Business.DTO.HotelSearchResultDTO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/search-hotel")
public class SearchHotelService extends HttpServlet {

    User.Type authType = User.Type.CLIENT;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HTMLAuth.authorizeUserType(request, response, authType))
            return;
        Float longitude = Float.valueOf(request.getParameter("lng"));
        Float latitude = Float.valueOf(request.getParameter("lat"));
        Integer adults = Integer.valueOf(request.getParameter("n-adults"));
        Integer children = Integer.valueOf(request.getParameter("n-children"));
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"), dtf);
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"), dtf);


        List<HotelSearchResultDTO> results = null;
        try {
            results = SearchHotels.execute(longitude, latitude, adults, children, checkIn, checkOut);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("results", results);
        request.getRequestDispatcher("/WEB-INF/client/list-hotels.jsp").forward(request, response);
    }
}
