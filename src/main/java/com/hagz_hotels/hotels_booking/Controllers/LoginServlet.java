package com.hagz_hotels.hotels_booking.Controllers;

import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email"), password = request.getParameter("password"), type = request.getParameter("type");
        User user = userDAO.findByEmailAndPasswordAndType(email, password, type);
        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        request.getRequestDispatcher("/index").forward(request, response);
    }
}
