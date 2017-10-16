/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: CreateDataBAse.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 16
 * Last Modified: 10, 2017 16
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.util;

public class CreateDataBase {


    // creates table called Players in DBProg32758
    public static void createTable() {

        java.sql.Connection conn = null;
        java.sql.Statement stm = null;

        // DB check
        try {
            //Check for DB
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306", "admin", "");

            if (conn != null) {
                java.sql.ResultSet rs = conn.getMetaData().getCatalogs();

                while (rs.next()) {
                    String catalog = rs.getString(1);
                    if (catalog.equals("DBProg32758")) {
                        // window that warns user that the database already exsists
                        javax.swing.JOptionPane.showMessageDialog(null, "The database already exsists.", "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            String createDB = "CREATE DATABASE DBProg32758;";
                            stm = conn.createStatement();
                            stm.executeUpdate(createDB);
                        } catch (java.sql.SQLException ex) {
                            System.out.println(ex.getMessage());
                            if (ex.getSQLState().equals("HY000") && ex.getErrorCode() == 1007)
                                javax.swing.JOptionPane.showMessageDialog(null, "The database already exsists.", "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
                            else {
                                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage() + "SQL State: " + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
            }
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage() + "SQL State: " + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
        }

        String createTblSql = "CREATE TABLE Players (Last_Name VARCHAR(20), First_Name VARCHAR(20), Group VARCHAR(20), Login VARCHAR(20), Password VARCHAR(20), Preferred_Car_Name VARCHAR(20), Logo VARCHAR(20), Score VARCHAR(20));";
        try {
            stm = conn.createStatement();
            stm.executeUpdate(createTblSql);
        } catch (java.sql.SQLException ex) {
            if (ex.getSQLState().equals("42S01") && ex.getErrorCode() == 1050)
                javax.swing.JOptionPane.showMessageDialog(null, "The table already exsists.", "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
            else {
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage() + "SQL State: " + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } finally {
            try {
                stm.close();
                conn.close();
            } catch (java.sql.SQLException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage() + "SQL State: " + e.getSQLState() + " ErrorCode: " + e.getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
