//package fr.upem.jeu.hanabi;


import java.util.*;

/**Author : Raphaël BOURJOT
 * A variable of this class represent the hand of a player in the game.
 */
public interface CardStack {
	
    LinkedList<Card> cardStructure= new LinkedList<Card>();
    public abstract void addCard();
    

}