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
<div class="form-group row">
    <label class="col-2 col-form-label" for="maxPrice">Set Max Price</label>
    <input class="form-control col-10" type="number" min="0" step="0.01" placeholder="Max Price" id="maxPrice">
</div>

<div class="form-group row">
    <label class="col-2 col-form-label" for="minRate">Set Min Rate</label>
    <input class="form-control col-10" type="number" min="0" step="0.01" placeholder="Min Rate" id="minRate">
</div>

<div class="container">
    <%
        for (HotelSearchResultDTO result : results) {
    %>
    <hr/>
    <div class="row hotel">
        <div class="container">
            <div class="row">
                <a href="one-hotel-info?hotelId=<%=result.getHotelId()%>"><h2><%=result.getHotelName()%>
                </h2>
                </a>
            </div>
            <div class="row">
                <img src="image/<%=result.getImageLink()%>" alt="Image couldn't be loaded">
            </div>
        </div>
        <div class="container">
            <dt class="col-3">Rating</dt>
            <dd class="col-9 rate"><%=result.getHotelRate() == null ? "No Reviews Yet" : result.getHotelRate()%>
            </dd>

            <dt class="col-3">Min Price</dt>
            <dd class="col-9 minPrice"><%=result.getMinPrice()%>
            </dd>

            <dt class="col-3">Max Price</dt>
            <dd class="col-9"><%=result.getMaxPrice()%>
            </dd>

            <dt class="col-3">Distance from specified location</dt>
            <dd class="col-9"><%=result.getDistance() >= 1e30 ? "No Hotel Location Provided" : result.getDistance()%>
            </dd>
        </div>
    </div>
    <%}%>
</div>
<script src="scripts/client/list-hotel.js"></script>
</body>
</html>
