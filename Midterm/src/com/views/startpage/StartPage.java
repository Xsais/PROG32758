/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.views.startpage.StartPage
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.views.startpage;

import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import com.views.adminpage.AdminPage;
import com.views.userpage.UserPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPage extends PageView implements Initializable {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnUser;

    private UserPage userPage;

    private ConnectToDB dbConnection;

    private AdminPage adminPage;

    /**
     * Initializes the Page
     *
     * @param dbConnection A valid copy of ConnectToDB
     */
    public StartPage(ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;

        try {

            FXMLHelper.loadControl(this).load();
        } catch (IOException e) {
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
    public void onCloseRequest(WindowEvent evt) {

    }

    /**
     * Occurs when the PageView has been requested to open
     *
     * @param sender The Object in which sent the request
     */
    @Override
    public void onOpen(Object sender) {

    }

    /**
     * Passes the reference of the PageController to registered PageView
     *
     * @param pageController The PageController that is the holder of the PageView
     */
    @Override
    public void init(PageController pageController) {

        super.init(pageController);

        pageController.registerPage(adminPage);
        pageController.registerPage(userPage);
    }
}
