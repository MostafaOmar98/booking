package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public;

import com.hagz_hotels.hotels_booking.Business.validators.EmailValidator;
import com.hagz_hotels.hotels_booking.Business.validators.exceptions.EmailExistException;
import com.hagz_hotels.hotels_booking.Business.validators.exceptions.InvalidEmailException;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            emailValidator.validate(request);

        } catch (EmailExistException e) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setAttr("status", "Email already exists");
            jsonResponse.setAttr("success", "false");
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);
        } catch (InvalidEmailException e) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setAttr("status", "Email is invalid");
            jsonResponse.setAttr("success", "false");
            response.setContentType("application/json");
            response.getWriter().println(jsonResponse);
        } catch (Exception e) {
            ///// TODO: 1/14/21 check any other errors
            e.printStackTrace();
        }
    }
}
