/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: StartPage.java
 * Main class: com.init.Main.java
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
 *          │   ├── PointerPage.fxml
 *          │   └── PointerPage.java
 *          └── startPage
 *              └── StartPage.fxml
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: Controller for the starting of the Game
 * ----------------------------------------------------------------------------+
 */

package com.views.startpage;

import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import com.views.adminpage.AdminPage;
import com.views.userpage.UserPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPage extends PageView implements Initializable {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnUser;

    private com.views.userpage.UserPage userPage;

    private ConnectToDB dbConnection;

    private com.views.adminpage.AdminPage adminPage;

    public StartPage(ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;

        try {
            com.util.fxml.FXMLHelper.loadControl(this).load();
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
    public void initialize(URL location, ResourceBundle resources) {

        btnAdmin.setText("Administrator\n(DB Creation and Initialization)");

        btnUser.setText("User\n(Sign-up and Login)");

        btnUser.setOnAction(evt -> pageController.show(userPage));

        btnAdmin.setOnAction(evt -> pageController.show(adminPage));

        adminPage = new AdminPage(dbConnection);
        userPage = new UserPage(dbConnection);
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */
    @Override
    public void onClose(Object sender, int statusCode) {

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

    @Override
    public void init(PageController pageController) {

        super.init(pageController);
        pageController.registerPage(adminPage);
        pageController.registerPage(userPage);
    }
}
