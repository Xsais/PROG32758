/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.info.User
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.util.info;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User {

    private IntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

    private DoubleProperty credit = new SimpleDoubleProperty(this, "credit", 0);

    private Name fullName;

    private int groupNumber;

    private String username;

    private String preferredCar;

    private int logo;

    public User(String firstName, String lastName, int groupNumber, String user, String
            preferredCar, int logo, int score, double credit) {

        setFullName(firstName, lastName);
        setGroupNumber(groupNumber);
        setUsername(user);
        setPreferredCar(preferredCar);
        setLogo(logo);
        setScore(score);
        setCredit(credit);
    }

    /**
     * Assigns the Users Full Name
     *
     * @param firstName The desired User First Name
     * @param lastName  The desired User Last Name
     */
    public void setFullName(String firstName, String lastName) {

        this.fullName = new Name(firstName, lastName);
    }

    /**
     * ntly define Retrieves the curred User Full Name
     *
     * @return The currently defined full name for the user
     */
    public Name getFullName() {

        return fullName;
    }

    /**
     * Retrieves the currently defined Group Number for The user
     *
     * @return The currently defined Group Number
     */
    public int getGroupNumber() {

        return groupNumber;
    }

    /**
     * Assigns the desired group number to the user
     *
     * @param groupNumber The desired group number
     */
    public void setGroupNumber(int groupNumber) {

        this.groupNumber = groupNumber;
    }

    /**
     * Retrieves the currently defined Username
     *
     * @return
     */
    public String getUsername() {

        return username;
    }

    /**
     * Assigns the desired Username
     *
     * @param username The desired Username
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * Retrieves the currently defined Preferred Car
     *
     * @return The current value for the PreferredCar
     */
    public String getPreferredCar() {

        return preferredCar;
    }

    /**
     * Assigns the desired Preferred Car
     *
     * @param preferredCar
     */
    public void setPreferredCar(String preferredCar) {

        this.preferredCar = preferredCar;
    }

    /**
     * Retrieves the current defined value for the logo
     *
     * @return The current value for the logo
     */
    public int getLogo() {

        return logo;
    }

    /**
     * Assigns the desired value to the Logo
     *
     * @param logo The desired logo value
     */
    public void setLogo(int logo) {

        this.logo = logo;
    }

    /**
     * Retrieves the current value for the Score
     *
     * @return The current defined Score
     */
    public int getScore() {

        return score.get();
    }

    /**
     * Assign the giving Score
     *
     * @param score The desired score
     */
    public void setScore(int score) {

        this.score.set(score);
    }

    /**
     * Retrieves the property representing the Users Score
     *
     * @return The property repressing the users score
     */
    public IntegerProperty scoreProperty() {

        return score;
    }

    /**
     * Retrieves the current value defined for the credit
     *
     * @return THe currently defened value for the credit
     */
    public double getCredit() {

        return credit.get();
    }

    /**
     * Assign the Credit to the given value
     *
     * @param credit The desired value for the credit
     */
    public void setCredit(double credit) {

        this.credit.set(credit);
    }

    /**
     * Retrieves the property that represents the current value of the Credit
     *
     * @return The property that represents the Credit
     */
    public DoubleProperty creditProperty() {

        return credit;
    }
}
