<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Hotel Location</title>
</head>
<body>
<div id="mapid" style="height: 600px;">

</div>
</body>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
      integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
      crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
        integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
        crossorigin=""></script>
<script>

    let map = L.map('mapid');

    function getLocation() {
        if (navigator.geolocation) {
            console.log("OK");
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            window.alert("Your browser does not support geolocation");
        }
    }


    function showPosition(position) {
        map.setView([position.coords.latitude, position.coords.longitude], 13);
        // map.setView([51.505, -0.09], 13);
        L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            maxZoom: 18,
            id: 'mapbox/streets-v11',
            tileSize: 512,
            zoomOffset: -1,
            accessToken: 'pk.eyJ1IjoiYmVraDk4IiwiYSI6ImNranJ2bDIyajJjb2Iyc21qanBxaXNzeHAifQ.39NuO-aTrDV0x5kTlzmXqA'
        }).addTo(map);
    }

    getLocation()
</script>
</body>
</html>