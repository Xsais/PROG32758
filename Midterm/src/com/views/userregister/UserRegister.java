/*

 * ----------------------------------------------------------------------------+

 * Group Leader: Daniel Hope

 * Member(s): Georgina Luce

 *            Nathaniel Primo

 *            Michael Marc

 * Group #: 1

 * Filename: UserRegister.java

 * Other Files in this Project:

 *     -

 * Assignment: Midterm - Micro-Project 1 (Part 1)

 * Creation Date: 11, 2017 26

 * Last Modified: 11, 2017 30

 * Java Version: 1.8.1_141

 * Description: Controls for pop out window providing the user with login and password prompts

 * ----------------------------------------------------------------------------+

 */

package com.views.userregister;


import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageType;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UserRegister class provides functionality for UserRegister.fxml, allowing user to register using name and group
 * values.  User is prevented from entering blank fields.  This class will check for a match with last_Name, first_Name
 * and Group, updating the database with attributes if provided details are correct.
 */
public class UserRegister extends PageView implements Initializable {

    private static final String LOCK_OUT_CODE = "xxxxxx";

    @FXML
    private Button btnOK, btnExit;

    @FXML
    private Label lblLastName, lblFirstName, lblGroup, lblLogin, lblPassword,
            lblPreferredCarName, lblCredit, lblScore, lblLogo, lblEmpty, lblLockout, lblValidInput;

    @FXML
    private TextField txtLastName, txtFirstName, txtGroup, txtLogin,
            txtPreferredCarName, txtCredit, txtScore, txtLogo;

    @FXML
    private PasswordField txtPassword;

    private ResultSet rs = null;

    private ConnectToDB dbConnection;

    // constructor that creates connection to DB and sets object as pop out type page
    public UserRegister(ConnectToDB dbConnection) {

        pageType = PageType.POP_UP;

        // select DB for user detail verification
        try {
            this.dbConnection = dbConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLHelper.loadControl(this).load();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Query DB for lastname, firstname & group and check against user input for validity Makes sure user does not
     * already have an account registered
     *
     * @throws HeadlessException
     * @throws SQLException
     */
    public void isValid() throws HeadlessException, SQLException {
        rs = dbConnection.executeQuerry("SELECT * FROM DBProg32758.Players WHERE Last_Name = '"
                + txtLastName.getText().trim() + "' AND First_Name = '" + txtFirstName.getText().trim() + "' AND " +
                "`Group` = '"
                + Integer.valueOf(txtGroup.getText().trim()) + "' OR `Login`='" + txtLogin.getText().trim() + "'ORDER BY 'Login'");

        rs.beforeFirst();
        rs.last();
        int size = rs.getRow();
        if (size > 1) {

            JOptionPane.showMessageDialog(null, "The desired Login has already been taken"
                    , "Car Racing Game", JOptionPane.WARNING_MESSAGE);

            return;
        }
        if (rs.last()) {

            String player = rs.getString(4);
            if (player != null && !player.equals("")) {
                JOptionPane.showMessageDialog(null, "You have already registered an account.", "Car Racing Game",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                writeToDB();
            }
            rest();
        } else {
            JOptionPane.showMessageDialog(null, "Either you are not registered or your Last Name, First Name, Group " +
                    "are not correct.", "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
            rest();
        }
    }

    //Clears user's row and enters new one with all fields from user input
    public void writeToDB() {

        try {
            dbConnection.executeUpdate(String.format("UPDATE DBProg32758.Players SET `Group`=%d, `Login`='%s'" +
                            ", `Password`='%s', `Preferred_Car_Name`='%s', `Logo`=%d, `Score`=%d, `Credits`=%4.2f " +
                            "WHERE `Last_Name`='%s' AND `First_Name`='%s'", Integer.valueOf(txtGroup.getText().trim()),
                    txtLogin.getText().trim()
                    , txtPassword.getText(), txtPreferredCarName.getText(), Integer.valueOf(txtLogo.getText().trim())
                    , Integer.valueOf(txtScore.getText().trim()), Double.valueOf(txtCredit.getText().trim())
                    , txtLastName.getText().trim(), txtFirstName.getText().trim()));

            JOptionPane.showMessageDialog(null, "You are now registered, you can login.", "Car Racing Game",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "SQL State: " + e.getSQLState() + " ErrorCode: " + e
                    .getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method for text field listener to prevent user from clicking OK button with empty or incorrect fields
     */
    public void ValidateInput() {

        ValidIntegers();
        CheckLockout();
        BlankFields();
        ValidCredits(txtCredit.getText());
        if (ValidIntegers() && CheckLockout() && BlankFields() && ValidCredits(txtCredit.getText())) {
            btnOK.setDisable(false);
        } else {
            btnOK.setDisable(true);
        }
    }

    public boolean ValidCredits(String str) {

        try {
            Float i = Float.parseFloat(str);
            if (i > 99.99) {
                lblValidInput.setVisible(true);
                return false;
            }
        } catch (NumberFormatException nfe) {
            lblValidInput.setVisible(true);
            return false;
        }
        lblValidInput.setVisible(false);
        return true;
    }

    public boolean BlankFields() {

        if (!txtLastName.getText().equals("") && !txtFirstName.getText().equals("") && !txtPassword.getText().equals
                (LOCK_OUT_CODE)
                && !txtPassword.getText().equals("") && !txtGroup.getText().equals("") && !txtLogin.getText().equals("")
                && !txtPreferredCarName.getText().equals("") && !txtCredit.getText().equals("") && !txtScore.getText
                ().equals("")
                && !txtLogo.getText().equals("")) {
            lblEmpty.setVisible(false);
            return true;
        } else {
            lblEmpty.setVisible(true);
            return false;
        }
    }

    public boolean CheckLockout() {

        if (!txtPassword.getText().equals(LOCK_OUT_CODE)) {
            lblLockout.setVisible(false);
            return true;
        } else {
            lblLockout.setVisible(true);
            return false;
        }
    }

    public boolean ValidIntegers() {

        if (isInt(txtGroup.getText()) && isInt(txtScore.getText())) {
            lblValidInput.setVisible(false);
            return true;
        } else {
            lblValidInput.setVisible(true);
            return false;
        }
    }

    public boolean isInt(String str) {

        try {
            Integer i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        lblLockout.setVisible(false);
        lblValidInput.setVisible(false);
        lblEmpty.setText("No fields can be left blank.");
        lblLockout.setText("Password cannot be 'xxxxxx'.");
        lblValidInput.setText("Group and Score values must be integers. Credits must be valid number.");

        txtLastName.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtFirstName.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtGroup.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtLogin.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtPassword.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtPreferredCarName.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtCredit.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtScore.textProperty().addListener(p -> {
            ValidateInput();
        });

        txtLogo.textProperty().addListener(p -> {
            ValidateInput();
        });

        // verify user details when OK button is clicked
        btnOK.setOnAction(evt -> {

            try {
                isValid();

            } catch (HeadlessException e) {
                e.printStackTrace();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // exit login pop out page
        btnExit.setOnAction(p -> pageController.hidePopUps(0));
    }

    /**
     * reset fields for next user registration
     */
    public void rest() {

        txtFirstName.clear();
        txtLastName.clear();
        txtGroup.clear();
        txtLogin.clear();
        txtPassword.clear();
        txtPreferredCarName.clear();
        txtCredit.clear();
        txtLogo.clear();
        txtScore.clear();
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */

    @Override
    public void onClose(Object sender, int statusCode) {

        rest();
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

        txtLastName.requestFocus();

    }
}
