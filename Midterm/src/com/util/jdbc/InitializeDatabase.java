/*

 * ----------------------------------------------------------------------------+

 * Group Leader: Daniel Hope

 * Member(s): Georgina Luce

 *            Nathaniel Primo

 *            Michael Marc

 * Group #: 1

 * Filename: InitializeDataBase.java

 * Other Files in this Project:

 *     -

 * Assignment: Midterm - Micro-Project 1 (Part 1)

 * Creation Date: 11, 2017 24

 * Last Modified: 11, 2017 25

 * Java Version: 1.8.1_141

 * Description: Reads the Prog32758Students file, clears the sql table, adds firstname and lastname to table

 * ----------------------------------------------------------------------------+

 */

package com.util.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * The InitializeDatabase class clears the Player table to ensure data
 * is not duplicated, reads the file and writes the first and last name 
 * values to the Players table
 */
public class InitializeDatabase {
	
	public InitializeDatabase(ConnectToDB dbConnection) {
		
		try {			
			dbConnection.executeUpdate("DELETE FROM Players");

			for(Scanner sc = new Scanner(new File("src\\com\\res\\data\\Prog32758Students.txt")); sc.hasNextLine(); ) {
				
				String line = sc.nextLine();
				if (line.length() == 0) {
					continue;
				}
				String[] tokens = line.split(",");
	
				dbConnection.executeUpdate("INSERT INTO Players (Last_Name, First_Name, `Group`) " + "VALUES ('" + tokens[0] + "', '" + tokens[1] + "', " + Integer.parseInt(tokens[2]) +")");
				}
			JOptionPane.showMessageDialog(null, "Database initialization successfully completed.\n Click OK to continue.", "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage() + " Error: File not found.", "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "SQL State: " + e.getSQLState() + " ErrorCode: " + e.getErrorCode(), "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
		}
	
	}

}
