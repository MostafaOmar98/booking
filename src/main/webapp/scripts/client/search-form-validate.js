function validate(e){
    e.preventDefault();
   /** we need the dates to be today or after
    * we need checkin and checkout to be valid both
    * we need checkin smaller than checkout 
    * todo add user messages to invalid inputs
    * 
    */
   let checkIn = document.getElementById("check-in").value;
   let checkOut = document.getElementById("check-out").value;
   let params ={
       checkIn: checkIn,
       checkOut: checkOut
   }
   console.log(params);
   $.ajax({
        type:"POST",
        url:"search-hotels",
        data: params,
        success: function (data) {
            if (data["success"] === "false") {

                document.getElementById("success").className += " alert alert-danger";
                document.getElementById('update-status').innerText = data["status"];
                return;
            }
            else {
                // window.location.href = data["redirect"];
                console.log(data);
                document.getElementById("success").className += " alert alert-success";
                document.getElementById('update-status').innerText = data["status"];
                //if we have valid input we submit the form
                $('#search-form').submit();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jgXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    })

   //todo check what to do with front end validation
   // if(validateEmpty(e)){
   //     validateCheckIn(checkIn,e);
   //     validateCheckOut(checkIn,checkOut ,e);
   //  }
   // return;
}
/// make check out after check in, can't be equal
function validateCheckOut(checkIn,checkOut  ,e){
    if(checkIn.getTime()> checkOut.getTime()){
        const  element = "check-out"
        document.getElementById(element+"-error").textContent = element+" can't be before check in or the same as checkin";
        e.preventDefault();
        return false;
    }
    return true;
}
function validateCheckIn(checkIn,e){
    if(!(isToday(checkIn) || isAfterToday(checkIn))){
        const  element = "check-in"
        document.getElementById(element+"-error").textContent = element+" can't be before today";
        e.preventDefault();
        return false;
    }
    return true;
}
const isToday = (someDate) => {
    const today = new Date()
    return someDate.getDate() === today.getDate() &&
      someDate.getMonth() === today.getMonth() &&
      someDate.getFullYear() === today.getFullYear();
}
function isAfterToday(input ){
    var today = new Date();

    return input.getTime()>= today.getTime() ;
}
function isValidDate(d) {
    return d instanceof Date && !isNaN(d);
}
function validateEmpty(e){
    const arr= ["address", "check-in", "check-out"];
    let ret = true;
    arr.forEach((element)=>{
        if(isEmptyById(element)){
            document.getElementById(element+"-error").textContent = element+" can't be empty";
            e.preventDefault();
            ret = false;
        }
        else{
            document.getElementById(element+"-error").textContent = '';

        }
    });
    return ret;
}
function isEmptyById(elementId){
    return document.getElementById(elementId).value.length == 0;
}
$( function (){

    const submitBtn = document.getElementById("search-btn");
    submitBtn.addEventListener("click" , validate);
} );


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

$('#addLocationModal').on('shown.bs.modal', function() {
    map.invalidateSize();
});