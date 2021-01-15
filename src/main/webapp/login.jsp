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
    <link href="style/login.css" rel="stylesheet">

</head>
<body>
<header class="page-header header container-fluid">
    <div class="overlay">
        <div class="container-fluid">
            <div class="row">
                <div class="col-6 middle-comp">
                    <h2 class="comp-title bg-text">Welcome to Hagz</h2>
                    <p class="bg-text">You can login as a client and start booking your next trip, or login as admin and
                        list your hotel
                        now!</p>
                </div>
                <div class="col-3 middle-comp justify-content-end">
                    <form class="was-validated">
                        <div class="form-group">
                            <input class="form-control" type="text" name="username" id="username" placeholder="Username"
                                   required>
                            <div class="invalid-feedback">Please enter a non-empty valid username</div>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="password" name="password" id="password"
                                   placeholder="Password" required>
                            <div class="invalid-feedback">Please enter non empty password</div>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="admin" name="type" value="ADMIN" checked>
                            <label for="admin" class="form-check-label bg-text">Admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="client" name="type" value="CLIENT">
                            <label for="client" class="form-check-label bg-text">Client</label>
                        </div>
                        <div class="row mt-2">
                            <div class="col">
                                <span class="text-danger align-bottom" id="loginError" hidden>Username Password combination not found</span>
                            </div>
                            <div class="col">
                                <button type="submit" id="login-submit"
                                        class="btn btn-primary float-right">Login
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-3 offset-6">
                    <div class="row">
                        <div class="col justify-content start">
                            <p class="bg-text">Don't have an account? Sign up now!</p>
                            <a href="sign-up" class="btn btn-light float-right">Sign up</a>
                        </div>
                    </div>
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
<%--For custom styling--%>
<script src="scripts/public/main.js"></script>
<script src="scripts/login.js"></script>
</body>
</html>