package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomDAO {

    IMapper<ResultSet, Room> mapper = new IMapper<ResultSet, Room>() {
        @Override
        public Room apply(ResultSet set) throws SQLException {
            Room room = new Room();
            room.setRoomId(set.getInt("RoomId"));
            room.setPricePerNight(set.getFloat("PricePerNight"));
            room.setType(set.getString("Type"));
            room.setMaxAdults(set.getInt("MaxAdults"));
            room.setMaxChildren(set.getInt("MaxChildren"));
            room.setHotelId(set.getInt("HotelId"));
            room.setFacilities(set.getString("Facilities"));
            return room;
        }
    };

    IMapper<ResultSet, Float> priceMapper = new IMapper<ResultSet, Float>() {
        @Override
        public Float apply(ResultSet set) throws SQLException {
            Float price = null;
            price = set.getFloat("Price");
            return price;
        }
    };

    public List<Room> findByHotelID(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Room WHERE hotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public void create(Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, Integer hotelId, String facilities) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Room (PricePerNight, Type, MaxAdults, MaxChildren, HotelId, Facilities) VALUES(?, ?, ?, ?, ?, ?)";
        DBUtil.executeUpdate(query, pricePerNight, type, maxAdults, maxChildren, hotelId, facilities);
    }

    public void update(Integer roomId, Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, String facilities) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Room SET PricePerNight=?, Type=?, maxAdults=?, maxChildren=?, facilities=? WHERE RoomId=?";
        DBUtil.executeUpdate(query, pricePerNight, type, maxAdults, maxChildren, facilities, roomId);
    }

    public void delete(Integer roomId) throws SQLException, ClassNotFoundException {
        System.out.println("Inside delete: " + roomId);
        String query = "DELETE FROM Room WHERE RoomId=?";
        DBUtil.executeUpdate(query, roomId);
    }

    public boolean has(Integer roomId, Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Room WHERE RoomId=? AND HotelId=?";
        return DBUtil.selectOne(query, mapper, roomId, hotelId) != null;
    }

    public Room findById(Integer roomId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Room WHERE RoomId=?";
        return DBUtil.selectOne(query, mapper, roomId);
    }

    public Float getMinAvailablePriceByCriteria(Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut, Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT MIN(PricePerNight) as Price FROM Room WHERE " +
                "Room.HotelId = ? AND " +
                "MaxAdults >= ? AND " +
                "MaxChildren >= ? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                DBUtil.OVERLAPPING_RESERVATION +
                ") GROUP BY HotelId";
        return DBUtil.selectOne(query, priceMapper, hotelId, adults, children, checkIn, checkOut);
    }

    public Float getMaxAvailablePriceByCriteria(Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut, Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT MAX(PricePerNight) as Price FROM Room WHERE " +
                "Room.HotelId = ? AND " +
                "MaxAdults >= ? AND " +
                "MaxChildren >= ? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                DBUtil.OVERLAPPING_RESERVATION +
                ") GROUP BY HotelId ";
        return DBUtil.selectOne(query, priceMapper, hotelId, adults, children, checkIn, checkOut);
    }

    public List<Room> findByCriteria(Integer hotelId, Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Room WHERE " +
                "Room.HotelId = ? AND " +
                "MaxAdults >= ? AND " +
                "MaxChildren >= ? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                DBUtil.OVERLAPPING_RESERVATION + ")";
        System.out.println(query);
        return DBUtil.selectAll(query, mapper, hotelId, adults, children, checkIn, checkOut);
    }

    public boolean isAvailable(Integer roomId, LocalDate checkIn, LocalDate checkOut) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Room WHERE " +
                "RoomId=? AND " +
                "NOT EXISTS (" +
                "SELECT * FROM ClientRoomReservation WHERE " +
                "Room.RoomId = ClientRoomReservation.RoomId AND " +
                DBUtil.OVERLAPPING_RESERVATION +
                ")";
        return DBUtil.selectOne(query, mapper, roomId, checkIn, checkOut) != null;
    }
}
