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
 * Assignment: 
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: Controller for the admin section of the game
 * ----------------------------------------------------------------------------+
 */

package com.views.adminpage;

import javafx.fxml.Initializable;
import com.util.PageView;
import com.util.PageController;
import com.util.FXMLHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPage extends PageView implements Initializable {

    private PageController pageController;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnInit;

    @FXML
    private Button btnDisplay;

    public AdminPage() {

        FXMLHelper.loadControl(this);
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

        btnExit.setOnAction(evt -> pageController.showPrevious());

        btnCreate.setOnAction(p -> com.util.CreateDataBase.createTable());

        /** TODO : Debase Initialization
            btnInit.setOnAction();
         **/

        /** TODO : Debase Display
            btnDisplay.setOnAction();
         **/

    }

    @Override
    public void init(PageController pageController) {

        this.pageController = pageController;
    }
}
