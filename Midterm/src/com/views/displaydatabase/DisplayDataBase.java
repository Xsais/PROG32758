/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: CreateDataBAse.java
 * Other Files in this Project:
 *     -
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 16
 * Last Modified: 11, 2017 26
 * Java Version: 1.8.1_141
 * Description: Initializes the DBProg32758 database and prepares it for data entry
 * ----------------------------------------------------------------------------+
 */

package com.views.displaydatabase;

import javax.swing.JOptionPane;
import com.views.adminpage.AdminPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DisplayDataBase extends PageView implements Initializable {
	
	@FXML
	private Button btnAdmin;
	private AdminPage adminPage;
	
	public static ResultSet res;

	/**
	 * Displays database in 'page' format
	 * 
	 *
	 */
	public DisplayDataBase(ConnectToDB dbConnection) {

		try {
			// Displays database even if non-existent
			displayTable(dbConnection);
			// Uses DisplayDataBase.fxml to display database
			FXMLHelper.loadControl(this).load();
		} 
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					ex.getMessage() + "SQL State: " + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(),
					"Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private void displayTable(ConnectToDB dbConnection) throws SQLException {

		try {
			String query = "SELECT CONCAT(First_Name, ' ', Last_Name) AS 'Name', 'Group', 'Login', 'Password', 'Logo', 'Score' FROM DBProg32758.Players;";

			// Run Query
			ResultSet res = dbConnection.executeQuerry(query);
		} 
		catch (SQLException ex) {
			throw ex;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAdmin.setOnAction(evt -> pageController.show(adminPage));
	}

	@Override
	public void onClose(Object sender, int statusCode) {
		// empty
	}

	@Override
	public void onCloseRequest(WindowEvent evt) {
		// empty
	}

	@Override
	public void onOpen(Object sender) {
		// empty
	}

}