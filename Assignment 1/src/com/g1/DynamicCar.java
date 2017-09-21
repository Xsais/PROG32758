/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: DynamicCar.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 09, 2017 21
 * Last Modified: 09, 2017 21
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.g1;

public interface DynamicCar {

    /**
     * Method that generates a random number
     *
     * @param min The lowest possible number
     * @param max The highest possible number
     */
    int CreateRandomNumber(int min, int max);

    /**
     * Method to starts the car
     *
     * @param h The highest possible number
     * @param l The lowest possible number
     */
    void Start(int h, int l);

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
    void Run(int h, int l);

    /**
     * This method accelerates the car
     *
     * @param highspeed The desired speed in which to accelerate by
     */
    int Accelerate(int highspeed);
}
