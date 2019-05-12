package fr.upem.jeu.hanabi;

/**
 * Represent a card of the game Hanabi.
 * @author Axel Durand.
 * @version 0.1 .
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

    /*
     * Id of a card used for mix a Deck.
     * @since 0.1 ;
     */
    /*private final int id;*/

 
    /**
     * convert a Card in a string
     * @return a representation of the Card. The format returned of the Card is 'Color Value'.
     */
    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        if (this.color==0)
        	return "";
        if(isWhite()) {
        	builder.append("W");
        }
        else if(isGreen()) {
        	builder.append("G");
        }
        else if(isYellow()) {
        	builder.append("Y");
        }
        else if(isRed()) {
        	builder.append("R");
        }
        else if(isBlue()) {
        	builder.append("B");
        }
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
    	Card c=(Card)o;
    	return c.color==this.color && c.value==this.value;
    }

    /**
     * Constructor of Card
     * @param color 
     *  Color of the card
     * @param value 
     * 	Value of the card
     * @param id
     * 	Id of the card
     * @since 0.1 ;
     */
    public Card(int color, int value/*, int id*/) {
    	if(color>5||value>5);//lancez une exception
    	this.color=color;
        this.value=value;
        /*this.id=id;*/
    }

    /**
     * Identifier color method.
     * @return true if card is of white color.
     * @since 0.1 ;
     */   
    public boolean isWhite() {
        return this.color==0;
    }

    /**
     * Identifier color method.    
     * @return true if card is of green color.
     * @since 0.1 ;
     */
    public boolean isGreen() {
        return this.color==1;
    }

    /**
     * Identifier color method.
     * @return true if card is of yellow color.
     * @since 0.1 ;
     */
    public boolean isYellow() {
         return this.color==2;
    }

    /**
     * Identifier color method.
     * @return true if card is of red color.
     * @since 0.1 ;
     */
    public boolean isRed() {
         return this.color==3;
    }

    /**
     * Identifier color method.
     * @return true if card is of blue color.
     * @since 0.1 ;
     */
    public boolean isBlue() {
           return this.color==4;
    }
    int getColor() {
    	return this.color;
    }
    int getValue() {
    	return this.value;
    }
    //Main de test
    public static void main(String[] args) {
    	Card c1=new Card(1,1);
    	Card c2=new Card(3,2);
    	Card c3=new Card(1,2);
    	System.out.println(c2);
    	System.out.println(c1.equals(c3));
    	
    }
   
}