package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin-home")
public class AdminHomeService extends HttpServlet {

    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;
    private HotelImageDAO hotelImageDAO;
    private final User.Type authType = User.Type.ADMIN;
    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
        hotelImageDAO = new HotelImageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (Auth.isAuth(user, authType) != Auth.Status.OK) {
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        }
        Hotel hotel = hotelDAO.findByAdminId(user.getUserId());
        if (hotel == null)
            request.getRequestDispatcher("/WEB-INF/add-hotel.jsp").forward(request, response);
        else {
            request.setAttribute("hotel", hotel);
            request.setAttribute("rooms", roomDAO.findByHotelID(hotel.getHotelId()));
            request.setAttribute("images", hotelImageDAO.findByHotelId(hotel.getHotelId()));
            request.getRequestDispatcher("/WEB-INF/hotel-management.jsp").forward(request, response);
        }
    }
}
