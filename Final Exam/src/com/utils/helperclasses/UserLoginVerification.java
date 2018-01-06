/*
 * ----------------------------------------------------------------------------------------------+
 *   * Group Leader: Daniel Hope
 *   * Member(s):
 *   *     - Georgina Luce
 *   *     - Nathaniel Primo
 *   *     - Michael Marc
 *   * Group #: 1
 *   * Filename: com.utils.helperclasses.UserLoginVerification.java
 *   * Assignment: Final Exam
 *   * Creation Date:
 *   * Last Modified: 2017/12/27
 *   * Java Version: 1.8.0_141
 *   * Description: Helper class that keeps track of user attempts by checking DBProg32758 and verifying that login
 *   *      and password credentials
 *   *		are correct, if not tracking lists are updated and control is returned to UserLoginServlet. In the case of
 *   *      3 consecutive failed
 *   *		attempts at a user login the database is updated and the user is locked out from any further attempts.
 * ----------------------------------------------------------------------------------------------+
 */

package com.utils.helperclasses;

import com.utils.servlets.UserLoginServlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginVerification {

    private static String lockCode = null;

    private static int failCount = 0;

    private static ResultSet rs = null;

    public static int isValidLogin(String login, String password, ConnectToDB dbConnection) throws SQLException {

        if (password.equals(lockCode)) {

            return 2;
        }

        try {

            // check DB for user defined login

            rs = dbConnection.executeQuerry(String.format("SELECT * FROM DBProg32758.Players WHERE Login = '%s'"

                    , login));

        } catch (SQLException e) {

            e.printStackTrace();

        }

        if (rs.next()) {

            // check if user is already in the list of logins, if not add them and
            // initialize attempts count
            if (!UserLoginServlet.loginTracker.contains(login)) {

                UserLoginServlet.loginTracker.add(login);
                UserLoginServlet.attemptsTracker.add(1);

                // assign number of attempts from attemptsTracker list and store in failCount to check validity
                // of login credentials
            } else {

                failCount = UserLoginServlet.attemptsTracker.get(UserLoginServlet.loginTracker.indexOf(login));
            }
            // if login is valid, check if user account is already locked
            if (rs.getString("Password").equals(lockCode)) {

                UserLoginServlet.attemptsTracker.set(UserLoginServlet.loginTracker.indexOf(login), 3);

                return 2;

                // if login is valid, check password validity associated with login
            } else if (rs.getString("Password").equals(password)) {

                UserLoginServlet.attemptsTracker.set(UserLoginServlet.loginTracker.indexOf(login), 0);

                return 0;
                // add to attemptTracker and if 3 failed attempts in a row, update DB to lock user out
            } else {

                failCount += 1;

                UserLoginServlet.attemptsTracker.set(UserLoginServlet.loginTracker.indexOf(login), failCount);

                if (failCount == 3) {
                    dbConnection.executeUpdate(

                            String.format("UPDATE DBProg32758.Players SET Password = 'xxxxxx' WHERE Login = '%s'",
                                    login));

                }
                return 1;

            }

        } else {

            return 1;
        }
    }

    public static String getLockCode() {

        return lockCode;
    }

    public static void setLockCode(String lockCode) {

        UserLoginVerification.lockCode = lockCode;
    }
}
