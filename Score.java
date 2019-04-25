import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Score{ 
	
	public static void main(String args[]){
		try{
			FileWriter writer = new FileWriter("Scoreboard.txt");
			BufferedWriter bwriter = new BufferedWriter(writer);
			bwriter.write("81");
			bwriter.newLine();
			bwriter.write("82");
			bwriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
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
	
	
	
	

}