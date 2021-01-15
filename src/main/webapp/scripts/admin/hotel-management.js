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
            } else {
                inp.attr("disabled", ''); // disallow  change
                btn.html("<i class='fa fa-edit'></i>");
                let params = {
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
    for (let i = 0; i < roomUpdateBtns.length; ++i) {
        roomUpdateBtns[i].addEventListener('click', updateRoomBtnClicked);
    }

    function createErrorField(error) {
        let errorDiv = document.createElement("div");
        errorDiv.classList.add('field-error');
        errorDiv.innerHTML = "<small class='text-danger text-muted'>" + error + "</small>"
        return errorDiv
    }

    /**
     @param {Event} e
     */
    function updateRoomBtnClicked(e) {
        let btn = e.target;
        if (btn.tagName !== 'BUTTON')
            btn = btn.parentElement;
        let row = btn.parentElement.parentElement;
        let inputElements = row.querySelectorAll('input, textarea');
        if (btn.classList.contains('inactive')) {
            Array.from(inputElements).forEach(function (inp) {
                inp.removeAttribute('disabled');
            });
            btn.innerHTML = "<i class='fa fa-check'></i>";
            btn.classList.remove('inactive');
        } else {
            // removing errors
            console.log(row);
            let errors = row.querySelectorAll('.field-error');
            console.log(errors);
            Array.from(errors).forEach(function (e) {
                e.remove();
            });

            // frontend validation
            let ok = true;
            let params = {};
            Array.from(inputElements).forEach(function (inp) {
                params[inp.name] = inp.value;
                inp.classList.remove('border-danger');
                if (inp.name !== 'facilities' && inp.value === '') {
                    inp.classList.add('border-danger');
                    inp.parentElement.append(createErrorField("Field can't be empty"));
                    ok = false;
                }
                if (inp.type === 'number' && (isNaN(Number(inp.value)) || inp.value < 0)) {
                    inp.classList.add('border-danger');
                    inp.parentElement.append(createErrorField("Field must be a non-negative number"));
                    ok = false;
                }
            });
            console.log(params);
            // request if valid
            if (ok) {
                Array.from(inputElements).forEach(function (inp) {
                    inp.setAttribute('disabled', '');
                });
                $.post("update-room", params, function (data, status) {
                    console.log(data);
                });
                btn.innerHTML = "<i class='fa fa-edit'></i>"
                btn.classList.add('inactive');
            }
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
        let params = {"roomId": roomId};
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
    deleteBtn.addEventListener('click', function (e) {
        deleteImage(e, selectedImage);
    });

    /**
     @param {Event} e
     */
    function deleteImage(e, selectedImage) {
        let params = {
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