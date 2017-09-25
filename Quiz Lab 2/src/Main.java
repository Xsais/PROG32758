/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Main.java
 * Main class: 
 * Other Files in this Project:
 *     - Animals.java
 *     -
 * Assignment: 
 * Creation Date: 09, 2017 25
 * Last Modified: 09, 2017 25
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

public class Main {

    public static void printQuestion(String qNum) {

       System.out.printf("Results for %s : ", qNum);
    }

    public static void main(String... args) {

        // Question 7.1
        Monkey Bobo = new Monkey();

        // Question 7.2
        HumanBeing John = new HumanBeing();

        // Question 7.3
        printQuestion("7.4");
        Bobo.grooming();

        // Question 7.5
        printQuestion("7.6");
        John.pooping();

        // Question 7.7 - Compile Error
        // Bobo.talking();

        // Question 8.0 - Compile error
        // John.playing();

        // Question 7.11
        Animals Bobo2 = new Monkey();

        // Question 7.12
        Animals John2 = new HumanBeing();

        // Question 7.13
        printQuestion("7.14");
        John2.sleeping();

        // Question 7.16 - Compile Error
        //bJohn2.hangingAround();

        // Question 7.18 - Compile Error
        //bBobo2.talking();
    }
}
