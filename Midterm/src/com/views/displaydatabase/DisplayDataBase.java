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
import com.init.Main;
import com.util.fxml.page.PageController;
import com.views.startpage.StartPage;
import com.views.adminpage.AdminPage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import com.views.adminpage.AdminPage;
import com.views.userpage.UserPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayDataBase extends PageView implements Initializable {
	
	@FXML
	private Button btnAdmin;
	private AdminPage adminPage;
    private TableView<Person> tableView;
    private TableColumn<Person, String> Name;
    private TableColumn<Person, String> Group;
    private TableColumn<Person, String> Login;
    private TableColumn<Person, String> Password;
    private TableColumn<Person, String> Logo;
    private TableColumn<Person, String> Score;
    private ResultSet res;

	/**
	 * Displays database in 'page' format
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
			res = dbConnection.executeQuerry(query);
		} 
		catch (SQLException ex) {
			throw ex;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAdmin.setOnAction(evt -> pageController.show(adminPage));
		/*
		Name.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("name"));
        Group.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("group"));
        Login.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("login"));
        Password.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("password"));
        Logo.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("Logo"));
        Score.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("score"));
        
        tableView.getItems().setAll(parseUserList());
        
        while (res.next()) {
        	Name.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>(res.getString(1)));
        	System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t"
					+ res.getString(4) + "\t" + res.getString(5) + "\t" + res.getString(6));
        }
			
        
	}
	private List<Person> parseUserList(){
    	return List<Person>;
    }
	*/
	}
	private class Person {
		private String name;
		private String group;
		private String login;
		private String password;
		private String logo;
		private String score;
		
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
		// empty
	}

	@Override
	public void onCloseRequest(WindowEvent evt) {
		// empty
	}

	@Override
	public void onOpen(Object sender, String... args) {
		// empty
	}

}