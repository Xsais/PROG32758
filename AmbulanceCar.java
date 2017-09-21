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
 *     - 
 * Assignment: 
 * Creation Date: 09, 2017 21
 * Last Modified: 09, 2017 21
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.g1;

public class AmbulanceCar extends Car.AmbulanceCar{
	
	Siren sr = new Siren();
	
	public void EmergencyAlert(){
		sr.fireSiren();
	}
}
