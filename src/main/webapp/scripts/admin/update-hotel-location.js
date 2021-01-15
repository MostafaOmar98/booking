let hotelLngElement = document.getElementsByName("hotelLng")[0];
let hotelLatElement = document.getElementsByName("hotelLat")[0];
let succ = document.getElementById("successSpan");
function selectPosition(e) {
    // console.log(e);
    hotelLngElement.value = e.lngLat.lng;
    hotelLatElement.value = e.lngLat.lat;
    succ.setAttribute('hidden', '');
}

if (navigator.geolocation) {
    if (hotelLngElement.value === "null")
        navigator.geolocation.getCurrentPosition(function(position){
            initMap(position.coords.longitude, position.coords.latitude, selectPosition);
        });
    else
        initMap(hotelLngElement.value, hotelLatElement.value, selectPosition);
} else
    window.alert("Your browser doesn't support geolocation");


document.getElementById("updateLocationBtn").addEventListener('click', function () {
    params = {
        hotelId: document.getElementById("hotelId").value,
        longitude: hotelLngElement.value,
        latitude: hotelLatElement.value
    };
    $.post("update-hotel", params, function (data, status) {
        document.getElementById("successSpan").removeAttribute("hidden");
    })
})