
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	//attribute
	private int track[] = new int[100];
	private ArrayList<Boat> player = new ArrayList<Boat>();
	private Boat winner;
	Scanner keyboard = new Scanner(System.in);
	
	//constructor
	public Game() {
		System.out.println("===== Welcome To BR Boat Racing In Java =====");
		System.out.println("=========== Instruction ===========");
		System.out.println("1) There will be only 2 players in this game");
		System.out.println("2) Player are required to roll the dice to determine how many step they are able to move");
		System.out.println("3) Player who reach to the 100 first is the winner");
		
		System.out.println("\nAre You Ready?");
		
		//Maximum of 2 players in the game
		for (int i = 0; i < 2; i++) {
			String player_input;
			boolean valid_input = false;

			while (!valid_input) {
				System.out.println("\nPlayer " + (i+1) + " ready to start?");
				System.out.println("Enter S to Start or Enter L to show Leaderboard");
				player_input = keyboard.next
						().toUpperCase();
				if (player_input.equals("S")) { 
					BoatSelection();
					valid_input = true;	
				}else if (player_input.equals("L")) { 
					LeaderBoard LB = new LeaderBoard();
					System.out.println(LB);
					valid_input = false;
				}else if(!player_input.matches("^[a-zA-Z ]*$")){
					System.out.println("Sorry! Only Alphabet Are Allowed!");
					valid_input = false;
				}else {
					System.out.println("ERROR 404! Invalid Input!");
					valid_input = false;
				}
			}// end while loop
		}// end for loop
		
		randomizeTrack();
		
		Boat.setPlayer_num(0);
		Boat.resetPlayerNmList(); //used to reset the player's name list
	}
	
	//setter getter
	public int getTrack(int index) {
		return track[index];
	}
	public void setTrack(int value, int index) {
		this.track[index] = value;
	}
	public Boat getPlayer(int index) {
		return player.get(index);
	}
	public void setPlayer(Boat player, int index) {
		this.player.set(index, player);
	}
	public Boat getWinner() {
		return winner;
	}
	public void setWinner(int index) {
		this.winner = getPlayer(index);
	}
	
	//toString
	public String toString() {
		String printOut = player.get(findGuide()).getName() + " is guiding the race!\n";
		
		for (int i = 0; i < player.size(); i++) { 
			printOut += player.get(i).getName() + "'s current position: " + (player.get(i).getPos()+1) + "\n";
		}
		return printOut;
	}
	
	//other methods
	public void randomizeTrack() {
		for (int i = 1; i < 99; i ++) { //to randomize number up to 99 as 100 is the finish line
			Random r = new Random();
			int game = r.nextInt(100);
	    
			if (game < 58) {     
				setTrack(0, i);
			}
			else if (game < 63) { 
				setTrack(-1, i);
			}
			else if (game < 68) { 
				setTrack(1, i);
			}
			else if (game < 72) { 
				setTrack(-2, i);
			}
			else if (game < 76) { 
				setTrack(2, i);
			}
			else if (game < 80) { 
				setTrack(-3, i);
			}
			else if (game < 84) {
				setTrack(3, i);
			} 
			else if (game < 87) { 
				setTrack(-4, i);
			}
			else if (game < 90) { 
				setTrack(4, i);
			}
			else if (game < 93) { 
				setTrack(-5, i);
			}
			else if (game < 95) {
				setTrack(5, i);
			}
			else if (game < 98) { 
				setTrack(-6, i);
			}
			else {                
				setTrack(6, i);
			}
		}
	}// end randomizeTrack()
	
	public int findGuide() { //return index of guiding player
		int GuidePosition = -1;
		int GuideIndex = 0;
		
		for (int i = 0; i < player.size(); i ++) {
			if (getPlayer(i).getPos() > GuidePosition ) {
				GuidePosition = player.get(i).getPos();
				GuideIndex = i;
			}
		}
		
		return GuideIndex;
	}
	
	private void crash(int index) { //movement of players
		for (int i = 0; i < player.size(); i ++) { 
			if (i != index && player.get(index).getPos() == player.get(i).getPos()) {
				player.get(i).setPos(player.get(i).getPos() + 1);
				System.out.println(player.get(i).getName() + "'s boat had faced a small collision");
				crash(i);
			}
		}
	}
	
	//show track
	private void printGameTrack(Boat b) {
		int center = b.getPos();
		int start = center - 10;
		int end = center + 10;
		
		if (end > 99) {
			end = 99;
		}
		if (start < 0) {
			start = 0;
		}
		
		String riverpath = "|"; boolean occupied = false;
		
		for (int k = start; k <= end; k++) {
			occupied = false;
			for (int a = 0; a < player.size(); a++) {
				if (player.get(a).getPos() == k) {
					riverpath = riverpath + (a+1);
					occupied = true;
				}
			}
			
			if (!occupied) {
				switch ((int)Math.signum(track[k])) {
					case 1: 
						riverpath += "C";
						break;
					case 0: 
						riverpath += " ";
						break;
					case -1: 
						riverpath += "T";
						break;
				}
			}
			riverpath = riverpath + "|";
		}
		
		System.out.println("\n" + riverpath);
	}
	
	public void BoatSelection() {
		String choice;
		Boolean valid_choice = false;
		
		
		while (!valid_choice) {
			System.out.println("\nThere Are Two Type Of Boat Avalaible!");
			System.out.println("S - Slow Boat \nSD - Speed Boat (Advanatege: Less Damage)");
			System.out.println("Please Select The Boat Of Your Choice");
			choice = keyboard.next().toUpperCase();
			if (choice.equals("S")) {
				player.add(new Boat());
				valid_choice = true;
				
			}else if  (choice.equals("SD")){
				player.add(new Speed_Boat());
				valid_choice = true;
				
			}else {
				System.out.println("ERROR 404 Invalid Input");
				valid_choice = false;
			}
		}
	}
	
	public void startRace() {
		int a = 0;
		
		while(player.get(a).getPos() < 99) {
			player.get(a).setTurn(player.get(a).getTurn() + 1);
			printGameTrack(player.get(a));
			System.out.println(player.get(a).getName() + " it's your turn!" );
			System.out.println("Press ENTER to roll the dice!\n");
			keyboard.nextLine();
			
			//movement based on dice
			player.get(a).move(0, true);
			crash(a);
			
			//move if there is traps / current
			for(int k = 0; k < 3; k ++) {
				player.get(a).move(track[player.get(a).getPos()], false);
				crash(a);
			}
			if(player.get(a).getPos() < 99) {
				if (a == player.size()-1) {
					System.out.println("\n\n=====End of Round " + player.get(a).getTurn() + "=====");
					System.out.println(toString());
					a = 0;
				}
				else {
					a++;
				}// end else
				
			}// end if
			
		}// end while loop
		
		setWinner(a);
	}// end startRace()
}