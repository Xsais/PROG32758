package com.utils.servlets;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.helperclasses.UserLoginVerification;

/**
 * Servlet implementation class UserLoginServlet
 * 
 * Takes the user login and password and verifies information in the database.
 * Displays a pop up if user information is incorrect, locks them out after 3 failed attempts in a row and 
 * changes password in database to "xxxxxx", then notifies user to of lock out and to contact their database admin. 
 * 
 */
@WebServlet("/UserLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		UserLoginVerification user = new UserLoginVerification(request.getParameter("userLogin"),
				request.getParameter("userPassword"));
				
				try {
					user.isValidLogin();
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"\r\n" + 
				"<link href=\"../../res/style/reset.css\" rel=\"stylesheet\" type='text/css'>\r\n" + 
				"\r\n" + 
				"<link href=\"../../res/style/default.css\" rel=\"stylesheet\" type='text/css'>\r\n" + 
				"\r\n" + 
				"<title>CAR RACING GAME</title>\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body class='loginBody'>\r\n" + 
				"\r\n" + 
				"	<div class=\"app-pane top\">\r\n" + 
				"\r\n" + 
				"			<img src=\"../../res/img/png-sheridan.jpg\">\r\n" + 
				"\r\n" + 
				"			<div class=\"app-title\">CAR RACING GAME</div>\r\n" + 
				"\r\n" + 
				"			<div class=\"dynamic-box top\">\r\n" + 
				"\r\n" + 
				"				<p>Game designed by Group 1</p>\r\n" + 
				"\r\n" + 
				"				<ul class=\"mini-text\">\r\n" + 
				"\r\n" + 
				"					<li>Daniel Hope</li>\r\n" + 
				"\r\n" + 
				"					<li>Georgina Luce</li>\r\n" + 
				"\r\n" + 
				"					<li>Nathaniel Primo</li>\r\n" + 
				"\r\n" + 
				"					<li>Michael Marc</li>\r\n" + 
				"\r\n" + 
				"				</ul>\r\n" + 
				"\r\n" + 
				"			</div>\r\n" + 
				"\r\n" + 
				"		</div>\r\n" + 
				"\r\n" + 
				"	<form method=\"post\" class='loginForm' action='/UserLogin'>\r\n" + 
				"		<fieldset class='loginFieldSet'>\r\n" + 
				"			<div>Enter User Login:   <input name='userLogin' id='userLogin' type='text' \r\n" + 
				"				title='Login can be any combination of letters and numbers, maximum of 10' \r\n" + 
				"				maxlength='10' pattern='^[a-zA-Z0-9])+$' size=\"14\"required></div>\r\n" + 
				"			<br> \r\n" + 
				"			<div>Enter User Password:<input name='userPassword' type='password' \r\n" + 
				"				title='Password must contain at least 1 letter and 1 number, maximum of 10' \r\n" + 
				"				maxlength='10' pattern='^(?=.*\\d)(?=.*[a-zA-Z]).{2,}$' size=\"10\" required /></div> \r\n" + 
				"			<br>\r\n" + 
				"			<div class='loginButtons'>\r\n" + 
				"				<button type='submit'>Sign In</button>\r\n" + 
				"				<button type='button' onclick=\"window.location = '../user.html'\">Exit</button>\r\n" + 
				"			</div>\r\n" + 
				"		</fieldset>\r\n" + 
				"	</form>\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	<script>\r\n" + 
				"		window.onload = function() {\r\n" + 
				"  			document.getElementById(\"userLogin\").focus();\r\n" + 
				"		};\r\n" + 
				"	</script>\r\n" + 
				"	\r\n" + 
				"</body>");
		
	}

}
