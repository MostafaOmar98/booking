package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.ClientHotelReview;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientHotelReviewDAO {

    IMapper<ResultSet, ClientHotelReview> mapper = new IMapper<ResultSet, ClientHotelReview>() {
        @Override
        public ClientHotelReview apply(ResultSet set) throws SQLException {
            ClientHotelReview ret = new ClientHotelReview();
            ret.setClientId(set.getInt("ClientId"));
            ret.setReservationId(set.getInt("ReservationId"));
            ret.setCreatedAt(set.getTimestamp("CreatedAt").toLocalDateTime());
            ret.setStars(set.getInt("Stars"));
            ret.setComment(set.getString("Comment"));
            return ret;
        }
    };

    IMapper<ResultSet, Float> avgRateMapper = new IMapper<ResultSet, Float>() {
        @Override
        public Float apply(ResultSet set) throws SQLException {
            Float ret = null;
            ret = set.getFloat("avg");
            return ret;
        }
    };

    public List<ClientHotelReview> findByHotelId(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientHotelReview, ClientRoomReservation, Room WHERE " +
                "ClientHotelReview.ReservationId = ClientRoomReservation.ReservationId AND " +
                "ClientRoomReservation.RoomId = Room.RoomId AND " +
                "Room.HotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public Float findAverageRatingByHotelId(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT AVG(Stars) as avg FROM ClientHotelReview, ClientRoomReservation, Room WHERE " +
                "ClientHotelReview.ReservationId = ClientRoomReservation.ReservationId AND " +
                "ClientRoomReservation.RoomId = Room.RoomId AND " +
                "Room.HotelId=? " +
                "GROUP BY HotelId";
        return DBUtil.selectOne(query, avgRateMapper, hotelId);
    }

    public void create(Integer clientId, Integer reservationId, Integer stars, String comment) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO ClientHotelReview (ClientId, ReservationId, CreatedAt, Stars, Comment) VALUE " +
                "(?, ?, NOW(), ?, ?)";
        DBUtil.executeUpdate(query, clientId, reservationId, stars, comment);
    }

    public Boolean has(Integer reservationId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientHotelReview WHERE ReservationId=?";
        return DBUtil.selectOne(query, mapper, reservationId) != null;
    }

    public void deleteByRoomId(Integer roomId) throws SQLException, ClassNotFoundException {
        String query = "DELETE ClientHotelReview FROM ClientHotelReview, ClientRoomReservation WHERE " +
                "ClientHotelReview.ReservationId = ClientRoomReservation.ReservationId AND " +
                "ClientRoomReservation.RoomId=?";
        DBUtil.executeUpdate(query, roomId);
    }
}
