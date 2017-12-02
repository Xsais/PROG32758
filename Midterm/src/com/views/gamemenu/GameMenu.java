/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.views.gamemenu.GameMenu
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.views.gamemenu;

import com.controls.playermenu.PlayerMenu;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import com.util.info.User;
import com.util.jdbc.ConnectToDB;
import com.views.gamepage.GamePage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameMenu extends PageView implements Initializable {

    private ConnectToDB dbConnection;

    @FXML
    private Button btnStart;

    @FXML
    private PlayerMenu pmPlayer;

    private User user;

    private StringProperty username = new SimpleStringProperty(this, "username", null);

    private PreparedStatement pullInfo;

    public GameMenu(ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;

        gamePage = new GamePage(dbConnection);

        try {

            pullInfo = dbConnection.getConnection().prepareStatement("SELECT `Last_Name`, " +
                    "`First_Name`, `Group`, `Preferred_Car_Name`, `Logo`, `Score`, `Credits` FROM " +
                    "DBProg32758.Players WHERE `Login`=?");
        } catch (SQLException e) {

            e.printStackTrace();
        }

        username.addListener(evt -> setUser());
        pmPlayer = new PlayerMenu();

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

                this.user = new User(userInfo.getString(2), userInfo.getString(1), userInfo.getInt
                        (3), username, userInfo.getString(4), userInfo.getInt(5), userInfo.getInt
                        (6), userInfo.getDouble(7));

                pmPlayer.setUsedPlayer(user);
                gamePage.setActiveUser(user);
            }

            userInfo.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */
    @Override
    public void onClose(Object sender, int statusCode) { }

    /**
     * Occurs when the application classes peacefully
     *
     * @param evt The WindowEvent associated with the closure
     */
    @Override
    public void onCloseRequest(WindowEvent evt) {

    }

    @Override
    public void dispose() {

        pmPlayer.dispose();

        try {

            pullInfo.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * Occurs when the PageView has been requested to open
     *
     * @param sender The Object in which sent the request
     */
    @Override
    public void onOpen(Object sender, String... args) {

    }

    private GamePage gamePage;

    @Override
    public void init(PageController pageController) {

        super.init(pageController);

        pageController.registerPage(gamePage);
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

        btnStart.setOnAction(e -> {

            pmPlayer.setPlayingMusic(false);

            pageController.show(gamePage, String.format("musicpos=%s", pmPlayer.getMusicPosition())
                    , String.format("musicstate=%d", pmPlayer.isPlayingMusic() ? 1 : 0)
                    , String.format("scoredisplay=%d", pmPlayer.isScoreVisible() ? 1 : 0));
        });
    }

    public String getUsername() {

        return username.get();
    }

    public void setUsername(String username) {

        this.username.set(username);
    }

    public StringProperty usernameProperty() {

        return username;
    }
}
