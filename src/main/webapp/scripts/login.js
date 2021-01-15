$(function () {
    let submitBtn = document.querySelector('#login-submit');
    submitBtn.addEventListener('click', function (e) {
        e.preventDefault();
        let username = document.getElementById("username"), password = document.getElementById("password"),
            type = document.querySelector('input[type="radio"][name="type"]:checked');
        let params = {
            username: username.value,
            password: password.value,
            type: type.value
        };

        console.log(params);
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