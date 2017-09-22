/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: DynamicCar.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
 *     - AmbulanceManagementSystem.java
 *     - Car.java
 *     - Driver.java
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
 * Description: Common duties of a car
 * ----------------------------------------------------------------------------+
 */

package com.g1;

/**
 * Common duties of a car
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
public interface DynamicCar {

    /**
     * Method that generates a random number
     *
     * @param min The lowest possible number
     * @param max The highest possible number
     */
    int createRandomNumber(int min, int max);

    /**
     * Method to starts the car
     *
     * @param h The highest possible number
     * @param l The lowest possible number
     */
    void start(int h, int l);

    /**
     * Method that starts running the car
     *
     */
    void StartRunning();

    /**
     * Method that runs the car with positive and negative
     *
     * @param h The highest possible number
     * @param l The lowest possible number
     */
    void run(int h, int l);

    /**
     * This method accelerates the car
     *
     * @param highspeed The desired speed in which to accelerate by
     */
    int accelerate(int highspeed);
}
