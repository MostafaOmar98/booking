package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class HotelImageDAO {

    Function<ResultSet, HotelImage> mapper = set -> {
        HotelImage hotelImage = new HotelImage();
        try {
            hotelImage.setHotelId(set.getInt("HotelId"));
            hotelImage.setImageId(set.getInt("ImageId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelImage;
    };

    public Integer getMax(Integer hotelId) {
        List<HotelImage> list = DBUtil.selectAll("SELECT * FROM HotelImage WHERE HotelId=?", mapper, hotelId);
        return list.get(list.size() - 1).getImageId();
    }

    public void create(Integer hotelId) {
        String query = "INSERT INTO HotelImage (HotelId) VALUES(?)";
        DBUtil.executeUpdate(query, hotelId);
    }

    public List<HotelImage> findByHotelId(Integer hotelId) {
        String query = "SELECT * FROM HotelImage WHERE HotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }
}
