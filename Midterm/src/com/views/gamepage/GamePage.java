package com.views.gamepage;

import com.controls.game.Game;
import com.controls.playermenu.PlayerMenu;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageView;
import com.util.game.state.GameState;
import com.util.info.Name;
import com.util.info.User;
import com.util.jdbc.ConnectToDB;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GamePage extends PageView implements Initializable {

    @FXML
    private Game gDisplay;

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
    public void onClose(Object sender, int statusCode) { }

    @Override
    public void dispose() {

        try {
            finalUpdate.close();
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

        try {

            if (finalUpdate == null && activeUser.get() == null && finalUpdate.isClosed()) {

                return;
            }

            finalUpdate.setInt(1, activeUser.get().getScore());

            finalUpdate.setDouble(2, activeUser.get().getCredit());

            Name playerName = activeUser.get().getFullName();

            finalUpdate.setString(3, playerName.getLastName());

            finalUpdate.setString(4, playerName.getFirstName());

            finalUpdate.executeUpdate();
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

        for (String arg : args) {

            String[] values = arg.split("=");

            switch (values[0]) {

                case "musicstate":

                    if (values[1].equals("0")) {

                        break;
                    }
                    pmPlayer.setPlayingMusic(true);
                    break;
                case "musicpos":

                    if (values[1].equals("0.0 ms")) {

                        break;
                    }
                    pmPlayer.setMusicPosition(Duration.valueOf(values[1].replace(" ", "")));
                    break;
                case "scoredisplay":

                    if (values[1].equals("0")) {

                        break;
                    }
                    pmPlayer.setScoreVisible(true);
                    break;
            }
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

        pmPlayer.setButtonText("Play");

        pmPlayer.setToggleFunction(s -> {

            switch (gDisplay.getGameState()) {

                case Running:

                    gDisplay.setGameState(GameState.Paused);
                    break;
                case Paused:
                case Stopped:

                    gDisplay.setGameState(GameState.Running);
                    break;
            }

            return null;
        });

        gDisplay.gameStateProperty().addListener((s, old, newV) -> {

            switch (newV) {

                case Running:

                    Platform.runLater(() -> pmPlayer.setButtonText("Pause"));
                    break;
                case Stopped:
                case Paused:

                    Platform.runLater(() -> pmPlayer.setButtonText("Play"));
                    break;
            }
        });

        gDisplay.setWinningAction(i -> {

            Platform.runLater(() -> pmPlayer.setInfoMessage("Congratulations, yon won"));

            User activeUser = this.activeUser.get();

            Platform.runLater(() -> activeUser.setScore(activeUser.getScore() + 50));

            return 0;
        });

        gDisplay.setLosingAction(i -> {

            Platform.runLater(() -> pmPlayer.setInfoMessage("You lose, try again"));

            return 0;
        });
    }

    public User getActiveUser() {

        return activeUser.get();
    }

    public void setActiveUser(User activeUser) {

        this.activeUser.set(activeUser);
        this.pmPlayer.setUsedPlayer(activeUser);
        this.gDisplay.setPlayingUser(activeUser);
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
                    .prepareStatement("UPDATE dbprog32758.players SET `Score`=?, `Credits`=? WHERE `Last_Name`=? AND " +
                            "`First_Name`=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
