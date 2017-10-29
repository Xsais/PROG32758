/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Name.java
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

public class Name {

    private String firstName;

    private String lastName;

    public Name(String firstName, String lastName) {

        initFirstName(firstName);
        initLastName(lastName);
    }

    public String getFirstName() {

        return firstName;
    }

    private final void initFirstName(String firstName) throws IllegalArgumentException {

        if (firstName == null || firstName.equals("")) {

            throw new IllegalArgumentException("ERROR: The First Name cannot be null or a null string");
        }
        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    private final void initLastName(String lastName) throws IllegalArgumentException {

        if (lastName == null || lastName.equals("")) {

            throw new IllegalArgumentException("ERROR: The Last Name cannot be null or a null string");
        }
        this.lastName = lastName;
    }
}
