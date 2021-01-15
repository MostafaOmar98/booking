package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Client;

import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Util.Auth;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "update-profile", value = "/update-profile")
public class UpdateProfile extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT, User.Type.ADMIN}; // Added admin by bekh
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!JSONAuth.authorizeUserType(request, response, authType[0], authType[1])){
//            response.sendRedirect("index.jsp"); commented by bekh
            return ;
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String newPhone = request.getParameter("phone");
        String newEmail = request.getParameter("email");


        try {
            userDAO.update(newEmail, newPhone,user.getPassword(),user.getUserId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            user = userDAO.findById(user.getUserId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("user",user);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setAttr("status", "updated successfully");
        jsonResponse.setAttr("success", "true");
        response.setContentType("application/json");
        response.getWriter().println(jsonResponse);

    }
}
