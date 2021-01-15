<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.HotelImage" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
    ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
    ArrayList<HotelImage> images = (ArrayList<HotelImage>) request.getAttribute("images");
%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
    <script src='https://api.mapbox.com/mapbox-gl-js/v2.0.1/mapbox-gl.js'></script>
    <link href='https://api.mapbox.com/mapbox-gl-js/v2.0.1/mapbox-gl.css' rel='stylesheet'/>
    <script src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.2.0/mapbox-gl-geocoder.min.js'></script>
    <link rel='stylesheet'
          href='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.2.0/mapbox-gl-geocoder.css'
          type='text/css'/>
    <script src="https://unpkg.com/@mapbox/mapbox-sdk/umd/mapbox-sdk.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        #map-container {
            position: relative;
            height: 600px;
            width: 600px;
        }

        #map {
            position: relative;
            height: inherit;
            width: inherit;
        }
    </style>
    <title>Hotel Info</title>
</head>
<body>
<%@include file="include/header-navbar.jsp" %>
<input type="hidden" id="hotelId" value="<%=hotel.getHotelId()%>">

<div class="container-fluid" id="basicInfoContainer">
    <div class="row mb-2" id="nameDiv">
        <label class="col-sm-6 col-form-label" for="name"><b>Name</b></label>
        <div class="col-sm-6 input-group">
            <input class="form-control" type="text" name="name" id="name" value="<%=hotel.getName()%>" required
                   disabled>
        </div>
    </div>
    <div class="row mb-2" id="phoneDiv">
        <label class="col-sm-6 col-form-label" for="phone"><b>Phone</b></label>
        <div class="col-sm-6 input-group">
            <input class="form-control" type="text" name="phone" id="phone" value="<%=hotel.getPhone()%>" disabled>
        </div>
    </div>
</div>
<hr/>
<div class="container-fluid mt-5">
    <input type="hidden" id="lng" value="<%=hotel.getLongitude()%>">
    <input type="hidden" id="lat" value="<%=hotel.getLatitude()%>">
    <div id="map-container">
        <div id="map">
        </div>
    </div>
    <span id="locError" hidden><b>This hotel doesn't have location information</b></span><br>
</div>
<hr/>
<div class="container-fluid mt-5">
    <div class="row mb-2">
        <label for="checkIn" class="col-sm-2 col-form-label">Check In </label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="checkIn" name="checkIn"
                   value="<%=request.getParameter("checkIn")%>"
                   disabled>
        </div>
    </div>
    <div class="row mb-2">
        <label for="check-in" class="col-sm-2 col-form-label">Check In </label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="check-in" name="checkIn" disabled>
        </div>
    </div>
    <div class="row mb-2">
        <label for="check-out" class="col-sm-2 col-form-label">Checkout</label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="check-out" name="checkOut" disabled>
        </div>
    </div>
    <fieldset class="form-group">
        <div class="row mb-2">
            <legend class="col-form-label col-sm-2 pt-0">Number of Adults</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input class="form-check-input" type="number" name="n-adults" id="n-adults" value="0" min="0"
                           max="10" disabled>
                </div>
            </div>
        </div>
        <div class="row mb-2">
            <legend class="col-form-label col-sm-2 pt-0">Number of Children</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input class="form-check-input" type="number" name="n-children" id="n-children" value="0"
                           min="0"
                           max="10" disabled>
                </div>
            </div>
        </div>
    </fieldset>
</div>
<hr/>
<div class="container-fluid mt-5">
    <h2>Rooms Available For Your Reservation Input</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Type</th>
            <th>Max Adults</th>
            <th>Max Children</th>
            <th>Price Per Night</th>
            <th>Facilities</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Room r : rooms) {
        %>
        <tr>
            <td><%=r.getType()%>
            </td>
            <td><%=r.getMaxAdults()%>
            </td>
            <td><%=r.getMaxChildren()%>
            </td>
            <td><%=r.getPricePerNight()%>
            </td>
            <td><%=r.getFacilities()%>
            </td>
            <td>
                <form action="reserve-room" method="post">
                    <input type="hidden" name="roomId" value="<%=r.getRoomId()%>">
                    <input type="hidden" name="checkIn" value="<%=request.getParameter("checkIn")%>">
                    <input type="hidden" name="checkOut" value="<%=request.getParameter("checkOut")%>">
                    <input type="submit" value="Reserve">
                </form>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>


</div>
<hr/>
<div class="container-fluid mt-5" id="Images">
    <h2>Images</h2>
    <div class="row">
        <% for (HotelImage image : images) {%>
        <div class="col-sm-4">
            <img src="image/<%=image.getName()%>" class="img img-fluid m-1" imageId="<%=image.getImageId()%>"
                 alt="Couldn't load image">
        </div>
        <%}%>
    </div>
</div>
<hr/>
<a class="mt-5" href="hotel-reviews?hotelId=<%=hotel.getHotelId()%>"><h2>View Hotel Reviews</h2></a>

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
<script src="scripts/util/map-util.js"></script>
<script src="scripts/client/one-hotel-info.js"></script>
</body>
</html>