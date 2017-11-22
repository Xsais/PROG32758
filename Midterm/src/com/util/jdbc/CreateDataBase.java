/*

 * ----------------------------------------------------------------------------+

 * Group Leader: Daniel Hope

 * Member(s): Georgina Luce

 *            Nathaniel Primo

 *            Michael Marc

 * Group #: 1

 * Filename: CreateDataBase.java

 * Other Files in this Project:

 *     -

 * Assignment: Midterm - Micro-Project 1 (Part 1)

 * Creation Date: 10, 2017 16

 * Last Modified: 11, 2017 20

 * Java Version: 1.8.1_141

 * Description: Initializes the DBProg32758 database and prepares it for data entry

 * ----------------------------------------------------------------------------+

 */

package com.util.jdbc;

import javax.swing.*;
import java.sql.SQLException;


/**
 * The CreateDataBase class checks for the existence of a database called DBProg32758, and if it does not exist the
 * database is created, and creates a table called Players
 */
public class CreateDataBase {

    /**
     * Initializes the database and prepares it for data entry
     */
    public CreateDataBase(ConnectToDB dbConnection) {

        // calls methods to check if DB has already been created and if not creates the DB and a table called Players
        try {

            dbValidate(dbConnection);

            createTable(dbConnection);

            // catch exception and show error message in pop up window
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage() + "SQL State: "

                            + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(), "Car Racing Game",

                    javax.swing.JOptionPane.WARNING_MESSAGE);

        }

    }


    /**
     * Creates table called Players in DBProg32758
     *
     * @param dbConnection The statement object for the desired connection
     */
    private void createTable(ConnectToDB dbConnection) throws SQLException {

        // creates table called Players and show pop up window telling user table has been created
        try {

            dbConnection.executeUpdate("CREATE TABLE DBProg32758.Players (`Last_Name` VARCHAR(20)," +

                    " `First_Name` VARCHAR(20), `Group` INT(255), `Login` VARCHAR(20), `Password` VARCHAR(20)," +

                    " `Preferred_Car_Name` VARCHAR(20), `Logo` VARCHAR(20), `Score` INT(255))");

            JOptionPane.showMessageDialog(null,

                    "Table Successfully created...\n Click OK to continue.", "Car Racing Game",

                    JOptionPane.INFORMATION_MESSAGE);

            // catch exception and show error message in pop up window (if error occurs) otherwise notify user that table exists
        } catch (SQLException ex) {

            if (ex.getErrorCode() != 1050) {

                throw ex;

            }

            JOptionPane.showMessageDialog(null, "The table already exists.",

                    "Car Racing Game", JOptionPane.WARNING_MESSAGE);

        }
    }


    /**
     * Verify that DBProg32758 is created
     *
     * @param dbConnection The statement object for the desired connection
     * @throws SQLException If there was an error creating the database
     */
    private void dbValidate(ConnectToDB dbConnection) throws SQLException {

        // notify user with pop up window that DB already exists
        try {

            dbConnection.execute("USE DBProg32758");

            JOptionPane.showMessageDialog(null, "The database already exists.",

                    "Car Racing Game", JOptionPane.WARNING_MESSAGE);

            // notify user that DB was created successfully if DB doesn't exist, otherwise show error message
        } catch (SQLException e) {

            if (e.getErrorCode() != 1049) {

                throw e;
            }

            dbConnection.execute("CREATE DATABASE DBProg32758");

            JOptionPane.showMessageDialog(null,

                    "Database Successfully created...\n Click OK to continue.", "Car Racing Game",

                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
