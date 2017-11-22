/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.views.userpage.UserPage
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.views.userpage;

import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import com.views.userlogin.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPage extends PageView implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCreate;

    private ConnectToDB dbConnection;

    private UserLogin userLogin;

    /**
     * Initalizes the UserPage
     *
     * @param dbConnection A valid copy of ConnectToDB
     */
    public UserPage(ConnectToDB dbConnection) {

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

        userLogin = new UserLogin(dbConnection);

        btnLogin.setOnAction(p -> pageController.show(userLogin));


        /* TODO: User to be added when complete

            btnCreate.setOnAction();

        */

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

    public void init(PageController pageController) {

        super.init(pageController);

        pageController.registerPage(userLogin);

    }
}
