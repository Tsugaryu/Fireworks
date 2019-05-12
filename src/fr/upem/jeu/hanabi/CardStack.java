package fr.upem.jeu.hanabi;


import java.util.*;

/**Author : Rapha�l BOURJOT
 * This interface represent a list of cards. A class which implements this interface needs to define a method void addCard()
 * A variable of this class represent the hand of a player in the game.
 */
public interface CardStack {
	
    LinkedList<Card> cardStructure;
   
    public abstract void addCard();
    

}