package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Admin;


import com.hagz_hotels.hotels_booking.Util.Auth;
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
import java.sql.SQLException;

@WebServlet("/admin-home")
public class AdminHomeService extends HttpServlet {

    private final HotelDAO hotelDAO = new HotelDAO();
    private final RoomDAO roomDAO = new RoomDAO();
    private final HotelImageDAO hotelImageDAO = new HotelImageDAO();
    private final User.Type authType = User.Type.ADMIN;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Auth.authorizeUserType(request, authType);
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect("index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect("/WEB-INF/public/unauthorized.jsp");
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            Hotel hotel = hotelDAO.findByAdminId(user.getUserId());
            if (hotel == null) {
                request.getRequestDispatcher("/WEB-INF/admin/add-hotel.jsp").forward(request, response);
            } else {
                request.setAttribute("hotel", hotel);
                request.setAttribute("rooms", roomDAO.findByHotelID(hotel.getHotelId()));
                request.setAttribute("images", hotelImageDAO.findByHotelId(hotel.getHotelId()));
                request.getRequestDispatcher("/WEB-INF/admin/hotel-management.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }
}
