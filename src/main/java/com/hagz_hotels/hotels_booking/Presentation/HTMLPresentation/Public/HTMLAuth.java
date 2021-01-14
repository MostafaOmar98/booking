package com.hagz_hotels.hotels_booking.Presentation.HTMLPresentation.Public;

import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class HTMLAuth {
    public static void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Auth.authenticate(request);
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        }
    }

    public static void authorizeUserType(HttpServletRequest request, HttpServletResponse response, User.Type... authTypes) throws IOException {
        try {
            Auth.authorizeUserType(request, authTypes);
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( "/WEB-INF/public/unauthorized.jsp");
            e.printStackTrace();
        }
    }

    public static void authorizeHotel(HttpServletRequest request, HttpServletResponse response, Integer hotelId) throws IOException, SQLException, ClassNotFoundException {
        try {
            Auth.authorizeHotel(request, hotelId);
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( "/WEB-INF/public/unauthorized.jsp");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    public static void authorizeRoom(HttpServletRequest request, HttpServletResponse response, Integer roomId) throws SQLException, ClassNotFoundException, IOException {
        try {
            Auth.authorizeRoom(request, roomId);
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( "/WEB-INF/public/unauthorized.jsp");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    public static void authorizeReservation(HttpServletRequest request, HttpServletResponse response, Integer reservationId) throws SQLException, ClassNotFoundException, IOException {
        try {
            Auth.authorizeReservation(request, reservationId);
        } catch (Auth.AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            response.sendRedirect( "/WEB-INF/public/unauthorized.jsp");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}
