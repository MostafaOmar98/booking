<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %><%--
  Created by IntelliJ IDEA.
  User: tw3
  Date: 1/13/21
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<div class="form-change" >
    <form action="">
        <div class="form-row">
            <div class="form-group">
                <label for="display-name" class="col-lg-4">Disply Name</label>
                <div class="col-lg-8">
                    <input type="text"  id="display-name"class="form-control" value=<%=request.getAttribute("name")%>>
                </div>
                <div class="display-name-error" id="display-name-error-span">
                    <p id="display-name-error"></p>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label for="display-name" class="col-lg-4">Email</label>
                <div class="col-lg-8">
                    <input type="email"  id="email" class="form-control" value=<%=request.getAttribute("email")%>>
                </div>
                <div class="email-error" id="email-error-span">
                    <p id="email-error"></p>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label for="phone" class="col-lg-4">Phone Number</label>
                <div class="col-lg-8">
                    <input type="tel"  id="phone"class="form-control" value= <%=request.getAttribute("phone")%>>
                </div>
                <div class="phone-error" id="phone-error-span">
                    <p id="phone-error"></p>
                </div>
            </div>
        </div>
        <div align="right">
            <button class="btn btn-primary" id="edit-profile"  >Edit Profile</button>

        </div>
        <div id="success" role="alert">
            <p id="update-status"></p>
        </div>

    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/scripts/client/profile.js"></script>
</body>
</html>
