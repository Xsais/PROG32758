/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.fxml.FXMLHelper
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.util.fxml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Maintains the required data for the FXMLHelper object
 *
 * @author Nathaniel Primo
 **/

public class FXMLHelper {
	
	/**
	 * Loads the given control and links it to it FXML file
	 *
	 * @param controller The controller for the FXML document
	 * @return An FXMLLoader object with the controller and root set
	 */
	public static <T extends Node> FXMLLoader loadControl(T controller) {
		
		return loadControl(controller, String.format("%s.fxml", controller.getClass()
				.getSimpleName()));
	}
	
	/**
	 * Loads the given control and links it to it FXML file
	 *
	 * @param controller The controller for the FXML document
	 * @param fxmlName The desired name of the FXML document to be linked to the controller
	 * @return An FXMLLoader object with the controller and root set
	 */
	public static <T extends Node> FXMLLoader loadControl(T controller, String fxmlName) {
		
		Class<?> controllerClass = controller.getClass();
		
		return new FXMLLoader(controllerClass.getResource(fxmlName)) {{
			
			setRoot(controller);
			setController(controller);
		}};
	}
}
