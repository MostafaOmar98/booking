package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.ClientHotelReview;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class ClientHotelReviewDAO {

    Function<ResultSet, ClientHotelReview> mapper = new Function<ResultSet, ClientHotelReview>() {
        @Override
        public ClientHotelReview apply(ResultSet set) {
            ClientHotelReview ret = new ClientHotelReview();
            try {
                ret.setClientId(set.getInt("ClientId"));
                ret.setReservationId(set.getInt("ReservationId"));
                ret.setCreatedAt(set.getTimestamp("CreatedAt").toLocalDateTime());
                ret.setStars(set.getInt("Stars"));
                ret.setComment(set.getString("Comment"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return ret;
        }
    };

    Function<ResultSet, Float> avgRateMapper = new Function<ResultSet, Float>() {
        @Override
        public Float apply(ResultSet set) {
            Float ret = null;
            try {
                ret = set.getFloat("avg");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return ret;
        }
    };

    public List<ClientHotelReview> findByHotelId(Integer hotelId) {
        String query = "SELECT * FROM ClientHotelReview, ClientRoomReservation, Room WHERE " +
                "ClientHotelReview.ReservationId = ClientRoomReservation.ReservationId AND " +
                "ClientRoomReservation.RoomId = Room.RoomId AND " +
                "Room.HotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public Float findAverageRatingByHotelId(Integer hotelId) {
        String query = "SELECT AVG(Stars) as avg FROM ClientHotelReview, ClientRoomReservation, Room WHERE " +
                "ClientHotelReview.ReservationId = ClientRoomReservation.ReservationId AND " +
                "ClientRoomReservation.RoomId = Room.RoomId AND " +
                "Room.HotelId=? " +
                "GROUP BY HotelId";
        return DBUtil.selectOne(query, avgRateMapper, hotelId);
    }
}
