<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %>
<%--quick fix for includes shared between admin and client. probably bad design and there is a better solution;--%>
<%
    User user = (User) session.getAttribute("user");
    if (user != null && user.getType() == User.Type.ADMIN) { %>
<%@include file="../../admin/include/header-navbar.jsp" %>
<% } else if (user != null){ %>
<%@include file="../../client/include/header-navbar.jsp" %>
<%}%>
