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
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <%--    icons--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%--    custom styling--%>
    <link href="style/main.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-md ">
    <a class="navbar-brand" href="#">Hagz</a>
    <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="main-navigation">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown">
                    Other
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="profile">Update Profile</a>
                    <a class="dropdown-item" href="change-password">Change Password</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="sign-out">Sign Out</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div class="form-change" align="center">
    <form align="center">
        <div class="form-group row align-items-center">

            <div class="col-6 offset-3" align="center">
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

            <div class="error col-6 offset-3" align="center" role="alert" id="password1-error-span">
                     <p id="password1-error"></p>
            </div>
        </div>
        <div class="form-group row align-items-center" align="center">
            <div class="col-6 offset-3">
            <div align="center">

                <label for="password2">Confirm Password</label>
            </div>

            <input type="password" class="form-control" id="password2" placeholder="Re-type Password" required>

            </div>
        </div>
        <div class="form-group row align-items-center" align="center">
            <div class="error col-6 offset-3" role="alert" id="password2-error-span">
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
<script src="${pageContext.request.contextPath}/scripts/public/change-password.js"></script>
</body>
</html>
