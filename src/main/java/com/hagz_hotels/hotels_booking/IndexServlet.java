package com.hagz_hotels.hotels_booking;

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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    HotelDAO hotelDao;

    @Override
    public void init() throws ServletException {
        hotelDao = new HotelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        User user = (User)session.getAttribute("user");
        if (user == null)
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        else if (user.getType() == User.Type.CLIENT)
            request.getRequestDispatcher("/WEB-INF/client-search.jsp").forward(request, response);
        else {
            Hotel hotel = hotelDao.findByAdminId(user.getUserId());
            if (hotel == null)
                request.getRequestDispatcher("/WEB-INF/add-hotel.jsp").forward(request, response);
            else {
                request.setAttribute("hotel", hotel);
                request.getRequestDispatcher("/WEB-INF/hotel-home.jsp").forward(request, response);
            }
        }

    }
}
