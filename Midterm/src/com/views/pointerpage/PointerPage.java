/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.views.pointerpage.PointerPage
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.views.pointerpage;

import com.controls.banner.Banner;
import com.util.fxml.FXMLHelper;
import com.util.fxml.NodeConstraint;
import com.util.fxml.page.PageController;
import com.util.jdbc.ConnectToDB;
import com.views.startpage.StartPage;
import javafx.fxml.FXMLLoader;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PointerPage extends PageController {

    private FXMLLoader fxmlLoader;

    private ConnectToDB dbConnection;

    private StartPage startPage;

    public PointerPage() {

        super(new NodeConstraint(new Banner(), 0, 0));
        fxmlLoader = FXMLHelper.loadControl(this);
    }

    public FXMLLoader getLoader() {

        return fxmlLoader;
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

            // Create Connection to DataBase
            dbConnection = new ConnectToDB("localhost", "root", "");
        } catch (SQLException e) {

            // Warn User of error
            JOptionPane.showMessageDialog(null, String.format("%s SQL State: %s ErrorCode: %d", e
                            .getMessage(), e.getSQLState(), e.getErrorCode()), "Car Racing Game",
                    JOptionPane.WARNING_MESSAGE);

            System.exit(-1);
            close(null);
        }

        startPage = new StartPage(dbConnection);

        // Assigns the `main` page to the Page Controller
        initMainPage(startPage);
        showMain();
    }

    @Override
    public void dispose() {

        if (dbConnection == null) {

            return;
        }
        dbConnection.closeConnection();
    }

}
