package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Client;

import com.hagz_hotels.hotels_booking.Business.validators.PasswordValidator;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.JSONExceptionFactory;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update-password", value = "/update-password")
public class UpdatePassword extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT, User.Type.ADMIN}; // Added by bekh
    UserDAO userDAO = new UserDAO();
    PasswordValidator passwordValidator = new PasswordValidator();
    /// todo extend authorization to give array of user types
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); // added by bekh
        if(!JSONAuth.authorizeUserType(request, response, authType[0], authType[1])){ // added by bekh
//            response.sendRedirect("index.jsp"); COMMENTED BY BEKH
            return ;
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        try {
            passwordValidator.validate(request);
            String newPassword = request.getParameter("password");
            userDAO.update(user.getUsername(), user.getEmail(),user.getPhone(),newPassword,user.getUserId());
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setAttr("status", "password updated successfully");
            jsonResponse.setAttr("success", "true");
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);
        } catch (Exception e) {
            JsonResponse jsonResponse =  JSONExceptionFactory.getJSONResponse(e);
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);
        }


    }
}
