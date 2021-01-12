<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <title>Local search app</title>
    <meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no'/>
    <script src='https://api.mapbox.com/mapbox-gl-js/v2.0.1/mapbox-gl.js'></script>
    <link href='https://api.mapbox.com/mapbox-gl-js/v2.0.1/mapbox-gl.css' rel='stylesheet'/>
    <script src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.2.0/mapbox-gl-geocoder.min.js'></script>
    <link rel='stylesheet'
          href='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.2.0/mapbox-gl-geocoder.css'
          type='text/css'/>
    <script src="https://unpkg.com/@mapbox/mapbox-sdk/umd/mapbox-sdk.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        #map {
            position: absolute;
            top: 0;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

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

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(initMap);
    } else
        window.alert("Your browser doesn't support geolocation");

    let selectedPositionMarker = null;
    let popup = null;

    function initMap(position) {
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

    function getAddress(lng, lat) {

    }

    function selectPosition(e) {
        console.log(e);
        selectedPositionMarker.setLngLat(e.lngLat);
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

</script>

</html>