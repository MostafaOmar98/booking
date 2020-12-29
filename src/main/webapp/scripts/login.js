$(function () {
    $("#login").click( function(){
        let params = {
            email: $("#email").val(),
            password: $("#password").val(),
            type: $("input[type='radio'][name='type']:checked").val()
        };

        $.post("login", params, function (data, status) {
            alert("Data: " + data + " \nStatus: " + status);
            console.log(typeof(data));
            console.log(data);
            console.log(data["error"])
        }, "json")
    })
})