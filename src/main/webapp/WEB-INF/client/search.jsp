<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %>
<%-- Created by IntelliJ IDEA. User: ahmeddrawy Date: 1/4/2021 Time: 2:36 PM To change this template use File |
    Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Hotels Search</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>

<body>
  <!-- <form onsubmit="return validate()"> -->
<%--  todo add /list url --%>
    <form action="client-home" method="GET">
    <div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">Address </label>
      <div class="col-sm-4">
        <input type="text"  class="form-control" id="location" name="location"placeholder="Cairo" >
      </div>
    </div>
    <div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">Check In </label>
      <div class="col-sm-10">
        <input type="date" class="form-control" id="check-in" >
      </div>
    </div>
    <div class="form-group row">
      <label for="inputPassword3" class="col-sm-2 col-form-label">Checkout</label>
      <div class="col-sm-10">
        <input type="date" class="form-control" id="check-out" >
      </div>
    </div>
    <fieldset class="form-group">
      <div class="row">
        <legend class="col-form-label col-sm-2 pt-0">Number of Adults</legend>
        <div class="col-sm-10">
          <div class="form-check">
            <input class="form-check-input" type="number" name="n-adults" id="n-adults" value="0" min="0" max="10">
          </div>
        </div>
      </div>
      <div class="row">
        <legend class="col-form-label col-sm-2 pt-0">Number of Adults</legend>
        <div class="col-sm-10">
          <div class="form-check">
            <input class="form-check-input" type="number" name="n-children" id="n-children" value="0" min="0" max="10">
          </div>
        </div>
      </div>
    </fieldset>
  
    <div class="form-group row">
      <div class="col-sm-10">
        <button type="submit" id="search-btn" class="btn btn-primary">Search</button>
      </div>
    </div>
  </form>
  <script src="${pageContext.request.contextPath}/scripts/client/search-form-validate.js"></script>
</body>

</html>