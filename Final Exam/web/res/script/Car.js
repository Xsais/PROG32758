/*
 *  ----------------------------------------------------------------------------------------------+
 *    * Group Leader: Daniel Hope
 *    * Member(s):
 *    *     - Georgina Luce
 *    *     - Nathaniel Primo
 *    *     - Michael Marc
 *    * Group #: 1
 *    * Filename: /web/res/script/Car.js
 *    * Assignment: Final Exam
 *    * Creation Date: 12/30/17 1:20 PM
 *    * Last Modified: 12/30/17 3:14 PM
 *    * Java Version: 1.8.0_141
 *    * Description: A simple javascript port of the Car.java
 * ----------------------------------------------------------------------------------------------+
 */

//Second Constructor that constructs a Car to be driven by a
// non-experienced person.
//This Car is created with a specified name, and starts the race with an
// initial speed
//given by the player as a parameter to the constructor during its
// creation.
function Car(name, is) {

    //Encapsulation Principle: All the attributes are by default private
    var carName = name;

    var initialSpeed = is ? 0 : is; //startSpeed speed of a car to be driven by a

    var startSpeed = 0; //first speed of the car when it is started

    var currentSpeed = 0; //current speed of the car

    var zeroCounter = 0;//counter of seconds when speedIncreaseStep speed
    // is zero (constant speed)

    let speedIncreaseStep = 0;//Car speed increase step

    //returns the name of the car
    this.getCarName = function () {

        return carName;
    }

    //sets the name of the car
    this.setCarName = function (newName) {

        carName = newName;
    }

    //returns the zeroCounter of the car
    this.getZeroCounter = function () {

        return zeroCounter;
    }

    //sets the zeroCounter of the car
    this.setZeroCounter = function (newZc) {

        zeroCounter = newZc;
    }

    //returns the initialSpeed of the car
    this.getInitialSpeed = function () {

        return initialSpeed;
    }

    //sets the initialSpeed of the car
    this.setInitialSpeed = function (newSpeed) {

        initialSpeed = newSpeed;
    }

    //returns the startSpeed of the car
    this.getStartSpeed = function () {

        return startSpeed;
    }

    //sets the startSpeed of the car
    this.setStartSpeed = function (sp) {

        startSpeed = sp;
    }

    //returns the speedIncreaseStep of the car
    this.getSpeedIncreaseStep = function () {

        return speedIncreaseStep;
    }

    //sets the speedIncreaseStep of the car
    this.setSpeedIncreaseStep = function (newInc) {

        speedIncreaseStep = newInc;
    }

    //returns the currentSpeed of the car
    this.getCurrentSpeed = function () {

        return currentSpeed;
    }

    //sets the currentSpeed of the car
    this.setCurrentSpeed = function (newCurrentSpeed) {

        currentSpeed = newCurrentSpeed;
    }

    //Method to starts the car (initialize the car speed)
    this.start = function (h, l) {

        if (initialSpeed != 0) {

            startSpeed = initialSpeed;
        } else {

            startSpeed = this.createRandomNumber(h, l);
        }
    }

    //Method that generates a random number between minx10 and maxx10
    this.createRandomNumber = function (min, max) {

        return Math.round(Math.random() * 10 * (Math.random() > 0.5 ? max : min));
    }

    //Method that starts running the car (just double the initial speed to run)
    this.StartRunning = function () {

        currentSpeed *= 2;
    }

    //Method that runs the car with positive and negative speedIncreaseStep
    // (normal acceleration and deceleration)
    this.run = function (h, l) {

        speedIncreaseStep = this.createRandomNumber(h, l);
        currentSpeed += speedIncreaseStep;
    }

    //This method accelerates the cars by step of highSpeed (High speed
    // acceleration)
    this.accelerate = function (speed) {

        currentSpeed += speed;
        return currentSpeed;
    }

    //This method accelerates the cars by step of moreGas (Very high speed
    // acceleration)
    this.automaticAccelerationIncrease = function (speed) {

        currentSpeed += speed;
    }
}
