/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: AmbulanceManagementSystem.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 19
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.g1;

public class AmbulanceManagementSystem {

    /**
     * Creates an AmbulanceManagementSystem
     *
     */
    public AmbulanceManagementSystem() { }

    /**
     * after 10 seconds tells Ambulance driver to start the car
     *
     */
    public void EmergencyAlertAmbulanceDriver() {

        try {

            Thread.sleep(10000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        new AmbulanceDriver().AccidentOccursStartAmbulanceCar();
    }
}
