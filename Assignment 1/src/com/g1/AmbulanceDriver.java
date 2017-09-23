/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: AmbulanceDriver.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
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
 * Last Modified: 09, 2017 23
 * Java Version: 1.8.0_144
 * Description: The person or thing that drives the ambulance
 * ----------------------------------------------------------------------------+
 */

package com.g1;

/**
 * The person or thing that drives the ambulance
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
public class AmbulanceDriver extends Driver {

    /**
     * Starts the process of calling an Ambulance
     *
     */
    public void AccidentOccursStartAmbulanceCar() {

        // Writes 2 blank lines
        System.out.println("\n");

        // Displays message
        System.out.println("The Ambulance Driver's Phone is ringing...........");

        // Pause 10s
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
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

    /**
     * Declares the Ambulances current position
     *
     */
    public void CommunicateYourPositioning() {

        // Writes 2 blank lines
        System.out.println("\n");

        // Displays message
        System.out.println("Hello, OK, The Emergency Medical Staff is on his way for Assistance.\n"
                + "Please stand by.......................");


    }
}
