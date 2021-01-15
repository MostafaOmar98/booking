<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/profile.css">
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    icons--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
</head>
<body>
<%@include file="include/header-navbar.jsp" %>
<%
    User user = (User) session.getAttribute("user");
%>
<div class="container-fluid">
    <form action="">
        <div class="form-row form-group">
            <label for="email" class="col-sm-4 col-form-label">Email</label>
            <div class="col-sm-8">
                <input type="email" id="email" class="form-control" value="<%=user.getEmail()%>" required>
            </div>
            <div class="email-error" id="email-error-span">
                <small id="email-error"></small>
            </div>
        </div>
        <div class="form-row form-group">
            <label for="phone" class="col-sm-4 col-form-label">Phone Number</label>
            <div class="col-sm-8">
                <input type="text" id="phone" class="form-control" value="<%=user.getPhone()%>" required>
            </div>
            <div class="phone-error" id="phone-error-span">
                <p id="phone-error"></p>
            </div>
        </div>
        <div align="right">
            <button class="btn btn-primary" id="edit-profile">Edit Profile</button>

        </div>
        <div id="success" role="alert">
            <p id="update-status"></p>
        </div>
    </form>
</div>
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
<script type="module" src="${pageContext.request.contextPath}/scripts/public/profile.js"></script>
</body>
</html>
