//package fr.upem.jeu.hanabi;


import java.util.*;

/**
 * 
 */
public class Discard {


    /**
     * 
     */
    private LinkedList<Card> discard;

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
<<<<<<< HEAD
    private  Discard() {
    	this.discard=new LinkedList<Card>();
=======
    private void Discard(List<Card> d) {
    	 
>>>>>>> e8f8bf087e3fb457bd35863a7dcc6164dcb7f66c
    }

    /**
     * @return
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
    public void addCard(Card d) {
    	this.discard.addFirst(d);
    	this.top=d;
    }

}