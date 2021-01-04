$(function () {
    $("#login").click( function(){
        // TODO: frontend check on emptiness and selecting
        let params = {
            email: $("#email").val(),
            password: $("#password").val(),
            type: $("input[type='radio'][name='type']:checked").val()
        };

        // TODO: Error Highlighting
        $.post("validate-credentials", params, function (data, status) {
            if (data["email_error"] !== undefined) {
                // TODO: Show error
            }
            else
                window.location.href = data["redirect"];
        }, "json")
    })
})