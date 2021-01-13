package com.hagz_hotels.hotels_booking.Controllers.Client;

import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@WebServlet("/search-hotel")
public class SearchHotelService extends HttpServlet {

    User.Type authType = User.Type.CLIENT;
    HotelDAO hotelDAO;
    RoomDAO roomDAO;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
    }

    private double sphericalDistance(Float lng1, Float lat1, Float lng2, Float lat2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lng2 - lng1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Auth.authenticate(request, response, authType))
            return;
        Float longitude = Float.valueOf(request.getParameter("lng"));
        Float latitude = Float.valueOf(request.getParameter("lat"));
        Integer adults = Integer.valueOf(request.getParameter("n-adults"));
        Integer children = Integer.valueOf(request.getParameter("n-children"));
        System.out.println(request.getParameter("checkIn"));
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"), dtf);
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"), dtf);

        List<Hotel> hotels = hotelDAO.findByCriteria(adults, children, checkIn, checkOut);
        hotels.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                double d = sphericalDistance(h1.getLongitude(), h1.getLatitude(), longitude, latitude) - sphericalDistance(h2.getLongitude(), h2.getLatitude(), longitude, latitude);
                if (d < 0)
                    return -1;
                else if (d == 0)
                    return 0;
                return 1;
            }
        });
        request.setAttribute("hotels", hotels);
        request.getRequestDispatcher("/WEB-INF/client/list-hotels.jsp").forward(request, response);
    }
}
