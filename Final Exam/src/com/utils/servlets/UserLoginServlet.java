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
@WebServlet("/userLogin/login")
public class UserLoginServlet extends HttpServlet {

	private UserLoginVerification userLoginVerification = new UserLoginVerification();
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			if (!userLoginVerification.isValidLogin(request.getParameter("userLogin")
                        , request.getParameter("userPassword"))) {

				// TODO: Send user to game screen

                return;
            }

			response.sendRedirect("./userlogin.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
