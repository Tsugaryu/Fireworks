package fr.upem.jeu.hanabi.game.stack;


import java.util.*;

import fr.upem.jeu.hanabi.game.content.Card;

/**
 * Class which represent the discard of the game Hanabi.
 * @author Raphael BOURJOT
 * @version 1.0
 */
public class Discard {


    /**
     * Structure which contains the card discarded by players.
     * @since 0.1
     */
    private final LinkedList<Card> discard;

    /**
     * Singleton of a discard.
     * @since 0.1
     */
    private static Discard singleton;

    /**
     * represent the top of the discard.
     */
    private Card top;


    /**
     * @return a string which contains the display of all the cards in the discard
     */
    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        for (Card c : this.discard) {
            res.append(" ");
            res.append(c);
        }
        return res.toString();
    }


    private Discard() {
    	this.discard=new LinkedList<Card>();
    }

    /**
     * Create the instance of Discard
     * @return
     * The instance of card
     */
    public static Discard createDiscard() {
    	if(Discard.singleton!=null)return Discard.singleton;
    	Discard dcard= new Discard();
    	dcard.top=null;
    	Discard.singleton=dcard;
    	return dcard;
    }

    /**
     * @return the top of the discard 
     */
    Card getTop() {
        return this.top;
    }
    /*ajoute une carte à la défausse*/
    /**
     * Add a card to the discard
     * @param d
     * The card to add
     * */
    public void addCard(Card d) {
    	this.discard.addFirst(d);
    	this.top=Objects.requireNonNull(d);
    }

}