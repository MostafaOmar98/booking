package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;

import com.hagz_hotels.hotels_booking.Business.Public.SearchUser;
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

@WebServlet("/validate-credentials")
public class ValidateCredentialsService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String username = request.getParameter("username"), password = request.getParameter("password"), type = request.getParameter("type"); // TODO: username instead of email

        JsonResponse jsonResponse = new JsonResponse();
        User user = null;
        try {
            user = SearchUser.execute(username, password, type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            jsonResponse.setAttr("error", "email_not_found");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        jsonResponse.setAttr("redirect", getServletContext().getContextPath() + "/index.jsp");
        response.getWriter().println(jsonResponse);
    }
}
