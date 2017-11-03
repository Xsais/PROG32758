/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.controls.banner.Banner
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.controls.banner;

import com.util.fxml.FXMLHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Banner extends GridPane implements Initializable {
	
	// Stores the name of the Group as given by the Teacher
	private final String groupName = "Group 1";
	
	// Stores names of the members in the group as provided by Slate
	private final String[] groupMembers = {"Daniel Hope", "Georgina Luce", "Nathaniel Primo",
	                                       "Michael Marc"};
	
	@FXML
	private VBox vbCredits;
	
	@FXML
	private Label lblDesignedBy;
	
	/**
	 * Initializes the control and load the look and feel
	 */
	public Banner() {
		
		try {
			
			// Loads the given control and links it to the desired FXML file
			FXMLHelper.loadControl(this).load();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 *
	 * @param location The location used to resolve relative paths for the root object, or
	 * 		<tt>null</tt> if the location is not known.
	 * @param resources The resources used to localize the root object, or <tt>null</tt> if
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblDesignedBy.setText(String.format("Game designed by %s", groupName));
		
		ObservableList<Node> creditNames = vbCredits.getChildren();
		
		// Display all defined Group Member to screen and assigns the correct
		// styles
		for(String name : groupMembers) {
			
			Label nameLabel = new Label(String.format("• %s", name));
			nameLabel.getStyleClass().add("credit-text");
			
			creditNames.add(nameLabel);
		}
	}
}
