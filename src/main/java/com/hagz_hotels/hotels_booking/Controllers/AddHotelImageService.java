package com.hagz_hotels.hotels_booking.Controllers;


import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add-hotel-image")
public class AddHotelImageService extends HttpServlet {

    HotelImageDAO hotelImageDAO;
    User.Type authType = User.Type.ADMIN;

    @Override
    public void init() throws ServletException {
        hotelImageDAO = new HotelImageDAO();
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

        // TODO : Finish Image
        String image = request.getParameter("image");
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        String path = "../../../../../images/" + hotelId + "/test";
        System.out.println("Path: " + path);

    }
}
