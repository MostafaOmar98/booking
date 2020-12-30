$(function () {
    const hotelId = $("#hotelId").val();

    $("#changeNameBtn").click(function(){
        buttonInputClick($("#changeNameBtn"), $("#name"));
    })

    $("#changePhoneBtn").click(function(){
        buttonInputClick($("#changePhoneBtn"), $("#phone"));
    })

    function buttonInputClick(btn, inp) {
            if (inp.attr("disabled") !== undefined) {
                // allow change
                inp.removeAttr("disabled");
                btn.html("Done");
            } else {
                // TODO: Frontend validation
                inp.attr("disabled", ''); // disallow  change
                btn.html("Change");
                params = {
                    hotelId: hotelId,
                };
                params[inp.attr("name")] = inp.val();

                console.log(params);
                $.post("update-hotel", params, function (data, status) {
                    // TODO: Show Errors?
                    console.log(data);
                    console.log(status);
                })
            }
    }

})