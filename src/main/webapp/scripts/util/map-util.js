mapboxgl.accessToken = 'pk.eyJ1IjoiYmVraDk4IiwiYSI6ImNranJ2bDIyajJjb2Iyc21qanBxaXNzeHAifQ.39NuO-aTrDV0x5kTlzmXqA';

let selectedPositionMarker = null;
let map = null;

function initMap(lng, lat, selectCallback, blockDefault=false) {
    // initializing map
    map = new mapboxgl.Map({
        container: 'map', // Container ID
        style: 'mapbox://styles/mapbox/streets-v11', // Map style to use
        center: [lng, lat], // Starting position [lng, lat]
        zoom: 18, // Starting zoom level
    });

    map.on('load', function () {
        // Setting up marker on selected position
        selectedPositionMarker = addMarker(lng, lat);

        // setting up popup on selectedPositionMarker
        showAddressPopupOnMarker(selectedPositionMarker);

        // Adding geocoder (search) control to map
        let geocoder = new MapboxGeocoder({ // Initialize the geocoder
            accessToken: mapboxgl.accessToken, // Set the access token
            mapboxgl: mapboxgl, // Set the mapbox-gl instance
            marker: false, // Do not use the default marker style
            placeholder: 'Enter location'
        });
        map.addControl(geocoder);
        // console.log(e);

        // Adding the onclick event
        map.on('click', function(e){
            // console.log(e);
            if (!blockDefault)
                selectPositionDefault(e);
            if (selectCallback != null)
                selectCallback(e);
        });
    });
}

/**
 *
 * @param marker: mapboxgl.Marker
 */
function showAddressPopupOnMarker(marker) {
    selectedPositionPopup = new mapboxgl.Popup({
        closeOnClick: false,
        closeOnMove: false,
    });

    let mapboxClient = mapboxSdk({accessToken: mapboxgl.accessToken});
    let geocodingClient = mapboxClient.geocoding;
    geocodingClient.reverseGeocode({
        query: [marker.getLngLat().lng, marker.getLngLat().lat]
    }).send().then(response => {
        const match = response.body;
        let address = match.features[1].place_name;
        console.log(address);
        selectedPositionPopup.setHTML("<b>" + address + "<b>");
        selectedPositionMarker.setPopup(selectedPositionPopup);
        selectedPositionMarker.togglePopup();
    });
}

function addMarker(lng, lat) {
    marker = new mapboxgl.Marker();
    marker.setLngLat([lng, lat]);
    marker.addTo(map);
    return marker;
}

function selectPositionDefault(e) {
    // Changing Marker Position
    selectedPositionMarker.setLngLat(e.lngLat);
    showAddressPopupOnMarker(selectedPositionMarker);
}