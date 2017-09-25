/* 
* ----------------------------------------------------------------------------+ 
* Group Leader: Daniel Hope 
* Member(s): Georgina Luce 
*            Nathaniel Primo 
*            Michael Marc 
* Group #: 1 
* Filename: AmbulanceCar.java 
* Main File: UseMyF1Car.java
* Other Files in this Project:
*     - AmbulanceDriver.java
*     - AmbulanceManagementSystem.java
*     - Car.java
*     - Driver.java
*     - DynamicCar.java
*     - ElectricLifeSaving.java
*     - Formula1.java
*     - Formula1Version2.java
*     - ISCar.java
*     - SelfTriggerSiren.java
*     - Siren.java
* Assignment: Assignment 1
* Creation Date: 09, 2017 21 
* Last Modified: 09, 2017 21 
* Java Version: 1.8.0_144
* Description: The representation of a AmbulanceCar object that extends the Car parent class
* ---------------------------------------------------------------------------- 
*/

package com.g1;

/**
 * The representation of a AmbulanceCar object that extends the Car parent class
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
public class AmbulanceCar extends Car {

    /**
     * Method to invoke the fireSiren method
     *
     */
    public void EmergencyAlert() {

        // Create Siren object to call fireSiren method
        try {

            new Siren().fireSiren();

            // Catch and alert user of exception
        } catch (InterruptedException e) {

            System.out.println("error with siren");
        }
    }
}
