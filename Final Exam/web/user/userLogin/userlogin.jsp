<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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

<form method="get" class='loginForm' action='./login'>

    <fieldset class='loginFieldSet'>

        <div>Enter User Login: <input name='userLogin' autofocus id='userLogin' type='text' pattern="^[a-zA-Z0-9]*{1,10}$"

                                      title='Login can be any combination of letters and numbers, maximum of 10'

                                      maxlength='10' size="10" required></div>

        <br>

        <div>Enter User Password:<input name='userPassword' type='password' pattern="^(?=.*\d)(?=.*[a-zA-Z]).{2,10}$"

                                        title='Password must contain at least 1 letter and 1 number, maximum of 10'

                                        maxlength='10' size="10" required/></div>

        <br>

        <div>

            <button class='loginButtons' type='submit'>Sign In</button>

            <button class='loginButtons' type='button' onclick="window.location = '../user.html'">Exit</button>

        </div>

    </fieldset>

</form>
<%! String[] error = new String[] {"The Login and Password are not correct\\nPlease try again "
        + "if you are already registered, or complete your registration if you are not"
        , "Your account has been locked\\nPlease contact your Database Administrator"};%>

<%  String err = request.getParameter("err");
    if (err != null) { %>

<script>alert('<%= error[Integer.valueOf(err)] %>');</script>
<% } %>
</body>
</html>
