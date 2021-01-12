

$(function (){
    let submitBtn = document.getElementById("change-password-btn");
    submitBtn.addEventListener("click" , function (e){
        e.preventDefault();
        let password1 = document.getElementById("password1");
        let password2 = document.getElementById("password2");
        if(!checkEquality(password1, password2)){
            document.getElementById("password-retype-error").innerText = "the passwords are not identical";
            return;
        }
        params={
           newPassword: password1
        }
        $.ajax({
            type:"POST",
            url: "update-password",
            params: params,
            success: function (data) {
                if (data["error"] !== undefined) {

                    console.log("received nothing");
                    return;
                }
                else {
                    // window.location.href = data["redirect"];
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
function checkEquality(password1, password2){
    return password1 === password2;
}
function enableSubmit(){
    document.getElementById("change-password-btn").hidden = false;
}