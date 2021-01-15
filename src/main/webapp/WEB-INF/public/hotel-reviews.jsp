<%@ page import="com.hagz_hotels.hotels_booking.Business.DTO.ReviewDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ReviewDTO> reviews = (List<ReviewDTO>) request.getAttribute("reviews");
    request.setAttribute("hotelId", request.getParameter("hotelId")); // for navbar
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    icons--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
    <style>
        .checked{
            color: orange;
        }
    </style>
    <title>Hotel Reviews</title>
</head>
<body>
<nav class="navbar navbar-expand-md ">
    <a class="navbar-brand" href="#">Hagz</a>
    <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="main-navigation">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown">
                    Other
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="profile">Update Profile</a>
                    <a class="dropdown-item" href="change-password">Change Password</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="sign-out">Sign Out</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<h2>Reviews</h2>
<%
    for (ReviewDTO r : reviews) {
%>
<div class="container-fluid mt-4">
    <div class="row">
        <div class="col-sm-4">
            <div class="container-fluid">
                <div class="row">
                    <i class="fa fa-user mr-2 mb-2"></i> <span><%=r.getClientName()%></span>
                </div>
                <div class="row">
                    <i class="fa fa-bed mr-2 mb-2"></i> <span><%=r.getRoomType()%></span>
                </div>
                <div class="row">
                    <i class="fa fa-calendar mr-2 mb-2"></i> <span><%=r.getCheckIn()%> ~ <%=r.getCheckout()%></span>
                </div>
                <div class="row">
                    <span class="fa fa-star <%=r.getStars() >= 1 ? "checked" : ""%>"></span>
                    <span class="fa fa-star <%=r.getStars() >= 2 ? "checked" : ""%>"></span>
                    <span class="fa fa-star <%=r.getStars() >= 3 ? "checked" : ""%>"></span>
                    <span class="fa fa-star"<%=r.getStars() >= 4 ? "checked" : ""%>></span>
                    <span class="fa fa-star"<%=r.getStars() >= 5 ? "checked" : ""%>></span>
                </div>
            </div>
        </div>
        <div class="col-8">
            <small class="text-muted">Reviewed: <span><%=r.getCreatedAt()%></span></small>
            <p><%=r.getComment()%>
            </p>
        </div>
    </div>
</div>
<hr/>
<%}%>

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
