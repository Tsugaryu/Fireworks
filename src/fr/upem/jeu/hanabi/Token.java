
import java.util.*;

/**Author : Raphaël BOURJOT
 * 
 * A variable of this class represents the stack of token remaining.
 * The stack can contain control tokens or error tokens.
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
     * Create a new variable Token. If max=3, the variable is an error Token. Else (when max=8), the variable is a control Token
     */
    public Token(int max) {
        this.maxToken=max;
        if (max==3)
            this.number=0;
        else
            this.number=8;
    }


    /**
     * remove 1 to the token.number for the variable this
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
        return (this.number==this.maxToken)
    }

    /**
     * add 1 to the token.number for the variable this
     */
    public void addToken() {
        this.number+=1;
    }

}