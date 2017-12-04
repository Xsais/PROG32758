/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.controls.playermenu.PlayerMenu
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.controls.playermenu;

import com.util.MusicPlayer;
import com.util.fxml.FXMLHelper;
import com.util.info.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.function.Function;


public class PlayerMenu extends GridPane implements Initializable {

    public double refillAmount = 5;

    // instantiate MusicPlayer Object for use in vbMusic action event
    MusicPlayer musicPlayer = new MusicPlayer();

    private User usedPlayer;

    @FXML
    private VBox vbMusic;


    @FXML
    private VBox vbScore;


    @FXML
    private VBox vbCredit;


    @FXML
    private VBox vbScoreContainer;


    @FXML
    private VBox vbCreditContainer;


    @FXML
    private Label lblScore;


    @FXML
    private Label lblCredit;

    private SimpleStringProperty infoMessage = new SimpleStringProperty(this, "infoMessage");

    private SimpleStringProperty buttonText = new SimpleStringProperty(this, "buttonText");

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnToggleGame;

    // Determines weather the score is visible to the user
    private BooleanProperty scoreVisible = new SimpleBooleanProperty(this, "scoreVisible", false);

    private Function<Object, String> toggleFunction;


    /**
     * Initializes the control and load the look and feel.
     */
    public PlayerMenu() {

        try {

            // Loads the given control and links it to the desired FXML file
            FXMLHelper.loadControl(this).load();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    /**
     * Retrieves the current displayed score.
     *
     * @return The score that is currently being displayed.
     */
    public int getScore() {

        if (this.usedPlayer == null) {

            return 0;
        }
        return this.usedPlayer.getScore();

    }


    /**
     * Assigns the desired score to be visually displayed.
     *
     * @param score The desired score to be displayed.
     */
    public void setScore(int score) {

        if (this.usedPlayer == null) {

            return;
        }
        this.usedPlayer.setScore(score);

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

        btnToggleGame.visibleProperty().bind(btnToggleGame.textProperty().isNotEmpty());

        lblInfo.visibleProperty().bind(lblInfo.textProperty().isNotEmpty());

        btnToggleGame.textProperty().bind(buttonText);

        btnToggleGame.setOnMouseClicked(e -> {

            if (toggleFunction == null) {

                return;
            }

            String message = toggleFunction.apply(this);

            if (message == null) {

                return;
            }

            infoMessage.set(message);
        });

        lblInfo.textProperty().bind(infoMessage);

        vbScore.setOnMouseClicked(evt -> scoreVisible.set(!scoreVisible.get()));

        vbCredit.setOnMouseClicked(evt -> {
            // set initial credit
            double tempGetCredit = getCredit();

            // increment credit by $5 for each button click
            tempGetCredit += refillAmount;

            // display incremented credit to user
            setCredit(tempGetCredit);
        });

        vbMusic.setOnMouseClicked(evt -> musicPlayer.play_pauseMusic());

        vbScoreContainer.visibleProperty().bind(scoreVisible);
    }

    /**
     * Retrieves the current displayed credit.
     *
     * @return The currently displayed Credit.
     */
    public double getCredit() {

        if (this.usedPlayer == null) {

            return 0;
        }
        return this.usedPlayer.getCredit();

    }

    /**
     * Assigns the currently displayed credit
     *
     * @param credit
     */
    public void setCredit(double credit) {

        if (this.usedPlayer == null) {

            return;
        }
        this.usedPlayer.setCredit(credit);

    }

    /**
     * Retrieves a value that determines the visibility of the score.
     *
     * @return The current visibility of the displayed score.
     */
    public boolean isScoreVisible() {

        return scoreVisible.get();
    }

    /**
     * Assigns the visibility of the displayed score.
     *
     * @param scoreVisible The desired visibility of the displayed score.
     */
    public void setScoreVisible(boolean scoreVisible) {

        this.scoreVisible.set(scoreVisible);
    }

    /**
     * Retrieves the property in which defines the visibility of the score.
     *
     * @return The property that determines the visibility of the Score.
     */
    public BooleanProperty scoreVisibleProperty() {

        return scoreVisible;

    }

    /**
     * Disposes the Object and releases all resources used
     */
    public void dispose() {

        musicPlayer.dispose();
    }

    public User getUsedPlayer() {

        return usedPlayer;
    }

    public void setUsedPlayer(User usedPlayer) {

        this.usedPlayer = usedPlayer;

        lblCredit.textProperty().bind(usedPlayer.creditProperty().asString("$%.2f"));

        lblScore.textProperty().bind(usedPlayer.scoreProperty().asString());
    }

    public double getRefillAmount() {

        return this.refillAmount;
    }

    public void setRefillAmount(double refillAmount) {

        this.refillAmount = refillAmount;
    }

    public boolean isPlayingMusic() {

        return musicPlayer.isPlaying();
    }

    public void setPlayingMusic(boolean playing) {

        musicPlayer.setPlaying(playing);
    }

    public Duration getMusicPosition() {

        return musicPlayer.getPosition();
    }

    public void setMusicPosition(Duration position) {

        musicPlayer.setPosition(position);
    }

    public String getInfoMessage() {

        return infoMessage.get();
    }

    public void setInfoMessage(String infoMessage) {

        this.infoMessage.set(infoMessage);
    }

    public SimpleStringProperty infoMessageProperty() {

        return infoMessage;
    }

    public Function<Object, String> getToggleFunction() {

        return toggleFunction;
    }

    public void setToggleFunction(Function<Object, String> toggleFunction) {

        this.toggleFunction = toggleFunction;
    }

    public String getButtonText() {

        return buttonText.get();
    }

    public void setButtonText(String buttonText) {

        this.buttonText.set(buttonText);
    }

    public SimpleStringProperty buttonTextProperty() {

        return buttonText;
    }
}
