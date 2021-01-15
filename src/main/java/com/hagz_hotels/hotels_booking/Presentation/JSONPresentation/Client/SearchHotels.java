package com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Client;

import com.hagz_hotels.hotels_booking.Business.validators.DateIntervalValidator;
import com.hagz_hotels.hotels_booking.Business.validators.PasswordValidator;
import com.hagz_hotels.hotels_booking.Business.validators.ValidationExceptionFactory;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.JSONExceptionFactory;
import com.hagz_hotels.hotels_booking.Presentation.JSONPresentation.Public.JSONAuth;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
/// take request return HotelsDTO
@WebServlet(name = "search-hotels", value = "/search-hotels")
public class SearchHotels extends HttpServlet {
    User.Type[] authType = {User.Type.CLIENT, User.Type.ADMIN}; // Added by bekh
    UserDAO userDAO = new UserDAO();
    DateIntervalValidator dateIntervalValidator = new DateIntervalValidator();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String checkIn = request.getParameter("checkIn");
            String checkOut = request.getParameter("checkOut");
            if(checkOut == null|| checkIn == null || checkIn.length() ==0 || checkOut.length() ==0) {
                throw ValidationExceptionFactory.getIntervalException(DateIntervalValidator.State.Empty);
            }
            dateIntervalValidator.validate(request,"checkOut");



            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setAttr("status", "we have valid input");
            jsonResponse.setAttr("redirectUrl", "search-hotel");
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
