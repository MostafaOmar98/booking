package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Admin;


import com.hagz_hotels.hotels_booking.Business.Admin.UpdateRoom;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-room")
public class UpdateRoomService extends HttpServlet {

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

        // Invalid input format will be handled by frontend. Frontend bypass will return 500 Internal Server Error which is intended.
        Integer maxAdults = Integer.valueOf(request.getParameter("maxAdults"));
        Integer maxChildren = Integer.valueOf(request.getParameter("maxChildren"));
        Float pricePerNight = Float.valueOf(request.getParameter("pricePerNight"));
        String type = request.getParameter("type"),
                facilities = request.getParameter("facilities");


        JsonResponse jsonResponse = new JsonResponse();
        try {
            UpdateRoom.execute(roomId, pricePerNight, type, maxAdults, maxChildren, facilities);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            jsonResponse.setAttr("type_error", "type_empty");
        }
        response.getWriter().println(jsonResponse);
    }
}
