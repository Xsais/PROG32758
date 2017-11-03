/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.game.Driver
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.util.game;

public class Driver {
	
	/*****************************************************************
	 *                     Methods                                   *
	 *                                                               *
	 ****************************************************************/
	//Method used by the Driver to accelerate the car
	public void punchOnAccelorPedal(Car myCar, int accel) {
		
		myCar.accelerate(accel);
	}
}