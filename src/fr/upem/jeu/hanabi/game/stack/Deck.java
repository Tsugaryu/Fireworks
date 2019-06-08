package fr.upem.jeu.hanabi.game.stack;


import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

import fr.upem.jeu.hanabi.game.Board;
import fr.upem.jeu.hanabi.game.HandPlayer;
import fr.upem.jeu.hanabi.game.content.Card;
import fr.upem.jeu.hanabi.io.Parameter;

/**
 * @author Axel Durand
 * @version 1.1
 * The Deck class represents a stack of card in which player draw cards.
 * This class is a Singleton.
 */
public final class Deck {


    /**
     * represents the card stack.
     * @since 0.1.
     */
    private LinkedList<Card> draw;
    
    private static volatile Deck singleton=null;



    /**
     * deal the cards of the deck to each player.
     * @param board board where are the player
     * 
     */
    public void deal(Board board) {
    	ArrayList<HandPlayer> players=board.getGamerPlace();
    	int i,j;
    	Card card;
    	Parameter param=Parameter.getInstance();
    	int maxPlayer=param.getMaxPlayer();
    	int cardToDistrib=param.getNumberOfCardByPlayer();
    	if(!(players.size()<=maxPlayer/2))cardToDistrib-=1; 
    		//make draw player
    		for(i=0;i<players.size();i++){
    			for(j=0;j<cardToDistrib;j++) {
    				card=this.draw();
    				players.get(i).addCard(card);
    			}
    		}
    		//record the hand of the players
        	board.setGamerPlace(players);   
    	
    	}
    /** 
     * @return the size of the draw.
     * */
    public int getSizeDraw() {
    	return draw.size();
    }
    /**
     * Represent a Deck in a string
     * @return a representation of Deck
     * */
    @Override
    public String toString(){
    	StringBuilder builder=new StringBuilder();
    	builder.append("Number of Card in the Deck ");
    	builder.append(getSizeDraw());
    	return builder.toString();
    }
    /**
     * Draw one card from the deck
     * @return the card in the top of the Deck.
     */
    public Card draw() {
       Card drew=this.draw.removeFirst();
       
       return drew;
    }

    /*on ne met pas de javadoc*/   
    private Deck(LinkedList<Card> d) {
    	if(d.size()==0)throw new IllegalArgumentException("You cannot create a deck with no card !");
    	this.draw=d;
    }
    
    /**
     * Creates a deck of cards.
     * @return the singleton class Deck if not already created 
     * @return else the instance of the class created.
     * @throws  IllegalStateException when you try to create a deck although the parameters are not loaded.
     * @version 1.1
     */
    public static Deck createDeck() throws IllegalStateException {
        if(Deck.singleton!=null)return Deck.singleton;
        if(Parameter.getInstance()==null) {
        	throw new IllegalStateException("Parameters must be charged before creating the deck");
        }
        Deck d;
       // int value,color;
        LinkedList<Card> list=new LinkedList<Card>();
        Parameter param=Parameter.getInstance();
        int[] numberOfValue=param.getNumberOfValueAvailableByFamily();
        String[] colors =  param.getColorFamily();
        for(int i=0;i<numberOfValue.length;i++) {
        	for(int j=0;j<colors.length;j++) {
        		for(int k=0;k<numberOfValue[i];k++) {
        			//j=code  couleur pour accéder à la couleur se trouvant dans les paramètres
        			list.add(new Card(j,i+1));
        		}
        	}
        }
        /*shuffle the deck*/
        Collections.shuffle(list,new Random());
        d=new Deck(list);
        Deck.singleton=d;
        return d;
    }
    /*
     //MAIN DE TEST
      public static void main(String[] args) {
    	Deck d= Deck.createDeck();
    	Card c;
    	int un=0,deux=0,trois=0,quatre=0,cinq=0;
    	System.out.println(d.getSizeDraw());
    	while(d.getSizeDraw()!=0) {
    		c= d.draw();
    		System.out.print(c+" ");
    		if(c.getValue()==1)un+=1;
    		if(c.getValue()==2)deux+=1;
    		if(c.getValue()==3)trois+=1;
    		if(c.getValue()==4)quatre+=1;
    		if(c.getValue()==5)cinq+=1;
    	}
    	System.out.println(System.lineSeparator());
    	System.out.println("Un="+un+"Deux="+deux+"trois="+trois+"quatre="+quatre+"cinq="+cinq);
    	System.out.println(d.getSizeDraw());
    }*/

}