public class Card{
	private String suit;
	private String value;
	
	public Card(String suit, String value){
		this.suit = suit;
		this.value = value;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public String getValue(){
		return value;
	}
	
	public int getRealValue(){
		if (value == "Ace"){
			return 1;
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
	
	public String toString(){
		return  value + " of " + suit;
	}
	
}



