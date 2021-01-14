package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Client;

import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update-password", value = "/update-password")
public class UpdatePassword extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT};
    UserDAO userDAO = new UserDAO();
    /// todo extend authorization to give array of user types
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!Auth.authenticate(request,response,authType)){
            response.sendRedirect("index.jsp");
            return ;
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String newPassword = request.getParameter("newPassword");

        userDAO.update(user.getName(), user.getEmail(),user.getPhone(),newPassword,user.getUserId());
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setAttr("status", "password updated successfully");
        jsonResponse.setAttr("success", "true");
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);

    }
}
