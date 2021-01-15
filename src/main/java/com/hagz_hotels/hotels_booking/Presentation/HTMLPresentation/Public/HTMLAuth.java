package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public;

import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HTMLAuth {
    public static boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Auth.authenticate(request);
            return true;
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean authorizeUserType(HttpServletRequest request, HttpServletResponse response, User.Type... authTypes) throws IOException {
        try {
            Auth.authorizeUserType(request, authTypes);
            return true;
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( request.getContextPath() + "/unauthorized.jsp");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean authorizeHotel(HttpServletRequest request, HttpServletResponse response, Integer hotelId) throws IOException, SQLException, ClassNotFoundException {
        try {
            Auth.authorizeHotel(request, hotelId);
            return true;
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( request.getContextPath() + "/unauthorized.jsp");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        return false;
    }

    public static boolean authorizeRoom(HttpServletRequest request, HttpServletResponse response, Integer roomId) throws SQLException, ClassNotFoundException, IOException {
        try {
            Auth.authorizeRoom(request, roomId);
            return true;
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( request.getContextPath() + "/unauthorized.jsp");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        return false;
    }

    public static boolean authorizeReservation(HttpServletRequest request, HttpServletResponse response, Integer reservationId) throws SQLException, ClassNotFoundException, IOException {
        try {
            Auth.authorizeReservation(request, reservationId);
            return true;
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( request.getContextPath() + "/unauthorized.jsp");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        return false;
    }
}
