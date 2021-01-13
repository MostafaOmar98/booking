hotelLatElement = document.getElementById("lat");
hotelLngElement = document.getElementById("lng");

if (hotelLatElement.value === "null") {
    document.getElementById("locError").removeAttribute("hidden");
    document.getElementById("map-container").style.display = 'none';
}
else {
    initMap(hotelLngElement.value, hotelLatElement.value, null, true);
}
