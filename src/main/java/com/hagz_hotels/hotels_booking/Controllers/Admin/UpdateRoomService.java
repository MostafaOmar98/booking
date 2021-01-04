package com.hagz_hotels.hotels_booking.Controllers.Admin;


import com.hagz_hotels.hotels_booking.Controllers.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-room")
public class UpdateRoomService extends HttpServlet {

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

        JsonResponse jsonResponse = new JsonResponse();

        // Invalid input format will be handled by frontend. Frontend bypass will return 500 Internal Server Error which is intended.
        Integer roomId = Integer.valueOf(request.getParameter("roomId")),
                maxAdults = Integer.valueOf(request.getParameter("maxAdults")),
                maxChildren = Integer.valueOf(request.getParameter("maxChildren"));
        Float pricePerNight = Float.valueOf(request.getParameter("pricePerNight"));
        String type = request.getParameter("type"),
                facilities = request.getParameter("facilities");


        if (type.equals(""))
            jsonResponse.setAttr("type_error", "type_empty");
        else
            roomDAO.update(roomId, pricePerNight, type, maxAdults, maxChildren, facilities);
        response.getWriter().println(jsonResponse);
    }
}
