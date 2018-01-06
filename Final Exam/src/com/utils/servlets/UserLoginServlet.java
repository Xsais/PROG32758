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
 *   * Description: A Servlet that uses a helper class(UserLoginVerification) to keep track of user login attempts
 *   *      and that informs user of login failures
 * 	 *      and redirects web page to either userlogin.jsp(if the number consecutive failed attempts < 3) or cargame
 * 	 .html
 * 	 *      (if login attempt is successful)
 * ----------------------------------------------------------------------------------------------+
 */

package com.utils.servlets;

import com.utils.helperclasses.ConnectToDB;
import com.utils.helperclasses.UserLoginVerification;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class UserLoginServlet
 * <p>
 * <p>
 * <p>
 * A Servlet that uses a helper class(UserLoginVerification) to keep track of user login attempts and that informs
 * user of login failures
 * and redirects web page to either userlogin.jsp(if the number consecutive failed attempts < 3) or cargame.html(if
 * login attempt is successful)
 */

@WebServlet("/user/userLogin/login")

public class UserLoginServlet extends HttpServlet {

    // used for keeping track of user login and attempts(for all users)
    public static List<String> loginTracker = new ArrayList<>();

    public static List<Integer> attemptsTracker = new ArrayList<>();

    String login, password;

    private ConnectToDB dbConnection;

    /**
     * @throws IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (UserLoginVerification.getLockCode() == null) {

            UserLoginVerification.setLockCode((String) request.getServletContext().getAttribute("db_lockOut"));
        }

        login = request.getParameter("userLogin");
        password = request.getParameter("userPassword");

        if (this.dbConnection == null) {

            this.dbConnection = (ConnectToDB) request.getServletContext().getAttribute("db_conection");
        }


        try {

            switch (UserLoginVerification.isValidLogin(login, password, dbConnection)) {

                case 0:

                    response.sendRedirect(String.format("../../game/gamemenu.jsp?login=%s", login));
                    break;
                case 1:

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
                    break;
                case 2:

                    response.sendRedirect(String.format("./userlogin.jsp?err=%s", 2));
                    break;

            }

        } catch (Exception e) {
            //e.printStackTrace();
            response.sendRedirect(String.format("./userlogin.jsp?err=%d", 0));

        }

    }

}
