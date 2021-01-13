package com.hagz_hotels.hotels_booking.Controllers;


import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.DTO.ReviewDTO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientHotelReview;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hotel-reviews")
public class HotelReviewsService extends HttpServlet {

    RoomDAO roomDAO;
    UserDAO userDAO;
    ClientHotelReviewDAO clientHotelReviewDAO;
    ClientRoomReservationDAO clientRoomReservationDAO;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
        userDAO = new UserDAO();
        clientHotelReviewDAO = new ClientHotelReviewDAO();
        clientRoomReservationDAO = new ClientRoomReservationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // no need for authentication. public page
        Integer hotelId = Integer.valueOf(request.getParameter("hotelId"));
        List<ClientHotelReview> reviews = clientHotelReviewDAO.findByHotelId(hotelId);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();

        for (ClientHotelReview r : reviews) {
            ReviewDTO d = new ReviewDTO();
            d.setComment(r.getComment());
            d.setStars(r.getStars());
            d.setCreatedAt(r.getCreatedAt());

            User client = userDAO.findById(r.getClientId());
            d.setClientName(client.getName());

            ClientRoomReservation reservation = clientRoomReservationDAO.findById(r.getReservationId());
            d.setCheckIn(reservation.getCheckIn());
            d.setCheckout(reservation.getCheckOut());

            Room room = roomDAO.findById(reservation.getRoomId());
            d.setRoomType(room.getType());

            reviewDTOs.add(d);
        }

        request.setAttribute("reviews", reviewDTOs);
        request.getRequestDispatcher("/WEB-INF/hotel-reviews.jsp").forward(request, response);
    }
}
