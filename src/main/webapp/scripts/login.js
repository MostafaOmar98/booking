$(function () {
    let submitBtn = document.querySelector('input[type="submit"]');
    console.log(submitBtn);
    submitBtn.addEventListener('click', function (e) {
        e.preventDefault();
        let email = document.getElementById("email"), password = document.getElementById("password"),
            type = document.querySelector('input[type="radio"][name="type"]:checked');
        params = {
            email: email.value,
            password: password.value,
            type: type.value
        };


        $.ajax({
            type: "POST",
            url: "validate-credentials",
            data: params,
            success: function (data) {
                if (data["error"] !== undefined) {
                    document.getElementById('loginError').removeAttribute('hidden');
                }
                else {
                    window.location.href = data["redirect"];
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jgXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });

    })
})