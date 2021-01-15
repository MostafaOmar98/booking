

$(function (){
    let submitBtn = document.getElementById("change-password-btn");
    submitBtn.addEventListener("click" , function (e){
        e.preventDefault();
        let password1 = document.getElementById("password1").value;
        let password2 = document.getElementById("password2").value;
        if(!validateEmpty(e)){
            return;
        }
        if(!checkEquality("password-retype-error",password1, password2)){
            return;
        }
        let params={
           password: password1
        };
        $.ajax({
            type:"POST",
            url: "update-password",
            data: params,
            success: function (data) {
                if (data["success"] === "false") {
                    console.log("received nothing");
                    document.getElementById("success").className += " alert alert-danger";
                    document.getElementById('update-status').innerText = data["status"];
                    return;
                }
                else {
                    window.location.href = "index.jsp";
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
function checkEquality(errorId,password1, password2){
    if(password1 === password2){
        document.getElementById(errorId).innerText='';
        document.getElementById(errorId).classList.remove("alert");
        document.getElementById(errorId).classList.remove("alert-danger");
        return true;
    }
    else{
        document.getElementById(errorId).innerText = "the passwords are not identical";
        document.getElementById(errorId).classList.add("alert");
        document.getElementById(errorId).classList.add("alert-danger");
        return;
        return false;
    }
}
function validateEmpty(e){
    const arr= ["password1", "password2"];
    let ret = true;
    arr.forEach((element)=>{
        if(isEmptyById(element)){
            document.getElementById(element+"-error").textContent = "can't be empty";
            let dangerClasses = " alert alert-danger";
            document.getElementById(element + "-error-span").classList.add("alert");
            document.getElementById(element + "-error-span").classList.add("alert-danger");
            e.preventDefault();
            ret = false;
        }
        else{
            document.getElementById(element+"-error").textContent = '';
            document.getElementById(element+'-error').classList.remove("alert");
            document.getElementById(element+'-error').classList.remove("alert-danger");

        }
    });
    return ret;
}
function isEmptyById(elementId){
    return document.getElementById(elementId).value.length == 0;
}
function enableSubmit(){
    document.getElementById("change-password-btn").hidden = false;
}