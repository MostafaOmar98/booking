package com.hagz_hotels.hotels_booking.Model.DAO;

import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotelImageDAO {

    IMapper<ResultSet, HotelImage> mapper = set -> {
        HotelImage hotelImage = new HotelImage();
        try {
            hotelImage.setHotelId(set.getInt("HotelId"));
            hotelImage.setImageId(set.getInt("ImageId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelImage;
    };

    public Integer getMax(Integer hotelId) throws SQLException, ClassNotFoundException {
        List<HotelImage> list = DBUtil.selectAll("SELECT * FROM HotelImage WHERE HotelId=?", mapper, hotelId);
        return list.get(list.size() - 1).getImageId();
    }

    public Integer create(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO HotelImage (HotelId) VALUES(?)";
        return DBUtil.insert(query, hotelId);
    }

    public void delete(Integer imageId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM HotelImage WHERE ImageId=?";
        DBUtil.executeUpdate(query, imageId);
    }

    public List<HotelImage> findByHotelId(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM HotelImage WHERE HotelId=?";
        return DBUtil.selectAll(query, mapper, hotelId);
    }

    public HotelImage findOneByHotelId(Integer hotelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM HotelImage WHERE HotelId=?";
        return DBUtil.selectOne(query, mapper, hotelId);
    }
}
