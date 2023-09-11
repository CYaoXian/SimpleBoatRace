
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Boat {
	static private int player_num = 0;
	static private ArrayList<String> playerNmList = new ArrayList<String>();
	
	private String name; 
	private int pos; //it represent the current position from 0-99 
	private int turn; //current turn number
	Scanner keyboard = new Scanner(System.in);
	
	///constructor
	public Boat() {
		player_num = player_num + 1;
		setPlayerName();
		turn = 0;
		pos = 0;
		playerNmList.add(name);
	}
	
	///setter getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public String toString() {
		return name + "'s current position: " + pos;
	}
	
	//static variables
	static public int getPlayer_num() {
		return player_num;
	}
	static public void setPlayer_num(int p_num) {
		player_num = p_num;
	}
	static public String getPlayerNmList(int index) {
		return playerNmList.get(index);
	}
	static public void resetPlayerNmList() {
		playerNmList.clear();
	}
	
	public void move(int movement, boolean dice) { ///movement of player is determine by the dice
		if (dice) {
			Random random= new Random();
			 movement = random.nextInt(6) + 1;
		}
		
		//the position of player are being set
		pos = pos + movement;
		
		if (pos < 0) {
			pos = 0; //movement cannot be lesser than 0
		}
		if (pos > 99) {
			pos = 99; //movement cannot be greater than 100 as finish line is at the 100th position.
		}
		
		//show the results gained from the dice and the player's location
		if (dice) {
			System.out.printf("%s rolled a %d!and move to position %d\n",name, movement, (pos+1));

		}
		else {
			switch ((int)Math.signum( movement)) { 
				case 1:
					System.out.printf("%s's boat moves %d steps forward as it pass by the current and gained extra power to move forward to %d ",name, movement, (pos+1));
					break;
				case -1:
					System.out.printf("%s's boat moves %d steps backward as it pass by the trap and move backward to %d ",name, movement, (pos+1));
					break;
				case 0:
					//Empty as it does nothing
					break;
				default:
					System.out.println("ERROR 404!!! Invalid Switch Statement!");	
			}// end switch statement
		}// end else statement
	}// end move
	
	private void setPlayerName() {
		boolean valid_name = false;
		String playername;
		
		while (!valid_name) {
			System.out.printf("\nPlease Enter Player %d Name (at least 2 character and not more than 15): \n", player_num);
			playername = keyboard.nextLine(); 
			valid_name = true;
			
			//check validity for player name
			if (playername.length() < 2 || playername.length() > 15) {
				System.out.println("Player Name must have at least 2 to 15 character!");
				valid_name = false;
			}
			if (!playername.matches("^[a-zA-Z ]*$")){
				System.out.println("Sorry Only Alphabet Are Allowed!");
				valid_name = false;
			}
			name = playername;
		}// end while loop
		
	}// end setPlayerName()
}
