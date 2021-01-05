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
<div>
    Email: <input type="text" name="email" id="email"><br>
    Password: <input type="password" name="password" id="password"><br>
    <input type="radio" id="admin" name="type" value="ADMIN" checked> Admin
    <input type="radio" id="client" name="type" value="CLIENT">Client<br>
    <input type="submit" value="Login" id="login">
    <div class="g-recaptcha" data-sitekey="6LclGiIaAAAAAFRWupUInlrJlSwJC01lq4PGPOFe"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/login.js"></script>
</body>
</html>