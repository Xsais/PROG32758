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

import com.util.ConnectToDB;
import com.util.PageController;
import com.util.PageView;
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

    private PageView userPage;

    private ConnectToDB dbConnection;

    private PageView adminPage;

    public StartPage(ConnectToDB dbConnection) {

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
    public void initialize(URL location, ResourceBundle resources) {

        btnAdmin.setText("Administrator\n(DB Creation and Initialization)");

        btnUser.setText("User\n(Sign-up and Login)");

        btnUser.setOnAction(evt -> pageController.showPage(userPage));

        btnAdmin.setOnAction(evt -> pageController.showPage(adminPage));

        adminPage = new AdminPage(dbConnection);
        userPage = new UserPage(dbConnection);
    }

    @Override
    public void init(PageController pageController) {

        super.init(pageController);
        pageController.registerPage(adminPage);
        pageController.registerPage(userPage);
    }
}
