package com.views.userlogin;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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
	private Button btnLogin;

	@FXML
	private Label lblLogin, lblPassword, lblLoginEmpty, lblPasswordEmpty;

	@FXML
	private TextField txtLogin, txtPassword;


	private ResultSet rs = null;
	private ConnectToDB dbConnection;
	private static final String LOCK_OUT_CODE = "xxxxxx";

	public UserLogin(ConnectToDB dbConnection) {
		
		pageType = PageType.POP_OUT;

		try {

			this.dbConnection = dbConnection;

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

			/* check if user defined login and password are valid
			if (isValidLogin()) {

				// TODO: open page to play game
				// btnLogin.setAction(p -> GamePage());

				// inform user after 3 failed attempts that they have been locked out of the database
			} else {

				JOptionPane.showMessageDialog(null,
						"Your account has been locked\n Please contact your Database " + "Administrator",
						"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
				try {

					dbConnection.executeUpdate(
							String.format("UPDATE DBProg32758.Players SET Password = '%s' WHERE Login = '%s'",
									LOCK_OUT_CODE, txtLogin.getText()));

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
*/
	public boolean isValidLogin() throws HeadlessException, SQLException {

		// check for valid login and password combo 3 times
		for (int i = 0; i < 3; i++) {

			// check existence of user login
			try {
				
				// check DB for user defined login
				rs = dbConnection
						.executeQuerry(String.format("SELECT * FROM Prog32758.Players WHERE Login = '%s'", txtLogin.getText()));

			} catch (SQLException e) {

				e.printStackTrace();
			}

			if (rs.next()) {

				// check if user account is already locked and break loop if so
				if (rs.getString("Password").equals(LOCK_OUT_CODE)) {

					break;

					// if login is valid check password validity associated with login
				} else if (rs.getString("Password").equals(txtPassword.getText())) {

					return true;

					// inform user that login and/or password are incorrect (password incorrect scenario)
				} else {

					JOptionPane.showMessageDialog(null,
							"The Login and Password are not correct\n Please try again"
									+ " if you are already registered or complete your registration if you are not",
							"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

				}
				// inform user that login and/or password are incorrect (login incorrect scenario)
			} else {

				JOptionPane.showMessageDialog(null,
						"The Login and Password are not correct\n Please try again"
								+ " if you are already registered or complete your registration if you are not",
						"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		// returns false for incorrect login and/or password
		return false;
	}

	@Override
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

		/*
		 * TODO: User Creation
		 * 
		 * txtLogin.addListener(); txtPassword.addListener();
		 * 
		 * btnLogin.setOnAction(evt -> isValid());
		 * 
		 */

	}
}
