<%@ page import="com.hagz_hotels.hotels_booking.Model.DTO.ReviewDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ReviewDTO> reviews = (List<ReviewDTO>) request.getAttribute("reviews");
%>
<html>
<head>
    <title>Hotel Reviews</title>
</head>
<body>
<header>Reviews</header>
<%
    for (ReviewDTO r : reviews) {
%>
<div class="container">
    <div class="row">
        <div class="col-3">
            <%=r.getClientName()%>
            <%=r.getStars()%> stars
            <%=r.getCheckIn()%> - <%=r.getCheckout()%>
            <%=r.getCreatedAt()%>
            <%=r.getRoomType()%>
        </div>
        <div class="col-9">
            <%=r.getComment()%>
        </div>
    </div>
</div>
<%}%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
