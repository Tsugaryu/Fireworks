package fr.upem.jeu.hanabi.game.content;

import java.util.Objects;

/**@author : Raphael BOURJOT
 * @version 1.0
 * A variable of this class represents the stack of token remaining.
 * The stack can contain control tokens or error tokens.
 * 
 *
 */
public class Token {

    /**
     * Token.number is the number of token remaining
     */
    private int number;

    /**
     * Token.maxToken is the maximum number of token available. 0<=Token.number<=Token.number .
     */
    private final int maxToken;

    /**
     * @param max
     * Create a new variable Token. Initialize maxToken and number to max.
     * @throws IllegalArgumentException  when you try to create token which have a negative or empty value. 
     *      */
    public Token(int max) {
    	if(max<=0)   throw new IllegalArgumentException("Token cannot be empty or negative at his creation");
        this.maxToken=Objects.requireNonNull(max);
        this.number=Objects.requireNonNull(max);
    }


    /**
     * remove 1 to the token.number
     * @throws IllegalStateException when you try to remove a token when the stack is empty.
     */
    public void removeToken()throws IllegalStateException {
    	if (this.isEmpty())
    		throw new IllegalStateException("You can't have less token than zero!");
        this.number-=1;
    }

    /**
     * Check if the token stack is empty
     * @return true if there is no token remaining; or false if there is at least one token.
     */
    public boolean isEmpty() {
        return this.number==0;
    }

    /**
     * @return "Token remaining : number of token"
     */
    @Override
    public String toString() {
        return ("remaining : " + this.number);
    }

    

    /**
     * Check if the token stack is full.
     * @return a boolean. If the number of token is equal to the max, the method return true. Else, it return false.
     */
    public boolean isLimit() {
        return (this.number==this.maxToken);
    }

    /**
     * add 1 to the token.number
     * @throws IllegalStateException when you try to add a token when the stack is full.
     */
    public void addToken()throws IllegalStateException {
    	if (this.number==this.maxToken)
    		throw new IllegalStateException("You can't have more tokens than the max !");
        this.number+=1;
    }/*
    //MAIN DE TEST
    public static void main(String[] args) {
    	Token t1=new Token(1);
    	Token t2=new Token(5);
    	t1.removeToken();
    	System.out.println(t1);
    	System.out.println(t1.isEmpty());
    	try {
    		//t1.removeToken();
        	t1.addToken();
        	System.out.println(t1);
        	
        	
    	}catch(IllegalArgumentException e) {
    		
    	}
    	t2.addToken();
    	
    }
    */
    
    int getNumber() {
		return this.number;
	}
    
}