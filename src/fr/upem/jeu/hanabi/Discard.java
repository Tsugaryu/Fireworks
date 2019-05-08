package fr.upem.jeu.hanabi;


import java.util.*;

/**
 * 
 */
public class Discard extends CardStack {


    /**
     * 
     */
    private List<Card> discard;

    /**
     * 
     */
    private static Discard singleton;

    /**
     * 
     */
    private Card top;


    /**
     * @return a string which contains the display of all the cards in the discard
     */
    public String toString() {
        String res="";
        for (Card c : this.discard) {
            res+=(" " + c);
        }
        return res;
    }

    /**
     * @param d
     */
    private void Discard(List<Card> d) {
        
    }

    /**
     * @return
     */
    public static Discard createDiscard() {
    	if(Discard.singleton!=null)return Discard.singleton;
    	super();
    	Discard dcard= new Discard();
    	dcard.top=null;
    	Discard.singleton=dcard;
    	return dcard;
    }

    /**
     * @return the top of the discard 
     */
    public Card getTop() {
        return this.top;
    }

}