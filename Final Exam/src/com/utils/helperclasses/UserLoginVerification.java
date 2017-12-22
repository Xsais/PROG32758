package com.utils.helperclasses;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UserLoginVerification {
	
	public UserLoginVerification() { }

	private static final String LOCK_OUT_CODE = "xxxxxx";

	private int failCount = 0;

	private ResultSet rs = null;

	private ConnectToDB dbConnection;

	public boolean isValidLogin(String login, String password) throws SQLException {
		
		if (failCount < 3) {

			// check existence of user login

			try {

				// check DB for user defined login

				rs = dbConnection.executeQuerry(String.format("SELECT * FROM DBProg32758.Players WHERE Login = '%s'"

						, login));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (rs.next()) {

				// if login is valid, check if user account is already locked

				if (rs.getString("Password").equals(LOCK_OUT_CODE)) {

					showLockOutMessage(login);

					failCount = 3;

					// if login is valid, check password validity associated with login

				} else if (rs.getString("Password").equals(password)) {

					failCount = 0;

					return true;

				} else {

					showLoginErrorMessage();

					if (failCount == 3) {

						showLockOutMessage(login);

					}

				}

				// inform user that login and/or password are incorrect (login incorrect
				// scenario)

			} else {

				showLoginErrorMessage();

				if (failCount == 3) {

					showLockOutMessage(login);

				}

			}

		} else {

			showLockOutMessage(login);

		}

		return false;
	}
	
	/**
	 * 
	 * Inform user that their account has been locked, locks the account, and exits
	 * login pop out window
	 * 
	 */
	private void showLockOutMessage(String login) throws SQLException {

		// inform user that account has been locked

		JOptionPane.showMessageDialog(null, "Your account has been locked\n Please contact your Database Administrator",

				"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

		// set user password to all x's in DB

		dbConnection.executeUpdate(

				String.format("UPDATE DBProg32758.Players SET Password = 'xxxxxx' WHERE Login = '%s'", login));

		// exit login screen

		//btnExit.fire();

	}
	
	/**
	 * 
	 * Inform user of incorrect login/password and increments failed attempts
	 * counter by one
	 * 
	 */
	private void showLoginErrorMessage() {

		// inform user of incorrect login/password

		JOptionPane.showMessageDialog(null,

				"The Login and Password are not correct\n Please try again"

						+ " if you are already registered or complete your registration if you are not",

				"Car Racing Game", JOptionPane.INFORMATION_MESSAGE);

		// increase fail counter by 1

		failCount += 1;

	}
}
