/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: ElectronicLifeSaving.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
 *     - AmbulanceManagementSystem.java
 *     - Car.java
 *     - Driver.java
 *     - DynamicCar.java
 *     - Formula1.java
 *     - Formula1Version2.java
 *     - ISCar.java
 *     - SelfTriggerSiren.java
 *     - Siren.java
 * Assignment: Assignment 1
 * Creation Date: 09, 2017 21
 * Last Modified: 09, 2017 21
 * Java Version: 1.8.0_144
 * Description: Common duties of the Electronic Life Saving system
 * ----------------------------------------------------------------------------+
 */

package com.g1;

/**
 * Common duties of the Electronic Life Saving system
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
public interface ElectronicLifeSaving {

    /**
     * Detects when there has been an electronic crash
     *
     */
    void CrashElectronicDetection();

    /**
     * Alerts the ambulance of a crash
     *
     */
    void AlertAmbulanceManagementSystem();
}
