package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/add-hotel")
public class AddHotelService extends HttpServlet {

    private HotelDAO hotelDAO;
    private User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (Auth.isAuth(user, authType) != Auth.Status.OK) {
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        }
        // TODO: Backend validation on name
        String name = request.getParameter("name");
        hotelDAO.create(name, user.getUserId());
        response.sendRedirect("admin-home");
    }
}
