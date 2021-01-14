package com.hagz_hotels.hotels_booking.Business.Client;

import com.hagz_hotels.hotels_booking.Business.DTO.HotelSearchResultDTO;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelImageDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.HotelImage;
import com.hagz_hotels.hotels_booking.Util.Util;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchHotels {
    private static final HotelDAO hotelDAO = new HotelDAO();
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final ClientHotelReviewDAO clientHotelReviewDAO = new ClientHotelReviewDAO();
    private static final HotelImageDAO hotelImageDAO = new HotelImageDAO();
    private static final String DEFAULT_IMAGE = "0/default.jpg";

    public static List<HotelSearchResultDTO> execute(Float longitude, Float latitude, Integer adults, Integer children, LocalDate checkIn, LocalDate checkOut) throws SQLException, ClassNotFoundException {
        List<Hotel> hotels = hotelDAO.findByCriteria(adults, children, checkIn, checkOut);
        List<HotelSearchResultDTO> results = new ArrayList<>();

        for (Hotel hotel : hotels) {
            HotelSearchResultDTO r = new HotelSearchResultDTO();
            r.setHotelId(hotel.getHotelId());
            r.setHotelName(hotel.getName());
            r.setHotelRate(clientHotelReviewDAO.findAverageRatingByHotelId(hotel.getHotelId()));
            HotelImage imageId = hotelImageDAO.findOneByHotelId(hotel.getHotelId());
            if (imageId == null)
                r.setImageLink(DEFAULT_IMAGE);
            else
                r.setImageLink(imageId.getName());
            r.setMinPrice(roomDAO.getMinAvailablePriceByCriteria(adults, children, checkIn, checkOut, hotel.getHotelId()));
            r.setMaxPrice(roomDAO.getMaxAvailablePriceByCriteria(adults, children, checkIn, checkOut, hotel.getHotelId()));
            if (hotel.getLongitude() == null)
                r.setDistance((float) 1e30);
            else
                r.setDistance(Util.sphericalDistance(longitude, latitude, hotel.getLongitude(), hotel.getLatitude()));
            results.add(r);
        }

        results.sort(new Comparator<HotelSearchResultDTO>() {
            @Override
            public int compare(HotelSearchResultDTO r1, HotelSearchResultDTO r2) {
                if (r1.getDistance() < r2.getDistance())
                    return -1;
                else if (r1.getDistance() > r2.getDistance())
                    return 1;
                return 0;
            }
        });
        return results;
    }
}
