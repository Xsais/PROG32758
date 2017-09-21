/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: AmbulanceDriver.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 09, 2017 21
 * Last Modified: 09, 2017 21
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.g1;

public class AmbulanceDriver extends Driver{

	public void AccidentOccursStartAmbulanceCar(){
		
		// Writes 2 blank lines
		System.out.println("\n");
		
		// Displays message
		System.out.println("The Ambulance Driver's Phone is ringing...........");
		
		// Pause 10s
		try {
			Thread.sleep(10000);
        } 
		catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		// Writes 2 blank lines
		System.out.println("\n");
		
		// Start Ambulance Class
		AmbulanceCar ambulance = new AmbulanceCar();
		
		// Displays message
		System.out.println("The Ambulance Car has been started.............");
		
		// Writes 2 blank lines
		System.out.println("\n");
		
		// Communicates position
		CommunicateYourPositioning();
		
		// Writes 2 blank lines
		System.out.println("\n");
		
		// Calls ambulanceCar.EmergencyAlert
		ambulance.EmergencyAlert();
		
	}
	
	public void CommunicateYourPositioning(){
		
		// Writes 2 blank lines
		System.out.println("\n");
		
		// Displays message
		System.out.println("Hello, OK, The Emergency Medical Staff is on his way for Assistance.\n"
				   + "Please stand by.......................");
		
		
	}
}
