/*

 * ----------------------------------------------------------------------------+

 * Group Leader: Daniel Hope

 * Member(s): Georgina Luce

 *            Nathaniel Primo

 *            Michael Marc

 * Group #: 1

 * Filename: UserRegister.java

 * Other Files in this Project:

 *     -

 * Assignment: Midterm - Micro-Project 1 (Part 1)

 * Creation Date: 11, 2017 26

 * Last Modified: 11, 2017 26

 * Java Version: 1.8.1_141

 * Description: Controls for pop out window providing the user with login and password prompts

 * ----------------------------------------------------------------------------+

 */

package com.views.userregister;


import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageController;
import com.util.jdbc.ConnectToDB;
import com.util.fxml.page.PageType;
import com.util.fxml.page.PageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * The UserRegister class provides functionality for UserRegister.fxml, allowing user to register using name and group values.  User is 
 * prevented from entering blank fields.  This class will check for a match with last_Name, first_Name and Group, updating the database with
 * attributes if provided details are correct.
 *
 */
public class UserRegister extends PageView implements Initializable {

	private static final String LOCK_OUT_CODE = "xxxxxx";
	@FXML
	private Button btnOK, btnExit;
	@FXML
	private Label lblLastName, lblFirstName, lblGroup, lblLogin, lblPassword,
	lblPreferredCarName, lblCredit, lblScore, lblLogo, lblEmpty, lblLockout;
	@FXML
	private TextField txtLastName, txtFirstName, txtGroup, txtLogin,
	txtPreferredCarName, txtCredit, txtScore, txtLogo;
	@FXML
	private PasswordField txtPassword;
	private ResultSet rs = null;
	private ConnectToDB dbConnection;
	private com.views.gamemenu.GameMenu gameMenu;

	// constructor that creates connection to DB and sets object as pop out type page
	public UserRegister(ConnectToDB dbConnection) {

		pageType = PageType.POP_UP;
		gameMenu = new com.views.gamemenu.GameMenu(dbConnection);

		// select DB for user detail verification
		try {
			this.dbConnection = dbConnection;
			dbConnection.executeQuerry("USE DBProg32758;");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FXMLHelper.loadControl(this).load();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Query DB for lastname, firstname & group and check against user input for validity
	 * @throws HeadlessException
	 * @throws SQLException
	 */
	public void isValid() throws HeadlessException, SQLException {
		
		rs = dbConnection.executeQuerry("SELECT * FROM Players WHERE Last_Name = '" + txtLastName.getText() 
			+ "' AND First_Name = '" + txtFirstName.getText() + "' AND `Group` = '" + txtGroup.getText() + "'");
		if (rs.next()) {
			writeToDB();
		} else {
			JOptionPane.showMessageDialog(null, "Either you are not registered or your Last Name, First Name, Group are not correct.", "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	//Clears user's row and enters new one with all fields from user input
	public void writeToDB() {
		
		try {
			dbConnection.executeUpdate("DELETE FROM Players WHERE Last_Name = '" + txtLastName.getText() 
			+ "' AND First_Name = '" + txtFirstName.getText() + "' AND `Group` = '" + txtGroup.getText() + "'");
			
			dbConnection.executeUpdate("INSERT INTO Players (Last_Name, First_Name, `Group`, Login, Password, "
				+ "Preferred_Car_Name, Logo, Score) VALUES ('" + txtLastName.getText() + "', '" + txtFirstName.getText() 
				+ "', '" + Integer.parseInt(txtGroup.getText()) + "', '" + txtLogin.getText() + "', '" + txtPassword.getText() 
				+ "', '" + txtPreferredCarName.getText() + "', '" + txtLogo.getText() + "', '" + Integer.parseInt(txtScore.getText()) + "')");
			
			JOptionPane.showMessageDialog(null, "You are now registered, you can login.", "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "SQL State: " + e.getSQLState() + " ErrorCode: " + e.getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 *  Method for text field listener to prevent user from clicking OK button with empty fields
	 */
	public void isBlankField() {
		lblEmpty.setVisible(true);
		// check if login and password fields have text and password is not the same as lock out code, if so enable Login button
		if (txtPassword.getText().equals(LOCK_OUT_CODE)) {
			lblLockout.setVisible(true);
		}
		if (!txtLastName.getText().equals("") && !txtFirstName.getText().equals("")	&& !txtPassword.getText().equals(LOCK_OUT_CODE) 
			&& !txtPassword.getText().equals("") && !txtGroup.getText().equals("") && !txtLogin.getText().equals("")
			&& !txtPreferredCarName.getText().equals("") && !txtCredit.getText().equals("") && !txtScore.getText().equals("")
			&& !txtLogo.getText().equals("")) {
			btnOK.setDisable(false);
			lblEmpty.setVisible(false);
			lblLockout.setVisible(false);
		}
	}

	@Override
	public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

		btnOK.setDisable(true);
		lblLockout.setVisible(false);
		lblEmpty.setText("No fields can be left blank.");
		lblLockout.setText("Password cannot be 'xxxxxx'.");
		
		txtLastName.textProperty().addListener(p -> {
			isBlankField();
		});

		txtFirstName.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtGroup.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtLogin.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtPassword.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtPreferredCarName.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtCredit.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtScore.textProperty().addListener(p -> {
			isBlankField();
		});
		
		txtLogo.textProperty().addListener(p -> {
			isBlankField();
		});

		// verify user details when OK button is clicked
		btnOK.setOnAction(evt -> {

			try {
				isValid();

			} catch (HeadlessException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		// exit login pop out page
		btnExit.setOnAction(p -> pageController.hidePopUps(0));
	}

	@Override
	public void init(PageController pageController) {

		super.init(pageController);
		pageController.registerPage(gameMenu);
	}

	/**
	 * 
	 * Occurs when the PageView has been requested to close
	 * 
	 * @param sender
	 *            The object in which closed the PageView
	 * 
	 * @param statusCode
	 *            The giving status code of the page closure
	 * 
	 */

	@Override
	public void onClose(Object sender, int statusCode) {

		// reset fields for next user registration
		txtFirstName.clear();
		txtLastName.clear();
		txtGroup.clear();
		txtLogin.clear();
		txtPassword.clear();
		txtPreferredCarName.clear();
		txtCredit.clear();
		txtLogo.clear();
		txtScore.clear();
	}

	/**
	 * 
	 * Occurs when the application classes peacefully
	 *
	 * @param evt
	 *            The WindowEvent associated with the closure
	 * 
	 */

	@Override
	public void onCloseRequest(javafx.stage.WindowEvent evt) {

	}

	/**
	 * 
	 * Occurs when the PageView has been requested to open
	 *
	 * @param sender
	 *            The Object in which sent the request
	 * 
	 */

	@Override

	public void onOpen(Object sender) {

		txtLastName.requestFocus();

	}
}
