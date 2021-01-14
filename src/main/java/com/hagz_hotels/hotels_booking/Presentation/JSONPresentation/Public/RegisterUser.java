package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;

import com.hagz_hotels.hotels_booking.Business.validators.EmailValidator;
import com.hagz_hotels.hotels_booking.Business.validators.NameValidator;
import com.hagz_hotels.hotels_booking.Business.validators.exceptions.EmailExistException;
import com.hagz_hotels.hotels_booking.Business.validators.exceptions.InvalidEmailException;
import com.hagz_hotels.hotels_booking.Business.validators.exceptions.InvalidUserNameException;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.JSONExceptionFactory;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;
import com.hagz_hotels.hotels_booking.Util.MailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register-user", value = "/register-user")
public class RegisterUser extends HttpServlet {
    // TODO: 1/14/21 validate input
    // TODO: 1/14/21 send mail with password
    // TODO: 1/14/21 add user to database
    // TODO: 1/14/21 we need no authentication to register we need only captcha to avoid spamming
    // TODO: 1/14/21 validate right email
    // TODO: 1/14/21 validate username is not in database
    // TODO: 1/14/21 add username col to database
    EmailValidator emailValidator = new EmailValidator();
    NameValidator nameValidator = new NameValidator();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            emailValidator.validate(request);
            nameValidator.validate(request);
            String RegisteredEmail = (String) request.getParameter("email");
            String RegisteredName = (String) request.getParameter("username");

            String RegisteredPassword  = "1234";/// // TODO: 1/14/21 generate password
            MailUtil.sendMail(RegisteredEmail,"Registeration Confirmation","password is : "+RegisteredPassword);
            JsonResponse jsonResponse = new JsonResponse();
            UserDAO userDAO = new UserDAO();
            Integer userId = userDAO.create(RegisteredEmail,RegisteredPassword,RegisteredName);
            jsonResponse.setAttr("status", "Email registered successfully !!");
            jsonResponse.setAttr("Email", RegisteredEmail);
            jsonResponse.setAttr("success", "true");
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);


        }
        catch (Exception e) {
            JsonResponse jsonResponse = JSONExceptionFactory.getJSONResponse(e);
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);
        }
    }
}
