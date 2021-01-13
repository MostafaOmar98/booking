package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class ClientRoomReservationDAO {
    Function<ResultSet, ClientRoomReservation> mapper = new Function<ResultSet, ClientRoomReservation>() {
        @Override
        public ClientRoomReservation apply(ResultSet set) {
            ClientRoomReservation r = new ClientRoomReservation();
            try {
                r.setReservationId(set.getInt("ReservationId"));
                r.setRoomId(set.getInt("RoomId"));
                r.setClientId(set.getInt("ClientId"));
                r.setCheckIn(set.getDate("CheckIn").toLocalDate());
                r.setCheckOut(set.getDate("CheckOut").toLocalDate());
                r.setCreatedAt(set.getTimestamp("CreatedAt").toLocalDateTime());
                r.setStatus(set.getString("Status"));
                r.setTotalPrice(set.getFloat("TotalPrice"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return r;
        }
    };

    public List<ClientRoomReservation> findAllByHotelId(Integer hotelId) {
        String query = "SELECT * FROM ClientRoomReservation, Room " +
                "WHERE ClientRoomReservation.RoomId = Room.RoomId " +
                "AND Room.HotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public ClientRoomReservation findById(Integer reservationId) {
        String query = "SELECT * FROM ClientRoomReservation WHERE ReservationId=?";
        return DBUtil.selectOne(query, mapper, reservationId);
    }

    public void updateStatus(Integer reservationId, String status) {
        String query = "UPDATE ClientRoomReservation SET Status=? WHERE ReservationId=?";
        DBUtil.executeUpdate(query, status, reservationId);
    }

    public void create(Integer clientId, Integer roomId, LocalDate checkIn, LocalDate checkOut, Float totalPrice) {
        String query = "INSERT INTO ClientRoomReservation (ClientId, RoomId, CreatedAt, CheckIn, CheckOut, STATUS, TotalPrice) VALUES " +
                "(?, ?, NOW(), ?, ?, \"PENDING\", ?)";
        DBUtil.executeUpdate(query, clientId, roomId, checkIn, checkOut, totalPrice);
    }
}
