function validate(e){

   /** we need the dates to be today or after
    * we need checkin and checkout to be valid both
    * we need checkin smaller than checkout 
    * todo add user messages to invalid inputs
    * 
    */
   let checkIn =new Date(document.getElementById("check-in").value);
   let checkOut = new Date(document.getElementById("check-out").value);
   if(validateEmpty(e)){
       validateCheckIn(checkIn,e);
       validateCheckOut(checkIn,checkOut ,e);
    }
   return;
}
function validateCheckOut(checkIn,checkOut  ,e){
    if(checkIn.getTime()>= checkOut.getTime()){
        const  element = "check-out"
        document.getElementById(element+"-error").textContent = element+" can't be before check in";
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
    return someDate.getDate() == today.getDate() &&
      someDate.getMonth() == today.getMonth() &&
      someDate.getFullYear() == today.getFullYear();
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
const submitBtn = document.getElementById("search-btn");
submitBtn.addEventListener("click" , validate);


// ---- Add Location stuff
function selectPosition(e) {
    document.getElementsByName("lng")[0].value = e.lngLat.lng;
    document.getElementsByName("lat")[0].value = e.lngLat.lat;
}
if (navigator.geolocation)
    navigator.geolocation.getCurrentPosition(function(position){
        initMap(position.coords.longitude, position.coords.latitude, selectPosition);
    });

$('#addLocationModal').on('shown.bs.modal', function() {
    map.invalidateSize();
});