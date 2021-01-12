$(function () {
    let cancelButtons = document.querySelectorAll('.cancelBtn');
    Array.from(cancelButtons).forEach(function (btn) {
        if (getStatusOfRowWithBtn(btn) === 'CHECKED_OUT' || getStatusOfRowWithBtn(btn) === 'CANCELED' || getStatusOfRowWithBtn(btn) === 'CHECKED_IN')
            hide(btn);
        btn.addEventListener('click', requestUpdateReservation);
    });

    let updateButtons = document.querySelectorAll('.updateBtn');
    Array.from(updateButtons).forEach(function (btn) {
        let s = getStatusOfRowWithBtn(btn);
        if (s === "PENDING")
            btn.textContent = "Confirm";
        else if (s === "CONFIRMED")
            btn.textContent = "Check In";
        else if (s === "CHECKED_IN")
            btn.textContent = "Check Out";
        else {
            console.log(s);
            hide(btn);
        }
        btn.addEventListener('click', requestUpdateReservation);
    });

    function requestUpdateReservation(e) {
        let btn = e.target;
        let rId = btn.parentElement.querySelector('#rid').value;
        let newState;
        if (btn.innerText === 'Cancel')
            newState = 'CANCELED';
        else if (btn.innerText === 'Confirm')
            newState = 'CONFIRMED';
        else if (btn.innerText === 'Check In')
            newState = 'CHECKED_IN';
        else if (btn.innerText === 'Check Out')
            newState = 'CHECKED_OUT';
        params = {
            reservationId: rId,
            state: newState
        };
        $.post("update-reservation", params, function (data, status) {
            window.location.href = window.location.href;
        });
    }

    function getStatusOfRowWithBtn(btn) {
        let row = btn.parentElement.parentElement;
        let statusElement = row.children[7];
        return statusElement.innerText;
    }

    function hide(e) {
        e.style.display = 'none';
    }

    function isValidDate(d) {
        return d instanceof Date && !isNaN(d);
    }

    function filter(clientName, fromDate, toDate) {
        let allRows = document.querySelectorAll('.data-rows tr');
        Array.from(allRows).forEach(function (row) {
            row.style.removeProperty('display');
        })
        Array.from(allRows).forEach(function (row) {
            let name = row.children[0].innerText, checkIn = new Date(row.children[4].innerText);
            console.log(checkIn);
            console.log(fromDate);
            console.log(toDate);
            if (clientName !== "" && !name.includes(clientName))
                hide(row);
            else if (isValidDate(fromDate) && checkIn.getTime() < fromDate.getTime())
                hide(row);
            else if (isValidDate(toDate) && checkIn.getTime() > toDate.getTime())
                hide(row);
        })
    }


    const clientNameElement = document.getElementById("clientName");
    const fromDateElement = document.getElementById("fromDate");
    const toDateElement = document.getElementById("toDate");

    clientNameElement.addEventListener('input', function () {
        filter(clientNameElement.value, new Date(fromDateElement.value), new Date(toDateElement.value));
    });
    fromDateElement.addEventListener('input', function () {
        filter(clientNameElement.value, new Date(fromDateElement.value), new Date(toDateElement.value));
    });
    toDateElement.addEventListener('input', function () {
        filter(clientNameElement.value, new Date(fromDateElement.value), new Date(toDateElement.value));
    });
})