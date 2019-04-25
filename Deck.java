import java.util.*;

public class Deck{
	ArrayList<Card> aDeck = new ArrayList<Card>();
	String[] suit = {"Heart", "Diamond", "Spade", "Club"};
	String[] value = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	public Deck(){
		for (int i = 0; i < suit.length; i++){
			for (int j = 0; j < value.length; j++){
				this.aDeck.add(new Card(suit[i], value[j]));
			}
		}
	}
			
	public ArrayList<Card> getDeck(){
		return aDeck;
	}
	
	public int getRandom(){
		int index = new Random().nextInt(aDeck.size());	
		return index;
	}
	
	public int getSize(){
		return aDeck.size();
	}	
	
	public void deckReset(){
		for (int i = 0; i < suit.length; i++){
			for (int j = 0; j < value.length; j++){
				this.aDeck.add(new Card(suit[i], value[j]));
			}
		}			
	}
	
	public static void main(String[] args){
		Deck myDeck = new Deck();
		System.out.println(myDeck.getDeck());
	}
}



