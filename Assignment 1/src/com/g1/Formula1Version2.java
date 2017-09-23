/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Formula1Version2.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
 *     - AmbulanceManagementSystem.java
 *     - Car.java
 *     - Driver.java
 *     - DynamicCar.java
 *     - ElectricLifeSaving.java
 *     - Formula1.java
 *     - ISCar.java
 *     - SelfTriggerSiren.java
 *     - Siren.java
 * Assignment: Assignment 1
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 23
 * Java Version: 1.8.0_144
 * Description: Updated Formula1 class to adhere to government initiatives for
 * Electronic Control System for Crash Detection (ECSCD)
 * ----------------------------------------------------------------------------+
 */

package com.g1;

/**
 * Updated Formula1 class to adhere to government initiatives for
 * Electronic Control System for Crash Detection (ECSCD)
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */

public class Formula1Version2 extends Formula1 implements ElectronicLifeSaving {

    /**
     * Constructor that initializes name, min, max, and factor attributes
     *
     * @param name The desired name of the Car
     * @param min The minimum random speed
     * @param max The maximum random speed
     * @param factor The speed factor increase
     */
    public Formula1Version2(String name, int min, int max, int factor) {

        // call to the Formula1 parent constructor
        super(name, min, max, factor);
    }

    /**
     * Constructor that initializes name, initial speed, min, max, and factor attributes
     *
     * @param name The desired name of the Car
     * @param x The initial speed of the car
     * @param min The minimum random speed
     * @param max The maximum random speed
     * @param factor The speed factor increase
     */
    public Formula1Version2(String name, int x, int min, int max, int factor) {

        // call to the Formula1 parent constructor
        super(name, x, min, max, factor);
    }

    /**
     * Implements the Electronic Crash Detection method
     *
     */
    public void ElectronicCrashDetection() {

        // print two blank lines for clarity of message
        System.out.println("\n");

        // call method to alert Ambulance Management System
        AlertAmbulanceManagementSystem();
    }

    /**
     * Prepares for crash
     *
     */
    public void CrashElectronicDetection() {

        // Detects an Electronic Crash
        ElectronicCrashDetection();
    }

    /**
     * Alert user that Catastrophe Automatic Detection and Ambulance Remote System's have been initialized
     *
     */
    public void AlertAmbulanceManagementSystem() {

        // print two blank lines for message clarity
        System.out.println("\n");

        System.out.print("The Catastrophe Automatic Detection is triggered by the Car Controller System.");

        // print two blank lines for message clarity
        System.out.println("\n");

        System.out.println("The Ambulance Remote System is alerted. Please Wait  ! ! ! ! ! ! ! ! ! ! ! ! !");

        // pause program for dramatic effect
        try {

            Thread.sleep(10000);
        } catch (Exception e) {

            System.out.println("error");
        }

        // create object to use method that will alert an ambulance driver object of emergency
        AmbulanceManagementSystem ams = new AmbulanceManagementSystem();

        ams.EmergencyAlertAmbulanceDriver();
    }
}
