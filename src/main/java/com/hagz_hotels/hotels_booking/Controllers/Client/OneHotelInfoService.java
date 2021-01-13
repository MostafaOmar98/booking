package com.hagz_hotels.hotels_booking.Controllers.Client;

import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.DTO.HotelSearchResultDTO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/one-hotel-info")
public class OneHotelInfoService extends HttpServlet {

    User.Type authType = User.Type.CLIENT;
    HotelDAO hotelDAO;
    RoomDAO roomDAO;
    HotelImageDAO hotelImageDAO;
    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
        hotelImageDAO = new HotelImageDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        Hotel hotel = hotelDAO.findById(hotelId);
        List<Room> rooms = roomDAO.findByHotelID(hotelId);
        List<HotelImage> images = hotelImageDAO.findByHotelId(hotelId);
        request.setAttribute("hotel", hotel);
        request.setAttribute("rooms", rooms);
        request.setAttribute("images", images);
        request.getRequestDispatcher("/WEB-INF/client/one-hotel-info.jsp").forward(request, response);
    }
}
