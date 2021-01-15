<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %>
<%--quick fix for includes shared between admin and client. probably bad design and there is a better solution;--%>
<%
    User generalNavBarUser = (User) session.getAttribute("user");
    if (generalNavBarUser != null && generalNavBarUser.getType() == User.Type.ADMIN) { %>
<%@include file="../../admin/include/header-navbar.jsp" %>
<% } else if (generalNavBarUser != null){ %>
<%@include file="../../client/include/header-navbar.jsp" %>
<%}%>
