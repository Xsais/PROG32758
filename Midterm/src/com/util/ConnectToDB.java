/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: ConnectToDB.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 19
 * Last Modified: 10, 2017 19
 * Java Version: 1.8.0_141
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.util;

import java.sql.*;

public class ConnectToDB {

    private Connection connection;

    private Statement statement;

    private ResultSet lastResult;

    public Connection getConnection() {

        return connection;
    }

    public ResultSet getLastResult() {

        return lastResult;
    }

    public Statement getStatment() {

        return statement;
    }

    public void closeConnection() {

        try {

            connection.close();
            statement.close();

            if (lastResult == null) {

                return;
            }

            lastResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for registering Driver and creating connection to user defined database
     *
     * @param subName The desired Sub-Domain
     * @param user    The desired user
     * @param pass    The desired password for the giving user
     */
    public ConnectToDB(String subName, String user, String pass) throws SQLException {

        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:3306", subName), user, pass);

        statement = connection.createStatement();
    }


    /**
     * Method for executing statements in sql
     *
     * @param query The desired query to be executed
     */
    public void execute(String query) throws SQLException {

        // create user defined execute statement for sql
        statement.execute(query);
    }


    /**
     * Method for executing queries in sql returning a ResultSet
     *
     * @param query The desired query to be executed
     * @return The result set of the last successfully executed query
     */
    public ResultSet executeUpdate(String query) throws SQLException {

        lastResult = statement.executeQuery(query);

        return lastResult;

    }
}