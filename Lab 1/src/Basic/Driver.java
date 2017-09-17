/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Driver.java
 * Main class: UseMyCar.java
 * Other Files in this Project:
 *     - Car.java
 * Assignment: Lab 1
 * Creation Date: 2017-09-14
 * Last Modified: 2017-09-16
 * Java Version: 1.8.0_102
 * Description: The representation of a Driver of a Car
 * ----------------------------------------------------------------------------+
 */

package Basic;

/**
 * The representation of a Driver of a Car.
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */

public class Driver {

    /*****************************************************************
     *                     Methods                                   *
     *                                                               *
     ****************************************************************/

    /**
     * Used by the Driver to accelerate the car
     *
     * @param myCar The desired car Object
     * @param accel The desired Acceleration
     */
    public void punchOnAccelorPedal(Car myCar, int accel) {

        myCar.accelerate(accel);
    }
}
