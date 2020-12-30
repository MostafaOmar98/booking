<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %>
<%
    User user = (User)session.getAttribute("user");
    if (user == null)
        response.sendRedirect("login.jsp");
    else if (user.getType() == User.Type.ADMIN)
        response.sendRedirect("admin-home");
    else
        response.sendRedirect("client-home");
%>