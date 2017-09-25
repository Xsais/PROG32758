/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: AmbulanceManagementSystem.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
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
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 19
 * Java Version: 1.8.0_144
 * Description: A system that manages the ambulances
 * ----------------------------------------------------------------------------+
 */

package com.g1;

/**
 * A system that manages the ambulances
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
public class AmbulanceManagementSystem {

    /**
     * Creates an AmbulanceManagementSystem
     *
     */
    public AmbulanceManagementSystem() {

    }

    /**
     * After 10 seconds tells Ambulance driver to start the car
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
