/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Siren.java
 * Main class: USEMyF1Car.java
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 19
 * Java Version: 
 * Description: 
 * ----------------------------------------------------------------------------+
 */

package com.g1;

import java.awt.*;

public class Siren implements  SelfTriggerSiren {

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
     * Starts the siren
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