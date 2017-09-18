/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Car.java
 * Main class: UseMyCar.java
 * Other Files in this Project:
 *     - Driver.java
 * Assignment: Lab 1
 * Creation Date: 2017-09-14
 * Last Modified: 2017-09-16
 * Java Version: 1.8.0_102
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package Basic;

/**
 * The representation of a Car object.
 *
 * @author Daniel Hope, Georgina Luce, Nathaniel Primo, Michael Marc
 */

public class Car {

    /*****************************************************************
     *                     Attributes                                *
     *                                                               *
     ****************************************************************/
    //**Encapsulation Principle: All the attributes are by default private**

    // name of the car
    private String carName = "Toy car";

    //first speed of the car when it is started
    private int startSpeed;

    //startSpeed speed of a car to be driven by a non-experimented person
    private int initialSpeed;

    //current speed of the car
    private int currentSpeed;

    //Car speed increase step
    private int speedIncreaseStep;

    //counter of seconds when speedIncreaseStep speed is zero (constant speed)
    private int zeroCounter;

    /*****************************************************************
     *                     Constructors                              *
     *                                                               *
     ****************************************************************/

    /**
     * Creates a new car object with the giving name
     *
     * Original Note: First Constructor that constructs a Car with just a specified name
     *
     * @param myNameCar The desired name of the car
     */
    public Car(String myNameCar) {

        carName = myNameCar;
    }

    /**
     *  Creates a new car object with the giving name and initial speed
     *
     * Original Note: Second Constructor that constructs a Car to be driven by a non- experimented person.
     * This Car is created with a specified name, and starts the race with an initial speed
     * given by the player as a parameter to the constructor during its creation.
     *
     * @param myNameCar The desired name of the car
     * @param is The desired Initial speed of the car
     */
    public Car(String myNameCar, int is) {

        carName = myNameCar;
        initialSpeed = is;
    }

    /**
     * Creates a Car Object
     *
     * Original Note: Third is a parameterless Constructor that constructs an empty
     * car that looks exactly as a real car but doesn't really move
     *
     */
    public Car() { }

    /*****************************************************************
     *                     Methods                                   *
     *                                                               *
     ****************************************************************/
    //Method that generates a random number between minx10 and maxx10

    /**
     * Generates a number given a maximum and a minimum (Note: max and min are multiplied by ten)
     *
     * @param min The desired minimum number in which the number should be bigger than
     * @param max The desired maximum number in which the number should be smaller than
     * @return
     */
    static int createRandomNumber(int min, int max) {

        min*= 10;
        max *= 10;
        return (int) (Math.random() * (max - min) + 1 + min);
    }

    /*****************************************************************
     *                     Accessor Methods                          *
     *                                                               *
     ****************************************************************/

    /**
     * Retrieves the curent value for the Car Name
     *
     * Original Note: returns the name of the car
     *
     * @return The current value of Car Name
     */
    public String getCarName() {

        return carName;
    }

    /**
     * Retrieves the current value for the Car Speed
     *
     * Original Note: returns the startSpeed of the car
     *
     * @return The current value of the Car Speed
     */
    public int getStartSpeed() {

        return startSpeed;
    }

    /**
     * Retrieves the curent value for the Car Initial speed
     *
     * Original Note: returns the initialSpeed of the car
     *
     * @return The current value of the Cars Initial Speed
     */
    public int getInitialSpeed() {

        return initialSpeed;
    }

    /**
     * Retrieves the Current Speed of the Car
     *
     * Original Note: returns the currentSpeed of the car
     *
     * @return The current speed of thee car
     */
    public int getCurrentSpeed() {

        return currentSpeed;
    }

    /**
     * Retrieves the current Zero-Counter of the car
     *
     * Original Note: returns the zeroCounter of the car
     *
     * @return The current zero counter of the car
     */
    public int getZeroCounter() {

        return zeroCounter;
    }

    /**
     * Retrieves the current speed in which the car increases
     *
     * Original Note: returns the speedIncreaseStep of the car
     *
     * @return The current speed in which the car increases
     */
    public int getSpeedIncreaseStep() {

        return speedIncreaseStep;
    }

    /*****************************************************************
     *                     Mutator Methods                           *
     *                                                               *
     ****************************************************************/

    /**
     * Assigns the specified Car Color
     *
     * Original Note: sets the name of the car
     *
     * @param newName The color in which the car is
     */
    public void setCarName(String newName) {

        carName = newName;
    }

    /**
     * Assigns the speed in which the Car starts at
     *
     * Original Note: sets the startSpeed of the car
     *
     * @param sp The desired speed at which the car starts
     */
    public void setStartSpeed(int sp) {

        startSpeed = sp;
    }

    /**
     * Assigns the Initail speed in which the car starts
     *
     * Original Note: sets the initialSpeed of the car
     *
     * @param newSpeed The desired initial speed
     */
    public void setInitialSpeed(int newSpeed) {

        initialSpeed = newSpeed;
    }

    /**
     * Assign the Current speed of the Car
     *
     * @param newCurrentSpeed
     */
    public void setCurrentSpeed(int newCurrentSpeed) {

        currentSpeed = newCurrentSpeed;
    }

    /**
     * Determines the speed in which the car increases gradually
     *
     * Original Note: sets the speedIncreaseStep of the car
     *
     * @param newInc The desired increase speed
     */
    public void setSpeedIncreaseStep(int newInc) {

        speedIncreaseStep = newInc;
    }

    /**
     * Assigns the Zero Counter of the car
     *
     * Original Note: sets the zeroCounter of the car
     *
     * @param newZc The desire Zero Counter
     */
    public void setZeroCounter(int newZc) {

        zeroCounter = newZc;
    }

    /**
     * Starts the car giving a maximum and minimum (Note: max and min are multiplied by ten)
     *
     * Original Note: Method to starts the car (initialize the car speed)
     *
     * @param h The highest value (Maximum)
     * @param l The lowest value (Minimum)
     */
    public void start(int h, int l) {

        if (initialSpeed != 0) {//startSpeed = initialSpeed

            startSpeed = this.initialSpeed;
        } else {//startSpeed = randomNumber generated from createRandomNumber(h, l);

            startSpeed = createRandomNumber(l, h);
        }
    }

    /**
     * Starts running the car
     *
     * Orignal Note: Method that starts running the car (just double the initial speed to run)
     *
     */
    public void StartRunning() {

        initialSpeed *= 2;
    }

    /**
     * Runs the car giving a maximum and minimum number  (Note: max and min are multiplied by ten)
     *
     * Original Note: Method that runs the car with positive and negative
     * speedIncreaseStep(normal acceleration and deceleration)
     * speedIncreaseStep will be = createRandomNumber(h, l);
     * currentSpeed will be =  speedIncreaseStep;
     *
     * @param h The highest value (Maximum)
     * @param l The lowest value (Minimum)
     */
    public void run(int h, int l) {

        currentSpeed = createRandomNumber(l, h);
        speedIncreaseStep = createRandomNumber(l, h);
    }

    /**
     * Accelerate the car by a specified factor
     *
     * Original Note: This method accelerates the cars by step of highSpeed (High speed acceleration).
     * It saves highSpeed to currentSpeed and returns currentSpeed
     *
     * @param highSpeed The speed in which to accelerate the car
     * @return The new speed of the car after acceleration
     */
    public int accelerate(int highSpeed) {

        return currentSpeed += highSpeed;
    }

    /**
     * Accelerate the car by a specified factor
     *
     * Original Note: This method accelerates the cars by step of moreGaz (Very high speed acceleration).
     * It will set moreGas to currentSpeed
     *
     * @param moreGaz The speed in which to accelerate the car
     */
    public void automaticAccelerationIncrease(int moreGaz) {

        currentSpeed += moreGaz;
    }

}
