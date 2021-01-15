<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Hotel" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.HotelImage" %>
<%
    Hotel hotel = (Hotel) request.getAttribute("hotel");
    ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
    ArrayList<HotelImage> images = (ArrayList<HotelImage>) request.getAttribute("images");
    request.setAttribute("hotelId", hotel.getHotelId()); // for header-navbar
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    icons--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
    <title>Hotel Management</title>
</head>
<body>
<%@include file="include/header-navbar.jsp" %>
<input type="hidden" id="hotelId" value="<%=hotel.getHotelId()%>">
<div class="container-fluid" id="basicInfoContainer">
    <div class="row mb-2" id="nameDiv">
        <label class="col-sm-6 col-form-label" for="name"><b>Name</b></label>
        <div class="col-sm-6 input-group">
            <input class="form-control" type="text" name="name" id="name" value="<%=hotel.getName()%>" required
                   disabled>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="changeNameBtn"><i class="fa fa-edit"></i>
                </button>

            </div>
        </div>
    </div>
    <div class="row mb-2" id="phoneDiv">
        <label class="col-sm-6 col-form-label" for="phone"><b>Phone</b></label>
        <div class="col-sm-6 input-group">
            <input class="form-control" type="text" name="phone" id="phone" value="<%=hotel.getPhone()%>" disabled>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="changePhoneBtn"><i class="fa fa-edit"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<hr/>
<div class="container-fluid mt-4" id="roomsContainer">
    <h2>Rooms</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Type</th>
            <th>Max Adults</th>
            <th>Max Children</th>
            <th>Price Per Night</th>
            <th>Facilities</th>
            <th colspan="2" class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%--        <%--%>
        <%--            Integer idx = 0;--%>
        <%--            for (Room room : rooms) {--%>
        <%--                out.println("<tr>");--%>
        <%--                out.println("<td>");--%>
        <%--                out.println("<input type=\"text\" required disabled name=\"type\" value=\"" + room.getType() + "\"");--%>
        <%--                out.println("</td>\n");--%>

        <%--                out.println("<td>");--%>
        <%--                out.println("<input type=\"number\" min=\"0\" required disabled name=\"maxAdults\" value=\"" + room.getMaxAdults() + "\"");--%>
        <%--                out.println("</td>\n");--%>

        <%--                out.println("<td>");--%>
        <%--                out.println("<input type=\"number\" min=\"0\" required disabled name=\"maxChildren\" value=\"" + room.getMaxChildren() + "\"");--%>
        <%--                out.println("</td>\n");--%>


        <%--                out.println("<td>");--%>
        <%--                out.println("<input type=\"number\" min=\"0\" required disabled name=\"pricePerNight\" value=\"" + room.getPricePerNight() + "\"");--%>
        <%--                out.println("</td>\n");--%>

        <%--                out.println("<td>");--%>
        <%--                out.println("<input type=\"text\" disabled name=\"facilities\" " + "value=\"" + room.getFacilities() + "\">");--%>
        <%--                out.println("</td>\n");--%>

        <%--                out.println("<td>");--%>
        <%--                out.println("<input disabled hidden name=\"roomId\" value=\"" + room.getRoomId() + "\"");--%>
        <%--                out.println("</td>\n");--%>

        <%--                out.println("<td>");--%>
        <%--                out.println("<button type=\"button\" " + "class=\"roomDeleteBtn btn-danger btn\">Delete</button>");--%>
        <%--                out.println("</td>");--%>

        <%--                out.println("<td>");--%>
        <%--                out.println("<button type=\"button\" " + "class=\"roomUpdateBtn\">Update</button>");--%>
        <%--                out.println("</td>");--%>

        <%--                out.println("</tr>");--%>
        <%--                idx++;--%>
        <%--            }--%>
        <%--        %>--%>
        <%for (Room room : rooms) {%>
        <tr>
            <td>
                <input class="form-control" type="text" name="type" value="<%=room.getType()%>" disabled>
            </td>
            <td>
                <input class="form-control" type="number" name="maxAdults" value="<%=room.getMaxAdults()%>" min="0"
                       disabled>
            </td>
            <td>
                <input class="form-control" type="number" name="maxChildren" value="<%=room.getMaxChildren()%>" min="0"
                       disabled>
            </td>
            <td>
                <input class="form-control" type="number" step="0.01" name="pricePerNight"
                       value="<%=room.getPricePerNight()%>" min="0" disabled>
            </td>
            <td>
                <textarea class="form-control" name="facilities" disabled><%=room.getFacilities()%></textarea>
            </td>
            <td hidden>
                <input type="hidden" value="<%=room.getRoomId()%>">
            </td>
            <td>
                <button class="btn btn-danger roomDeleteBtn" type="button"><i class="fa fa-trash"></i></button>
            </td>
            <td>
                <button class="btn btn-secondary roomUpdateBtn" type="button"><i class="fa fa-edit"></i></button>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <div class="conatiner-fluid">
        <div class="row justify-content-end mr-4">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRoomModal">
                Add Room
            </button>
        </div>
    </div>

    <div class="modal fade" id="addRoomModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addRoomLabel">Add Room</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="add-room" method="post">
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label" for="type">Type: </label>
                            <div class="col-sm-8">
                                <input class="form-control" type="text" id="type" name="type" placeholder="Room Type"
                                       required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label" for="maxAdults">Max Adults: </label>
                            <div class="col-sm-8">
                                <input class="form-control" type="number" value="0" min="0" required id="maxAdults"
                                       name="maxAdults">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label" for="maxChildren">Max Children: </label>
                            <div class="col-sm-8">
                                <input class="form-control" type="number" value="0" min="0" required id="maxChildren"
                                       name="maxChildren">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label" for="pricePerNight">Price Per Night: </label>
                            <div class="col-sm-8">
                                <input class="form-control" type="number" value="0" min="0" step="0.01" required
                                       id="pricePerNight"
                                       name="pricePerNight">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label" for="facilities">Facilities: </label>
                            <div class="col-sm-8">
                                <textarea class="form-control" type="text" id="facilities" name="facilities"
                                          placeholder="Leave empty if room has no facilities"></textarea>
                            </div>
                        </div>
                        <input type="hidden" name="hotelId" value="<%=hotel.getHotelId()%>">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" id="addRoomBtn" value="Add Room">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<hr/>
<div class="container-fluid mt-4" id="Images">
    <h2>Images</h2>
    <form action="add-hotel-image" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="image">Upload Image</label>
            <input class="form-control-file" id="image" type="file" name="image"><br>
            <input class="btn btn-outline-secondary" type="submit" value="Upload">
        </div>
        <input type="hidden" name="hotelId" value="<%=hotel.getHotelId()%>"><br>
    </form>
    <div class="row">
        <%--        <%--%>
        <%--            for (int i = 0; i < images.size(); ++i) {--%>
        <%--                out.println(--%>
        <%--                        "<div class=\"col-sm-4\">\n" +--%>
        <%--                                "<img src=\"image/" + images.get(i).getName() + "\" style=\"width:100%;height:100%\" class=\"img\" imageId=\"" + images.get(i).getImageId() + "\">\n" +--%>
        <%--                                "</div>");--%>
        <%--            }--%>
        <%--        %>--%>
        <% for (HotelImage image : images) {%>
        <div class="col-sm-4 mt-1 ml-1">
            <img src="image/<%=image.getName()%>" class="img img-fluid m-1" imageId="<%=image.getImageId()%>"
                 alt="Couldn't load image">
        </div>
        <%}%>
    </div>
    <button id="deleteImageBtn" class="btn btn-danger btn-lg"><i class="fa fa-trash"></i></button>
</div>
<hr/>
<form action="update-hotel-location" method="post">
    <input type="hidden" name="hotelId" value="<%=hotel.getHotelId()%>">
    <input type="submit" value="Update Location">
</form>

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
<%--For custom styling--%>
<script src="scripts/admin/hotel-management.js"></script>
</body>
</html>