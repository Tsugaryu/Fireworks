package fr.upem.jeu.hanabi;


import java.util.*;

/**Author : Raphaël BOURJOT
 * A variable of this class represent the hand of a player in the game.
 */
public class CardStack {

    /**
     * Default constructor
     */
    public CardStack() {
    	this.cardStructure=new List<Card>[5];
    }

    /**
     * 
     */
    private List<Card> cardStructure;

}