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

    public static class AuthenticationException extends Exception {
        AuthenticationException(String message) {
            super(message);
        }
    }

    public static class AuthorizationException extends Exception {
        AuthorizationException(String message) {
            super(message);
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

    public static void authenticate(HttpServletRequest request) throws AuthenticationException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new AuthenticationException("User Not Logged in");
        }
    }

    public static void authorizeUserType(HttpServletRequest request, User.Type... authTypes) throws AuthenticationException, AuthorizationException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean ok = false;
        for (User.Type authType : authTypes) {
            ok = ok || (authType == user.getType());
        }
        if (!ok)
            throw new AuthorizationException("User Type " + user.getTypeName() + " Not Allowed Here");
    }

    public static void authorizeHotel(HttpServletRequest request, Integer hotelId) throws SQLException, ClassNotFoundException, AuthenticationException, AuthorizationException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) (session.getAttribute("user"));
        if (!hotelDAO.has(user.getUserId(), hotelId)) {
            throw new AuthorizationException("User with id " + user.getUserId() + " is not authorized on hotel " + hotelId);
        }
    }

    public static void authorizeRoom(HttpServletRequest request, Integer roomId) throws SQLException, ClassNotFoundException, AuthenticationException, AuthorizationException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) (session.getAttribute("user"));
        Hotel hotel = hotelDAO.findByAdminId(user.getUserId());
        String message = "User with id " + user.getUserId() + " not allowed on room id " + roomId;
        if (hotel == null)
            throw new AuthorizationException(message);
        if (!roomDAO.has(roomId, hotel.getHotelId()))
            throw new AuthorizationException(message);
    }

    public static void authorizeReservation(HttpServletRequest request, Integer reservationId) throws SQLException, ClassNotFoundException, AuthenticationException, AuthorizationException {
        authenticate(request);
        HttpSession session = request.getSession();
        User user = (User) (session.getAttribute("user"));
        if (!clientRoomReservationDAO.has(reservationId, user.getUserId())) {
            throw new AuthorizationException("User with id " + user.getUserId() + " not authorized on reservation id " + reservationId);
        }
    }
}
