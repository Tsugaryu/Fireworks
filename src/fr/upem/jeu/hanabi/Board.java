package fr.upem.jeu.hanabi;


import java.util.*;

/**
 @author Axel Durand
 * @version 0.1
 * The Board class represents the board of the game.
 * This class is a Singleton.
 */
public final class Board {


    /**
     * Place where the fireworks are put.
     */
    private Card[][] board;

    /**
     * Representationn of the place and hand of the players.
     */
    private ArrayList<HandPlayer> gamerPlace;

    /**
     * Red token that are one of the mean to end the game
     */
    private Token bankError;

    /**
     * Blue token which control the actions of the player
     */
    private Token bankControl;

    /**
     * represents a Deck of card
     */
    private Deck deck;

    /**
     * Place where the discard card go.
     */
    private Discard graveyard;

    /**
     * 
     */
    private static volatile Board singleton=null;


    /**
     * Update gamers hand.
     * @param gamers hand of the player
     */
    public void setGamerPlace(ArrayList<HandPlayer> gamers) {
    	this.gamerPlace=gamers;
    }
    /**
     * getter of @see gamerplace.
     * @return
     */
    public ArrayList<HandPlayer> getGamerPlace() {
    	return this.gamerPlace;
    }

    /**
     * check if all of the fireworks are finished or if the deck is empty or if there is error token anymore.
     * @return the result of the description
     */
    public boolean end() {
    	int end=0;
    	for(int i=0;i<this.board.length;i++) {
    		if(this.board[i].length==5) {
    			end++;
    		}
    	}
        return this.bankError.isEmpty() || deck.getSizeDraw()==0||end>=5;
    }
    
    /**
     * @param c a card in the hand of a player.
     * @param rank the place to put in.
     * @return true if the place of the card is correct .
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

    
    private  Board(ArrayList<HandPlayer> j, Deck d) {
     
    	this.bankError=new Token(3);
    	this.bankControl=new Token(8);
    	this.board=new Card[5][5];
    	this.graveyard=new Discard();
    	this.gamerPlace=j;
    	this.deck=d;
    }

    /**
     * method factory of board.
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