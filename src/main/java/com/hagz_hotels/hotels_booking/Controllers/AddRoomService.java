package com.hagz_hotels.hotels_booking.Controllers;


import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/add-room")
public class AddRoomService extends HttpServlet {

    RoomDAO roomDAO;
    User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
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
        roomDAO.create(
                Float.valueOf(request.getParameter("pricePerNight")),
                request.getParameter("type"),
                Integer.valueOf(request.getParameter("maxAdults")),
                Integer.valueOf(request.getParameter("maxChildren")),
                Integer.valueOf(request.getParameter("hotelId")),
                request.getParameter("facilities")
        );
        response.sendRedirect("admin-home");
    }
}
