package fr.upem.jeu.hanabi.game;
//package fr.upem.jeu.hanabi;


import java.util.*;

import fr.upem.jeu.hanabi.game.content.Card;
import fr.upem.jeu.hanabi.game.content.Token;
import fr.upem.jeu.hanabi.game.stack.Deck;
import fr.upem.jeu.hanabi.game.stack.Discard;

/**
 @author Axel Durand and Raphael BOURJOT
 * @version 1.0
 * The Board class represents the board of the game.
 * This class is a Singleton.
 */
public final class Board {


    /**
     * Place where the fireworks are put.
     */
    private final Card[] board;

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

    private static volatile Board singleton=null;
    /**
     * Actual turn in the game
     */
    private int turn;
    
    /**
     * Getter of @see bankControl
     * @return control bank
     * */
    public Token getControlBank() {
    	return this.bankControl;
    }
    /**
     * Setter of @see bankControl
     * @param tok to set
     * */
   public void setControlBank(Token tok) {
    	this.bankControl=Objects.requireNonNull(tok);
    }
    /**
     * Setter of @see turn
     * @param tour = number of tour
     * */
   public void setTurn(int tour){
    		this.turn=Objects.requireNonNull(tour);
    	}
    /**
     * Update gamers hand.
     * @param gamers hand of the player
     */
    public void setGamerPlace(ArrayList<HandPlayer> gamers) {
    	this.gamerPlace=Objects.requireNonNull(gamers);
    }
    /**
     * getter of @see gamerplace
     * @return the heand of a player.
     * 
     */
    public ArrayList<HandPlayer> getGamerPlace() {
    	return this.gamerPlace;
    }

    /**
     * Check if all of the fireworks are finished or if the deck is empty or if there is error token anymore.
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
     * Place a card on the board
     * @param c a card in the hand of a player.
     * @param rank the place to put in.
     * @return true if the place of the card is correct .
     */public boolean putCard(Card c, int rank) {
         if (c.getValue()==1 && this.board[rank-1].getValue()==-1) {
        	
        	 //On vérifie que ce 1 n'a pas déjà été posé
             int i;
             for (i=0;i<this.board.length;i++) {
                 if (i!=(rank-1)) {
                     if (c.getColor()==this.board[i].getColor())
                         return false;
                 }
             }
             this.board[rank-1]=c;
             return true;
         }
       
         if(this.board[rank-1].getValue()==c.getValue()-1 && this.board[rank-1].getColor()==c.getColor()) {
             this.board[rank-1]=c;
             return true;

         }
         return false;
     }
    
    private  Board(ArrayList<HandPlayer> j) {
    	if(j.size()<=1) {
    		throw new IllegalArgumentException("You cannot have less than 2 two players !");
    	}
    	this.gamerPlace=Objects.requireNonNull(j);
    	this.bankError=new Token(3);
    	this.bankControl=new Token(8);
    	this.board=new Card[5];
    	int i;
    	for (i=0;i<this.board.length;i++)
    		this.board[i]=new Card(-1,-1);
    	this.graveyard=Discard.createDiscard();
    	this.deck=Deck.createDeck();
    }

    /**
     * method factory of board.
     * @param numberGamer 
     * represent the number of gamer
     * @return
     * The instance of a board
     */
    public static Board createBoard(int numberGamer) {
    	if(Board.singleton!=null)return Board.singleton;
    	//creer les joueurs
    	ArrayList<HandPlayer> j=new ArrayList<HandPlayer>(numberGamer);
    	for(int i=0;i<numberGamer;i++) {
    		j.add(new HandPlayer(i+1,new ArrayList<Card>()));
    	}
    	
    	Board board=new Board(j);
    	board.deck.deal(board);
    	Board.singleton=board;
        return board;
    }
    /**
     * Display the results of the game
     * */
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
    		System.out.println("Murabito A : That was really good !");
    		System.out.println("Murabito B : If it's not perfect, it's not good for me.");	
    	}
    	else if(res>20 && res<25) {
    		System.out.println("Murabito A : The beautiful red ! !");
    		System.out.println("Murabito B : We forgot the last one NOOOOOOOOOO");
    	}
    	else if(res==25) {
    		System.out.println("Murabito A : It's a dream !");
    		System.out.println("Murabito B : They are genius !");
    		System.out.println("The Innkeeper : GOOOOOLDEN LEGENDAAAARYYYYYYY");
    	}
    }
    /**
     * Getter of @see graveyard
     * @return a discard object
     * */
    public Discard getDiscard() {
    	return this.graveyard;
    }
    /**
     * Setter of @see graveyard
     * @param d = new discard toSet
     * */
   public  void setDiscard(Discard d) {
    	this.graveyard=Objects.requireNonNull(d);
    }
    /**
     * Getter of @see bankError
     * @return the data of bankerror
     * */
    public Token getBankError() {
		return this.bankError;
	}
    /**
     * Setter of @see bankError
     * @param bankError to set
     * */
    public void setBankError(Token bankError) {
		this.bankError = Objects.requireNonNull(bankError);
	}
    /**
     * Getter of @see deck
     * @return deck
     * */ 
    public Deck getDeck() {
		return deck;
	}
    /**
     * Setter of @see deck
     * @param deck deck value to set
     * */
    public void setDeck(Deck deck) {
		this.deck = Objects.requireNonNull(deck);
	}
    /**
     * @return the representation of Board
     * */
    @Override
    public String toString() {
    	String newLine=System.lineSeparator();
    	StringBuilder builder=new StringBuilder();
    	//Affiche Deck
       	builder.append(this.deck);
       	builder.append(newLine);
       	builder.append("-----------------");
    	builder.append(newLine);
       	//Affiche Token Erreur
    	builder.append("Token rouge ");
    	builder.append(this.bankError);
    	builder.append(newLine);
       	builder.append("-----------------");
    	builder.append(newLine);
    	//Affiche Token Controle
    	builder.append("Token controle ");
    	builder.append(this.bankControl);
    	builder.append(newLine);
       	builder.append("-----------------");
    	builder.append(newLine);
    	//Affiche le Board
    	builder.append("Board ");
    	builder.append(newLine);
    	for(int i=0;i<this.board.length;i++) {
    		//On affiche les Cartes
    		
    		builder.append(i+1);
    		builder.append(" ");
    		
    	}
    	
    	builder.append(newLine);
    	for(int i=0;i<this.board.length;i++) {
    		//On affiche les Cartes
    		builder.append(this.board[i]);
    	}
    	builder.append(newLine);
       	builder.append("-----------------");
    	builder.append(newLine);
    	//Affiche la main des joueurs
    	//NOTE : ATTENTION LE JOUEUR ACTUEL DOIT VOIR SA MAIN DE MANIERE CACHEE
    	int i=0;
    	for(HandPlayer hp : this.gamerPlace) {
    		if(i==this.turn) {
    			builder.append(hp.showHidden());
    		}
    		else
    			builder.append(hp);
    		builder.append(newLine);
    		i++;
    	}
    	//builder.append(newLine);
       	builder.append("-----------------");
    	builder.append(newLine);
    	//on affichera la discard si l'utilisateur le demande --> on fera une fonction juste pour Ã§a
    	/*
    	builder.append(this.graveyard);
    	builder.append(newLine);
       	builder.append("-----------------");
    	builder.append(newLine);
    	*/
    	return builder.toString();
    }
}