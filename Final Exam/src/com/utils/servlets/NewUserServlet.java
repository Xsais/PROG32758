/*
 * ----------------------------------------------------------------------------------------------+
 *   * Group Leader: Daniel Hope
 *   * Member(s):
 *   *     - Georgina Luce
 *   *     - Nathaniel Primo
 *   *     - Michael Marc
 *   * Group #: 1
 *   * Filename: com.utils.servlets.UserLoginServlet.java
 *   * Assignment: Final Exam
 *   * Creation Date:
 *   * Last Modified: 2017/12/27
 *   * Java Version: 1.8.0_141
 *   * Description: A Servlet for ensuring user registration is done correctly and adds to database
 * ----------------------------------------------------------------------------------------------+
 */

package com.utils.servlets;

import com.utils.helperclasses.ConnectToDB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class NewUserServlet
 * <p>
 * <p>
 * <p>
 * A Servlet that verifies user registration details, updates the database with them
 * and redirects web page to userlogin.jsp (if the registration is successful)
 */

@WebServlet("/user/newUser/register")
public class NewUserServlet extends HttpServlet {
	
	private ConnectToDB dbConnection;
    String lastName, firstName, group, login, password,	preferredCarName, credit, score, logo;
    private static ResultSet rs = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
        	firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            group = request.getParameter("group");
            login = request.getParameter("login");
            password = request.getParameter("password");
            preferredCarName = request.getParameter("preferredCarName");
            score = request.getParameter("score");
            credit = request.getParameter("credit");
            logo = request.getParameter("logo");
            
            //Check if firstname, lastname and group exist in an entry in database
            rs = dbConnection.executeQuerry("SELECT * FROM Players WHERE Last_Name = '" + lastName 
            + "' AND First_Name = '" + firstName + "' AND `Group` = '" + Integer.parseInt(group) + "'");
            
            //Check if user has already registered
            if (rs.next()) {
            	ResultSet results = dbConnection.executeQuerry("SELECT * FROM Players WHERE Last_Name = '" + lastName 
                + "' AND First_Name = '" + firstName + "' AND `Group` = '" + group + "' AND Login IS NOT NULL");
                if (results.next()) {
                	response.sendRedirect(String.format("./newuser.jsp?err=%d", 0));
                }
                else {
                	//update database with user's new details
                    (dbConnection).executeUpdate(String.format("UPDATE players SET "
                    		+ "`Login`=%s, `Password`=%s,`Preferred_Car_Name`=%s,`Logo`=%s, `Score`=%s, `Credits`=%s "
                    		+ "WHERE `Last_Name`=%s, `First_Name`=%s, `Group`=%s", login, password, preferredCarName, logo, Integer.parseInt(score), Double.parseDouble(credit),
                    		lastName, firstName, Integer.parseInt(group)));
                    //forward to login page
                    response.sendRedirect("../user/userLogin/userlogin.jsp");
                }
            } 
	
        } catch (SQLException e) {
        	response.sendRedirect(String.format("./newuser.jsp?err=%d", 1));
        }


    }

}
