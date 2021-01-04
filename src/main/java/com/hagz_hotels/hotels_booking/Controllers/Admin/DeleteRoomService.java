package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delete-room")
public class DeleteRoomService extends HttpServlet {

    RoomDAO roomDAO;
    User.Type authType = User.Type.ADMIN;
    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        if (!Auth.authenticateJson(request, response, authType))
            return;

        Integer roomId = Integer.valueOf(request.getParameter("roomId"));

        if (!Auth.authorizeJsonRoom(request, response, roomId))
            return;
        JsonResponse jsonResponse = new JsonResponse();
        roomDAO.delete(roomId);
        response.getWriter().println(jsonResponse);
    }
}
