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
    <form method="get" class='app-pane loginForm' action='./register'>
        <label>Last Name: <input name='lastName' autofocus id='lastName' type='text'
                                        title='Last name must be alpha values, maximum of 15'
                                        maxlength='15' size="14" required pattern="[a-zA-Z ]+">
        </label>
        
        <label>First Name: <input name='firstName' type='text'
                                          title='First Name name must be alpha values, maximum of 15'
                                          maxlength='15' size="14" required pattern="[a-zA-Z ]+"/>
        </label>
        
        <label>Group: <input name='group' type='text'
                                          title='Group must be one number, from 0 to 8'
                                          maxlength='1' size="14" required pattern="[1-8]"/>
        </label>
        
        <label>Login: <input name='login' type='text'
                                          title='Login can be any combination of letters and numbers, maximum of 10'
                                          maxlength='10' size="14" required/>
        </label>
        
        <label>Password: <input name='password' type='password'
                                          title='Password must contain at least 1 letter, 1 number, minimum 3 and maximum of 10'
                                          maxlength='10' size="14" required pattern="(?=.*[a-z].*[A-Z])(?=.*[0-9]).{3,10}"/>
        </label>
        
        <label>Preferred Car Name: <input name='preferredCarName' type='text'
                                          title='Preferred car name can be any alpha numeric value, maximum of 10'
                                          maxlength='10' size="14" required/>
        </label>
        
        <label>Credit: <input name='credit' type='text'
                                          title='Credit must be number value, maximum of 10'
                                          maxlength='10' size="14" required pattern="\d{1,4}"/>
        </label>
        
        <label>Score: <input name='score' type='text'
                                          title='Score must be number value, maximum of 10'
                                          maxlength='10' size="14" required pattern="[0-9]{1,10}"/>
        </label>
        
        <label>Logo: <input name='logo' type='text'
                                          title='Maximum length of 10'
                                          maxlength='10' size="14" required"/>
        </label>
        <button class="app-button" type='submit'>Sign In</button>
    </form>
    <button class="app-button exit" onclick="window.location = '../user.html'">Exit User</button>
</div>


</body>
</html>
