/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.views.adminpage.AdminPage
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.views.adminpage;

import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import com.util.jdbc.CreateDataBase;
import com.util.jdbc.InitializeDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

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
            FXMLHelper.loadControl(this).load();
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

        btnExit.setOnAction(evt -> pageController.showPrevious());

        btnCreate.setOnAction(p -> new CreateDataBase(dbConnection));

        btnInit.setOnAction(p -> new InitializeDatabase(dbConnection));

        /* TODO : Debase Display to be implemented
            btnDisplay.setOnAction();
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
    public void onOpen(Object sender, String... args) {

    }

    /**
     * Passes the reference of the PageController to registered PageView
     *
     * @param pageController The PageController that is the holder of the PageView
     */
    @Override
    public void init(PageController pageController) {

        super.init(pageController);

    }
}
