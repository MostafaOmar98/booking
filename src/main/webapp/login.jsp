<%--<!doctype html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">--%>
<%--    <title>Login</title>--%>
<%--    <script src='https://www.google.com/recaptcha/api.js'></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form class="was-validated" style="width:50%; margin:auto">--%>
<%--    <div class="form-group">--%>
<%--        <label for="email">Email</label>--%>
<%--        <input class="form-control" type="email" name="email" id="email" placeholder="Enter email" required>--%>
<%--        <div class="invalid-feedback">Please enter a non-empty valid email</div>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label for="password">Password</label>--%>
<%--        <input class="form-control"  type="password" name="password" id="password" placeholder="Password" required>--%>
<%--        <div class="invalid-feedback">Please enter non empty password</div>--%>
<%--    </div>--%>
<%--    <div class="form-check form-check-inline">--%>
<%--        <label for="admin">As Admin</label>--%>
<%--        <input class="form-check-input" type="radio" id="admin" name="type" value="ADMIN" checked>--%>
<%--    </div>--%>
<%--    <div class="form-check form-check-inline">--%>
<%--        <label for="client">As Client</label>--%>
<%--        <input class="form-check-input" type="radio" id="client" name="type" value="CLIENT">--%>
<%--    </div>--%>
<%--    <br>--%>
<%--&lt;%&ndash;    <div class="g-recaptcha" data-sitekey="6LclGiIaAAAAAFRWupUInlrJlSwJC01lq4PGPOFe" data-callback="enableSubmit"></div>&ndash;%&gt;--%>
<%--    <input type="submit" value="Login" id="login-submit" class="btn btn-primary">--%>
<%--</form>--%>
<%--<small class="text-danger" id="loginError" hidden>Email and password combination now found</small>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/scripts/login.js"></script>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact</a>
            </li>
        </ul>
    </div>
</nav>
<header class="page-header header container-fluid">
    <div class="overlay">
        <div class="container">
            <div class="row">
                <div class="col-8 middle-comp">
                    <h3 class="comp-title bg-text">Welcome to Hagz</h3>
                    <p class="bg-text">You can login as a client and start booking your next trip, or login as admin and list your hotel
                        now!</p>
                </div>
            </div>
        </div>
    </div>
</header>
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
<script src="scripts/public/main.js"></script>
</body>
</html>