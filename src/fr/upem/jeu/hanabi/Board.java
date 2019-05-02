
import java.util.*;

/**
 * 
 */
public final class Board {


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
    private Token bankError;

    /**
     * 
     */
    private Token bankControl;

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
    private static volatile Board singleton=null;



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
        return this.bankError.isEmpty() || deck.getSizeDraw()==0;
    }
    
    /**
     * @param c 
     * @param rank 
     * @return
     */
    public boolean putCard(Card c, int rank) {
        if(this.board[rank].length==0) {
        	this.board[rank][0]=c;
        	return true;
        }
        else if(this.board[rank].length==c.getValue()-1 && this.board[rank][this.board[rank].length-1].getColor()==c.getColor()) {
        	this.board[rank][this.board[rank].length]=c;
        
        }
        return false;
    }

    /**
     * 
     */
    private  Board(ArrayList<HandPlayer> j, Deck d) {
     
    	this.bankError=new Token(3);
    	this.bankControl=new Token(8);
    	this.board=new Card[5][5];
    	this.graveyard=new Discard();
    	this.gamerPlace=j;
    	this.deck=d;
    }

    /**
     * @param numberGamer 
     * @return
     */
    public static Board createBoard(int numberGamer) {
    	if(Board.singleton!=null)return Board.singleton;
    	//créer le Deck
    	Deck deck=Deck.createDeck();
    	//créer les joueurs
    	ArrayList<HandPlayer> j=new ArrayList<HandPlayer>(numberGamer);
    	Board board=new Board(j , deck);
    	deck.deal(board);
        return board;
    }

}