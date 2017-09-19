package src.Basic;

public class Formula1 extends ISCar {

	public Formula1(String name, int min, int max, int factor) {
		super(name, min, max, factor); 
	}
	
	public Formula1(String name, int x, int min, int max, int factor) {
		super(name, x, min, max, factor);
	}

		//Method that generates a random number between minx10 and maxx10
	 int AutomaticCreationRandomNumber(int min, int max, int factor){
		int myRandomInt = (int)(Math.random() * factor * (Math.random() > 1 ? min : max));
		return myRandomInt;
		}			
}
