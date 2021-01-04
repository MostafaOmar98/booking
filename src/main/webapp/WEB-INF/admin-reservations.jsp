<%@ page import="com.hagz_hotels.hotels_booking.Model.DTO.ReservationDTO" %>
<%@ page import="java.util.List" %>
<%
    List<ReservationDTO> reservations = (List<ReservationDTO>) request.getAttribute("reservations");
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Admin Reservations</title>
</head>
<body>
<div class="form-group">
    <label class="col-2 col-form-label" for="clientName">Search with client name</label>
    <input class="form-control col-10" type="text" placeholder="Client Name" id="clientName">
</div>

<div class="form-group row">
    <label for="fromDate" class="col-2 col-form-label">From</label>
    <input class="form-control col-10" type="date" id="fromDate">
</div>

<div class="form-group row">
    <label for="toDate" class="col-2 col-form-label">To</label>
    <input class="form-control col-10" type="date" id="toDate">
</div>


<table class="table table-bordered">
    <tr>
        <th>Client Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Room Id</th>
        <th>Status</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Issued At</th>
        <th>Actions</th>
    </tr>
<%
    for (ReservationDTO r : reservations) {
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
        out.println(r.getStatus());
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
        out.println("<button class=\"btn btn-danger cancelBtn\">Cancel</button>");
        out.println("<button class=\"btn updateBtn\">Update Status</button>");
        out.println("</td>");

        out.println("</tr>");
    }
%>
</table>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="scripts/admin-reservations.js"></script>
</body>
</html>