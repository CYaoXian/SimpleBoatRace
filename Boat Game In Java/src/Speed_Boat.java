import java.util.Random;

public class Speed_Boat extends Boat{
	@Override
	public void move(int movement, boolean dice) { ///movement of player is determine by the dice
		if (dice) {
			Random r= new Random();
			movement = r.nextInt(6) + 1; 
		}
		else {
			if (movement < -1) { movement ++; } // benefit: moves 1 less step backwards when hits a trap
		}
		
		//the position of player are being set
		setPos(getPos() + movement);
		
		if (getPos() < 0) {
			setPos(0); //movement cannot be lesser than 0
		}
		if (getPos() > 99) {
			setPos(99); //movement cannot be greater than 100 as finish line is at the 100th position.
		}
		
		//show the results gained from the dice and the player's location
		if (dice) {
			System.out.printf("%s rolled a %d!and move to position %d\n", getName(), movement, (getPos()+1));
		}
		else {
			switch ((int)Math.signum(movement)) {
				case 1:
					System.out.printf("%s's boat moves %d steps forward as it pass by the current and gained extra power to move forward to %d ",getName(), movement, (getPos()+1));
					break;
				case -1:
					System.out.printf("%s's boat moves %d steps backward as it pass by the trap and move backward to %d ",getName(), movement, (getPos()+1));
					break;
				case 0:
					//Empty as it does nothing
					break;
				default:
					System.out.println("ERROR 404!!! Invalid Switch Statement!");
			}// end switch statement
		}// end else statement
	}// end movement
}
