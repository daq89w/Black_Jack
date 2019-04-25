import java.util.*;
import java.lang.*;
import java.io.*;

class Mind{
	private int total;
	private boolean hit;

	public int getTotal(){
		return total;
	}
	
	public boolean getHit(){
		return hit;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	public void setHit(boolean hit){
		this.hit = hit;
	}
}

public class Black_Jack //this is the main class
{
	static void intro(){
		System.out.println("Dealer: Welcome to my Black Jack game...");
		try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
		System.out.println("Dealer: ...if you are ready...");
		try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
		System.out.println("Dealer: ...let's begin...");	
	}
	
	static int printMenu(){
		int validInput = 0;
		int userInput = 0;
		while ((userInput < 1) || (userInput > 5)){
			System.out.println("Dealer : Make your selection!");
			System.out.println("1. Start game");
			System.out.println("2. Load game");
			System.out.println("3. Exist game");
			System.out.println("4. Check high-scores");
			System.out.println("5. Clear high-scores");	
			userInput = userInput();
			if ((userInput < 1) || (userInput > 5)){
				System.out.println("Please select a valid option!");
			}else{
				validInput = userInput;
			}
		}
		return validInput;
	}
	
	static int setAceSelect(){
		boolean validInput = false;
		int aceValue = 1;
		while(!validInput){
			System.out.println("You got an Ace! What value will you assign to it?");
			System.out.println("1. One");
			System.out.println("2. Eleven");
			int userInput = userInput();
			System.out.println("You've selected " + userInput);
			if (userInput == 1){
				aceValue = 1;
				validInput = true;
			}else if(userInput == 2){
				aceValue = 11;
				validInput = true;
			}else{
				System.out.println("You must enter a valid selection!");
				validInput = false;
			}
		}
		return aceValue;
	}
	
	static void compareScore(int dealertotal, int playertotal){		
		if ((dealertotal == playertotal) || ((dealertotal > 21) && (playertotal > 21)) || ((dealertotal == 21)&&(playertotal == 21))){
			System.out.println("A tie!");
		}
		else if (((playertotal < dealertotal)&&(dealertotal <= 21)) || ((playertotal > 21)&&(dealertotal <= 21)) || ((playertotal != 21)&&(dealertotal == 21))){
			System.out.println("You lost!");
		}
		else if ((playertotal > dealertotal) || ((playertotal == 21)&&(dealertotal != 21)) || ((playertotal <= 21) && (dealertotal > 21))){
			System.out.println("You win!");
		}
		System.out.println("This game ended!");		
	}
	
	static void getPlayerHand(Player playerScore){
		System.out.println("Player's hand:");
		System.out.println(playerScore.getPlayerScore());
	}
	
	static void getDealerHand(Dealer dealerScore){
		System.out.println("Dealer's hand:");
		System.out.println(dealerScore.getDealerScore());
	}
	
	static int getPlayerTotal(Player playerScore){
		int total = 0;
		for (int i = 0; i < playerScore.getPlayerScore().size(); i++){
			String value;
			value = playerScore.getPlayerScore().get(i).getValue(); //returning "String" from class Card
			total += getCardValue(value);	//method returning int value 
		}
		return total;
	}		
	
	static Mind getDealerMind(Dealer dealerScore){
		Mind mind = new Mind();
		int total = 0;
		boolean hit = true;
		for (int i = 0; i < dealerScore.getDealerScore().size(); i++){
			String value;
			value = dealerScore.getDealerScore().get(i).getValue();
			if (value == "Ace"){
				if (total <= 10){ //hand is less than or equal to 10, make ace 11, making total of 21! Cannot take another card
					total += 11;
					hit = false;
				}else if((total > 16) && (total < 21)){ //hand is less than 20 and greater than 16, making ace 1, making total above or equal to 17, cannot take another card 
					total += 1;
					hit = false;
				}else{	//hand is less than 16 and greater than 10, making ace 1, take another card
					total += 1;
					hit = true;
				}
			}else{
				total += getCardValue(value);
				if (total >= 16){
					hit = false;
				}else{
					hit = true;
				}
			}
		}
		mind.setTotal(total);
		mind.setHit(hit);
		
		return mind;
	}
	
	static int printAnotherCardMenu(){
		int userInput = 0;
		int validInput = 0;
		while((userInput < 1) || (userInput > 2)){
			System.out.println("Do you want another card?");
			System.out.println("1. YES");
			System.out.println("2. NO");	
			userInput = userInput();
			if ((userInput < 1) || (userInput > 2)){
				System.out.println("Please select a valid option!");
			}else{
				validInput = userInput;
			}
		}
		return validInput;
	}
	
	static void printPlayerPass(){
		System.out.println("Player passed...");
	}
	
	static void printDealerPass(){
		System.out.println("Dealer passed...");
	}
	
	static int userInput(){
		boolean GoodInput = false;
		int input = 0;
		while (!GoodInput){
			Scanner myInput = new Scanner(System.in);
			GoodInput = myInput.hasNextInt();
			System.out.println(GoodInput);
			if (!GoodInput){
				System.out.println("Please enter a valid selection!");
			}else{
				input = myInput.nextInt();
			}
		}
		return input;
	}
	
	static void printPlayerGetCard(){
		System.out.println("Dealer hands you a card from the deck...");
	}
	
	static void printDealerGetCard(){
		System.out.println("Dealer picks up a card from the deck...");
	}
	
	static void HighScoreWrite(String highscore){
		try{
			FileWriter writer = new FileWriter("Scoreboard.txt", true);
			BufferedWriter bwriter = new BufferedWriter(writer);
			bwriter.write(highscore);
			bwriter.newLine();
			bwriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	static void getHighScore(){
		System.out.println("Player highscores:");
		try{
			FileReader reader = new FileReader("Scoreboard.txt");
			BufferedReader breader = new BufferedReader(reader);
			String value;
			while((value = breader.readLine()) != null){
				int ivalue;
				ivalue = Integer.parseInt(value);
				System.out.println(ivalue);
			}
			breader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
		
	
	static void clearHighScore(){
		try{
			FileWriter writer = new FileWriter("Scoreboard.txt");
			writer.write("");
			writer.close();
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	static int getCardValue(String value){
		if (value == "Ace"){
			return setAceSelect();
		}else if(value == "2"){
			return 2;
		}else if(value == "3"){
			return 3;
		}else if(value == "4"){
			return 4;
		}else if(value == "5"){
			return 5;
		}else if(value == "6"){
			return 6;
		}else if(value == "7"){
			return 7;
		}else if(value == "8"){
			return 8;
		}else if(value == "9"){
			return 9;
		}else if(value == "10"){
			return 10;
		}else if(value == "Jack"){
			return 11;
		}else if(value == "Queen"){
			return 10;
		}else if(value == "King"){
			return 10;
		}
		else
		{
			return 0;
		}		
	}
	

	public static void main(String[] args)
	{	
 		boolean menu = true;
		
		intro();
		
		while(menu){ //game screen
			boolean game = false;
			
			int input = printMenu();

			game = (input == 1)? true:false;
			menu = (input != 3)? true:false;
			
			if (input == 5){
				clearHighScore();
			}		

			if (input == 4){
				getHighScore();
			}
			
			while(game){
				int index = 0;
				Card card;
				Deck newDeck = new Deck();
				Mind mind = new Mind();
				Player playerScore = new Player();//keep track of individual cards
				Dealer dealerScore = new Dealer();//keep track of dealer cards
				boolean hit_player = true;
				boolean hit_dealer = true;
				
				Collections.shuffle(newDeck.getDeck()); //shuffling deck
				for (int i = 0; i < 2; i++){ //you pick 2 cards
					printPlayerGetCard();
					index = newDeck.getRandom();
					card = newDeck.getDeck().get(index);
					newDeck.getDeck().remove(index);
					playerScore.getPlayerScore().add(card); //store cards in players hands
				}
				
				for (int i = 0; i < 2; i++){ //dealer pick 2 cards
					printDealerGetCard();
					index = newDeck.getRandom();
					card = newDeck.getDeck().get(index);
					newDeck.getDeck().remove(index);
					dealerScore.getDealerScore().add(card); //store cards in dealers hands		
				}	
				
				System.out.println("Dealer's hand has: " + dealerScore.getDealerScore().get(0) + " and a ?");

				System.out.println("You have: ");
				System.out.println(playerScore.getPlayerScore());
			
				while(hit_player || hit_dealer){				
					
					if (hit_player){
						int player_input = printAnotherCardMenu();
						hit_player = (player_input == 1)? true:false;
					
						if(hit_player){
							index = newDeck.getRandom();
							card = newDeck.getDeck().get(index);
							newDeck.getDeck().remove(index);
							playerScore.getPlayerScore().add(card);
							printPlayerGetCard();
							System.out.println("You received a " + card);
						}
						else{
							printPlayerPass();
						}
					}
					else{
						printPlayerPass();
					}
					
					//dealerScore.Total();
					mind = getDealerMind(dealerScore);
					hit_dealer = mind.getHit();	
					if (hit_dealer){
						printDealerGetCard();
						index = newDeck.getRandom();
						card = newDeck.getDeck().get(index);
						newDeck.getDeck().remove(index);
						dealerScore.getDealerScore().add(card);
					}
					else{
						printDealerPass();
					}
				}
				mind = getDealerMind(dealerScore);

				getDealerHand(dealerScore);
				getPlayerHand(playerScore);
				int playerTotal = getPlayerTotal(playerScore);
				int dealerTotal = mind.getTotal();	
				System.out.println("Player has " + playerTotal);
				System.out.println("Dealer has " + dealerTotal);
				getDealerHand(dealerScore);
				getPlayerHand(playerScore);				
				compareScore(dealerTotal, playerTotal);
				
				System.out.println("You want to play another game?");
				game = false;
			}
		}	
		System.out.println("Exiting game...");
		HighScoreWrite("82 "); //not doing anything at the moment
	}
}



