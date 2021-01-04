function validate(e){

   /** we need the dates to be today or after
    * we need checkin and checkout to be valid both
    * we need checkin smaller than checkout 
    * todo add user messages to invalid inputs
    * 
    */
   let checkIn =new Date(document.getElementById("check-in").value);
   let checkOut = new Date(document.getElementById("check-out").value);

    if(!isValidDate(checkIn) || !isValidDate(checkOut) ||!(isAfterToday(checkIn) || isToday(checkIn))){
        e.preventDefault();
        return;
    }
    if(checkIn.getTime() >= checkOut.getTime())
        e.preventDefault();
    // return checkIn.getTime() >= checkOut.getTime();
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
const submitBtn = document.getElementById("search-btn");
submitBtn.addEventListener("click" , validate);