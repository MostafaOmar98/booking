<%@ page import="com.hagz_hotels.hotels_booking.Model.Entities.User" %><%--
  Created by IntelliJ IDEA.
  User: ahmeddrawy
  Date: 1/4/2021
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotels Search</title>
</head>
<body>

    <%
       User user =  (User)session.getAttribute("user");
       out.println("hello "+user.getName());
    %>
    <table>
      <tbody>
        <form action="/list" method="get">
          <tr>
            <th>Check in date</th>
            <th><input type="date" name="cin-date" id="cin-date"></th>
          </tr>
          <tr>
            <th>Check out date</th>
            <th><input type="date" name="cout-date" id="cout-date"></th>
          </tr>
          <tr>
            <th>Number of Adults</th>
            <th><input type="number" min="0" name="nadults" id="nadults" value="0"></th>
          </tr>
          <tr>
            <th>Number of Children</th>
            <th><input type="number" min="0" name="nchildren" id="nchildren" value="0"></th>
          </tr>
          <tr>
            <th></th>
            <th><input type="submit" value="submit"></th>
          </tr>
        </form>
        </tbody>
      </table>

</body>
</html>
