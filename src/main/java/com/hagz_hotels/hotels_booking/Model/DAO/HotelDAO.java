package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class HotelDAO {

    Function<ResultSet, Hotel> mapper = new Function<ResultSet, Hotel>() {
        @Override
        public Hotel apply(ResultSet set) {
            Hotel hotel = new Hotel();
            try {
                hotel.setHotelId(set.getInt("HotelId"));
                hotel.setName(set.getString("Name"));
                hotel.setLatitude(set.getFloat("Latitude"));
                if (set.wasNull())
                    hotel.setLatitude(null);
                hotel.setLongitude(set.getFloat("Longitude"));
                if (set.wasNull())
                    hotel.setLongitude(null);
                hotel.setAddress(set.getString("Address"));
                hotel.setPhone(set.getString("Phone"));
                hotel.setAdminId(set.getInt("AdminId"));
                return hotel;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return hotel;
        }
    };

    public Hotel findByAdminId(Integer adminId) {
        String query = "SELECT * FROM Hotel WHERE AdminId=?";
        return DBUtil.selectOne(query, mapper, adminId);
    }

    public Integer create(String name, Integer adminId) {
        String query = "INSERT INTO Hotel (Name, AdminId) VALUES(?, ?)";
        return DBUtil.insert(query, name, adminId);
    }

    public void update(Integer hotelId, String name, Float latitude, Float longitude, String address, String phone, Integer adminId) {
        if (name != null)
            update(hotelId, "Name", name);
        if (latitude != null)
            update(hotelId, "Latitude", latitude);
        if (longitude != null)
            update(hotelId, "Longitude", longitude);
        if (address != null)
            update(hotelId, "Address", address);
        if (phone != null)
            update(hotelId, "Phone", phone);
        if (adminId != null)
            update(hotelId, "AdminId", adminId);
    }

    private void update(Integer hotelId, String fieldName, Object value) {
        String query = "UPDATE Hotel SET " + fieldName + "=? WHERE HotelId=?";
        DBUtil.executeUpdate(query, value, hotelId);
    }

    public boolean has(Integer adminId, Integer hotelId) {
        String query = "SELECT * FROM Hotel Where AdminId=? AND HotelId=?";
        return DBUtil.selectOne(query, mapper, adminId, hotelId) != null;
    }

    public Hotel findById(Integer hotelId) {
        String query = "SELECT * FROM Hotel WHERE HotelId=?";
        return DBUtil.selectOne(query, mapper, hotelId);
    }
}
