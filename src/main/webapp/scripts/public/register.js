$( function (){


    let username = document.getElementById("username");
    let email = document.getElementById("email");
    console.log(username)
    console.log(email)
    let type = document.querySelector('input[type="radio"][name="type"]:checked');
    // console.log(type);
    let submitBtn =document.getElementById("register");
    submitBtn.addEventListener("click",function (e){
        e.preventDefault();
        if(!validateEmpty(e)){
            return;
        }

        if(!validateEmail(email.value)){
            return
        }
        let params = {username: username.value,email: email.value
            , type: type.value
        };
        $.ajax({
            type:"POST",
            url:"register-user",
            data: params,
            success: function (data) {
                if (data["success"] === "false") {
                    console.log("received nothing");
                    document.getElementById("success").className += " alert alert-danger";
                    document.getElementById('update-status').innerText = data["status"];
                    return;
                }
                else {
                    console.log(data);
                    document.getElementById("success").className += " alert alert-success";
                    document.getElementById('update-status').innerText = data["status"];
                }
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
    const arr= ["username", "email"];
    let ret = true;
    arr.forEach((element)=>{
        if(isEmptyById(element)){
            addAlert(element, element.toUpperCase()+" can't be empty");
            e.preventDefault();
            ret = false;
        }
        else{
            removeAlert(element);

        }
    });
    return ret;
}
function isEmptyById(elementId){
    return document.getElementById(elementId).value.length === 0;
}
function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!re.test(String(email).toLowerCase())){

        addAlert("email", "invalid Email");
        return false;
    }
    else{
        removeAlert("email");
        return true;
    }
}
function addAlert(element, message){
    document.getElementById(element+"-error").textContent = message;
    document.getElementById(element + "-error-span").classList.add("alert");
    document.getElementById(element + "-error-span").classList.add("alert-danger");

}
function removeAlert(element){
    document.getElementById(element+"-error").textContent = '';
    document.getElementById(element+'-error').classList.remove("alert");
    document.getElementById(element+'-error').classList.remove("alert-danger");
}
