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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
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
<input type="hidden" id="hotelId" value="<%=hotel.getHotelId()%>">

<div class="container">
    <dt class="col-3">Name:</dt>
    <dd class="col-9"><%=hotel.getName()%>
    </dd>

    <dt class="col-3">Phone:</dt>
    <dd class="col-9"><%=hotel.getPhone()%>
    </dd>
</div>

<div class="form-group row">
    <label for="checkIn" class="col-sm-2 col-form-label">Check In </label>
    <div class="col-sm-10">
        <input type="date" class="form-control" id="checkIn" name="checkIn" value="<%=request.getParameter("checkIn")%>"
               disabled>
        <span class="error">
          <p id="check-in-error"></p>
        </span>
    </div>
</div>
<div class="form-group row">
    <label for="checkOut" class="col-sm-2 col-form-label">Checkout</label>
    <div class="col-sm-10">
        <input type="date" class="form-control" id="checkOut" name="checkOut"
               value="<%=request.getParameter("checkOut")%>" disabled>
        <span class="error">
          <p id="check-out-error"></p>
        </span>
    </div>
</div>
<fieldset class="form-group">
    <div class="row">
        <legend class="col-form-label col-sm-2 pt-0">Number of Adults</legend>
        <div class="col-sm-10">
            <div class="form-check">
                <input type="number" name="n-adults" id="n-adults" value="<%=request.getParameter("n-adults")%>" min="0"
                       max="10" disabled>
                <%--                <input type="text">--%>
            </div>
        </div>
    </div>
    <div class="row">
        <legend class="col-form-label col-sm-2 pt-0">Number of Children</legend>
        <div class="col-sm-10">
            <div class="form-check">
                <input type="number" name="n-children" id="n-children" value="<%=request.getParameter("n-children")%>"
                       min="0" max="10" disabled>
            </div>
        </div>
    </div>
</fieldset>


<div class="container">
    <header>Rooms</header>
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
<br><br>
<h2>Hotel Images</h2>
<div class="container">
    <div class="row">
        <%
            for (int i = 0; i < images.size(); ++i) {
                out.println(
                        "<div class=\"col-4\">\n" +
                                "<img src=\"image/" + images.get(i).getName() + "\" style=\"width:100%\" class=\"img\" imageId=\"" + images.get(i).getImageId() + "\">\n" +
                                "</div>");
            }
        %>
    </div>
</div>

<input type="hidden" id="lng" value="<%=hotel.getLongitude()%>">
<input type="hidden" id="lat" value="<%=hotel.getLatitude()%>">
<div id="map-container">
    <div id="map">
    </div>
</div>
<span id="locError" hidden><b>This hotel doesn't have location information</b></span><br>

<a href="hotel-reviews?hotelId=<%=hotel.getHotelId()%>">View and Add Reviews</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="scripts/util/map-util.js"></script>
<script src="scripts/client/one-hotel-info.js"></script>
</body>
</html>