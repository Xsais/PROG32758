/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Siren.java
 * Main File: UseMyF1Car.java
 * Other Files in this Project:
 *     - AmbulanceCar.java
 *     - AmbulanceDriver.java
 *     - AmbulanceManagementSystem.java
 *     - Car.java
 *     - Driver
 *     - DynamicCar.java
 *     - ElectricLifeSaving.java
 *     - Formula1.java
 *     - Formula1Version2.java
 *     - ISCar.java
 *     - SelfTriggerSiren.java
 * Assignment: Assignment 1
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 23
 * Java Version: 1.8.0_144
 * Description: Represents a siren to be paired with an Ambulance
 * ----------------------------------------------------------------------------+
 */

package com.g1;

import java.awt.*;

/**
 * Represents a siren to be paired with an Ambulance
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */
public class Siren implements SelfTriggerSiren {

    /**
     * Fires a high pitched sound
     *
     * @throws  InterruptedException
     *          if any thread has interrupted the current thread. The
     *          <i>interrupted status</i> of the current thread is
     *          cleared when this exception is thrown.
     */
    public void fireSiren() throws InterruptedException {

        for (int d = 3; d > 0; d--) {

            for (int i = 2; i > 0; i--) {

                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(1000);
            }
            for (int i = 1; i > 0; i--) {

                Thread.sleep(1000);
            }
        }
    }

    /**
     * Prepare and fires the siren
     *
     */
    @Override
    public void TriggerSiren() {

        try {
            fireSiren();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}