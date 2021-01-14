$( function (){


    let displayName = document.getElementById("display-name");
    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let submitBtn =document.getElementById("edit-profile");
    submitBtn.addEventListener("click",function (e){
        e.preventDefault();
        if(!validateEmpty(e)){
            return;
        }

        if(!validateEmail(email.value)){
            return
        }
        params= {name: displayName.value,email: email.value, phone: phone.value};
        $.ajax({
            type:"POST",
            url:"update-profile",
            data: params,
            success:function (data) {
                document.getElementById("success").className += " alert alert-success";
                document.getElementById('update-status').innerText = data["status"];
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jgXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        })

    });
});
function validateEmpty(e){
    const arr= ["display-name", "email", "phone"];
    let ret = true;
    arr.forEach((element)=>{
        if(isEmptyById(element)){
            addAlert(document, element, "can't be empty");
            e.preventDefault();
            ret = false;
        }
        else{
           removeAlert(document,element);

        }
    });
    return ret;
}
function isEmptyById(elementId){
    return document.getElementById(elementId).value.length == 0;
}
function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!re.test(String(email).toLowerCase())){

        addAlert(document,"email", "invalid Email");
        return false;
    }
    else{
        removeAlert(document,"element");
        return true;
    }
}
 function addAlert(document,element, message){
    document.getElementById(element+"-error").textContent = message;
    document.getElementById(element + "-error-span").classList.add("alert");
    document.getElementById(element + "-error-span").classList.add("alert-danger");

}
 function removeAlert(document,element){
    document.getElementById(element+"-error").textContent = '';
    document.getElementById(element+'-error').classList.remove("alert");
    document.getElementById(element+'-error').classList.remove("alert-danger");
}
