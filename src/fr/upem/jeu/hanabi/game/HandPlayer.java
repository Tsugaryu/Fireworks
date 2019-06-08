package fr.upem.jeu.hanabi.game;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.upem.jeu.hanabi.game.content.Card;
import fr.upem.jeu.hanabi.io.Dialogue;

/**
 * Class which represent the identity of a player and the content of his hand.
 * You can decide with this class which action will the player execute on his hand.
 * @author Axel DURAND
 * @version 1.0
 * @since 0.1
 */
public class HandPlayer {

 

    /**
     * Hand of the player.
     * @since 0.1
     */
    private ArrayList<Card> hand;

    /**
     * Id of a player.
     * @since 0.1.
     */
    private final int idPlayer;


    /**
     * Memory during one turn of a player.
     * @since 0.1.
     */
    private Dialogue memory;
    /**
     * Getter of @see memory
     * @since 1.0
     * @return a Dialogue
     * */
    public Dialogue getMemory(){
    	return this.memory;
    }
    /**
     * Setter of @see memory
     * @since 1.0
     * @param d dialogue to actualize
     * */
    public void setMemory(Dialogue d) {
    	this.memory=d;
    	
    }
    /**
     * Only discard from the hand of a player.
     * @param c Cards to discard 
     * @return  the card dropped
     */
    public Card discard(Card c) {
    	
        Card discarded=new Card(0,0);
        int i=0;
        for(Card card : this.hand) {
        	if(card.equals(c)) {
        		discarded=card;
        		this.hand.remove(i);
        	}
        	i++;
        }
        return discarded;
    }
    /**
     * 
     * @return card
     * @param place where the card is
     * */
    public Card discard(int place) {
        Card discarded;
        discarded= this.hand.remove(place-1);
        return discarded;
    }

    /**
     * Swap two cards in the hand of a player
     * @param c first card to swap
     * @param c1 second card to swap
     * 
     */
    public void swap(Card c, Card c1) {
        int i,i1,j;
        j=0;
        i=-1;
        i1=-1;
    	//search the index
        for(Card iterator :this.hand) {
        	if(c==iterator)i1=j;
        	if(c1==iterator)i=j;
        		j++;
        }
        //swap index
    	
        if(i!=-1)this.hand.set(i, c1);
    	this.hand.set(i1,c);
        return ;
    }
    /**
     * @see swap
     * @param c first card to swap
     * @param c1 second card to swap
     * */
    public void swap(int c,int c1) {
    	
    	Card card=this.selectCard(c);
    	Card card1=this.selectCard(c1);
    	this.hand.set(c1-1, card);
    	this.hand.set(c-1,card1);
    	return;
    }/**
     	Add a card in the hand of a player
     	@param card to add
    */
    public void addCard(Card card) {
    	this.hand.add(card);
    }
    /**
     * Called for print the card of a player when it is his turn.
     * Replace his cards with '?'
     * @return a number of '?' which equals the number of cards in his hand.
     * */
    public String showHidden() {
    	StringBuilder builder=new StringBuilder();
    	String newLine=System.lineSeparator();
    	builder.append("Hand of Player ");
    	builder.append(this.idPlayer);
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append(i+1);
    		builder.append(" ");
    	}
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append("? ");
    	}
    	builder.append(newLine);
    	return builder.toString();
    	
    }
    /**
     * Represent the hand of a player.
     * */
    @Override
    public String toString() {
    	StringBuilder builder=new StringBuilder();
    	String newLine=System.lineSeparator();
    	builder.append("Hand of Player ");
    	builder.append(this.idPlayer);
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append(i+1);
    		builder.append("  ");
    		
    	}
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append(this.hand.get(i));
    		builder.append(" ");
    	}
    	builder.append(newLine);
    	return builder.toString();
    	
    }

    /**
     * Select a card in the hand of the player
     * @param rank Place where is the card.
     * @return the card selected.
     */
     Card selectCard(int rank) {
        return this.hand.get(rank-1);
      
    }
     /**
      * Select a card in the hand of a player.
      * @param rank Place where is the card.
      * @return the card selected.
      */
      static Card selectCard(int rank,HandPlayer player) {
         return player.selectCard(rank);
       
     }

    /**
     * Getter of @see idPlayer.
     * @return the id of a player.
     */
     int getId() {
        return this.idPlayer;     
    }

    /**
     * Constructor of HandPlayer
     * @param id id of the player
     * @param hand cards which will be in the hand of a player
     * @throws IllegalArgumentException when the id of a player if inferior to 1or hand of a player is empty at the creation.
     */
    public HandPlayer(int id, ArrayList<Card> hand) {
       if(id<1) {
    	   throw new IllegalArgumentException("A player cannot have a negative id");
       }
       /*
       if(hand.size()==0) {
    	   throw new IllegalArgumentException("The hand of a playercannot be of 0");
       }*/
       this.idPlayer=id;
       this.hand=hand;
       this.memory=new Dialogue();
    }
   /**
    * read the  next action of a player.
    * @return next action chose by a player.
    * @throws IOException when got reading problem
    * */  
   public static String readActionPlayer() throws IOException{
		String s="";
		try{
		InputStreamReader ise=new InputStreamReader(System.in);
		BufferedReader be=new BufferedReader(ise);
		s=be.readLine();
		return s;
		}catch(IOException e){
			throw new IOException("Error in lecture");
		}
		
	}
    /**
     * Check if the action of a player is Ok
     * @since 0.1
     * @deprecated
     * @return true if the choice is ok
     * @param s string to check
     * */
    public static boolean choiceActionIsOk(String s) {
    	int i,j;
    	if (s.equals("q") || s.equals("a"))
    		return true;
    	for (i=1;i<=5;i++) {
    		if (s.equals("d : "+i))
    			return true;
    		for (j=1;j<=5;j++) {
    			if (s.equals("p : "+ i + " " + j))
    				return true;
    			if (s.equals("s : "+ i + " " + j))
    				return true;
    		}
    	}
    	return false;    	
    }
    

    
    /*
      //MAIN DE TEST
       public static void main(String[] args) {
    	ArrayList<Card> carte=new ArrayList<Card>();
    	carte.add(new Card(1,4));
    	carte.add(new Card(2,3));
    	carte.add(new Card(4,2));
    	carte.add(new Card(0,3));
    	HandPlayer play=new HandPlayer(1,carte);
    	System.out.println(play);
    	System.out.println("Vrsion caché");
    	System.out.println(play.showHidden());
    	Card toDis=play.hand.get(3-1);
    	Card dis=play.discard(3);
    	if(!toDis.equals(dis)) {
    		System.out.println("Test of discard not pass");
    		return ;
    	}
    	play.addCard(dis);
    	//play.play(BOARD);
    	Card tomSelect=play.hand.get(0);
    	Card selected =play.selectCard(1);
    	if(!tomSelect.equals(selected)) {
    		System.out.println("Test of card select  not pass");
    		return ;
    	}
    	Card s1=play.selectCard(1);
    	Card s2=play.selectCard(2);
    	play.swap(1, 2);

    
    	
    	if(!(play.selectCard(1).equals(s2) && play.selectCard(2).equals(s1))) {
    		System.out.println("Test du swap nn passé");
    		return;
    	}
    
    	
    }*/

}