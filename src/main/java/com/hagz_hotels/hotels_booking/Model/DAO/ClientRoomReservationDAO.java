package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClientRoomReservationDAO {
    IMapper<ResultSet, ClientRoomReservation> mapper = new IMapper<ResultSet, ClientRoomReservation>() {
        @Override
        public ClientRoomReservation apply(ResultSet set) throws SQLException {
            ClientRoomReservation r = new ClientRoomReservation();
            r.setReservationId(set.getInt("ReservationId"));
            r.setRoomId(set.getInt("RoomId"));
            r.setClientId(set.getInt("ClientId"));
            r.setCheckIn(set.getDate("CheckIn").toLocalDate());
            r.setCheckOut(set.getDate("CheckOut").toLocalDate());
            r.setCreatedAt(set.getTimestamp("CreatedAt").toLocalDateTime());
            r.setStatus(set.getString("Status"));
            r.setTotalPrice(set.getFloat("TotalPrice"));
            return r;
        }
    };

    public List<ClientRoomReservation> findAllByHotelId(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientRoomReservation, Room " +
                "WHERE ClientRoomReservation.RoomId = Room.RoomId " +
                "AND Room.HotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public ClientRoomReservation findById(Integer reservationId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientRoomReservation WHERE ReservationId=?";
        return DBUtil.selectOne(query, mapper, reservationId);
    }

    public void updateStatus(Integer reservationId, String status) throws SQLException, ClassNotFoundException {
        String query = "UPDATE ClientRoomReservation SET Status=? WHERE ReservationId=?";
        DBUtil.executeUpdate(query, status, reservationId);
    }

    public void create(Integer clientId, Integer roomId, LocalDate checkIn, LocalDate checkOut, Float totalPrice) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO ClientRoomReservation (ClientId, RoomId, CreatedAt, CheckIn, CheckOut, STATUS, TotalPrice) VALUES " +
                "(?, ?, NOW(), ?, ?, \"PENDING\", ?)";
        DBUtil.executeUpdate(query, clientId, roomId, checkIn, checkOut, totalPrice);
    }

    public List<ClientRoomReservation> findAllByClientId(Integer clientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientRoomReservation WHERE ClientId=?";
        return DBUtil.selectAll(query, mapper, clientId);
    }

    public boolean has(Integer reservationId, Integer userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientRoomReservation WHERE ReservationId=? AND ClientId=?";
        return DBUtil.selectOne(query, mapper, reservationId, userId) != null;
    }

    public boolean hasAdmin(Integer reservationId, Integer adminId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM ClientRoomReservation, Room, Hotel, User WHERE " +
                "ClientRoomReservation.RoomId=Room.RoomId AND " +
                "Room.HotelId = Hotel.HotelId AND " +
                "Hotel.AdminId = User.UserId AND " +
                "ClientRoomReservation.ReservationId=? AND " +
                "User.Userid=?";
        return DBUtil.selectOne(query, mapper, reservationId, adminId) != null;
    }

    public void deleteByRoomId(Integer roomId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM ClientRoomReservation WHERE RoomId=?";
        DBUtil.executeUpdate(query, roomId);
    }
}
