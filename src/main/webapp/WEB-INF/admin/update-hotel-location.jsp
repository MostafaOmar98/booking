<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
    request.setAttribute("hotelId", request.getParameter("hotelId")); // for navbar
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='utf-8'/>
    <title>Update Hotel Location</title>
    <meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no'/>
    <script src='https://api.mapbox.com/mapbox-gl-js/v2.0.1/mapbox-gl.js'></script>
    <link href='https://api.mapbox.com/mapbox-gl-js/v2.0.1/mapbox-gl.css' rel='stylesheet'/>
    <script src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.2.0/mapbox-gl-geocoder.min.js'></script>
    <link rel='stylesheet'
          href='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.2.0/mapbox-gl-geocoder.css'
          type='text/css'/>
    <script src="https://unpkg.com/@mapbox/mapbox-sdk/umd/mapbox-sdk.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="style/main.css" rel="stylesheet">
</head>

<style>
    body {
        margin: 0;
        padding: 0;
    }

    #map {
        position: absolute;
        top: 13em;
        height: 80%;
        bottom: 0;
        width: 100%;
    }
</style>
<body>
<%@include file="include/header-navbar.jsp"%>
<input type="hidden" name="hotelLng" value="<%=hotel.getLongitude()%>">
<input type="hidden" name="hotelLat" value="<%=hotel.getLatitude()%>">
<input type="hidden" id="hotelId" value="<%=hotel.getHotelId()%>">
<div class="container-fluid">
    <div class="row">
        <button id="updateLocationBtn" class="btn btn-primary">Update Location</button>
    </div>
    <div class="row">
        <span id="successSpan" class="text-success" hidden>Location Updated</span>
    </div>
</div>
<div id='map'></div>
</body>
<%--Popper--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<%--Bootstrap js--%>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script src="scripts/util/map-util.js"></script>
<script src="scripts/admin/update-hotel-location.js"></script>
</html>