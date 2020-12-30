package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public List findByHotelID(Integer hotelId) {
        Connection con = DBUtil.getConnection();
        List<Room> rooms = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Room WHERE hotelId=?");
            stmt.setInt(1, hotelId);
            ResultSet set = stmt.executeQuery();
            while (set.next())
                rooms.add(map(set));
            DBUtil.close(con, stmt, set);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rooms;
    }

    public void create(Float pricePerNight, String type, Integer maxAdults, Integer maxChildren, Integer hotelId, String facilities) {
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Room (PricePerNight, Type, MaxAdults, MaxChildren, HotelId, Facilities) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setObject(1, pricePerNight);
            stmt.setObject(2, type);
            stmt.setObject(3, maxAdults);
            stmt.setObject(4, maxChildren);
            stmt.setObject(5, hotelId);
            stmt.setObject(6, facilities);
            stmt.executeUpdate();
            DBUtil.close(con, stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private Room map(ResultSet set) throws SQLException {
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
}
