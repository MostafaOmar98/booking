package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;


import com.hagz_hotels.hotels_booking.Business.Public.GetHotelReviews;
import com.hagz_hotels.hotels_booking.Business.DTO.ReviewDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/hotel-reviews")
public class HotelReviewsService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // no need for authentication. public page
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));

        List<ReviewDTO> reviewDTOs = null;
        try {
            reviewDTOs = GetHotelReviews.execute(hotelId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("reviews", reviewDTOs);
        request.getRequestDispatcher("/WEB-INF/hotel-reviews.jsp").forward(request, response);
    }
}
