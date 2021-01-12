<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %>
<%-- Created by IntelliJ IDEA. User: ahmeddrawy Date: 1/4/2021 Time: 2:36 PM To change this template use File |
    Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Hotels Search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
</head>

<body>
<!-- <form onsubmit="return validate()"> -->
<%--  todo add /list url --%>
<form action="search-hotel" method="GET">
    <div class="form-group row">
        <div id="map-container">
            <div id="map">
            </div>
        </div>
        <%--      <label for="inputEmail3" class="col-sm-2 col-form-label">Address </label>--%>
        <%--      <div class="col-sm-4">--%>
        <%--        <input type="text"  class="form-control" id="address" name="location"placeholder="Cairo" >--%>
        <%--        <span class="error">--%>
        <%--          <p id="address-error"></p>--%>
        <%--        </span>--%>
        <%--      </div>--%>
        <input type="hidden" name="lng">
        <input type="hidden" name="lat">
    </div>
    <div class="form-group row">
        <label for="inputEmail3" class="col-sm-2 col-form-label">Check In </label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="check-in">
            <span class="error">
          <p id="check-in-error"></p>
        </span>
        </div>
    </div>
    <div class="form-group row">
        <label for="inputPassword3" class="col-sm-2 col-form-label">Checkout</label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="check-out">
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
                    <input class="form-check-input" type="number" name="n-adults" id="n-adults" value="0" min="0"
                           max="10">
                </div>
            </div>
        </div>
        <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Number of Adults</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input class="form-check-input" type="number" name="n-children" id="n-children" value="0" min="0"
                           max="10">
                </div>
            </div>
        </div>
    </fieldset>

    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" id="search-btn" class="btn btn-primary">Search</button>
        </div>
    </div>
</form>
<script src="scripts/util/map-util.js"></script>
<script src="${pageContext.request.contextPath}/scripts/client/search-form-validate.js"></script>
</body>

</html>