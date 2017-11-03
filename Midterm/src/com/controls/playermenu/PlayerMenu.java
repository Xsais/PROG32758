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
import java.io.IOException;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class PlayerMenu extends GridPane implements Initializable {
	
	// Stores the current score to be visually displayed
	public IntegerProperty score = new SimpleIntegerProperty(this, "score", 0);
	
	
	// STores the current credit to be visually displayed
	public DoubleProperty credit = new SimpleDoubleProperty(this, "credit", 0);
	
	// instantiate MusicPlayer Object for use in vbMusic action event
	MusicPlayer musicPlayer = new MusicPlayer();
	
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
	
	// Determines weather the score is visible to the user
	private BooleanProperty scoreVisible = new SimpleBooleanProperty(this, "scoreVisible", false);
	
	// Determines weather the credit is visible to the user
	private BooleanProperty creditVisible = new SimpleBooleanProperty(this, "creditVisible",
			false);
	
	
	/**
	 * Initializes the control and load the look and feel.
	 */
	public PlayerMenu() {
		
		try {
			
			// Loads the given control and links it to the desired FXML file
			FXMLHelper.loadControl(this).load();
		} catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * Retrieves the current displayed score.
	 *
	 * @return The score that is currently being displayed.
	 */
	public int getScore() {
		
		return score.get();
		
	}
	
	
	/**
	 * Assigns the desired score to be visually displayed.
	 *
	 * @param score The desired score to be displayed.
	 */
	public void setScore(int score) {
		
		this.score.set(score);
		
	}
	
	
	/**
	 * Retrieves the property that represents the displayed score.
	 *
	 * @return The property that represents the displayed score.
	 */
	public IntegerProperty scoreProperty() {
		
		return score;
		
	}
	
	/**
	 * Retrieves the current displayed credit.
	 *
	 * @return The currently displayed Credit.
	 */
	public double getCredit() {
		
		return credit.get();
		
	}
	
	
	/**
	 * Assigns the currently displayed credit
	 *
	 * @param credit
	 */
	public void setCredit(double credit) {
		
		this.credit.set(credit);
		
	}
	
	/**
	 * Retrieves the property that represents the displayed credit.
	 *
	 * @return The property that represents the displayed credit.
	 */
	public DoubleProperty creditProperty() {
		
		
		return credit;
		
	}
	
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 *
	 * @param location The location used to resolve relative paths for the root object, or
	 * 		<tt>null</tt> if the location is not known.
	 * @param resources The resources used to localize the root object, or <tt>null</tt> if
	 */
	
	@Override
	
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
		
		
		// Binds the credit to be displayed with the desired format
		lblCredit.textProperty().bind(credit.asString("$%.2f"));
		
		
		lblScore.textProperty().bind(score.asString());
		
		vbScore.setOnMouseClicked(evt->scoreVisible.set(!scoreVisible.get()));
		
		vbCredit.setOnMouseClicked(evt->creditVisible.set(!creditVisible.get()));
		
		vbMusic.setOnMouseClicked(evt->musicPlayer.play_pauseMusic());
		
		vbScoreContainer.visibleProperty().bind(scoreVisible);
		
		vbCreditContainer.visibleProperty().bind(creditVisible);
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
	 * Retrieves a value that determines the visibility of the Credit.
	 *
	 * @return The current visibility of the displayed Credit.
	 */
	public boolean isCreditVisible() {
		
		return creditVisible.get();
	}
	
	/**
	 * Assigns the visibility of the displayed Credit.
	 *
	 * @param creditVisible The desired visibility of the displayed Credit.
	 */
	public void setCreditVisible(boolean creditVisible) {
		
		
		this.scoreVisible.set(creditVisible);
		
	}
	
	/**
	 * Retrieves the property in which defines the visibility of the Credit.
	 *
	 * @return The property that determines the visibility of the Credit.
	 */
	public BooleanProperty creditVisibleProperty() {
		
		
		return creditVisible;
		
	}
	
	/**
	 * Disposes the Object and releases all resources used
	 */
	public void dispose() {
		
		musicPlayer.dispose();
	}
	
}
