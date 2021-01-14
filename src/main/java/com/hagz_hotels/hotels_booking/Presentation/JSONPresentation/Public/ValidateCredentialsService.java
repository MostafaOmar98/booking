package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;

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

@WebServlet("/validate-credentials")
public class ValidateCredentialsService extends HttpServlet {

    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String email = request.getParameter("email"), password = request.getParameter("password"), type = request.getParameter("type"); // TODO: username instead of email
        User user = userDAO.findByEmailAndPasswordAndType(email, password, type);
        JsonResponse jsonResponse = new JsonResponse();
        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            jsonResponse.setAttr("redirect", getServletContext().getContextPath() + "/index.jsp");
        }
        else
            jsonResponse.setAttr("error", "email_not_found");
        response.getWriter().println(jsonResponse);
    }
}
