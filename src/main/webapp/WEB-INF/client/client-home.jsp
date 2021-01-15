<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Hotels Search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
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
<%@include file="include/header-navbar.jsp" %>
<div class="container-fluid">
    <form action="search-hotel" method="GET">
        <div class="form-group row">
            <div id="map-container">
                <div id="map">
                </div>
            </div>
            <input type="hidden" name="lng">
            <input type="hidden" name="lat">
        </div>
        <div class="form-group row">
            <label for="check-in" class="col-sm-2 col-form-label">Check In </label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="check-in" name="checkIn">
                <span class="error">
          <p id="check-in-error"></p>
        </span>
            </div>
        </div>
        <div class="form-group row">
            <label for="check-out" class="col-sm-2 col-form-label">Checkout</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="check-out" name="checkOut">
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
                <legend class="col-form-label col-sm-2 pt-0">Number of Children</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="number" name="n-children" id="n-children" value="0"
                               min="0"
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
</div>
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
<script src="${pageContext.request.contextPath}/scripts/client/search-form-validate.js"></script>
</body>

</html>