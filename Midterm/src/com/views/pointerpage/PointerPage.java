/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: PointerPage.java
 * Main class: 
 * Other Files in this Project:
 *     com
 *      ├── controls
 *      │   └── banner
 *      │       ├── Banner.fxml
 *      │       └── Banner.java
 *      ├── util
 *      │   ├── FXMLHelper.java
 *      │   ├── PageController.java
 *      │   └── PageView.java
 *      └── views
 *          ├── adminPage
 *          │   ├── AdminPage.fxml
 *          │   └── AdminPage.java
 *          ├── pointerPage
 *          │   └── PointerPage.fxml
 *          └── startPage
 *              ├── StartPage.fxml
 *              └── StartPage.java
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: Scene the controls page visibility
 * ----------------------------------------------------------------------------+
 */

package com.views.pointerpage;

import com.util.ConnectToDB;
import com.util.PageController;
import com.views.startpage.StartPage;

import java.net.URL;
import java.util.ResourceBundle;

public class PointerPage extends PageController {

    private javafx.fxml.FXMLLoader loader;

    private ConnectToDB dbConnection;

    public PointerPage() {

        loader = com.util.FXMLHelper.loadControl(this);
    }

    public javafx.fxml.FXMLLoader getLoader() {

        return loader;
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        super.initialize(location, resources);
        try {

            dbConnection = new ConnectToDB("localhost", "root", "");
        } catch (java.sql.SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage() + "SQL State: "
                            + e.getSQLState() + " ErrorCode: " + e.getErrorCode(), "Car Racing Game",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }

        initMainPage(new StartPage(dbConnection));
        showMain();
    }

    public int close() {

        dbConnection.closeConnection();
        return 0;
    }
}
