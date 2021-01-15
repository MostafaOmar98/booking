<%@ page import="java.util.List" %>
<%@ page import="com.hagz_hotels.hotels_booking.Business.DTO.HotelSearchResultDTO" %>
<%
    List<HotelSearchResultDTO> results = (List<HotelSearchResultDTO>) request.getAttribute("results");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
    <title>Search Results</title>
</head>
<body>
<%@include file="include/header-navbar.jsp" %>
<div class="container-fluid">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="maxPrice">Set Max Price</label>
        <input class="form-control col-sm-10" type="number" min="0" step="0.01" placeholder="Max Price" id="maxPrice">
    </div>
</div>

<div class="container-fluid">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="minRate">Set Min Rate</label>
        <input class="form-control col-sm-10" type="number" min="0" step="0.01" placeholder="Min Rate" id="minRate">
    </div>
</div>

<%
    for (HotelSearchResultDTO result : results) {
%>
<hr/>
<div class="container-fluid mb-5 hotel">
    <div class="row">
        <a href="one-hotel-info?hotelId=<%=result.getHotelId()%>&n-adults=<%=request.getParameter("n-adults")%>&n-children=<%=request.getParameter("n-children")%>&checkIn=<%=request.getParameter("checkIn")%>&checkOut=<%=request.getParameter("checkOut")%>">
            <h2><%=result.getHotelName()%>
            </h2>
        </a>
    </div>
    <div class="row">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-8">
                    <img class="img-fluid" src="image/<%=result.getImageLink()%>" alt="Image couldn't be loaded">
                </div>
                <div class="col-sm-4">
                    <div class="container-fluid">
                        <div class="row">
                            <dt class="col-3">Rating</dt>
                            <dd class="col-9 rate"><%=result.getHotelRate() == null ? "No Reviews Yet" : result.getHotelRate()%>
                            </dd>
                        </div>

                        <div class="row">
                            <dt class="col-3">Min Price</dt>
                            <dd class="col-9 minPrice"><%=result.getMinPrice()%>
                            </dd>
                        </div>

                        <div class="row">
                            <dt class="col-3">Max Price</dt>
                            <dd class="col-9"><%=result.getMaxPrice()%>
                            </dd>
                        </div>

                        <div class="row">
                            <dt class="col-3">Distance from specified location</dt>
                            <dd class="col-9"><%=result.getDistance() >= 1e30 ? "No Hotel Location Provided" : result.getDistance() + " meters"%>
                            </dd>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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
<script src="scripts/client/list-hotel.js"></script>
</body>
</html>
