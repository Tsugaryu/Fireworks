//package fr.upem.jeu.hanabi;


/**@author : Raphael BOURJOT
 * @version 0.1
 * A variable of this class represents the stack of token remaining.
 * The stack can contain control tokens or error tokens.
 * 
 * @throws an IllegalArgumentException when you intent to use addToken on a Token variable which have number equal to max
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
     */
    public Token(int max) {
        this.maxToken=max;
        this.number=max;
    }


    /**
     * remove 1 to the token.number
     */
    public void removeToken() {
    	if (isEmpty())throw new IllegalArgumentException("You can't have less token than zero!");
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
    
    int getNumber() {
		return this.number;
	}
    
}