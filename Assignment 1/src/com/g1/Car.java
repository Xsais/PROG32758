/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Car.java
 * Main class: USEMyF1Car.java
 * Other Files in this Project:
 *     -
 * Assignment:
 * Creation Date: 09, 2017 19
 * Last Modified: 09, 2017 19
 * Java Version:
 * Description:
 * ----------------------------------------------------------------------------+
 */

package com.g1;

public class Car implements DynamicCar
{
    /*****************************************************************
     *                     Attributes                                *
     *                                                               *
     ****************************************************************/
    //Encapsulation Principle: All the attributes are by default private
	private String carName; //name of the car
	private int startSpeed; //first speed of the car when it is started
	private int initialSpeed; //startSpeed speed of a car to be driven by a non-experimented person
    private int currentSpeed; //current speed of the car
    protected int speedIncreaseStep;//Car speed increase step
    private int zeroCounter;//counter of seconds when speedIncreaseStep speed is zero (constant speed)

    
    /*****************************************************************
     *                     Constructors                              *
     *                                                               *
     ****************************************************************/
    //First Constructor that constructs a Car with just a specified name
    public Car(String myNameCar)
    {
    	carName=myNameCar;
    }

    //Second Constructor that constructs a Car to be driven by a non-experimented person.
    //This Car is created with a specified name, and starts the race with an initial speed
    //given by the player as a parameter to the constructor during its creation.
    public Car(String myNameCar, int is)
    {
        carName=myNameCar;
        initialSpeed = is;
    }
    //Third is a parameterless Constructor that constructs an empty          
    //car that looks exactly as a real car but doesn't really move
    public Car()
    {
    	carName="ToyCar";
    }      
        
    /*****************************************************************
     *                     Accessor Methods                          *
     *                                                               *
     ****************************************************************/
    //returns the name of the car
    public String getCarName()
    {
		return this.carName;    
    }
    
    //returns the startSpeed of the car
    public int getStartSpeed()
    {
         return this.startSpeed;
    }
    
    //returns the initialSpeed of the car
    public int getInitialSpeed()
    {
		return this.initialSpeed;
    } 
    
    //returns the currentSpeed of the car
    public int getCurrentSpeed()
    {
		return this.currentSpeed;
    } 
    
    //returns the speedIncreaseStep of the car
    public int getSpeedIncreaseStep()
  	{
		return this.speedIncreaseStep;  	
    }
    
    //returns the zeroCounter of the car
    public int getZeroCounter()
    {
		return this.zeroCounter;
    }  
  
    /*****************************************************************
     *                     Mutator Methods                           *
     *                                                               *
     ****************************************************************/
    //sets the name of the car
    public void setCarName(String newName)
    {
    	this.carName=newName;
    }
    
    //sets the startSpeed of the car
    public void setStartSpeed(int sp)
    {
         this.startSpeed = sp;
    } 
    
    //sets the initialSpeed of the car
    public void setInitialSpeed(int newSpeed)
    {
         this.initialSpeed=newSpeed;
    }
    
    //sets the currentSpeed of the car
    public void setCurrentSpeed(int newCurrentSpeed)
    {
    	 this.currentSpeed=newCurrentSpeed;
    }
    
    //sets the speedIncreaseStep of the car
    public void setSpeedIncreaseStep(int newInc)
    {
    	 this.speedIncreaseStep=newInc;
    }
    
    //sets the zeroCounter of the car
    public void setZeroCounter(int newZc)
    {
    	 this.zeroCounter=newZc;
    }
    
    /*****************************************************************
     *                     Methods                                   *
     *                                                               *
     ****************************************************************/
	//Method that generates a random number between minx10 and maxx10
	 public int createRandomNumber(int min, int max){
		int myRandomInt = (int)(Math.random() * 10 * (Math.random() > 0.5 ? min : max));
		return myRandomInt;
		}
	
	//Method to starts the car (initialize the car speed)
    public void start(int h, int l)
    {
    	if(getInitialSpeed() != 0){
    		this.startSpeed = getInitialSpeed();
    	}
    	else{
    		this.startSpeed = this.createRandomNumber(h, l);
    	}
    }
    
	//Method that starts running the car (just double the initial speed to run)
    public void StartRunning()
    {
    	this.currentSpeed = getStartSpeed()*2;
    }
    
    //Method that runs the car with positive and negative speedIncreaseStep(normal acceleration and deceleration)
    public void run(int h, int l)
    {
    	this.speedIncreaseStep = this.createRandomNumber(h, l);
    	this.currentSpeed += getSpeedIncreaseStep();
    }
	
    //This method accelerates the cars by step of highSpeed (High speed acceleration)
    public int accelerate(int highSpeed)
    {
    	this.currentSpeed += highSpeed;
    	return getCurrentSpeed();
    }
    
    //This method accelerates the cars by step of moreGas (Very high speed acceleration)
    public void automaticAccelerationIncrease(int moreGas)
    {
    	this.currentSpeed += moreGas;
    }

}    
