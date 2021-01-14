package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;

import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class JSONAuth {
    public static boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            Auth.authenticate(request);
            return true;
        } catch (Auth.AuthenticationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHENTICATED");
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);
        return false;
    }

    public static boolean authorizeUserType(HttpServletRequest request, HttpServletResponse response, User.Type... authTypes) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            Auth.authorizeUserType(request, authTypes);
            return true;
        } catch (Auth.AuthenticationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHENTICATED");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHORIZED");
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);
        return false;
    }

    public static boolean authorizeHotel(HttpServletRequest request, HttpServletResponse response, Integer hotelId) throws IOException, SQLException, ClassNotFoundException {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            Auth.authorizeHotel(request, hotelId);
            return true;
        } catch (Auth.AuthenticationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHENTICATED");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHORIZED");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);
        return false;
    }

    public static boolean authorizeRoom(HttpServletRequest request, HttpServletResponse response, Integer roomId) throws SQLException, ClassNotFoundException, IOException {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            Auth.authorizeRoom(request, roomId);
            return true;
        } catch (Auth.AuthenticationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHENTICATED");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHORIZED");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);
        return false;
    }

    public static boolean authorizeReservation(HttpServletRequest request, HttpServletResponse response, Integer reservationId) throws SQLException, ClassNotFoundException, IOException {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            Auth.authorizeReservation(request, reservationId);
            return true;
        } catch (Auth.AuthenticationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHENTICATED");
            e.printStackTrace();
        } catch (Auth.AuthorizationException e) {
            jsonResponse.setAttr("error", "NOT_AUTHORIZED");
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);
        return false;
    }
}
