<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %><%
    Hotel hotel = (Hotel)request.getAttribute("hotel");
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Hotel Management</title>
</head>
<body>
<input type="hidden" id= "hotelId" value="<%=hotel.getHotelId()%>">
<div id="nameDiv">
    <label for="name" >Name: </label>
    <input type="text" name="name" id="name" value="<%=hotel.getName()%>" disabled><br>
    <button type="button" id="changeNameBtn">Change Name</button>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="scripts/hotel-management.js"></script>
</body>
</html>