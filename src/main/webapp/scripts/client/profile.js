$( function (){


    let displayName = document.getElementById("display-name");
    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let submitBtn =document.getElementById("edit-profile");
    submitBtn.addEventListener("click",function (e){
        params= {"name": displayName.value,"email": email.value, "phone": phone.value}
        $.ajax({
            url:"update-profile",
            data: params,
            type:"POST",
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

    })
});
