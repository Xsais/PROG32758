package com.views.gamepage;

import com.controls.game.Game;
import com.controls.playermenu.PlayerMenu;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageView;
import com.util.info.User;
import com.util.jdbc.ConnectToDB;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GamePage extends PageView implements Initializable {

    @FXML
    private Game gDisplay;

    private SimpleIntegerProperty totalRuns = new SimpleIntegerProperty(this, "totalRuns");

    @FXML
    private PlayerMenu pmPlayer;

    private ConnectToDB dbConnection;

    private SimpleObjectProperty<User> activeUser = new SimpleObjectProperty<>(this, "activeUser");

    private PreparedStatement finalUpdate;

    public GamePage(ConnectToDB dbConnection, User user) {

        this(dbConnection);
        setActiveUser(user);
    }

    public GamePage(ConnectToDB dbConnection) {

        try {

            FXMLHelper.loadControl(this).load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDbConnection(dbConnection);
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */
    @Override
    public void onClose(Object sender, int statusCode) {

        if (finalUpdate == null && activeUser != null || totalRuns.get() < 1) {

            return;
        }

        try {

            finalUpdate.setInt(1, activeUser.get().getScore());

            finalUpdate.setDouble(2, activeUser.get().getCredit());

            finalUpdate.setString(3, activeUser.get().getFullName().getFirstName());

            finalUpdate.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * Occurs when the application closes peacefully
     *
     * @param evt The WindowEvent associated with the closure
     */
    @Override
    public void onCloseRequest(WindowEvent evt) {

        onClose(this, 1);

        try {
            finalUpdate.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        pmPlayer = new PlayerMenu();
    }

    /**
     * Occurs when the PageView has been requested to open
     *
     * @param sender The Object in which sent the request
     */
    @Override
    public void onOpen(Object sender) {

        gDisplay.startGame();
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

        gDisplay.setWinningAction(i -> {

            totalRuns.add(1);

            User activeUser = this.activeUser.get();

            Platform.runLater(() -> activeUser.setScore(activeUser.getScore() + 50));

            return 0;
        });
    }

    public User getActiveUser() {

        return activeUser.get();
    }

    public void setActiveUser(User activeUser) {

        this.activeUser.set(activeUser);
        this.pmPlayer.setUsedPlayer(activeUser);
    }

    public SimpleObjectProperty<User> activeUserProperty() {

        return activeUser;
    }

    public ConnectToDB getDbConnection() {

        return dbConnection;
    }

    public void setDbConnection(ConnectToDB dbConnection) {

        this.dbConnection = dbConnection;

        try {
            finalUpdate = dbConnection.getConnection()
                    .prepareStatement("UPDATE dbprog32758.players SET `Score`=?, `Credits`=? WHERE `Login`=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalRuns() {

        return totalRuns.get();
    }

    public ReadOnlyIntegerProperty totalRunsProperty() {

        return totalRuns;
    }
}
