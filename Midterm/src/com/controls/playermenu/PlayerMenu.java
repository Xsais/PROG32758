/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: PlayerMenu.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 27
 * Last Modified: 10, 2017 27
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.controls.playermenu;

import com.util.ConnectToDB;
import com.util.FXMLHelper;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlayerMenu extends GridPane implements Initializable {

    public javafx.beans.property.IntegerProperty score = new javafx.beans.property.SimpleIntegerProperty(this, "score", 0);

    public javafx.beans.property.DoubleProperty credit = new javafx.beans.property.SimpleDoubleProperty(this, "credit", 0);

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

    private ConnectToDB dbConnection;

    private BooleanProperty scoreVisible = new SimpleBooleanProperty(this, "scoreVisible", false);

    private BooleanProperty creditVisible = new SimpleBooleanProperty(this, "creditVisible", false);

    public PlayerMenu() {

        try {
            FXMLHelper.loadControl(this).load();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public int getScore() {

        return score.get();
    }

    public void setScore(int score) {

        this.score.set(score);
    }

    public javafx.beans.property.IntegerProperty scoreProperty() {

        return score;
    }

    public double getCredit() {

        return credit.get();
    }

    public void setCredit(double credit) {

        this.credit.set(credit);
    }

    public javafx.beans.property.DoubleProperty creditProperty() {

        return credit;
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

        lblCredit.textProperty().bind(credit.asString("$%.2f"));

        lblScore.textProperty().bind(score.asString());

        vbScore.setOnMouseClicked(evt -> scoreVisible.set(!scoreVisible.get()));
        vbCredit.setOnMouseClicked(evt -> creditVisible.set(!creditVisible.get()));

        /* TODO: Music Playback
        vbMusic.setOnMouseClicked(evt -> { });
        */

        vbScoreContainer.visibleProperty().bind(scoreVisible);
        vbCreditContainer.visibleProperty().bind(creditVisible);
    }

    public boolean isScoreVisible() {

        return scoreVisible.get();
    }

    public void setScoreVisible(boolean scoreVisible) {

        this.scoreVisible.set(scoreVisible);
    }

    public BooleanProperty scoreVisibleProperty() {

        return scoreVisible;
    }

    public boolean isCreditVisible() {

        return creditVisible.get();
    }

    public void setCreditVisible(boolean creditVisible) {

        this.scoreVisible.set(creditVisible);
    }

    public BooleanProperty creditVisibleProperty() {

        return creditVisible;
    }
}
