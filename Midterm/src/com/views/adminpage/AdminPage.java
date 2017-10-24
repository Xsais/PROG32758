/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: AdminPage.java
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
 *          │   └── AdminPage.fxml
 *          ├── pointerPage
 *          │   ├── PointerPage.fxml
 *          │   └── PointerPage.java
 *          └── startPage
 *              ├── StartPage.fxml
 *              └── StartPage.java
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: Controller for the admin section of the game
 * ----------------------------------------------------------------------------+
 */

package com.views.adminpage;

import com.util.ConnectToDB;
import com.util.CreateDataBase;
import com.util.PageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPage extends PageView implements Initializable {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnInit;

    @FXML
    private Button btnDisplay;

    private ConnectToDB dbConnection;

    public AdminPage(ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;

        try {
            com.util.FXMLHelper.loadControl(this).load();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void s(javafx.beans.Observable p) {

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

        btnExit.setOnAction(evt -> pageController.showPrevious());

        btnCreate.setOnAction(p -> new CreateDataBase(dbConnection));

        /* TODO : Debase Initialization
            btnInit.setOnAction();
         */

        /* TODO : Debase Display
            btnDisplay.setOnAction();
         */

    }
}
