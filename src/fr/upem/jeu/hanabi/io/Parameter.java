package fr.upem.jeu.hanabi.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * @author Axel Durand
 * This class is a Singleton which contain the parameter which are launched in a game.
 * @version 1.1
 * 
 * */
public final class Parameter {
	/**
	 * Field which contain the maximum number of player you can have in a game.
	 * */
	private final int maxPlayer;
	/**
	 * Field which contain the maximum number of card a player can have in a game.
	 * */
	private final int numberOfCardByPlayer;
	/**
	 * Represent the different colors which are in the game.
	 * */
	private final String[] colorFamily;
	/**
	 * Number of time a value can appear in a Deck for each color family
	 * */
	private final int[]  numberOfValueAvailableByFamily;
	private static volatile Parameter singleton=null;
	/**
	 * getter of @see maxPlayer
	 * @return 
	 * maxPlayer
	 * */ 
	public int getMaxPlayer() {
		return this.maxPlayer;
	}
	/**
	 * getter of @see numberOfCardByPlayer
	 * @return 
	 * numberOfCardByPlayer
	 * */
	 public int getNumberOfCardByPlayer() {
		return this.numberOfCardByPlayer;
	}
	 /**
	 * getter of @see colorWithIt
	 * @return 
	 * colorFamily
	 **/
	 public String[] getColorFamily() {
		return this.colorFamily;
	}
	 /**
	  * getter of @see numberOfValueAvailableByFamily;
	  * @return 
	  * numberOfValueAvailableByFamily
	  * */
	 public int[] getNumberOfValueAvailableByFamily() {
		return this.numberOfValueAvailableByFamily;
	}
	private Parameter(int maxPlayer,int numberOfCardPlayer,String[] color,int[] numberOfValue) {
		if(maxPlayer<=1) {
			   throw new IllegalArgumentException("There must be at least 2 players");
					   }
		if(numberOfCardPlayer<=1) {
			   throw new IllegalArgumentException("A player must have at least 2 cards at maximum in his hands.");
		}
		this.maxPlayer=Objects.requireNonNull(maxPlayer);
		this.numberOfCardByPlayer=Objects.requireNonNull(numberOfCardPlayer);
		this.colorFamily= this.upperCase(color);
		this.numberOfValueAvailableByFamily=Objects.requireNonNull(numberOfValue);
	}
	/**
	 * @return the current instance of Parameter object.
	 * */
	public static Parameter getInstance() {
		return singleton;
	}
	/**
	 * Method factory for parameter
	 * Ask the user to select the different parameter of the game then record the file in the directory parameter under the name
	 * parameter.txt
	 * @throws IOException 
	 * When files got problems
	 * @return parameter
	 * */
	public static Parameter modifyParameterByGame() throws IOException {
		if(Parameter.singleton!=null)return Parameter.singleton;
		int maxPlayer;
	    int numberOfCardByPlayer;
	    String[] colorWithIt;
	    int[]  numberOfValueAvailableByFamily;
		Parameter param ;
		Pattern p = Pattern.compile("-");
		Scanner scan=new Scanner(System.in);
		System.out.println("What is the maximum number of player ?");
		maxPlayer=scan.nextInt();
		
		System.out.println("How many card can a player have ?");
		System.out.println("If the number of player is greater than (number of player /2) then you will have maxCard-1 card in your hand");
		numberOfCardByPlayer=scan.nextInt();
		
		System.out.println("What are the different color in the game ?");
		System.out.println("Format Color1Name-Color2Name-...-ColorNName. Beware dont write colors with the same firstLetter");
		String res=scan.nextLine();
		while(!Dialogue.isColorFormatCorrect(res)) {
			res=scan.nextLine();	
		}
		colorWithIt= p.split(res);
		 
		System.out.println("How many card value you can find in one deck ?");
		System.out.println("Format : 1=3-2=2-3=2-4=2-5=1");
		System.out.println("In this example we can have 3 card of value one of each color, 2 card of value 2 ...");
		res=scan.nextLine();
		while(!Dialogue.isFamilyNumberFormatCorrect(res)) {
			res=scan.nextLine();	
		}
		String[] numberCard=p.split(res);
        numberOfValueAvailableByFamily=new int[numberCard.length];
        for(int i=0;i<numberCard.length;i++) {
        	 int left=Integer.parseInt(""+numberCard[i].charAt(0))-1;
       	  int right=Integer.parseInt(""+numberCard[i].charAt(2));
            numberOfValueAvailableByFamily[left]=right;
        }
		 param=new Parameter(maxPlayer, numberOfCardByPlayer, colorWithIt, numberOfValueAvailableByFamily);
         //scan.close();
         if(!param.isParameterFileCorrect()) {
        	System.out.println("Data that you have entered cannot create a good game. Loading of default data");
        	param=Parameter.loadDefaultParameter();
         }
         else {
        	 try {
        	   FileOutputStream fis=new FileOutputStream("src/parameters/parameter.txt");
  	    	   OutputStreamWriter isr=new OutputStreamWriter(fis);
  	           BufferedWriter br = new BufferedWriter(isr);
  	           String newLine=System.lineSeparator();
  	           br.write("Max Player = "+maxPlayer);
  	           br.write(newLine);
  	           br.write("Card Number = "+numberOfCardByPlayer);
  	           br.write(newLine);
  	           br.write("Color are : ");
  	           for(int i=0;i<colorWithIt.length;i++) {
  	        	 br.write(colorWithIt[i]);
  	        	 if(i!=colorWithIt.length-1)br.write("-");
  	           }
  	           br.write(newLine);
  	           br.write("NumberCardByColor : ");
  	         for(int i=0;i<numberOfValueAvailableByFamily.length;i++) {
  	        	 br.write((i+1)+"="+numberOfValueAvailableByFamily[i]);
  	        	 if(i!=numberOfValueAvailableByFamily.length-1)br.write("-");
  	         }
  	        	 br.write(newLine);
  	        	 br.close();
  	        	 isr.close();
  	        	 fis.close();
  	        	 
  	           
        	 }catch(IOException e) {
        		throw new IOException("Problem at the reading or the closing"); 
        	 }
        	 
         }
         Parameter.singleton=param;
		return param; 
	}
	/**
	 * Load the file at /paramaters/parameter.txt
	 *  @return parameter or a default parameter if problems
	 *  @throws IOException when problem with the files
	 * */
	public static Parameter readFileParameter() throws IOException {
		if(Parameter.singleton!=null)return Parameter.singleton;
		Parameter param;
	      String reader;
	       int maxPlayer;
	        int numberOfCardByPlayer;
	       String[] colorWithIt;
	       int[]  numberOfValueAvailableByFamily;
	       File f=new File("src/parameters/parameter.txt");
    	   System.out.println(f.getCanonicalPath());
    	   System.out.println(f.canRead());
	       try {
	    	   FileInputStream fis=new FileInputStream("src/parameters/parameter.txt");
	    	  
	    	   InputStreamReader isr=new InputStreamReader(fis);
	           BufferedReader br = new BufferedReader(isr);
	    	   reader=br.readLine();
	    	   maxPlayer=Integer.parseInt(reader.substring(13));
	    	   reader=br.readLine();
	           numberOfCardByPlayer=Integer.parseInt(reader.substring(14));
	           reader=br.readLine();
	           reader=reader.substring(12);
	           Pattern p = Pattern.compile("-");
	           colorWithIt= p.split(reader);
	           reader=br.readLine();
	           reader=reader.substring(20);
	           String[] numberCard=p.split(reader);
	           numberOfValueAvailableByFamily=new int[numberCard.length];
	           for(int i=0;i<numberCard.length;i++) {
	        	  int left=Integer.parseInt(""+numberCard[i].charAt(0))-1;
	        	  int right=Integer.parseInt(""+numberCard[i].charAt(2));
	             numberOfValueAvailableByFamily[left]=right;
	           }
	           param=new Parameter(maxPlayer, numberOfCardByPlayer, colorWithIt, numberOfValueAvailableByFamily);
	           br.close();
	           isr.close();
	           fis.close();

	       }catch(IOException e) {
	         System.out.println("Erreur de lecture du fichier dans Parametre");
	         param=Parameter.loadDefaultParameter();
	       }

	    Parameter.singleton=param;
	    return  param;		
	}
	private String[] upperCase(String[] words) {
	      ArrayList<String> uppercases = new ArrayList<String>();
	      for(String s :  words) {
				 uppercases.add(s.toUpperCase());
			 }
	      return uppercases.toArray(words);
	}
	/**
	 * Load the default parameter of a play of Hanabi.
	 * @return parameter
	 * */
	public static Parameter loadDefaultParameter() {
		if(Parameter.singleton!=null)return Parameter.singleton;
		Parameter param;
		  int maxPlayer=5;
	      int numberOfCardByPlayer=5;
	      String[] colorWithIt=new String[5];
	      colorWithIt[0]="BLUE";
	      colorWithIt[1]="GREEN";
	      colorWithIt[2]="RED";
	      colorWithIt[3]="YELLOW";
	      colorWithIt[4]="WHITE";
	      int[]  numberOfValueAvailableByFamily=new int[5];
	      numberOfValueAvailableByFamily[0]=3;
	      numberOfValueAvailableByFamily[1]=2;
	      numberOfValueAvailableByFamily[2]=2;
	      numberOfValueAvailableByFamily[3]=2;
	      numberOfValueAvailableByFamily[4]=1;
	      param=new Parameter(maxPlayer, numberOfCardByPlayer, colorWithIt, numberOfValueAvailableByFamily);
	      Parameter.singleton=param;
	      return param;
	}
	
	private int countCardInDeck() {
		int totalCardInDeck=0;
		for(int i=0;i<this.numberOfValueAvailableByFamily.length;i++) {
			totalCardInDeck+=this.numberOfValueAvailableByFamily[i]*this.colorFamily.length;
		}
		return totalCardInDeck;
	}
	private boolean isParameterFileCorrect() {
		int totalCardInDeck=this.countCardInDeck();
		if(totalCardInDeck-this.maxPlayer*(this.numberOfCardByPlayer-1)<this.numberOfCardByPlayer-1) {
			return false;
		}
		return true;
	}
	/*
	//Main de test
	public static void main(String[] args) {
		Parameter parameter=Parameter.modifyParameterByGame();
		for(String s : parameter.colorWithIt)
		{
			System.out.println(s);
		}
		System.out.println("Max player"+parameter.maxPlayer);
		System.out.println("Card "+parameter.numberOfCardByPlayer);
		  for(int i=0;i<parameter.numberOfValueAvailableByFamily.length;i++) {
       	   System.out.println(i+" = "+ parameter.numberOfValueAvailableByFamily[i]);
          }
		
		
	}
	*/
}
