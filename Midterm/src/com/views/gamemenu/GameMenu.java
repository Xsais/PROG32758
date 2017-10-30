/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: GameMenu.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 28
 * Last Modified: 10, 2017 28
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.views.gamemenu;

import com.controls.playermenu.PlayerMenu;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;

public class GameMenu extends PageView implements Initializable {

    private final com.util.jdbc.ConnectToDB dbConnection;

    @FXML
    private Button btnStart;

    @FXML
    private PlayerMenu pmPlayer;

    private com.util.info.User user;

    private javafx.beans.property.StringProperty username = new javafx.beans.property.SimpleStringProperty(this, "username", null);

    private java.sql.PreparedStatement pullInfo;

    public GameMenu(com.util.jdbc.ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;

        try {

            pullInfo = dbConnection.getConnection().prepareStatement("SELECT `Last_Name`, `First_Name`, `Group`, `Preferred_Car_Name`, `Logo`, `Score` FROM DBProg32758.Players WHERE `Login`=?");
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }

        username.addListener(evt -> setUser());

        try {

            FXMLHelper.loadControl(this).load();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void setUser() {

        try {

            String username = this.username.get();

            pullInfo.setString(1, username);
            java.sql.ResultSet userInfo = pullInfo.executeQuery();

            if (userInfo.first()) {

                this.user = new com.util.info.User(userInfo.getString(1), userInfo.getString(2), userInfo.getInt(3), username, userInfo.getString(4), userInfo.getInt(5), userInfo.getInt(6));

                pmPlayer.scoreProperty().bind(this.user.scoreProperty());
                pmPlayer.creditProperty().bind(this.user.creditProperty());
            }

            userInfo.close();
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void init(PageController pageController) {

        super.init(pageController);
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */
    @Override
    public void onClose(Object sender, int statusCode) {

        pmPlayer.dispose();
    }

    /**
     * Occurs when the application classes peacefully
     *
     * @param evt The WindowEvent associated with the closure
     */
    @Override
    public void onCloseRequest(javafx.stage.WindowEvent evt) {

        try {

            pullInfo.close();
        } catch (java.sql.SQLException e) {

            e.printStackTrace();
        }
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
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        /* TODO: Game Page
        btnStart.setOnAction();
        */
    }

    public String getUsername() {

        return username.get();
    }

    public void setUsername(String username) {

        this.username.set(username);
    }

    public javafx.beans.property.StringProperty usernameProperty() {

        return username;
    }
}
