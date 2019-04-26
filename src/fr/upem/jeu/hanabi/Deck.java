
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
public final class Deck extends CardStack {


    /**
     * represents the card stack.
     * @since 0.1.
     */
    private LinkedList<Card> draw;

    private static volatile Deck singleton=null;



    /**
     * @param board 
     * 
     */
    public void deal(Board board) {
    	ArrayList<HandPlayer> players=board.getGamerPlace();
    	int i;
    	if(players.size()<=3) {
    		//make draw player
    		for(i=0;i<players.size();i++){
    			
    		}
    	}
    	else {
    		//make draw player
    	}
    	
        
    }
    public int getSizeDraw() {
    	return draw.size();
    }
    /**
     * @param player 
     * @return
     */
    public void draw(HandPlayer player) {
       Card drew=this.draw.pop();
       player.addCard(drew);
        return ;
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
    public static Deck createDraw() {
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

}