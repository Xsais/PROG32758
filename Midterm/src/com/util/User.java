/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: User.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 29
 * Last Modified: 10, 2017 29
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.util;

public class User {

    public javafx.beans.property.IntegerProperty score = new javafx.beans.property.SimpleIntegerProperty(this, "score", 0);

    public javafx.beans.property.DoubleProperty credit = new javafx.beans.property.SimpleDoubleProperty(this, "credit", 0);

    private Name fullName;

    private int groupNumber;

    private String username;

    private String preferredCar;

    private int logo;

    public User(String firstName, String lastName, int groupNumber, String user, String preferredCar, int logo, int score) {

        setFullName(firstName, lastName);
        setGroupNumber(groupNumber);
        setUsername(user);
        setPreferredCar(preferredCar);
        setLogo(logo);
        setScore(score);
    }

    public Name getFullName() {

        return fullName;
    }

    public void setFullName(String firstName, String lastName) {

        this.fullName = new Name(firstName, lastName);
    }

    public int getGroupNumber() {

        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {

        this.groupNumber = groupNumber;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPreferredCar() {

        return preferredCar;
    }

    public void setPreferredCar(String preferredCar) {

        this.preferredCar = preferredCar;
    }

    public int getLogo() {

        return logo;
    }

    public void setLogo(int logo) {

        this.logo = logo;
    }

    public int getScore() {

        return score.get();
    }

    public void setScore(int score) {

        this.score.set(score);
    }

    public javafx.beans.property.IntegerProperty scoreProperty() {

        return score;
    }

    public double getCredit() {

        return credit.get();
    }

    public void setCredit(double credit) {

        this.credit.set(credit);
    }

    public javafx.beans.property.DoubleProperty creditProperty() {

        return credit;
    }
}
