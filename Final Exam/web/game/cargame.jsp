<%@ page import="com.utils.helperclasses.ConnectToDB" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../res/style/reset.css" rel="stylesheet">
    <link href="../res/style/default.css" rel="stylesheet">
    <script src="../res/script/game/js-playermenu.js" type="text/javascript"></script>
    <script src="../res/script/Car.js" type="text/javascript"></script>
    <script src="../res/script/UseMyCar.js" type="text/javascript"></script>
    <title>CAR RACING GAME</title>
</head>
<body>
<div class="app-pane top">
    <img src="../res/img/png-sheridan.jpg">
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
    <div class="app-pane display">
        <div id="visual-display"></div>
    </div>
    <div class="app-pane info">
        <div class="app-pane info section">
            <div id="music-control" class="game icon music" title="Play Music">
                Play Music
                <img src="../res/img/png-music.png" height="32">
                <audio id="game-music" loop>
                    <source src="../res/audio/mp3-noise.mp3" type="audio/mp3">
                </audio>
            </div>
            <div id="view-score" class="game icon score" title="View Score">
                View Score
                <img src="../res/img/png-score.png" height="32">
            </div>

            <div id="credit-refill" class="game icon credit" title="Refill Credit">
                Credit Refill
                <img src="../res/img/png-credit.png" height="32">
            </div>
        </div>
        <div class="app-pane info section">
            <button id="app-play" class="app-button">Play</button>
        </div>
        <div class="app-pane info section">
            <div id="score-container" class="app-pane display stats score">Score:
                <span id="score-display">0</span>
            </div>
            <div class="app-pane display stats credit">Credit:
                <span id="credit-display">0</span>
            </div>
        </div>
    </div>
</div>


<% String login = request.getParameter("login");

    ConnectToDB dbConnection = (ConnectToDB) request.getServletContext().getAttribute("db_conection");

    if (login == null) {

        response.sendRedirect("../user/userLogin/userlogin.jsp");
        return;
    }

    ResultSet carName = dbConnection.executeQuerry(String.format("SELECT `Preferred_Car_Name`, `Score`, `Credits` from players WHERE `Login`='%s'", login));

    if (carName.next()) { %>

        <script>

            init('<%= login %>', <%= carName.getDouble(2) %>, <%= carName.getDouble(3) %>);
            window.addEventListener("load", function () {

                driverName = '<%= carName.getString(1) %>';
            })
        </script>
    <% } %>
</body>
</html>