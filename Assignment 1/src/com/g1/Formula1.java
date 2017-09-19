/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Formula1.java
 * Main class: USEMyF1Car.java
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

public class Formula1 extends ISCar {

	public Formula1(String name, int min, int max, int factor) {
		super(name, min, max, factor); 
	}
	
	public Formula1(String name, int x, int min, int max, int factor) {
		super(name, x, min, max, factor);
	}

		//Method that generates a random number between minx10 and maxx10
	 int AutomaticCreationRandomNumber(int min, int max, int factor){
		int myRandomInt = (int)(Math.random() * factor * (Math.random() > 1 ? min : max));
		return myRandomInt;
		}			
}
