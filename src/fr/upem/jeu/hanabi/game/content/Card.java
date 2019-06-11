package fr.upem.jeu.hanabi.game.content;

import java.util.Objects;

import fr.upem.jeu.hanabi.io.Parameter;

/**
 * Represent a card of the game Hanabi.
 * @author Axel Durand.
 * @version 1.1 
 */
public class Card {

    /**
     * Color of the card.     
     * @since 0.1 ;
     */
    private final int color;

    /**
     * Value of the card.
     * @since 0.1 ;
     */
    
    private final int value;

 
    /**
     * convert a Card in a string
     * @return a representation of the Card. The format returned of the Card is 'Color Value'.
     */
    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        Parameter param=Parameter.getInstance();
        String[] allColors=param.getColorFamily();
        if (this.color==-1)
        	return "  ";
        char letter=allColors[this.color].charAt(0);
        builder.append(letter);
        builder.append(this.value);
        return builder.toString();
    }

    /**
     * Check if two card are the same
     * @param o 
     * 	The object must be a Card.
     * @return true if the object got the same color and the same value.
     * 		   false if the object doesn't have the same color and the same value.
     */
    @Override
    public boolean equals(Object o) {
    	if (!(o instanceof Card)) {return false;    }
    	Card c=(Card)o;
    	return (c.color==this.color && c.value==this.value);
    }

    /**
     * Constructor of Card
     * @param color 
     *  Color of the card
     * @param value 
     * 	Value of the card
     * @since 0.1 ;
     */
    public Card(int color, int value){
    	this.color=Objects.requireNonNull(color);
        this.value=Objects.requireNonNull(value);
    }
    

    /**
     * getter of @see color
     * @return card color
     */
    public int getColor() {
    	return this.color;
    }
    /**
     * getter of @see value	
     * @return card value
     */
    public int getValue() {
    	return this.value;
    }
    /*
    //Main de test
    public static void main(String[] args) {
    	Card c1=new Card(1,1);
    	Card c2=new Card(3,2);
    	Card c3=new Card(1,2);
    	System.out.println(c2);
    	System.out.println(c1.equals(c3));
    	
    }*/
   
}