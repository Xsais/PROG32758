/* 
* ----------------------------------------------------------------------------+ 
* Group Leader: Daniel Hope 
* Member(s): Georgina Luce 
*            Nathaniel Primo 
*            Michael Marc 
* Group #: 1 
* Filename: AmbulanceCar.java 
* Main class:  
* Other Files in this Project: 
* 
* Assignment:  
* Creation Date: 09, 2017 21 
* Last Modified: 09, 2017 21 
* Java Version:  
* Description: The representation of a AmbulanceCar object that extends the Car parent class
* ---------------------------------------------------------------------------- 
*/ 

package com.g1;

// Class AmbulanceCar that inherits from Car class
public class AmbulanceCar extends Car {
	
	// Method to invoke the fireSiren method
	public void emergencyAlert() {
		
		// Create Siren object to call fireSiren method
		try {
			new Siren().fireSiren();
		
		// Catch and alert user of exception
		} catch (InterruptedException e) {
			System.out.println("error with siren");
		}
	}
}
