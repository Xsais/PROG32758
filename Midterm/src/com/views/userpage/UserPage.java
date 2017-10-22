/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: UserPage.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 21
 * Last Modified: 10, 2017 21
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.views.userpage;

import com.util.PageView;
import com.util.FXMLHelper;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.util.ConnectToDB;

public class UserPage extends PageView implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCreate;

    private ConnectToDB dbConnection;

    public UserPage(ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;
        try {
            com.util.FXMLHelper.loadControl(this).load();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        /* TODO: User Login
            btnLogin.setOnAction();
        */

        /* TODO: User Creation
            btnCreate.setOnAction();
        */
    }
}
