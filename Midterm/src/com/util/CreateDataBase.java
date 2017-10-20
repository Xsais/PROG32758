/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: CreateDataBAse.java
 * Other Files in this Project:
 *     -
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 16
 * Last Modified: 10, 2017 16
 * Java Version: 1.8.1_141
 * Description: Initializes the DBProg32758 database and prepares it for data entry
 * ----------------------------------------------------------------------------+
 */

package com.util;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateDataBase {

    /**
     * Initializes the database and prepares it for data entry
     *
     * @param user The desired user for the database
     * @param pass The password required for the desired user
     */
    public CreateDataBase(String user, String pass) {

        try {

            //Check for DB
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, pass);

            if (conn == null) {

                throw new IllegalArgumentException("ERROR: Internal or external error connecting to DataBase");
            }
            Statement stm = conn.createStatement();

            dbValidate(stm);
            createTable(stm);
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
    }

    /**
     * Creates table called Players in DBProg32758
     *
     * @param stm The statement object for the desired connection
     */
    private void createTable(Statement stm) {

        try {
            stm.executeUpdate("CREATE TABLE DBProg32758.Players (`Last_Name` VARCHAR(20)," +
                    " `First_Name` VARCHAR(20), Group INT(255), `Login` VARCHAR(20), `Password` VARCHAR(20)," +
                    " `Preferred_Car_Name` VARCHAR(20), `Logo` VARCHAR(20), `Score` INT(255))");

            JOptionPane.showMessageDialog(null,
                    "Table Successfully created...\n Click OK to continue.", "Car Racing Game",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {

            if (ex.getErrorCode() == 1050) {

                JOptionPane.showMessageDialog(null, "The table already exists.",
                        "Car Racing Game", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    /**
     * Verify that DBProg32758 is created
     *
     * @param stm The statement object for the desired connection
     * @throws SQLException If there was an error creating the database
     */
    private void dbValidate(Statement stm) throws SQLException {

        // DB check
        try {

            stm.execute("USE DBProg32758");

            JOptionPane.showMessageDialog(null, "The database already exists.",
                    "Car Racing Game", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {

            stm.execute("CREATE DATABASE DBProg32758");

            JOptionPane.showMessageDialog(null,
                    "Database Successfully created...\n Click OK to continue.", "Car Racing Game",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
