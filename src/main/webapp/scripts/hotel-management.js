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


    // Room Update
    const roomBtns = document.querySelectorAll('.roomBtn');
    for (var i = 0; i < roomBtns.length; ++i) {
        roomBtns[i].addEventListener('click', updateRoomBtnClicked);
    }

    /**
    @param {Event} e
     */
    function updateRoomBtnClicked(e) {
        let btn = e.target;
        let cur = btn.parentElement;
        if (btn.innerText === 'Update') {
            while (true) {
                cur = cur.previousElementSibling;
                if (cur === undefined || cur === null) {
                    console.log(cur);
                    break;
                }
                let inp = cur.firstElementChild;
                console.log(inp);
                inp.removeAttribute('disabled');
            }
            btn.innerText = 'Done';
        }
        else {
            btn.innerText = 'Update';
            let params = {};
            while (true) {
                cur = cur.previousElementSibling;
                if (cur === null) {
                    // console.log(cur);
                    break;
                }
                let inp = cur.firstElementChild;
                params[inp.name] = inp.value;
                // console.log(inp);
                inp.setAttribute('disabled', '');
            }
            // console.log(params);
            $.post("update-room", params, function(data, status){
                // TODO: show errors
                console.log(data);
            });
        }

    }

})