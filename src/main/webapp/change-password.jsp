<%--
  Created by IntelliJ IDEA.
  User: tw3
  Date: 1/12/21
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>change Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<div class="form-change" align="center">
    <form align="center">
        <div class="form-group row align-items-center">

            <div class="col-5" align="center">
                <div align="center">
                    <label for="password1" class="col-5 col-form-label">New Password</label>
                </div>
                <div align="center">

                    <input type="password" class="form-control" id="password1" placeholder="New Password" required>
                    <small id="password-help" class="form-text text-muted">Make sure your password is 8 characters or more.</small>
                </div>

            </div>
        </div>
        <div class="form-group row">

            <div class="error col-5" align="center" role="alert" id="password1-error-span">
                     <p id="password1-error"></p>
            </div>
        </div>
        <div class="form-group row align-items-center" align="center">
            <div class="col-5">
            <div align="center">

                <label for="password2">Confirm Password</label>
            </div>

            <input type="password" class="form-control" id="password2" placeholder="Re-type Password" required>

            </div>
        </div>
        <div class="form-group row align-items-center" align="center">
            <div class="error col-5" role="alert" id="password2-error-span">
                     <p id="password2-error"></p>
            </div>
        </div>
        <div class="error"><p id="password-retype-error"></p></div>
        <input type="submit" id="change-password-btn" class="btn btn-primary"  value="submit" >
        <div id="success" role="alert">
            <p id="update-status"></p>
        </div>
    </form>
</div>
    <script src='https://www.google.com/recaptcha/api.js'></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/client/change-password.js"></script>
</body>
</html>
