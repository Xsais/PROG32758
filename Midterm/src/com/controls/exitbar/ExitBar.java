/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.controls.exitbar.ExitBar
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.controls.exitbar;

import com.util.fxml.FXMLHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ExitBar extends GridPane implements Initializable {
	
	@FXML
	private Pane pneClose;
	
	private Function<Object, Integer> exit;
	
	/**
	 * Initializes the control and load the look and feel
	 *
	 * @param onExit The desired action to be preformed on the closure of the pop out
	 */
	public ExitBar(Function<Object, Integer> onExit) {
		
		try {
			
			// Loads the given control and links it to the desired FXML file
			FXMLHelper.loadControl(this).load();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		this.exit = onExit;
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
		
		// Apply the onExit event on initialized
		pneClose.setOnMouseClicked(p->{
			
			try {
				
				if(exit == null) {
					
					return;
				}
				
				exit.apply(this);
			} catch(Exception e) {
				
				e.printStackTrace();
			}
		});
	}
}
