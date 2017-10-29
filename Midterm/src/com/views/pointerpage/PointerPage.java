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
import com.util.FXMLHelper;
import com.util.PageController;
import com.views.startpage.StartPage;
import javafx.fxml.FXMLLoader;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PointerPage extends PageController {

    private javafx.fxml.FXMLLoader loader;

    private ConnectToDB dbConnection;

    private StartPage startPage;

    public PointerPage() {

        loader = FXMLHelper.loadControl(this);
    }

    public FXMLLoader getLoader() {

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
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage() + "SQL State: "
                            + e.getSQLState() + " ErrorCode: " + e.getErrorCode(), "Car Racing Game",
                    JOptionPane.WARNING_MESSAGE);

            System.exit(-1);
            close(null);
        }

        startPage = new StartPage(dbConnection);

        initMainPage(startPage);
        showMain();
    }

    @Override
    public void close(javafx.stage.WindowEvent evt) {

        super.close(evt);
        if (dbConnection == null) {

            return;
        }
        dbConnection.closeConnection();
        return;
    }
}
