$(function () {
    const hotelId = $("#hotelId").val();
    const changeNameBtn = $("#changeNameBtn");
    changeNameBtn.click(function () {
        const nameInput = $("#name");
        if (nameInput.attr("disabled") !== undefined) {
            nameInput.removeAttr("disabled");
            changeNameBtn.html("Done");
        } else {
            // TODO: Frontend validation
            nameInput.attr("disabled", '');
            changeNameBtn.html("Change Name");
            params = {
                hotelId: hotelId,
                name: nameInput.val()
            };
            console.log(params);
            $.post("update-hotel", params, function(data, status){
                // TODO: Show Errors?
                console.log(data);
                console.log(status);
            })
        }
    })
})