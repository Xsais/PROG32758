package com.views.userlogin;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {

    private static final String LOCK_OUT_CODE = "xxxxxx";

    public UserLogin(com.util.ConnectToDB dbConnection) {

        String[] userCred = new String[2];

        // check if user defined login and password are valid
        try {
            if (isValidLogin(dbConnection, userCred)) {

                // TODO: open page to play game

                // inform user after 3 failed attempts that they have been locked out of the database
            } else {

                javax.swing.JOptionPane.showMessageDialog(null,
                        "Your account has been locked\n Please contact your Database " + "Administrator", "Car Racing Game",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                try {

                    dbConnection.executeUpdate(
                            String.format("UPDATE DBProg32758.Players SET Password = '%s' WHERE Login = '%s'", LOCK_OUT_CODE, userCred[0]));

                } catch (java.sql.SQLException e) {

                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isValidLogin(com.util.ConnectToDB dbConnection, String[] userCred) throws HeadlessException, SQLException {

        // Create pop up window for user to enter login and password
        JTextField login = new JTextField();
        JTextField password = new JPasswordField();

        Object[] userInput = {new JLabel("User Login: "), login, new JLabel("Password: "), password};

        // check for valid login and password combo 3 times
        for (int i = 0; i < 3; i++) {

            // show login pop up window to user
            JOptionPane.showConfirmDialog(null, userInput, "Please enter your login and password",
                    JOptionPane.DEFAULT_OPTION);

            // account for empty fields and prompt user for corrections to mistakes
            if (login.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Login fields can not be empty",
                        "Car Racing Game", JOptionPane.WARNING_MESSAGE);

                --i;
                continue;
            }

            // capture user data
            userCred[0] = login.getText();
            userCred[1] = password.getText();

            ResultSet rs = null;


            // check existence of user login
            try {

                rs = dbConnection.executeQuerry(String.format("SELECT * FROM DBProg32758.Players WHERE Login = '%s'", userCred[0]));

            } catch (SQLException e) {

                e.printStackTrace();
            }
            if (rs.next()) {
                // check if user account is already locked and break loop if so
                if (rs.getString("Password").equals(LOCK_OUT_CODE)) {


                    break;

                    // check password
                } else if (rs.getString("Password").equals(userCred[1])) {

                    return true;

                    // inform user that user login and/or password are incorrect
                } else {

                    JOptionPane.showMessageDialog(null,
                            "The Login and Password are not correct\n Please try again"
                                    + " if you are already registered or complete your registration if you are not",
                            "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {

                JOptionPane.showMessageDialog(null,
                        "The Login and Password are not correct\n Please try again"
                                + " if you are already registered or complete your registration if you are not",
                        "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // returns true if valid user login and password are given, false if not
        return false;
    }
}
