package com.utils.html;

public class Attribute<T> {

    // Stores the Name of the attribute
    private String name;

    // Stores the value of the attribute
    private T value;

    /**
     * Creates an attribute giving a name and a value
     *
     * @param name The desired name of the attribute
     * @param value The desired value of the attribute
     */
    public Attribute(String name, T value) {

        setName(name);
        setValue(value);
    }

    /**
     * Retrieves the current name for the attribute
     *
     * @return
     */
    public String getName() {

        return name;
    }

    /**
     *  Assign the desired name to the attribute
     *
     * @param name The desired name of the attribute
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Retrieves the currently defined value of the attribute
     *
     * @return The current value of the attribute
     */
    public T getValue() {

        return value;
    }

    /**
     * Assigns the value of the attribute
     *
     * @param value The desired value for the attribute
     */
    public void setValue(T value) {

        this.value = value;
    }

    /**
     * A string representation of the attribute in the format:
     *
     *              [name]=[value]
     *
     * @return The string reforestation of the attribute
     */
    @Override
    public String toString() {

        return String.format("%s='%s'", name, value);
    }
}
