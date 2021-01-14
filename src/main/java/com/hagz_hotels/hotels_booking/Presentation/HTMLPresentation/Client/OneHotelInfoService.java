package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Client;

import com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public.HTMLAuth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/one-hotel-info")
public class OneHotelInfoService extends HttpServlet {

    User.Type authType = User.Type.CLIENT;
    HotelDAO hotelDAO;
    RoomDAO roomDAO;
    HotelImageDAO hotelImageDAO;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
        hotelImageDAO = new HotelImageDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HTMLAuth.authorizeUserType(request, response, authType))
            return;
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        Integer adults = Integer.valueOf(request.getParameter("n-adults"));
        Integer children = Integer.valueOf(request.getParameter("n-children"));
        System.out.println(request.getParameter("checkIn"));
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"), dtf);
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"), dtf);
        Hotel hotel = null;
        List<Room> rooms = null;
        List<HotelImage> images = null;
        try {
            hotel = hotelDAO.findById(hotelId);
            rooms = roomDAO.findByCriteria(hotelId, adults, children, checkIn, checkOut);
            images = hotelImageDAO.findByHotelId(hotelId);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("hotel", hotel);
        request.setAttribute("rooms", rooms);
        request.setAttribute("images", images);
        request.getRequestDispatcher("/WEB-INF/client/one-hotel-info.jsp").forward(request, response);
    }
}
