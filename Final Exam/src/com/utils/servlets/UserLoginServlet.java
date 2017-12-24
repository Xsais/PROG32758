/*



 * ----------------------------------------------------------------------------------------------+



 *  * Group Leader: Daniel Hope



 *  * Member(s):



 *  *     - Georgina Luce



 *  *     - Nathaniel Primo



 *  *     - Michael Marc



 *  * Group #: 1



 *  * Filename: com.utils.servlets.UserLoginServlet



 *  * Assignment: Final Exam



 *  * Creation Date:



 *  * Last Modified: 12/24/17 4:37 PM



 *  * Java Version: 1.8.0_141



 *  * Description: 
 *  A Servlet that uses a helper class(UserLoginVerification) to keep track of user login attempts and that informs user of login failures
 *  and redirects web page to either userlogin.jsp(if the number consecutive failed attempts < 3) or cargame.html(if login attempt is successful)



 * ----------------------------------------------------------------------------------------------+



 */

package com.utils.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.utils.helperclasses.UserLoginVerification;

/**
 * 
 * Servlet implementation class UserLoginServlet
 * 
 * 
 * 
 * Takes the user login and password and verifies information in the database.
 * 
 * Displays a pop up if user information is incorrect, locks them out after 3
 * failed attempts in a row and
 * 
 * changes password in database to "xxxxxx", then notifies user to of lock out
 * and to contact their database admin.
 * 
 * 
 * 
 */

@WebServlet("/user/userLogin/login")

public class UserLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// used for keeping track of user login and attempts(for all users)
	public static List<String> loginTracker = new ArrayList<String>();
	public static List<Integer> attemptsTracker = new ArrayList<Integer>();

	String login, password;

	/**
	 * 
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		login = request.getParameter("userLogin");
		password = request.getParameter("userPassword");
		Object dbConnection = request.getServletContext().getAttribute("db_conection");

		try {

			if (UserLoginVerification.isValidLogin(login, password, dbConnection)) {

				response.sendRedirect("../game/cargame.html");

			} else {
				int tempAttempt = 0;

				try {

					tempAttempt = loginTracker.indexOf(login);

				} catch (IndexOutOfBoundsException e) {

					response.sendRedirect(String.format("./userlogin.jsp?err=%d", 0));

				}
				if (attemptsTracker.get(tempAttempt) < 3) {
					
					response.sendRedirect(String.format("./userlogin.jsp?err=%d", 0));
					
				} else {

					response.sendRedirect(String.format("./userlogin.jsp?err=%d", 1));
				}
			}

		} catch (Exception e) {
			//e.printStackTrace();
			response.sendRedirect(String.format("./userlogin.jsp?err=%d", 0));

		}

	}

}
