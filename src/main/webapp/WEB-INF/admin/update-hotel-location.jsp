<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
%>
<!DOCTYPE html>
<html>
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
</head>

<style>
    body {
        margin: 0;
        padding: 0;
    }

    #map {
        position: absolute;
        top: 100px;
        height: 80%;
        bottom: 0;
        width: 100%;
    }
</style>
<body>
<input type="hidden" name="hotelLng" value="<%=hotel.getLongitude()%>">
<input type="hidden" name="hotelLat" value="<%=hotel.getLatitude()%>">
<input type="hidden" id="hotelId" value="<%=hotel.getHotelId()%>">
<button id="updateLocationBtn">Update Location</button>
<span id="successSpan" hidden>Location Updated</span>
<div id='map'></div>
</body>
<script src="scripts/util/map-util.js"></script>
<script src="scripts/admin/update-hotel-location.js"></script>
</html>