package com.utils.listener;

import com.utils.helperclasses.ConnectToDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.mysql.jdbc.Driver;

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
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        connection.closeConnection();
    }
}
