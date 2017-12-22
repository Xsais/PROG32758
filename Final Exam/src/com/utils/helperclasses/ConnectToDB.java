/*

 * ----------------------------------------------------------------------------------------------+

 *  * Group Leader: Daniel Hope

 *  * Member(s):

 *  *     - Georgina Luce

 *  *     - Nathaniel Primo

 *  *     - Michael Marc

 *  * Group #: 1

 *  * Filename: com.util.jdbc.ConnectToDB

 *  * Main class: com.init.Main

 *  * Assignment: Midterm

 *  * Creation Date:

 *  * Last Modified: 11/1/17 8:34 PM

 *  * Java Version: 1.8.0_141

 *  * Description:

 * ----------------------------------------------------------------------------------------------+

 */


package com.utils.helperclasses;


import java.sql.*;


public class ConnectToDB {

    private Connection connection;

    private Statement statement;

    private ResultSet lastResult;

    /**
     * Method for registering Driver and creating connection to user defined database
     *
     * @param subName The desired Sub-Domain
     * @param user    The desired user
     * @param pass    The desired password for the giving user
     */

    public ConnectToDB(String subName, String user, String pass) throws SQLException {


        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:3306", subName),

                user, pass);


        statement = connection.createStatement();

    }


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

    public int executeUpdate(String query) throws SQLException {


        return statement.executeUpdate(query);

    }


    /**
     * Method for executing queries in sql returning a ResultSet
     *
     * @param query The desired query to be executed
     * @return The result set of the last successfully executed query
     */

    public ResultSet executeQuerry(String query) throws SQLException {


        lastResult = statement.executeQuery(query);


        return lastResult;


    }

}
