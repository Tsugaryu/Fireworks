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
    private Card[] board;

    /**
     * Representation of the place and hand of the players.
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

    private Dialogue speakingInterface;
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
    		if(this.board[i].getValue()==5) {
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
        if(this.board[rank].getValue()==c.getValue()-1 && this.board[rank].getColor()==c.getColor()) {
        	this.board[rank]=c;
        	return true;
        
        }
        return false;
    }

    
    private  Board(ArrayList<HandPlayer> j, Deck d,Dialogue speak) {
     
    	this.bankError=new Token(3);
    	this.bankControl=new Token(8);
    	this.board=new Card[5];
    	this.graveyard=new Discard();
    	this.gamerPlace=j;
    	this.deck=d;
    	this.speakingInterface=speak;
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
    	Dialogue d=new Dialogue(numberGamer);
    	Board board=new Board(j , deck,d);
    	deck.deal(board);
        return board;
    }
    public void fireworksResult() {
    	int res=0;
    	for(Card c : this.board) {
    		res+=c.getValue();
    	}
    	if(res<=5) {
    		System.out.println("Murabito A : What ?! That sucks");
    		System.out.println("Murabito B : Returns the money back you thief !");
    	}
    	else if(res>5 && res<=10) {
    		System.out.println("Murabito A : *Clap Clap*");
    		System.out.println("Murabito B : *Clap Clap*");
    	}
    	else if(res>10 && res <=15) {
    		System.out.println("Murabito A : That was classic.");
    		System.out.println("Murabito B : Yeah, maybe they will do something better in another time ?");
    	}
    	else if(res>15 && res<=20) {
    		System.out.println("Murabito A : That was excellent !");
    		System.out.println("Murabito B : ");	
    	}
    	else if(res>20 && res<25) {
    		
    	}
    	else if(res==25) {
    		System.out.println("Murabito A : ");
    		System.out.println("Murabito B : ");
    		System.out.println("The Innkeeper : GOOOOOLDEN LEGENDAAAARYYYYYYY");
    	}
    }

}