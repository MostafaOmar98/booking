<%@ page import="com.hagz_hotels.hotels_booking.Business.DTO.ReservationDTOForAdmin" %>
<%@ page import="java.util.List" %>
<%
    List<ReservationDTOForAdmin> reservations = (List<ReservationDTOForAdmin>) request.getAttribute("reservations");
    request.setAttribute("hotelId", request.getParameter("hotelId")); // for navbar
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">

    <title>Admin Reservations</title>
</head>
<body>
<div class="container-fluid">
    <div class="row mb-2">
        <label class="col-sm-4 col-form-label" for="clientName"><b>Search with client name</b></label>
        <input class="form-control col-sm-8" type="text" placeholder="Client Name" id="clientName">
    </div>

    <div class="row mb-2">
        <label for="fromDate" class="col-sm-4 col-form-label"><b>Check In From</b></label>
        <input class="form-control col-sm-8" type="date" id="fromDate">
    </div>

    <div class="row mb-2">
        <label for="toDate" class="col-sm-4 col-form-label"><b>Check In To</b></label>
        <input class="form-control col-sm-8" type="date" id="toDate">
    </div>
</div>
<table class="mt-5 table table-bordered">
    <tr>
        <th>Client Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Room Id</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Issued At</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <tbody class="data-rows">
    <%
        for (ReservationDTOForAdmin r : reservations) {
            out.println("<tr>");

            out.println("<td>");
            out.println(r.getClientName());
            out.println("</td>");

            out.println("<td>");
            out.println(r.getClientPhone());
            out.println("</td>");

            out.println("<td>");
            out.println(r.getClientEmail());
            out.println("</td>");

            out.println("<td>");
            out.println(r.getRoomId());
            out.println("</td>");


            out.println("<td>");
            out.println(r.getCheckIn());
            out.println("</td>");

            out.println("<td>");
            out.println(r.getCheckOut());
            out.println("</td>");

            out.println("<td>");
            out.println(r.getCreatedAt());
            out.println("</td>");

            out.println("<td>");
            out.println(r.getStatus());
            out.println("</td>");

            out.println("<td>");
            out.println("<input type='hidden' id='rid' value='" + r.getReservationId() + "'>");
            out.println("<button class=\"btn btn-danger cancelBtn\">Cancel</button>");
            out.println("<button class=\"updateBtn\">Update</button>");
            out.println("</td>");

            out.println("</tr>");
        }
    %>
    </tbody>
</table>


<%--JQuery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%--Popper--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<%--Bootstrap js--%>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script src="scripts/admin/admin-reservations.js"></script>
</body>
</html>