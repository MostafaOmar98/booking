package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;


import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-room")
public class DeleteRoomService extends HttpServlet {

    RoomDAO roomDAO;
    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Integer roomId = Integer.valueOf(request.getParameter("roomId"));
        try {
            if (!JSONAuth.authorizeRoom(request, response, roomId))
                return;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        JsonResponse jsonResponse = new JsonResponse();
        try {
            roomDAO.delete(roomId);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.getWriter().println(jsonResponse);
    }
}
