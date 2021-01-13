package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RoomDAO {

    Function<ResultSet, Room> mapper = new Function<ResultSet, Room>() {
        @Override
        public Room apply(ResultSet set) {
            Room room = new Room();
            try {
                room.setRoomId(set.getInt("RoomId"));
                room.setPricePerNight(set.getFloat("PricePerNight"));
                room.setType(set.getString("Type"));
                room.setMaxAdults(set.getInt("MaxAdults"));
                room.setMaxChildren(set.getInt("MaxChildren"));
                room.setHotelId(set.getInt("HotelId"));
                room.setFacilities(set.getString("Facilities"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return room;
        }
    };

    Function<ResultSet, Float> priceMapper = new Function<ResultSet, Float>() {
        @Override
        public Float apply(ResultSet set) {
            Float price = null;
            try {
                price = set.getFloat("Price");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return price;
        }
    };

    public List<Room> findByHotelID(Integer hotelId) {
        String query = "SELECT * FROM Room WHERE hotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public void create(Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, Integer hotelId, String facilities) {
        String query = "INSERT INTO Room (PricePerNight, Type, MaxAdults, MaxChildren, HotelId, Facilities) VALUES(?, ?, ?, ?, ?, ?)";
        DBUtil.executeUpdate(query, pricePerNight, type, maxAdults, maxChildren, hotelId, facilities);
    }

    public void update(Integer roomId, Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, String facilities) {
        String query = "UPDATE Room SET PricePerNight=?, Type=?, maxAdults=?, maxChildren=?, facilities=? WHERE RoomId=?";
        DBUtil.executeUpdate(query, pricePerNight, type, maxAdults, maxChildren, facilities, roomId);
    }

    public void delete(Integer roomId) {
        System.out.println("Inside delete: " + roomId);
        String query = "DELETE FROM Room WHERE RoomId=?";
        DBUtil.executeUpdate(query, roomId);
    }

    public boolean has(Integer roomId, Integer hotelId) {
        String query = "SELECT * FROM Room WHERE RoomId=? AND HotelId=?";
        return DBUtil.selectOne(query, mapper, roomId, hotelId) != null;
    }

    public Room findById(Integer roomId) {
        String query = "SELECT * FROM Room WHERE RoomId=?";
        return DBUtil.selectOne(query, mapper, roomId);
    }

    public Float getMinAvailablePriceByCriteria(Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut, Integer hotelId) {
        String query = "SELECT MIN(PricePerNight) as Price FROM Room WHERE " +
                "Room.HotelId = ? AND " +
                "MaxAdults >= ? AND " +
                "MaxChildren >= ? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                "(Status = \"CHECKED_OUT\" OR Status = \"CANCELED\") AND " +
                "((CheckIn <= ? AND CheckOut >= ?) OR (CheckIn <= ? AND CheckOut >= ?))" +
                ") GROUP BY HotelId";
        return DBUtil.selectOne(query, priceMapper, hotelId, adults, children, checkIn, checkIn, checkOut, checkOut);
    }

    public Float getMaxAvailablePriceByCriteria(Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut, Integer hotelId) {
        String query = "SELECT MAX(PricePerNight) as Price FROM Room WHERE " +
                "Room.HotelId = ? AND " +
                "MaxAdults >= ? AND " +
                "MaxChildren >= ? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                "(Status = \"CHECKED_OUT\" OR Status = \"CANCELED\") AND " +
                "((CheckIn <= ? AND CheckOut >= ?) OR (CheckIn <= ? AND CheckOut >= ?))" +
                ") GROUP BY HotelId ";
        return DBUtil.selectOne(query, priceMapper, hotelId, adults, children, checkIn, checkIn, checkOut, checkOut);
    }

    public List<Room> findByCriteria(Integer hotelId, Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut) {
        String query = "SELECT * FROM Room WHERE " +
                "Room.HotelId = ? AND " +
                "MaxAdults >= ? AND " +
                "MaxChildren >= ? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                "(Status = \"CHECKED_OUT\" OR Status = \"CANCELED\") AND " +
                "((CheckIn <= ? AND CheckOut >= ?) OR (CheckIn <= ? AND CheckOut >= ?))" +
                ")";
        return DBUtil.selectAll(query, mapper, hotelId, adults, children, checkIn, checkIn, checkOut, checkOut);
    }
}
