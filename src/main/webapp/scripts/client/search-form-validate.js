/// make check out after check in, can't be equal
function validateCheckOut(checkIn, checkOut, e) {
    if (checkIn.getTime() >= checkOut.getTime()) {
        const element = "check-out"
        document.getElementById(element + "-error").textContent = element + " can't be before check in or the same as checkin";
        e.preventDefault();
        return false;
    }
    return true;
}

function validateCheckIn(checkIn, e) {
    if (!(isToday(checkIn) || isAfterToday(checkIn))) {
        const element = "check-in"
        document.getElementById(element + "-error").textContent = element + " can't be before today";
        e.preventDefault();
        return false;
    }
    return true;
}

function isToday(someDate){
    const today = new Date()
    return someDate.getDay() === today.getDay() &&
        someDate.getMonth() === today.getMonth() &&
        someDate.getFullYear() === today.getFullYear();
}

function isAfterToday(input) {
    var today = new Date();

    return input.getTime() > today.getTime();
}

function validateEmpty(e) {
    const arr = ["check-in", "check-out"];
    let ret = true;
    arr.forEach((element) => {
        if (isEmptyById(element)) {
            document.getElementById(element + "-error").textContent = element + " can't be empty";
            e.preventDefault();
            ret = false;
        } else {
            document.getElementById(element + "-error").textContent = '';

        }
    });
    return ret;
}

function isEmptyById(elementId) {
    return document.getElementById(elementId).value.length == 0;
}

const searchBtn = document.getElementById("search-btn");
searchBtn.addEventListener('click', function(e) {
    validateEmpty(e);
    checkIn = new Date(document.getElementById("check-in").value);
    checkOut = new Date(document.getElementById("check-out").value);
    validateCheckIn(checkIn, e);
    validateCheckOut(checkIn, checkOut, e);
})

// ---- Add Location stuff
function selectPosition(e) {
    document.getElementsByName("lng")[0].value = e.lngLat.lng;
    document.getElementsByName("lat")[0].value = e.lngLat.lat;
}

if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) {
        initMap(position.coords.longitude, position.coords.latitude, selectPosition);
        selectPosition({
            lngLat: {
                lng: position.coords.longitude,
                lat: position.coords.latitude
            }
        });
    });
}