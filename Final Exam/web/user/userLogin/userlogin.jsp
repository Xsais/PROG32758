<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../res/style/reset.css" rel="stylesheet" type='text/css'>
    <link href="../../res/style/default.css" rel="stylesheet" type='text/css'>
    <title>CAR RACING GAME</title>
</head>
<body>
<div class="app-pane top">
    <img src="../../res/img/png-sheridan.jpg">
    <div class="app-title">CAR RACING GAME</div>
    <div class="dynamic-box top">
        <p>Game designed by Group 1</p>
        <ul class="mini-text">
            <li>Daniel Hope</li>
            <li>Georgina Luce</li>
            <li>Nathaniel Primo</li>
            <li>Michael Marc</li>
        </ul>
    </div>
</div>
<div class="app-pane bottom">
    <form method="get" class='app-pane loginForm' action='./login'>
        <label>Enter User Login: <input name='userLogin' autofocus id='userLogin' type='text'
                                        title='Login can be any combination of letters and numbers, maximum of 10'
                                        maxlength='10' size="14" required>
        </label>
        <label>Enter User Password:<input name='userPassword' type='password'
                                          title="Password must not be 6 x's"
                                          maxlength='10' size="14" required/>
        </label>
        <button class="app-button" type='submit'>Sign In</button>
    </form>
    <button class="app-button exit" onclick="window.location = '../user.html'">Exit User</button>
</div>

<%! String[] error = new String[] {"The Login and Password are not correct\\nPlease try again if you are already "
        + "registered, or complete your registration if you are not"
        , "Your account has been locked\\nPlease contact your Database Administrator", "Invalid password, 6 x's are not allowed"};%>

<% String err = request.getParameter("err");

    if (err != null) { %>

<script>window.addEventListener("load", function () {

    alert("<%= error[Integer.valueOf(err)] %>");
})</script>
<% } %>
</body>
</html>
