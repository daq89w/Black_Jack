import java.util.*;

public class Hand{
	ArrayList<Card> playerHand = new ArrayList<Card>();
	
	public Hand(){
		this.playerHand.add(new Card("Heart", "Ace"));
	}
	
	public ArrayList<Card> getPlayerHand(){
		return playerHand;
	}

}