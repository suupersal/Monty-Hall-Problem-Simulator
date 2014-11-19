import java.util.Random;

//Author: Salvador Gutierrez
public class MontyHall {
	static Random generator = new Random();

	public static void main(String[] args) {
		boolean roundWon = false;
		int wins = 0;
		int losses = 0;
		int rounds = 100000;
		
		//Run (rounds) amount of rounds
		for (int i = 0; i < rounds; i++) {
			roundWon = runRound();
			if (roundWon)
				wins++;
			else 
				losses++;
		}

		System.out.println("Number of Rounds Ran: " + (wins + losses));
		System.out.println("Number of Wins: " + wins);
		System.out.println("Number of Losses: " + losses);
		System.out.println("Win Ratio: "+ ((double) wins / (double) (wins + losses)));
	}

	public static int rand(int min, int max) {
		return generator.nextInt(max - min + 1) + min;
	}

	public static boolean runRound() {
		//Wining door is randomly chosen
		int doorWithCar = rand(1,3);
		
		//the initial current door is randomly chosen
		//simulates player randomly choosing one of 3 doors.
		int currentDoor = rand(1, 3);
		
		//The HOST has to now reveal one of the non chosen doors.
        int revealedDoor=doorToReveal(doorWithCar, currentDoor);
		
        //Player will ALWAYS swap for newly revealed door.
        currentDoor=swap(currentDoor, revealedDoor);
		
		return (doorWithCar == currentDoor);

	}

	public static int doorToReveal(int doorWithCar, int currentDoor) {
		int result;
		// player chose door with car, so 
		//host reveals any other door.
		if (doorWithCar == currentDoor) {
			int rand = rand(0, 1);

			// reveal leftmost non-chosen door
			if (rand == 0) {
				if (currentDoor == 1) 
					result = 2;
				 else 
					result = 1;
			}
			
			// reveal right most non-chosen door
			else {
				if (currentDoor == 3) 
					result = 2;
			    else 
					result = 3;
			}
		}
		// player chose a sheep, return door with second sheep
		else 
			result = 6 - currentDoor - doorWithCar;

		return result;
	}

	public static int swap(int currentDoor, int revealedDoor) {
		return 6 - currentDoor - revealedDoor;

	}

}
