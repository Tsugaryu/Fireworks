
import java.util.*;

/**
 * 
 */
public class Board {

    /**
     * Default constructor
     */
    public Board() {
    }

    /**
     * 
     */
    private Card[][] board;

    /**
     * 
     */
    private ArrayList<HandPlayer> gamerPlace;

    /**
     * 
     */
    private ErrorToken bankError;

    /**
     * 
     */
    private ControlToken bankControl;

    /**
     * 
     */
    private Deck deck;

    /**
     * 
     */
    private Discard graveyard;

    /**
     * 
     */
    private static Board singleton;



    public void setGamerPlace(ArrayList<HandPlayer> gamers) {
    	this.gamerPlace=gamers;
    }
    public ArrayList<HandPlayer> getGamerPlace() {
    	return this.gamerPlace;
    }

    /**
     * @return
     */
    public boolean end() {
        // TODO implement here
        return false;
    }
    public
    /**
     * @param c 
     * @param rank 
     * @return
     */
    public boolean putCard(Card c, int rank) {
        // TODO implement here
        return false;
    }

    /**
     * @param numberGamer
     */
    private void Board(int numberGamer) {
        // TODO implement here
    }

    /**
     * @param numberGamer 
     * @return
     */
    public Board createBoard(int numberGamer) {
        // TODO implement here
        return null;
    }

}