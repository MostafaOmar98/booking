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
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
    <title>Client Reservations</title>
</head>
<body>
<%@include file="include/header-navbar.jsp" %>
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
                                    <label for="rate">Stars </label>
                                    <select id="rate" name="stars">
                                        <option value="1" selected>1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                                <label> Comment
                                    <textarea name="comment" form="reviewForm"
                                              placeholder="Add any comments here"></textarea>
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
</body>
</html>