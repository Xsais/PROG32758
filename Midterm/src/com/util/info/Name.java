/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.info.Name
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.util.info;

public class Name {
	
	private String firstName;
	
	private String lastName;
	
	/**
	 * Inatializes a NAme giving the first and last name
	 *
	 * @param firstName The desired First Name
	 * @param lastName The desired Last Name
	 */
	public Name(String firstName, String lastName) {
		
		initFirstName(firstName);
		initLastName(lastName);
	}
	
	/**
	 * Initializes the first name to the giving value
	 *
	 * @param firstName The desired first name
	 * @throws IllegalArgumentException If the giving is null or a null string
	 */
	private final void initFirstName(String firstName) throws IllegalArgumentException {
		
		if(firstName == null || firstName.equals("")) {
			
			throw new IllegalArgumentException("ERROR: The First Name cannot be null or a null " +
					"string");
		}
		this.firstName = firstName;
	}
	
	/**
	 * Initializes the last name to the giving value
	 *
	 * @param lastName The desired last name
	 * @throws IllegalArgumentException If the giving is null or a null string
	 */
	private final void initLastName(String lastName) throws IllegalArgumentException {
		
		if(lastName == null || lastName.equals("")) {
			
			throw new IllegalArgumentException("ERROR: The Last Name cannot be null or a null " +
					"string");
		}
		this.lastName = lastName;
	}
	
	/**
	 * Retrieves the value of the First NAme
	 *
	 * @return The value for the First NAme
	 */
	public String getFirstName() {
		
		return firstName;
	}
	
	/**
	 * Retrieves the value of the Last NAme
	 *
	 * @return The value for the Last NAme
	 */
	public String getLastName() {
		
		return lastName;
	}
	
	/**
	 * Returns a string representation of the Name. In the format: [firstName] [lastName]
	 *
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		
		return String.format("%s %s", firstName, lastName);
	}
}
