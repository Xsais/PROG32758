/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Driver.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
 *     - AmbulanceManagementSystem.java
 *     - Car.java
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
 * Description: Representation of a driver of a car
 * ----------------------------------------------------------------------------+
 */

package com.g1;

/**
 * Representation of a driver of a car
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
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