package com.views.userlogin;

import com.controls.playermenu.PlayerMenu;
import com.util.ConnectToDB;
import com.util.PageType;
import com.util.PageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin extends PageView implements Initializable {

    private static final String LOCK_OUT_CODE = "xxxxxx";

    @FXML
    private Button btnUserLogin, btnExit;

    @FXML
    private Label lblLogin, lblPassword, lblLoginEmpty, lblPasswordEmpty;

    @FXML
    private TextField txtLogin, txtPassword;

    private PlayerMenu playerMenu;

    private int failCount = 0;

    private ResultSet rs = null;

    private ConnectToDB dbConnection;

    private com.views.gamemenu.GameMenu gameMenu;

    // constructor that creates connection to DB and sets object as pop out type
    // page
    public UserLogin(ConnectToDB dbConnection) {

        pageType = PageType.POP_OUT;

        gameMenu = new com.views.gamemenu.GameMenu(dbConnection);

        try {

            this.dbConnection = dbConnection;
            dbConnection.executeQuerry("USE DBProg32758;");

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            com.util.FXMLHelper.loadControl(this).load();

        } catch (java.io.IOException e) {

            e.printStackTrace();

        }

    }

    // method informs user that their account has been locked, locks the account,
    // and exits login pop out window
    public void showLockOutMessage() throws SQLException {

        // inform user that account has been locked
        JOptionPane.showMessageDialog(null, "Your account has been locked\n Please contact your Database Administrator",
                "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

        // set user password to all x's in DB
        dbConnection.executeUpdate(
                String.format("UPDATE Players SET Password = 'xxxxxx' WHERE Login = '%s'", txtLogin.getText()));

        // exit login screen
        btnExit.fire();
    }

    // method informs user of incorrect login/password and increments falied
    // attempts counter by one
    public void showLoginErrorMessage() {

        // inform user of incorrect login/password
        JOptionPane.showMessageDialog(null,
                "The Login and Password are not correct\n Please try again"
                        + " if you are already registered or complete your registration if you are not",
                "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

        // increase fail counter by 1
        failCount += 1;
    }

    // method queries DB for login/password and checks against user input for
    // validity
    public void isValidLogin() throws HeadlessException, SQLException {

        if (failCount < 3) {

            // check existence of user login
            try {

                // check DB for user defined login
                rs = dbConnection
                        .executeQuerry(String.format("SELECT * FROM Players WHERE Login = '%s'", txtLogin.getText()));

            } catch (SQLException e) {

                e.printStackTrace();
            }

            if (rs.next()) {

                // if login is valid, check if user account is already locked
                if (rs.getString("Password").equals(LOCK_OUT_CODE)) {

                    showLockOutMessage();

                    failCount = 3;

                    // if login is valid, check password validity associated with login
                } else if (rs.getString("Password").equals(txtPassword.getText())) {

                    failCount = 0;

                    gameMenu.setUsername(txtLogin.getText());
                    pageController.show(gameMenu);

                    // inform user that login and/or password are incorrect (password incorrect
                    // scenario)
                } else {

                    showLoginErrorMessage();

                    if (failCount == 3) {

                        showLockOutMessage();

                    }

                }
                // inform user that login and/or password are incorrect (login incorrect
                // scenario)
            } else {

                showLoginErrorMessage();

                if (failCount == 3) {

                    showLockOutMessage();

                }
            }
        } else {

            showLockOutMessage();
        }
    }

    // method for text field listener to prevent user from clicking Login button
    // with empty Login/Password fields
    // and to give them a warning message that fields can not be blank
    public void isBlankField() {

        // check and warn user of empty text field, and disable Login button
        if (txtLogin.getText().equals("")) {

            lblLoginEmpty.setText("The Login field can not be blank, please enter a login.");
            lblLoginEmpty.setVisible(true);

            btnUserLogin.setDisable(true);

        } else {

            lblLoginEmpty.setVisible(false);
        }


        // check if user defined password is same as lock out code and warn user,
        // disable Login button
        if (txtPassword.getText().equals(LOCK_OUT_CODE)) {

            lblPasswordEmpty.setText("Password can not be xxxxxx.  Please try a different password.");
            lblPasswordEmpty.setVisible(true);

            btnUserLogin.setDisable(true);

        } else {

            lblPasswordEmpty.setVisible(false);
            btnUserLogin.setDisable(true);
        }


        // check and warn user if password field is empty, disable Login button
        if (txtPassword.getText().equals("")) {

            lblPasswordEmpty.setText("The Password field can not be blank, please enter a password.");
            lblPasswordEmpty.setVisible(true);

            btnUserLogin.setDisable(true);

            // if text is present in both Login and Password fields, and not DB lock out
            // password, then enable Login button
        } else {

            lblLoginEmpty.setVisible(false);

        }

        // check if login and password fields have text and password is not the same as lock out code, if so enable Login button
        if (!txtLogin.getText().equals("") && !txtPassword.getText().equals("")
                && !txtPassword.getText().equals(LOCK_OUT_CODE)) {

            btnUserLogin.setDisable(false);
        }

        // check if login field is empty and password field is same as lock out code, if so disable Login button
        if (txtLogin.getText().equals("") && txtPassword.getText().equals(LOCK_OUT_CODE)) {

            btnUserLogin.setDisable(true);
            lblLoginEmpty.setVisible(true);
            lblPasswordEmpty.setText("Password can not be xxxxxx.  Please try a different password.");
            lblPasswordEmpty.setVisible(true);
        }

        if (txtLogin.getText().equals("") && !txtPassword.getText().equals("")) {

            lblLoginEmpty.setVisible(true);
            btnUserLogin.setDisable(true);
        }
    }


    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        txtLogin.requestFocus();

        // initialize Login page warning labels and Login button availability
        btnUserLogin.setDisable(true);
        lblLoginEmpty.setText("The Login field can not be blank, please enter a login.");
        lblPasswordEmpty.setText("The Password field can not be blank, please enter a password.");

        // listen for valid scenarios in Login text field
        txtLogin.textProperty().addListener(p -> {

            isBlankField();

        });

        // listen for valid scenarios in Password text field
        txtPassword.textProperty().addListener(p -> {

            isBlankField();

        });

        // verify user credentials when Login button is clicked
        btnUserLogin.setOnAction(evt -> {
            try {

                isValidLogin();

            } catch (HeadlessException e) {

                e.printStackTrace();

            } catch (SQLException e) {

                e.printStackTrace();

            }
        });

        // exit login pop out page
        btnExit.setOnAction(p -> pageController.hidePopOut(0));

    }

    @Override
    public void init(com.util.PageController pageController) {

        super.init(pageController);
        pageController.registerPage(gameMenu);
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */
    @Override
    public void onClose(Object sender, int statusCode) {

        // reset fail counter, login and password text fields for next user login
        failCount = 0;
        txtLogin.clear();
        txtPassword.clear();

    }

    /**
     * Occurs when the application classes peacefully
     *
     * @param evt The WindowEvent associated with the closure
     */
    @Override
    public void onCloseRequest(javafx.stage.WindowEvent evt) {

    }

    /**
     * Occurs when the PageView has been requested to open
     *
     * @param sender The Object in which sent the request
     */
    @Override
    public void onOpen(Object sender) {

    }
}
