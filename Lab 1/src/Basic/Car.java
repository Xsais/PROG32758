package Basic;

public class Car {

    /*****************************************************************
     *                     Attributes                                *
     *                                                               *
     ****************************************************************/
    //Encapsulation Principle: All the attributes are by default private
    private String carName; //name of the car

    private int startSpeed; //first speed of the car when it is started

    private int initialSpeed; //startSpeed speed of a car to be driven by a non-experimented person

    private int currentSpeed; //current speed of the car

    private int speedIncreaseStep;//Car speed increase step

    private int zeroCounter;//counter of seconds when speedIncreaseStep speed is zero (constant speed)

    /*****************************************************************
     *                     Constructors                              *
     *                                                               *
     ****************************************************************/
    //First Constructor that constructs a Car with just a specified name
    public Car(String myNameCar) {

        carName = myNameCar;
    }

    //Second Constructor that constructs a Car to be driven by a non- experimented person.
    //This Car is created with a specified name, and starts the race with an initial speed
    //given by the player as a parameter to the constructor during its creation.
    public Car(String myNameCar, int is) {

        carName = myNameCar;
        initialSpeed = is;
    }

    //Third is a parameterless Constructor that constructs an empty
    //car that looks exactly as a real car but doesn't really move
    public Car() {

        carName = "Toy car";
    }

    /*****************************************************************
     *                     Methods                                   *
     *                                                               *
     ****************************************************************/
    //Method that generates a random number between minx10 and maxx10
    static int createRandomNumber(int min, int max) {

        min *= 10;
        max *= 10;
        return (int) (Math.random() * (max - min) + 1 + min);
    }

    /*****************************************************************
     *                     Accessor Methods                          *
     *                                                               *
     ****************************************************************/
    //returns the name of the car
    public String getCarName() {

        return carName;
    }

    /*****************************************************************
     *                     Mutator Methods                           *
     *                                                               *
     ****************************************************************/
    //sets the name of the car
    public void setCarName(String newName) {

        carName = newName;
    }

    //returns the startSpeed of the car
    public int getStartSpeed() {

        return startSpeed;
    }

    //sets the startSpeed of the car
    public void setStartSpeed(int sp) {

        startSpeed = sp;
    }

    //returns the initialSpeed of the car
    public int getInitialSpeed() {

        return initialSpeed;
    }

    //sets the initialSpeed of the car
    public void setInitialSpeed(int newSpeed) {

        initialSpeed = newSpeed;
    }

    //returns the currentSpeed of the car
    public int getCurrentSpeed() {

        return currentSpeed;
    }

    //sets the currentSpeed of the car
    public void setCurrentSpeed(int newCurrentSpeed) {

        currentSpeed = newCurrentSpeed;
    }

    //returns the speedIncreaseStep of the car
    public int getSpeedIncreaseStep() {

        return speedIncreaseStep;
    }

    //sets the speedIncreaseStep of the car
    public void setSpeedIncreaseStep(int newInc) {

        speedIncreaseStep = newInc;
    }

    //returns the zeroCounter of the car
    public int getZeroCounter() {

        return zeroCounter;
    }

    //sets the zeroCounter of the car
    public void setZeroCounter(int newZc) {

        zeroCounter = newZc;
    }

    //Method to starts the car (initialize the car speed)
    public void start(int h, int l) {

        if (initialSpeed != 0) {//startSpeed = initialSpeed

            startSpeed = this.initialSpeed;
        } else {//startSpeed = randomNumber generated from createRandomNumber(h, l);

            startSpeed = createRandomNumber(h, l);
        }
    }

    //Method that starts running the car (just double the initial speed to run)
    public void StartRunning() {

        initialSpeed *= 2;
    }

    //Method that runs the car with positive and negative
    //speedIncreaseStep(normal acceleration and deceleration)
    //speedIncreaseStep will be = createRandomNumber(h, l);
    //currentSpeed will be =  speedIncreaseStep;
    public void run(int h, int l) {

        currentSpeed = createRandomNumber(0, h);
        speedIncreaseStep = createRandomNumber(l, h);
    }

    //This method accelerates the cars by step of highSpeed (High speed acceleration).
    // It saves highSpeed to currentSpeed and returns currentSpeed
    public int accelerate(int highSpeed) {

        return currentSpeed += highSpeed;
    }

    //This method accelerates the cars by step of moreGaz (Very high speed acceleration).
    // It will set moreGas to currentSpeed
    public void automaticAccelerationIncrease(int moreGaz) {

        currentSpeed += moreGaz;
    }

}
