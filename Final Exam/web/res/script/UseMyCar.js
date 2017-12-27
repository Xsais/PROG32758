function CarTest() {


    this.theWinner = undefined;

    this.UseMyCar = async function (carName, print) {

        // Determines weather the execution is Sleeping
        this.isSleeping = false;


        //Create a car named specified just by its name : �Toyota�.
        var myFirstCar = new Car(carName);

        var wasCrash = false;


        //Create a car with a specified name: �Mazda� to be driven by a

        // non-experienced person.

        //This Car will start the race with an initial speed given by the

        // player as a parameter to the constructor

        //Let's take 20.

        var mySecondCar = new Car("ComputerCar");


        //Create the first Driver that will drive the first car

        var firstDriver = new Driver();


        //Create the second Driver that will drive the second car

        var secondDriver = new Driver();


        print();

        print("&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;" + "CAR RACING");
        print();

        print("&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;" + "----------");
        print();

        print();

        print();


        //Cars start

        myFirstCar.start(1, 0);

        mySecondCar.start(1, 0);


        //Cars start running. From here they all get the current speed

        myFirstCar.StartRunning();

        myFirstCar.setSpeedIncreaseStep(myFirstCar.getCurrentSpeed());


        mySecondCar.StartRunning();

        mySecondCar.setSpeedIncreaseStep(mySecondCar.getCurrentSpeed());


        //Let's race for 30 seconds

        let i = 1;

        while (i < 60) {

            if (this.isSleeping) {

                await new Promise(resolve => {
                    setTimeout(() => {
                        resolve();
                    }, 1000);
                });
                continue;
            }

            //if myFirstCar speedIncreaseStep is positive (Acceleration)

            if (myFirstCar.getSpeedIncreaseStep() > 0) {

                print(myFirstCar.getSpeedIncreaseStep() + " :: " + myFirstCar

                    .getCarName() + " >>> : " + myFirstCar.getCurrentSpeed() + "&ensp;&ensp;&ensp;&ensp;");

            }

            //if myFirstCar current speedIncreaseStep is negative

            // (Deceleration - Slow down)

            else if (myFirstCar.getSpeedIncreaseStep() < 0) {

                print(myFirstCar.getSpeedIncreaseStep() + " :: " + myFirstCar

                    .getCarName() + " <<< : " + myFirstCar.getCurrentSpeed() + "&ensp;&ensp;&ensp;&ensp;");

            }

            //if myFirstCar speedIncreaseStep speed is zero (Constant speed)

            else {

                //increment the car zeroCounter counter

                myFirstCar.setZeroCounter(myFirstCar.getZeroCounter() + 1);

                //prepare gas

                let gas = createInjection(2, 0);

                //Increase the speed related to the amount of gas alloted

                myFirstCar.setSpeedIncreaseStep(gas);

                //Get this speed when the driver punches on the accelerator

                // pedal

                firstDriver.punchOnAccelorPedal(myFirstCar, myFirstCar.getSpeedIncreaseStep());

                print(myFirstCar.getSpeedIncreaseStep() + " :: " + myFirstCar

                    .getCarName() + " +++ : " + myFirstCar.getCurrentSpeed() + "&ensp;&ensp;&ensp;&ensp;");

                //if the number of times zeroCounter has passed to zero is

                // multiple of 3

                if (myFirstCar.getZeroCounter() % 3 == 0) {

                    //prepare even more gas

                    let moreGas = createInjection(myFirstCar.getZeroCounter(), 0);

                    //Automatically increase the speed related to the amount

                    // of gas alloted

                    myFirstCar.automaticAccelerationIncrease(gas);

                    print(myFirstCar.getSpeedIncreaseStep() + " :: " + "" + myFirstCar

                            .getCarName() + " " + "*** : " + myFirstCar.getCurrentSpeed() +

                        "&ensp;&ensp;&ensp;&ensp;");

                }

            }

            //if mySecondCar speedIncreaseStep is positive (Acceleration)

            if (mySecondCar.getSpeedIncreaseStep() > 0) {

                print(mySecondCar.getSpeedIncreaseStep() + " :: " + mySecondCar

                    .getCarName() + " >>> " + ": " + mySecondCar.getCurrentSpeed());
                print();

                //if mySecondCar current speedIncreaseStep is negative

                // (Deceleration - Slow down)

            } else if (mySecondCar.getSpeedIncreaseStep() < 0) {

                print(mySecondCar.getSpeedIncreaseStep() + " :: " + mySecondCar

                    .getCarName() + " <<< " + ": " + mySecondCar.getCurrentSpeed());
                print();

            }

            //if mySecondCar  speedIncreaseStep speed is zero (Constant speed)

            else {

                //increment the car zeroCounter counter

                mySecondCar.setZeroCounter(mySecondCar.getZeroCounter() + 1);

                //increment the car zeroCounter counter

                let gas = createInjection(1, 0);

                //Increase the speed related to the amount of gas alloted

                mySecondCar.setSpeedIncreaseStep(gas);

                //Get this speed when the driver punches on the accelerator

                // pedal

                secondDriver.punchOnAccelorPedal(mySecondCar, mySecondCar.getSpeedIncreaseStep());

                print(mySecondCar.getSpeedIncreaseStep() + " :: " + mySecondCar

                        .getCarName() + " +++ " + ": " + mySecondCar.getCurrentSpeed() + "&ensp;&ensp;" +

                    "&ensp;&ensp;");
                print();

                //if the number of times zeroCounter has passed to zero is

                // multiple of 3

                if (mySecondCar.getZeroCounter() % 3 == 0) {

                    //prepare even more gas

                    let moreGas = createInjection(mySecondCar.getZeroCounter(), 0);

                    //Automatically increase the speed related to the amount

                    // of gas alloted

                    mySecondCar.automaticAccelerationIncrease(gas);

                    print(mySecondCar.getSpeedIncreaseStep() + " " + ":: " +

                        mySecondCar.getCarName() + " *** : " + mySecondCar.getCurrentSpeed() +

                        "&ensp;&ensp;&ensp;&ensp;");
                    print();

                }

            }


            //Keep running both the cars

            myFirstCar.run(2, -1);

            mySecondCar.run(2, -1);

            //Pass the seconds between two sppeeds

            await new Promise(resolve => {
                setTimeout(() => {
                    resolve();
                },1000);
            });

            i++;

        }

        print();

        print();


        if (myFirstCar.getCurrentSpeed() == mySecondCar.getCurrentSpeed()) {

            print("&ensp;&ensp;&ensp;&ensp;" + "There is no winner for this Race ");
            print();

        } else if (myFirstCar.getCurrentSpeed() > mySecondCar.getCurrentSpeed()) {

            if (this.theWinner != undefined) {

                this.theWinner(myFirstCar.getCarName());
            }

            print("&ensp;&ensp;&ensp;&ensp;" + "The winner of this Race is :  " + myFirstCar.getCarName());
            print();

        } else {

            if (this.theWinner != undefined) {

                this.theWinner(mySecondCar.getCarName());
            }

            print("&ensp;&ensp;&ensp;&ensp;" + "The winner of this Race is :  " + mySecondCar.getCarName());
            print();

        }


        print();

        print();


        //Method that generates a random amount of gas to be used for acceleration

        // (between minx10 and maxx10)

        function createInjection(min, max) {

            return Math.round(Math.random() * 10 * (Math.random() > 0.5 ? max : min));

        }

        function Driver() {

            //Method used by the Driver to accelerate the car
            this.punchOnAccelorPedal = function (myCar, accel) {

                myCar.accelerate(accel);
            }
        }

    }

}