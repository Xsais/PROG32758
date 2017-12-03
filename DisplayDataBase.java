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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayDataBase extends PageView implements Initializable {
	
	@FXML
	private Button btnAdmin;
    private TableView<Person> person;
    private TableColumn<Person, String> name;
    private TableColumn<Person, String> group;
    private TableColumn<Person, String> login;
    private TableColumn<Person, String> password;
    private TableColumn<Person, String> logo;
    private TableColumn<Person, String> score;
    private ResultSet res;

	/**
	 * Displays database in 'page' format
	 *
	 */
	public DisplayDataBase(ConnectToDB dbConnection) {

		try {
			// Runs query and stores in result set
			runQuery(dbConnection);
			// Acts as a controller, also calling initialize(URL, ResourceBundle)
			FXMLHelper.loadControl(this).load();
		} 
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					ex.getMessage() + "SQL State: " + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(),
					"Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private void runQuery(ConnectToDB dbConnection) throws SQLException {

		try {
			String query = "SELECT CONCAT(First_Name, ' ', Last_Name) AS 'Name', 'Group', 'Login', 'Password', 'Logo', 'Score' FROM DBProg32758.Players;";

			// Runs query above
			res = dbConnection.executeQuerry(query);
		} 
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Return to 'adminPage' button
		btnAdmin.setOnAction(evt -> pageController.hidePopUps(0));
				
        try {
        	// Adds all data (by row) to person class
			while (res.next()) {
				person.getItems().add(new Person(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)));
			}
		} 
        catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}	
		// Table is filled (by column) with data from person class
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		group.setCellValueFactory(new PropertyValueFactory<>("group"));
		login.setCellValueFactory(new PropertyValueFactory<>("login"));
		password.setCellValueFactory(new PropertyValueFactory<>("password"));
		logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
		score.setCellValueFactory(new PropertyValueFactory<>("score"));
	}
	
	/*
	 * In charge of capturing and storing the information of people
	 * Should only be used by DisplayDataBase class (private)
	 */
	private class Person {
		private String name;
		private String group;
		private String login;
		private String password;
		private String logo;
		private String score;
		
		public Person(String name, String group, String login, String password, String logo, String score) {
			this.name = name;
			this.group = group;
			this.login = login;
			this.password = password;
			this.logo = logo;
			this.score = score;
		}
		
		// Getters added for future expansion
		public String getName() {
			return name;
		}
		public String getGroup() {
			return group;
		}
		public String getLogin() {
			return login;
		}
		public String getPassword() {
			return password;
		}
		public String getLogo() {
			return logo;
		}
		public String getScore() {
			return score;
		}
		
	}

	@Override
	public void onClose(Object sender, int statusCode) {
		// do nothing
	}

	@Override
	public void onCloseRequest(WindowEvent evt) {
		// do nothing
	}

	@Override
	public void onOpen(Object sender) {
		// do nothing
	}

}