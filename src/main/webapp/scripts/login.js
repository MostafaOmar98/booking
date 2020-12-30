$(function () {
    $("#login").click( function(){
        let params = {
            email: $("#email").val(),
            password: $("#password").val(),
            type: $("input[type='radio'][name='type']:checked").val()
        };

        // TODO: Error Highlighting
        $.post("validate-credentials", params, function (data, status) {
            if (data["error"] === "ok") {
                window.location.href = data["redirect"];
            }
        }, "json")
    })
})