import java.util.*;

public class Player{
	ArrayList<Card> playerScore = new ArrayList<Card>();
	private int total;
	
	public void Total(){
		int total = 0;
		for (int i = 0; i < playerScore.size(); i++){
			total += playerScore.get(i).getRealValue();
		}
		this.total = total;
	}	
	
	public ArrayList<Card> getPlayerScore(){
		return playerScore;
	}
	
	public int getScore(){
		return total;
	}
	
	public static void main(String[] args){
	
	}
}