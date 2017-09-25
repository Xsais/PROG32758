/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Formula1.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
 *     - AmbulanceManagementSystem.java
 *     - Car.java
 *     - Driver.java
 *     - DynamicCar.java
 *     - ElectricLifeSaving.java
 *     - Formula1Version2.java
 *     - ISCar.java
 *     - SelfTriggerSiren.java
 *     - Siren.java
 * Assignment: Assignment 1
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 19
 * Java Version: 1.8.0_144
 * Description: A type of Car that is capable of great speed
 * ----------------------------------------------------------------------------+
 */

package com.g1;

public class Formula1 extends ISCar {

    public Formula1(String name, int min, int max, int factor) {

        super(name, min, max, factor);
    }

    public Formula1(String name, int x, int min, int max, int factor) {

        super(name, x, min, max, factor);
    }

    //Method that generates a random number between minx10 and maxx10
    int AutomaticCreationRandomNumber(int min, int max, int factor) {

        int myRandomInt = (int) (Math.random() * factor * (Math.random() > 1 ? min : max));
        return myRandomInt;
    }
}
