package com.hagz_hotels.hotels_booking.Business.Public;

import com.hagz_hotels.hotels_booking.Business.DTO.ReviewDTO;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientHotelReview;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetHotelReviews {
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final ClientHotelReviewDAO clientHotelReviewDAO = new ClientHotelReviewDAO();
    private static final ClientRoomReservationDAO clientRoomReservationDAO = new ClientRoomReservationDAO();

    public static List<ReviewDTO> execute(Integer hotelId) throws SQLException, ClassNotFoundException {

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
        return reviewDTOs;
    }
}
