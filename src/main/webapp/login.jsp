<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Login</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<form class="was-validated" style="width:50%; margin:auto">
    <div class="form-group">
        <label for="email">Email</label>
        <input class="form-control" type="email" name="email" id="email" placeholder="Enter email" required>
        <div class="invalid-feedback">Please enter a non-empty valid email</div>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input class="form-control"  type="password" name="password" id="password" placeholder="Password" required>
        <div class="invalid-feedback">Please enter non empty password</div>
    </div>
    <div class="form-check form-check-inline">
        <label for="admin">As Admin</label>
        <input class="form-check-input" type="radio" id="admin" name="type" value="ADMIN" checked>
    </div>
    <div class="form-check form-check-inline">
        <label for="client">As Client</label>
        <input class="form-check-input" type="radio" id="client" name="type" value="CLIENT">
    </div>
    <br>
    <div class="g-recaptcha" data-sitekey="6LclGiIaAAAAAFRWupUInlrJlSwJC01lq4PGPOFe"></div>
    <input type="submit" value="Login" class="btn btn-primary">
</form>
<small class="text-danger" id="loginError" hidden>Email and password combination now found</small>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/login.js"></script>
</body>
</html>