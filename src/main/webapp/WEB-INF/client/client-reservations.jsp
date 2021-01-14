<%@ page import="java.util.List" %>
<%@ page import="com.hagz_hotels.hotels_booking.Business.DTO.ReservationDTOForClient" %>
<%
    List<ReservationDTOForClient> reservations = (List<ReservationDTOForClient>) request.getAttribute("reservations");
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Client Reservations</title>
</head>
<body>
<table class="table table-bordered">
    <tr>
        <th>Room Id</th>
        <th>Total Price</th>
        <th>Created At</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <tbody class="data-rows">
    <%
        for (ReservationDTOForClient r : reservations) {
    %>
    <tr>
        <td><%=r.getRoomId()%>
        </td>
        <td><%=r.getTotalPrice()%>
        </td>
        <td><%=r.getCreatedAt()%>
        </td>
        <td><%=r.getCheckIn()%>
        </td>
        <td><%=r.getCheckOut()%>
        </td>
        <td><%=r.getStatus()%>
        </td>
        <td>
            <%if (r.getStatus().equals("PENDING") || r.getStatus().equals("CONFIRMED")) {%>
            <form action="client-cancel-reservation" method="post">
                <input type="hidden" value="<%=r.getReservationId()%>" name="reservationId">
                <input type="submit" value="Cancel">
            </form>
            <%}%>
            <% if (r.isReviewable()) {%>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addReviewModal">
                Add Review
            </button>

            <div class="modal fade" id="addReviewModal" tabindex="-1" role="dialog" aria-labelledby="addReviewModal"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addReviewLabel">Add Review</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="add-review" method="post" id="reviewForm">
                            <div class="modal-body">
                                <div class="form-check">
                                    <select name="stars">
                                        <option value="1" selected>1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                                <label> Comment
                                    <textarea name="comment" form="reviewForm" placeholder="Add any comments here"></textarea>
                                </label>
                                <input type="hidden" name="reservationId" value="<%=r.getReservationId()%>">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="submit" class="btn btn-primary" id="addRoomBtn" value="Add Review">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <%}%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>