<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.HotelImage" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
    ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
    ArrayList<HotelImage> images = (ArrayList<HotelImage>) request.getAttribute("images");
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Hotel Management</title>
</head>
<body>
<input type="hidden" id="hotelId" value="<%=hotel.getHotelId()%>">
<div class="container">
    <div class="row" id="nameDiv">
        <label for="name">Name: </label>
        <input class="form-control" type="text" name="name" id="name" value="<%=hotel.getName()%>" required disabled>
    </div>
    <div class="row justify-content-end">
        <button class="col-1" type="button" id="changeNameBtn">Change</button>
    </div>
    <div class="row" id="phoneDiv">
        <label for="phone">Phone: </label>
        <input class="form-control" type="text" name="phone" id="phone" value="<%=hotel.getPhone()%>" disabled>
    </div>
    <div class="row justify-content-end">
        <button class="col-1" type="button" id="changePhoneBtn">Change</button>
    </div>
</div>
<div class="container">
    <header>Rooms</header>
    <table class="table">
        <thead>
        <tr>
            <th>Type</th>
            <th>Max Adults</th>
            <th>Max Children</th>
            <th>Price Per Night</th>
            <th>Facilities</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>
        <tbody>
        <%
            Integer idx = 0;
            for (Room room : rooms) {
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"text\" required disabled name=\"type\" value=\"" + room.getType() + "\"");
                out.println("</td>\n");

                out.println("<td>");
                out.println("<input type=\"number\" min=\"0\" required disabled name=\"maxAdults\" value=\"" + room.getMaxAdults() + "\"");
                out.println("</td>\n");

                out.println("<td>");
                out.println("<input type=\"number\" min=\"0\" required disabled name=\"maxChildren\" value=\"" + room.getMaxChildren() + "\"");
                out.println("</td>\n");


                out.println("<td>");
                out.println("<input type=\"number\" min=\"0\" required disabled name=\"pricePerNight\" value=\"" + room.getPricePerNight() + "\"");
                out.println("</td>\n");

                out.println("<td>");
                out.println("<input type=\"text\" disabled name=\"facilities\" " + "value=\"" + room.getFacilities() + "\">");
                out.println("</td>\n");

                out.println("<td>");
                out.println("<input disabled hidden name=\"roomId\" value=\"" + room.getRoomId() + "\"");
                out.println("</td>\n");

                out.println("<td>");
                out.println("<button type=\"button\" " + "class=\"roomDeleteBtn btn-danger btn\">Delete</button>");
                out.println("</td>");

                out.println("<td>");
                out.println("<button type=\"button\" " + "class=\"roomUpdateBtn\">Update</button>");
                out.println("</td>");

                out.println("</tr>");
                idx++;
            }
        %>
        </tbody>
    </table>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRoomModal">
        Add Room
    </button>

    <div class="modal fade" id="addRoomModal" tabindex="-1" role="dialog" aria-labelledby="addRoomModal"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addRoomLabel">Add Room</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="add-room" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="type">Type: </label>
                            <input class="form-check" type="text" id="type" name="type" required>
                        </div>
                        <div class="form-group">
                            <label for="maxAdults">Max Adults: </label>
                            <input class="form-check" type="number" min="0" required id="maxAdults" name="maxAdults">
                        </div>
                        <div class="form-group">
                            <label for="maxChildren">Max Children: </label>
                            <input class="form-check" type="number" min="0" required id="maxChildren" name="maxChildren">
                        </div>
                        <div class="form-group">
                            <label for="pricePerNight">Price Per Night: </label>
                            <input class="form-check" type="number" min="0" step="0.01" required id="pricePerNight" name="pricePerNight">
                        </div>
                        <div class="form-group">
                            <label for="facilities">Facilities: </label>
                            <input class="form-check" type="text" id="facilities" name="facilities">
                        </div>
                        <input type="hidden" name="hotelId" value="<%=hotel.getHotelId()%>">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" id="addRoomBtn" value="Add Room">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<br><br>
<h2>Upload Image</h2>
<form action="add-hotel-image" method="post" enctype="multipart/form-data" class="justify-content-center">
    <input type="file" name="image"><br>
    <input type="hidden" name="hotelId" value="<%=hotel.getHotelId()%>"><br>
    <input type="submit" value="Upload">
</form>

<br><br>
<div class="container">
    <div class="row">
        <%
            for (int i = 0; i < images.size(); ++i) {
                out.println(
                        "<div class=\"col-4\">\n" +
                                "<img src=\"image/" + images.get(i).getName() + "\" style=\"width:100%\" class=\"img\" imageId=\"" + images.get(i).getImageId() + "\">\n" +
                                "</div>");
            }
        %>
    </div>
    <br><br>
    <button id="deleteImageBtn" class="btn btn-danger">Delete</button>
</div>

<a href="admin-reservations?hotelId=<%=hotel.getHotelId()%>">To Reservations</a>
<form action="update-hotel-location" method="post">
    <input type="hidden" name="hotelId" value="<%=hotel.getHotelId()%>">
    <input type="submit" value="Update Location">
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="scripts/admin/hotel-management.js"></script>
</body>
</html>