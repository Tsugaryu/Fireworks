package fr.upem.jeu.hanabi;


import java.util.*;

/**@author : Raphaël BOURJOT
 * @version 0.1
 * A variable of this class represents the stack of token remaining.
 * The stack can contain control tokens or error tokens.
 * Token.number is the number of token remaining
 * Token.maxToken is the maximum number of token available. 0<=Token.number<=Token.number .
 */
public class Token {



    /**
     * 
     */
    private int number;

    /**
     * 
     */
    private final int maxToken;

    /**
     * @param max
     * Create a new variable Token. Initialize maxToken and number to max.
     */
    public Token(int max) {
        this.maxToken=max;
        this.number=max;
    }


    /**
     * remove 1 to the token.number
     */
    public void removeToken() {
        this.number-=1;
    }

    /**
     * @return true if there is no token remaining; or false if there is at least one token.
     */
    public boolean isEmpty() {
        return this.number==0;
    }

    /**
     * @return "Token remaining : number of token"
     */
    public String toString() {
        return ("Token remaining : " + this.number);
    }

    

    /**
     * @return a boolean. If the number of token is equal to the max, the method return true. Else, it return false.
     */
    public boolean isLimit() {
        return (this.number==this.maxToken);
    }

    /**
     * add 1 to the token.number
     */
    public void addToken() {
    	if (this.number==this.maxToken)
    		throw new IllegalArgumentException("You can't have more tokens than the max !");
        this.number+=1;
    }

}