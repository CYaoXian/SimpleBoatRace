import java.util.Scanner;

public class Main_Menu {

	public static void main(String[] args) {
		  
		boolean loop = true;
		Scanner keyboard = new Scanner(System.in);
		String input; 
		Game river; 
		LeaderBoard LB = new LeaderBoard(); 
		
		while (loop) { 
			// start the race
			river = new Game();
			river.startRace();
			
			System.out.println("\nThe Winner is " + river.getWinner().getName() + "!");
			System.out.println("Score: " + (150 - river.getWinner().getTurn()) + "\n");
			LB.scoreWrite(river.getWinner().getName(), river.getWinner().getTurn());
			
			// show players leader board
			LB = new LeaderBoard();
			System.out.println(LB); 
			
			input = "v";
			while (input.equals("v")) {
				//ask whether player would like to play again
				System.out.println("Do you wish to start a new game?");
				System.out.println("Enter Y For Yes, Enter N For No");
				input = keyboard.nextLine().toUpperCase();
				if (input.equals("N")) {
					System.out.println("Good Game! Well Played! See You Soon!");
					loop = false;
				}else if (!input.equals("Y")) {
					System.out.println("ERROR 404 Invalid Input");
					input = "v";
				}// end else
				
			}// end while (input)
		}// end while (loop)
	    keyboard.close();
	}

}
