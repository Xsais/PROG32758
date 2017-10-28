package com.views.userlogin;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;

import com.util.ConnectToDB;
import com.util.PageType;
import com.util.PageView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserLogin extends PageView implements Initializable {

	@FXML
	private Button btnUserLogin, btnExit;

	@FXML
	private Label lblLogin, lblPassword, lblLoginEmpty, lblPasswordEmpty;

	@FXML
	private TextField txtLogin, txtPassword;
	
	private int failCount = 0;
	private ResultSet rs = null;
	private ConnectToDB dbConnection;
	private static final String LOCK_OUT_CODE = "xxxxxx";
	
	
	// constructor that creates connection to DB and sets object as pop out type page
	public UserLogin(ConnectToDB dbConnection) {
		
		pageType = PageType.POP_OUT;
		

		try {

			this.dbConnection = dbConnection;
			dbConnection.executeQuerry("USE DBProg32758;");

		}
		
		catch (Exception e) {
				e.printStackTrace();
			}
		
		try {

			com.util.FXMLHelper.loadControl(this).load();

		} catch (java.io.IOException e) {

			e.printStackTrace();

		}
			
	}

	
	// method informs user that their account has been locked, locks the account, and exits login pop out window
	public void showLockOutMessage() throws SQLException {
		
		JOptionPane.showMessageDialog(null,
				"Your account has been locked\n Please contact your Database Administrator",
				"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
		
		dbConnection.executeUpdate(String.format("UPDATE Players SET Password = 'xxxxxx' WHERE Login = '%s'",  
				txtLogin.getText()));
		
		btnExit.fire();
	}
	
	// method informs user of incorrect login/password and increments falied attempts counter by one
	public void showLoginErrorMessage() {
		
		JOptionPane.showMessageDialog(null,
				"The Login and Password are not correct\n Please try again"
						+ " if you are already registered or complete your registration if you are not",
				"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
		
		failCount +=1;
	}
	
	// method queries DB for login/password and checks against user input for validity
	public void isValidLogin() throws HeadlessException, SQLException {

		if (failCount < 3) {

			// check existence of user login
			try {
				
				// check DB for user defined login
				rs = dbConnection.executeQuerry(String.format("SELECT * FROM Players WHERE Login = '%s'", 
								txtLogin.getText()));

			} catch (SQLException e) {

				e.printStackTrace();
			}
			

			if (rs.next()) {

				// if login is valid, check if user account is already locked
				if (rs.getString("Password").equals(LOCK_OUT_CODE)) {
					
					showLockOutMessage();

					failCount = 3;

					// if login is valid, check password validity associated with login
				} else if (rs.getString("Password").equals(txtPassword.getText())) {
					
					failCount = 0;
					
					//TODO: connect to GamePage
					

					// inform user that login and/or password are incorrect (password incorrect scenario)
				} else {

					showLoginErrorMessage();
					
					if (failCount == 3) {
						
						showLockOutMessage();
						
					}

				}
				// inform user that login and/or password are incorrect (login incorrect scenario)
			} else {

				showLoginErrorMessage();
				
				if (failCount == 3) {
					
					showLockOutMessage();
					
				}
			}
		} else {
			
			showLockOutMessage();
		}
	}
	
	
	public void isBlankField() {
		
		lblLoginEmpty.setVisible(false);
		lblPasswordEmpty.setVisible(false);
		
		if (txtLogin.getText().equals("")) {
			
			lblLoginEmpty.setText("The Login field can not be blank, please enter a login.");
			lblLoginEmpty.setVisible(true);
			
			btnUserLogin.setDisable(true);
			
		} else if (txtPassword.getText().equals("xxxxxx")){
			
			lblPasswordEmpty.setText("Invalid password, password can not be xxxxxx");
			lblPasswordEmpty.setVisible(true);
			
			btnUserLogin.setDisable(true);
			
		} else if (txtPassword.getText().equals("")) {
			
			lblPasswordEmpty.setText("The Password field can not be blank, please enter a password.");
			lblPasswordEmpty.setVisible(true);
			
			btnUserLogin.setDisable(true);
			
		} else {
			
			btnUserLogin.setDisable(false);
		}
	}

	@Override
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

		  btnUserLogin.setDisable(true);
		  lblLoginEmpty.setText("The Login field can not be blank, please enter a login.");
		  lblPasswordEmpty.setText("The Password field can not be blank, please enter a password.");
		  
		  txtLogin.textProperty().addListener(p -> {
		  
		  	isBlankField();
		  
		  }); 
		  
		  
		  txtPassword.textProperty().addListener(p -> {
		  
		  	isBlankField();
		  
		  });
		  
		  
		  btnUserLogin.setOnAction(evt -> {
			try {
				
				isValidLogin();
				
			} catch (HeadlessException e) {
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
		});
		  
		  
		  btnExit.setOnAction(p ->  pageController.hidePopOut());
		 
	}
}
