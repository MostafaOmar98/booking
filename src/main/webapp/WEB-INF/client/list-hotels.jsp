<%@ page import="java.util.List" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.DTO.HotelSearchResultDTO" %>
<%
    List<HotelSearchResultDTO> results = (List<HotelSearchResultDTO>) request.getAttribute("results");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
<div class="container">
    <%
        for (HotelSearchResultDTO result : results) {
    %>
    <hr/>
    <div class="row">
        <div class="container">
            <div class="row">
                <a href="view-hotel?hotelId=<%=result.getHotelId()%>"><h2><%=result.getHotelName()%></h2>
                </a>
            </div>
            <div class="row">
                <img src="image/<%=result.getImageLink()%>" alt="Image couldn't be loaded">
            </div>
        </div>
        <div class="container">
            <div class="row">
                <h3>Rating: <%=result.getHotelRate() == null ? "No Rating Yet" : result.getHotelRate()%></h3>
            </div>
            <div class="row">
                <h3>Price Range: <%=result.getMinPrice()%> ~ <%=result.getMaxPrice()%></h3>
            </div>
            <div class="row">
                <h3>Distance From Specified Location: <%=result.getDistance() > 1e30 ? "No Location Specified" : result.getDistance()%></h3>
            </div>
        </div>
    </div>
    <%}%>
</div>
</body>
</html>
