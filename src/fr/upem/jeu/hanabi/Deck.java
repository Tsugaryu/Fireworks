package fr.upem.jeu.hanabi;


import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Axel Durand
 * @version 0.1
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
     * deal the cards of the deck to the player.
     * @param board 
     * 
     */
    public void deal(Board board) {
    	ArrayList<HandPlayer> players=board.getGamerPlace();
    	int i,j;
    	Card card;
    	if(players.size()<=3) {
    		//make draw player
    		for(i=0;i<players.size();i++){
    			for(j=0;j<5;j++) {
    				card=this.draw();
    				players.get(i).addCard(card);
    				System.out.println(card);
    			}
    		}
    	}
    	else {
    		//make draw player
    		for(i=0;i<players.size();i++){
    			for(j=0;j<4;j++) {//number of card to draw
    				players.get(i).addCard(draw());
    			}
    		}
    	}
    	//record the hand of the players
    	board.setGamerPlace(players);   
    }
    public int getSizeDraw() {
    	return draw.size();
    }
    @Override
    public String toString(){
    	return "Number of Card in the Deck "+getSizeDraw();
    }
    /**
     * @param player 
     * @return
     */
    public Card draw() {
       Card drew=this.draw.removeFirst();
       
       return drew;
    }

    /*on ne met pas de javadoc*/   
    private Deck(LinkedList<Card> d) {
    	this.draw=d;
    }
    
    /**
     * Creates a deck of 50 cards.
     * @return the singleton class Deck if not already created 
     * @return else the instance of the class created.
     */
    public static Deck createDeck() {
        if(Deck.singleton!=null)return Deck.singleton;
        Deck d;
        int value,color;
        LinkedList<Card> list=new LinkedList<Card>();
        /*--------CREATE CARD OF VALUE 1------------*/
        int i;
        /*create cards of value 1*/
        for(color=0;color<=4;color++) {
        	for(i=0;i<3;i++) {
        		list.add(new Card(color,1));
        	}
        }
        /*create cards of value 2,3,4*/
        for(value=2;value<=4;value++) {
        	for(color=0;color<=4;color++) {
        		for(i=0;i<2;i++) {
        		list.add(new Card(color,value));
        		}
        	}
        }
        
        /*create cards of value 5*/
        for(color=0;color<=4;color++) {
        	list.add(new Card(color,5));
        	
        }
        /*shuffle the deck*/
        Collections.shuffle(list,new Random());
        d=new Deck(list);
        Deck.singleton=d;
        return d;
    }
    /*public static void main(String[] args) {
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