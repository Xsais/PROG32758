/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: USEMyF1Car.java
 * Other Files in this Project:
 *     -
 * Assignment:
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 19
 * Java Version:
 * Description:
 * ----------------------------------------------------------------------------+
 */

package com.g1;


public class Formula1Version2  extends Formula1 {

	public void ElectronicCrashDetection() {
		System.out.println("\n");
		AlertAmbulancetManagementSystem();
	}
	
	public void AlertAmbulancetManagementSystem() {
		System.out.println("\n");
		System.out.println("The Catastrophe Automatic Detection is triggered by the Car Controller System.");
		System.out.println("\n");
		try { 
			System.out.println("The Ambulance Remote System is alerted. Please Wait  ! ! ! ! ! ! ! ! ! ! ! ! !");
  			Thread.sleep(10000);   
  	  	}
    	catch(Exception e) {
    		System.out.println("Sleep error.");
    	}
		AmbulanceManagementSystem abs = new AmbulanceManagementSystem();
		abs.EmergencyAlertAmbulanceDriver();
	}	
	
}
