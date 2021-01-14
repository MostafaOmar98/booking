package com.hagz_hotels.hotels_booking.Util;

import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class Auth {

    public static class AuthException extends Exception {
        AuthException() {
            super();
        }

        AuthException(String message) {
            super(message);
        }

        AuthException(Status status) {
            super(status.name());
        }
    }

    public enum Status {
        OK, AUTHORIZATION_ERROR, AUTHENTICATION_ERROR
    }

    public static HotelDAO hotelDAO = new HotelDAO();
    public static RoomDAO roomDAO = new RoomDAO();
    public static ClientRoomReservationDAO clientRoomReservationDAO = new ClientRoomReservationDAO();

    public static Status isAuth(User user, User.Type authType) {
        if (user == null)
            return Status.AUTHENTICATION_ERROR;
        else if (user.getType() != authType)
            return Status.AUTHORIZATION_ERROR;
        return Status.OK;
    }

    public static void authenticate(HttpServletRequest request) throws AuthException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new AuthException(Status.AUTHENTICATION_ERROR);
        }
    }

    public static void authorizeUserType(HttpServletRequest request, User.Type... authTypes) throws AuthException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean ok = false;
        for (User.Type authType : authTypes) {
            ok = ok || (authType == user.getType());
        }
        if (!ok)
            throw new AuthException(Status.AUTHORIZATION_ERROR);
    }

    public static void authorizeHotel(HttpServletRequest request, Integer hotelId) throws SQLException, ClassNotFoundException, AuthException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) (session.getAttribute("user"));
        if (!hotelDAO.has(user.getUserId(), hotelId)) {
            throw new AuthException(Status.AUTHORIZATION_ERROR);
        }
    }

    public static void authorizeRoom(HttpServletRequest request, Integer roomId) throws SQLException, ClassNotFoundException, AuthException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) (session.getAttribute("user"));
        Hotel hotel = hotelDAO.findByAdminId(user.getUserId());
        if (hotel == null)
            throw new AuthException(Status.AUTHORIZATION_ERROR);
        if (!roomDAO.has(roomId, hotel.getHotelId()))
            throw new AuthException(Status.AUTHORIZATION_ERROR);
    }

    public static void authorizeReservation(HttpServletRequest request, Integer reservationId) throws AuthException, SQLException, ClassNotFoundException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) (session.getAttribute("user"));
        if (!clientRoomReservationDAO.has(reservationId, user.getUserId())) {
            throw new AuthException(Status.AUTHORIZATION_ERROR);
        }
    }
}
