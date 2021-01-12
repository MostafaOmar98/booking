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
<script>
    mapboxgl.accessToken = 'pk.eyJ1IjoiYmVraDk4IiwiYSI6ImNranJ2bDIyajJjb2Iyc21qanBxaXNzeHAifQ.39NuO-aTrDV0x5kTlzmXqA';
    let geocoder = new MapboxGeocoder({ // Initialize the geocoder
        accessToken: mapboxgl.accessToken, // Set the access token
        mapboxgl: mapboxgl, // Set the mapbox-gl instance
        marker: false, // Do not use the default marker style
        placeholder: 'Enter location'
    });

    let hotelLngElement = document.getElementsByName("hotelLng")[0];
    let hotelLatElement = document.getElementsByName("hotelLat")[0];

    if (navigator.geolocation) {
        if (hotelLngElement.value === "null")
            navigator.geolocation.getCurrentPosition(initMap);
        else
            initMap({
                coords: {
                    longitude: hotelLngElement.value,
                    latitude: hotelLatElement.value
                }
            });
    } else
        window.alert("Your browser doesn't support geolocation");

    let selectedPositionMarker = null;
    let popup = null;

    function initMap(position) {
        console.log(position);
        let lnglatArray = [position.coords.longitude, position.coords.latitude];

        let map = new mapboxgl.Map({
            container: 'map', // Container ID
            style: 'mapbox://styles/mapbox/streets-v11', // Map style to use
            center: lnglatArray, // Starting position [lng, lat]
            zoom: 18, // Starting zoom level
        });
        map.on('load', function () {
            selectedPositionMarker = new mapboxgl.Marker();
            selectedPositionMarker.setLngLat(lnglatArray);
            selectedPositionMarker.addTo(map);

            popup = new mapboxgl.Popup({
                closeOnClick: false,
                closeOnMove: false,
            });
            map.addControl(geocoder);
            map.on('click', selectPosition)
        });
    }

    function selectPosition(e) {
        console.log(e);
        selectedPositionMarker.setLngLat(e.lngLat);
        hotelLngElement.value = e.lngLat.lng;
        hotelLatElement.value = e.lngLat.lat;
        let mapboxClient = mapboxSdk({accessToken: mapboxgl.accessToken});
        let geocodingClient = mapboxClient.geocoding;
        geocodingClient.reverseGeocode({
            query: [e.lngLat.lng, e.lngLat.lat]
        }).send().then(response => {
            // GeoJSON document with geocoding matches
            const match = response.body;
            console.log(match);
            let address = match.features[1].place_name;
            popup.setHTML("<b>" + address + "<b>");
            selectedPositionMarker.setPopup(popup);
            selectedPositionMarker.togglePopup();
        });
    }

    document.getElementById("updateLocationBtn").addEventListener('click', function(){
        params ={
            hotelId: document.getElementById("hotelId").value,
            longitude: hotelLngElement.value,
            latitude: hotelLatElement.value
        };
        $.post("update-hotel", params, function(data, status){
            document.getElementById("successSpan").removeAttribute("hidden");
        })
    })

</script>

</html>