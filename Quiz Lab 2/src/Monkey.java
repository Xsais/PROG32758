/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Monkey.java
 * Main class: Main.java
 * Other Files in this Project:
 *     - Animals.java
 *     - abstractAnimalsComonBehaviours.java
 *     - HumanBeing.java
 * Assignment:
 * Creation Date: 09, 2017 25
 * Last Modified: 09, 2017 25
 * Java Version: 1.8.0_144
 * Description: Quiz Lab 1 implementation
 * Assignment: Quiz Lab 2
 * Creation Date: 09, 2017 25
 * Last Modified: 09, 2017 25
 * Java Version: 1.8.0_144
 * ----------------------------------------------------------------------------+
 */

public class Monkey extends abstractAnimalsComonBehaviours {
    public void sleeping() {
        System.out.println("zzzz...");
    }
    public void scratching() {
        System.out.println("scratch... scratch...");
    }

    public void pooping() {
        System.out.println("plop plop");
    }

    public void grooming() {
        System.out.println("picking nats from hair");
    }
    public void communicating() {
        System.out.println("making noises");
    }

    public void playing() {
        System.out.println("swinging from some branches");
    }
}

