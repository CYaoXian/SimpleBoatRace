import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LeaderBoard {
	
	//attributes
	private String[] leaderNames = new String[5];
	private int[] leaderScores = new int[5];
	private String path;
	
	//constructor
	public LeaderBoard() { 
		path = "TopScore.txt";
		
		try {
		    File LBfile = new File(path);
		    if (LBfile.createNewFile()) {
		       System.out.println("No new High Scores were found!");
		       
		       //write to the new file
		       FileWriter leader_results = new FileWriter(path);
		       leader_results.write("|=====|0|=====|0|=====|0|=====|0|=====|0|");
		       leader_results.close();
		   }
		   
		    //read file contents
	       Scanner finput = new Scanner(LBfile);
		   String data = finput.nextLine();

		   int n = 0, n2 = 0;
		   for (int a = 0; a < leaderNames.length; a ++) {
			   n = data.indexOf("|", n); n2 = data.indexOf("|", n+1);
			   leaderNames[a] = data.substring(n+1 ,n2);
			   n = data.indexOf("|", n2+1);
			   leaderScores[a] = Integer.parseInt(data.substring(n2+1, n));
		   }
		   
		   finput.close();
		} 
		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	//setter//getter
	public String getLeaderNames(int index) {
		return this.leaderNames[index];
	}
	public void setLeaderNames(String leaderNames, int index) {
			this.leaderNames[index] = leaderNames;
	}
	
	@Override
	public String toString() {
		String scoreTable;
		
		scoreTable = " Name -  HighScores    " + "\n" 
					+ "=================================" + "\n";   
		for (int i = 0; i < leaderNames.length; i++) {
			scoreTable += leaderNames[i];
			
			scoreTable += " - " + leaderScores[i] + "\n";
		}
						  
		return scoreTable;
	}
		
	public int scoreCheck (int score) {
		for (int i = 0; i < leaderScores.length; i++) {
			if (score > leaderScores[i]) {
				return i + 1;
			}
		}
		return 6;
	}
		
	public void scoreWrite(String winner, int turn) {
		int score = 150 - turn;
		int p_ranking = scoreCheck(score);
		
		if (p_ranking < 6) {
			System.out.println("New High Score!");
			
			try {
			    FileWriter leader_results = new FileWriter(path); 
			    String  names_L = " ";
			    
			    for (int i = 0; i < leaderNames.length; i++) {
			    	if (p_ranking == i + 1) {
			    		 names_L += "|" + winner + "|" + score;
			    	} 
			    	if (p_ranking < i + 1) {
			    		 names_L += "|" + leaderNames[i-1] + "|" + leaderScores[i-1];
			    	}
			    	if (p_ranking > i + 1) {
			    		 names_L += "|" + leaderNames[i] + "|" + leaderScores[i];
			    	}
			    }
			    names_L += "|";
			    
			    leader_results.write(names_L);
			    leader_results.close();
			}
			catch(IOException ioe) {
			    System.err.println("IOException: " + ioe.getMessage());
			}
		}//end if statement
	}		
}