<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Room" %>
<%@ page import="java.util.ArrayList" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
    ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
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
        <input type="text" name="name" id="name" value="<%=hotel.getName()%>" disabled><br>
        <button type="button" id="changeNameBtn">Change</button>
    </div>
    <div class="row" id="phoneDiv">
        <label for="phone">Phone: </label>
        <input type="text" name="phone" id="phone" value="<%=hotel.getPhone()%>" disabled><br>
        <button type="button" id="changePhoneBtn">Change</button>
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
        </tr>
        </thead>
        <tbody>
        <%
            for (Room room : rooms) {
                out.println("<tr>");
                out.println("<td>");
                out.println(room.getType());
                out.println("</td>\n");

                out.println("<td>");
                out.println(room.getMaxAdults());
                out.println("</td>\n");

                out.println("<td>");
                out.println(room.getMaxChildren());
                out.println("</td>\n");


                out.println("<td>");
                out.println(room.getPricePerNight());
                out.println("</td>\n");

                out.println("<td>");
                out.println(room.getFacilities());
                out.println("</td>\n");
                out.println("</tr>");
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
                        <label for="type">Type: </label><input type="text" id="type" name="type"><br>
                        <label for="maxAdults">Max Adults: </label><input type="text" id="maxAdults"
                                                                          name="maxAdults"><br>
                        <label for="maxChildren">Max Children: </label><input type="text" id="maxChildren"
                                                                              name="maxChildren"><br>
                        <label for="pricePerNight">Price Per Night: </label><input type="text" id="pricePerNight"
                                                                                   name="pricePerNight"><br>
                        <label for="facilities">Facilities: </label><input type="text" id="facilities"
                                                                           name="facilities"><br>
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

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="scripts/hotel-management.js"></script>
</body>
</html>