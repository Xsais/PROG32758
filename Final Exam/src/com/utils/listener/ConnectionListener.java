/*
 * ----------------------------------------------------------------------------------------------+
 *   * Group Leader: Daniel Hope
 *   * Member(s):
 *   *     - Georgina Luce
 *   *     - Nathaniel Primo
 *   *     - Michael Marc
 *   * Group #: 1
 *   * Filename: com.utils.listener.ConnectionListener.java
 *   * Assignment: Final Exam
 *   * Creation Date:
 *   * Last Modified: 2017/12/27
 *   * Java Version: 1.8.0_141
 *   * Description: Creates and stores a connection object to a MYSQL DB in the servlet context
 * ----------------------------------------------------------------------------------------------+
 */

package com.utils.listener;

import com.mysql.jdbc.Driver;
import com.utils.helperclasses.ConnectToDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionListener implements ServletContextListener {

    // Stores the connection to the server
    ConnectToDB connection = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();

        ConnectToDB connection = null;

        try {

            Driver.class.newInstance();
            connection = new ConnectToDB(context.getInitParameter("db_host"), context.getInitParameter("db_user")
                    , context.getInitParameter("db_pass"));

            connection.execute("USE dbprog32758");
        } catch (Exception e) {

            e.printStackTrace();
        }

        context.setAttribute("db_conection", connection);

        context.setAttribute("db_lockOut", "xxxxxx");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        connection.closeConnection();
    }
}
