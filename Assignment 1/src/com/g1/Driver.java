/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Driver.java
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


public class Driver {
    /*****************************************************************
    *                     Methods                                   *
    *                                                               *
    ****************************************************************/
	//Method used by the Driver to accelerate the car
     public void punchOnAccelorPedal(Car myCar, int accel)      
	{
    	myCar.accelerate(accel);
	}  	
}