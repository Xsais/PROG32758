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

package com.util.jdbc;

import javax.swing.JOptionPane;
import com.init.Main;
import com.util.fxml.page.PageController;
import com.views.startpage.StartPage;
import com.views.adminpage.AdminPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DisplayDataBase extends PageView implements Initializable{
	
	@FXML
    private Button btnAdmin;
	private AdminPage adminPage;
	
    /**
     * Displays database
     *
     */
    public DisplayDataBase(ConnectToDB dbConnection) {

    	// Displays database even if non-existent
        try {
            displayTable(dbConnection);
            
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null,ex.getMessage() + "SQL State: "
                    + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(), "Car Racing Game",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            }

        }
        
        private void displayTable(ConnectToDB dbConnection) throws SQLException {

            try {
                String query = "SELECT CONCAT(First_Name, Last_Name) AS 'Name' 'Group', 'Login', 'Password', 'Logo', 'Score' FROM DBProg32758.Players;";
            	ResultSet result = dbConnection.executeQuerry(query);
            	System.out.println(result);
                /* Use com.util.fxml.DisplayDataBase.fxml to display database */
            	
            	// Button to return to admin page
            	Button btnAdmin = new Button("Return to Admin Page");
            	btnAdmin.setOnAction(evt -> pageController.show(adminPage));
        		
        		/*// Run Query
        		ResultSet res = ps.executeQuery();
        		System.out.println("\nName\tModel\t\tMake\tScreen\tYear");
        		while (res.next()){
        			System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4) + "\t" + res.getString(5));
        		}*/

            } 
            catch (SQLException ex) {
                    throw ex;
            }
            
        }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onClose(Object sender, int statusCode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onCloseRequest(WindowEvent evt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onOpen(Object sender) {
			// TODO Auto-generated method stub
			
		}
    
}
