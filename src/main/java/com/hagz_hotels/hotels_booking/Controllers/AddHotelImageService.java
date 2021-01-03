package com.hagz_hotels.hotels_booking.Controllers;


import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@MultipartConfig
@WebServlet("/add-hotel-image")
public class AddHotelImageService extends HttpServlet {

    HotelImageDAO hotelImageDAO;
    User.Type authType = User.Type.ADMIN;
    final String ROOT = "/home/bekh/IdeaProjects/hotels-booking/"; // TODO: Change
    // TODO: When registering admin user create folder for images.


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
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        hotelImageDAO.create(hotelId);
        String relativePath = "/images/" + hotelId + "/" + (hotelImageDAO.getMax(hotelId)) + ".png"; // TODO: Handle image number using other way than this, this has concurrency issues
        String fullPath = ROOT +  relativePath;

        Part filePart = request.getPart("image");
        InputStream fileContent = filePart.getInputStream();
        File file = new File(fullPath);
        FileOutputStream out = new FileOutputStream(file, false);
        int read;
        byte[] bytes = new byte[8192];
        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        response.sendRedirect("admin-home");
    }
}


