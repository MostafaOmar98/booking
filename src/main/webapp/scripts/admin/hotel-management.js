$(function () {
    const hotelId = $("#hotelId").val();

    $("#changeNameBtn").click(function () {
        buttonInputClick($("#changeNameBtn"), $("#name"));
    })

    $("#changePhoneBtn").click(function () {
        buttonInputClick($("#changePhoneBtn"), $("#phone"));
    })

    function buttonInputClick(btn, inp) {
        if (inp.attr("disabled") !== undefined) {
            // allow change
            inp.removeAttr("disabled");
            btn.html("<i class='fa fa-check'></i>");
        } else {
            inp.removeClass("border-danger");
            $(".field-error").remove();
            if (inp.val() === "" && inp.attr("id") === "name") {
                inp.addClass("border-danger");
                inp.parent().parent().append("<div class='field-error col-sm-6 offset-6'><small class='text-danger text-muted'>Field can't be empty</small></div>")
            }
            else {
                inp.attr("disabled", ''); // disallow  change
                btn.html("<i class='fa fa-edit'></i>");
                params = {
                    hotelId: hotelId,
                };
                params[inp.attr("name")] = inp.val();

                console.log(params);
                $.post("update-hotel", params, function (data, status) {
                    console.log(data);
                    console.log(status);
                });
            }
        }
    }


    // Room Update
    const roomUpdateBtns = document.querySelectorAll('.roomUpdateBtn');
    for (var i = 0; i < roomUpdateBtns.length; ++i) {
        roomUpdateBtns[i].addEventListener('click', updateRoomBtnClicked);
    }

    /**
     @param {Event} e
     */
    function updateRoomBtnClicked(e) {
        let btn = e.target;
        if (btn.tagName !== 'BUTTON')
            btn = btn.parentElement;
        let cur = btn.parentElement;
        console.log(btn.classList);
        if (btn.classList.contains('inactive')) {
            while (true) {
                cur = cur.previousElementSibling;
                if (cur === undefined || cur === null) {
                    console.log(cur);
                    break;
                }
                let inp = cur.firstElementChild;
                if (inp.tagName !== 'BUTTON')
                    inp.removeAttribute('disabled');
            }
            btn.innerHTML ="<i class='fa fa-check'></i>";
            btn.classList.remove('inactive');
        } else {
            // TODO: find cleaner way to iterate over these fields and validate type not empty
            let params = {};
            while (true) {
                cur = cur.previousElementSibling;
                if (cur === null) {
                    break;
                }
                let inp = cur.firstElementChild;
                if (inp.tagName !== 'BUTTON') {
                    params[inp.name] = inp.value;
                    inp.setAttribute('disabled', '');
                }
            }
            console.log(params);
            $.post("update-room", params, function (data, status) {
                console.log(data);
            });
            btn.innerHTML = "<i class='fa fa-edit'></i>"
            btn.classList.add('inactive');
        }
    }

    // Room Delete

    const roomDeleteBtns = document.querySelectorAll('.roomDeleteBtn');
    for (let i = 0; i < roomDeleteBtns.length; ++i) {
        roomDeleteBtns[i].addEventListener('click', deleteRoomBtnClicked);
    }

    /**
     @param {Event} e
     */
    function deleteRoomBtnClicked(e) {
        let btn = e.target;
        let roomId = btn.parentElement.previousElementSibling.firstElementChild.value;
        params = {"roomId": roomId};
        $.post("delete-room", params, function (data, status) {
            btn.parentElement.previousElementSibling.parentElement.remove();
        });
    }

    // Images selecting

    const imgs = document.querySelectorAll('.img');
    for (let i = 0; i < imgs.length; ++i) {
        imgs[i].addEventListener('click', imgSelected);
    }
    let selectedImage = null;

    /**
     @param {Event} e
     */
    function imgSelected(e) {
        if (selectedImage !== null) {
            selectedImage.classList.remove('border');
            selectedImage.classList.remove('rounded');
            selectedImage.classList.remove('border-success');
        }
        selectedImage = e.target;
        selectedImage.classList.add('border');
        selectedImage.classList.add('rounded');
        selectedImage.classList.add('border-success');
    }

    let deleteBtn = document.querySelector('#deleteImageBtn');
    deleteBtn.addEventListener('click', function(e){
        deleteImage(e, selectedImage);
    });

    /**
     @param {Event} e
     */
    function deleteImage(e, selectedImage) {
        params = {
            "hotelId": document.querySelector('#hotelId').value,
            "imageId": selectedImage.getAttribute('imageId')
        };
        console.log(selectedImage.getAttribute('imageId'));
        $.ajax({
            type: "POST",
            url: "delete-image",
            data: params,
            success: function (data) {
                window.location.href = window.location.href;
                console.log(data);
            }
        });
    }
})