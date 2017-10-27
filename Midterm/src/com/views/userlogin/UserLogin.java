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

		
	public boolean isValidLogin() throws HeadlessException, SQLException {

		if (failCount < 3) {

			// check existence of user login
			try {
				
				// check DB for user defined login
				rs = dbConnection
						.executeQuerry(String.format("SELECT * FROM Players WHERE Login = '%s'", 
								txtLogin.getText()));

			} catch (SQLException e) {

				e.printStackTrace();
			}
			

			if (rs.next()) {

				// if login is valid, check if user account is already locked
				if (rs.getString("Password").equals(LOCK_OUT_CODE)) {
					
					JOptionPane.showMessageDialog(null,
							"Your account has been locked\n Please contact your Database Administrator",
							"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

					failCount = 3;

					// if login is valid, check password validity associated with login
				} else if (rs.getString("Password").equals(txtPassword.getText())) {
					
					btnUserLogin.setDisable(false);
					failCount = 0;
					return true;

					// inform user that login and/or password are incorrect (password incorrect scenario)
				} else {

					JOptionPane.showMessageDialog(null,
							"The Login and Password are not correct\n Please try again"
									+ " if you are already registered or complete your registration if you are not",
							"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
					
					if (failCount == 2) {
						JOptionPane.showMessageDialog(null,
								"Your account has been locked\n Please contact your Database Administrator",
								"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
						dbConnection.executeUpdate(String.format("UPDATE Players SET Password = 'xxxxxx' WHERE Login = '%s'",  
								txtLogin.getText()));
					}

				}
				// inform user that login and/or password are incorrect (login incorrect scenario)
			} else {

				JOptionPane.showMessageDialog(null,
						"The Login and Password are not correct\n Please try again"
								+ " if you are already registered or complete your registration if you are not",
						"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
				
				if (failCount == 2) {
					JOptionPane.showMessageDialog(null,
							"Your account has been locked\n Please contact your Database Administrator",
							"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
					dbConnection.executeUpdate(String.format("UPDATE Players SET Password = 'xxxxxx' WHERE Login = '%s'",  
							txtLogin.getText()));
				}
			}
		} else {
			
			JOptionPane.showMessageDialog(null,
					"Your account has been locked\n Please contact your Database Administrator",
					"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
		}

		// returns false for incorrect login and/or password
		return false;
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
				failCount += 1;
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		  
		  btnExit.setOnAction(p ->  pageController.hidePopOut());
		 

	}
}
