<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %><%--
  Created by IntelliJ IDEA.
  User: tw3
  Date: 1/14/21
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType= "text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
</head>
<body>
<%--<%--%>
<%--    pageContext--%>
<%--%>--%>
<%@include file= "/include/header-navbar.jsp" %>

<form action="">
    <div class="form-row">
        <div class="form-group">
            <label for="username" class="col-lg-4">Username</label>
            <div class="col-lg-8">
                <input type="text"  id="username" class="form-control"  required>
            </div>
            <div class="username-error" id="username-error-span">
                <p id="username-error"></p>
            </div>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group">
            <label for="email" class="col-lg-4">Email</label>
            <div class="col-lg-8">
                <input type="text"  id="email" class="form-control"  required>
            </div>
            <div class="email-error" id="email-error-span">
                <p id="email-error"></p>
            </div>
        </div>
    </div>
    <%
        Object obj = session.getAttribute("user");
        if(obj != null  && ((User)obj).getType() == User.Type.ADMIN){

    %>
    <div class="form-row">
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="admin" name="type" value="ADMIN" checked>
                <label for="admin" class="form-check-label bg-text">Admin</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="client" name="type" value="CLIENT">
                <label for="client" class="form-check-label bg-text">Client</label>
            </div>
            <div class="type-error" id="type-error-span">
                <p id="type-error"></p>
            </div>
        </div>
    </div>
    <%
        }
    %>
    <div align="right">
        <button class="btn btn-primary" id="register"  >Register</button>

    </div>
    <div id="success" role="alert">
        <p id="update-status"></p>
    </div>


</form>

<script type="module" src="${pageContext.request.contextPath}/scripts/public/register.js"></script>

<%--JQuery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%--Popper--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<%--Bootstrap js--%>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
