//package fr.upem.jeu.hanabi;


import java.util.*;

/**
 * 
 */
public class HandPlayer {

 

    /**
     * 
     */
    private ArrayList<Card> hand;

    /**
     * 
     */
    private int idPlayer;


    /**
     * 
     */
    public Dialogue effectue;


    /**
     * @param c 
     * @param graveyard 
     * @return
     */
    public Card discard(Card c) {
        Card discarded=new Card(0,0);
        int i=0;
        for(Card card : this.hand) {
        	if(card.equals(c)) {
        		discarded=card;
        		this.hand.remove(i);
        	}
        	i++;
        }
        
        return discarded;
    }
    public Card discard(int place) {
        Card discarded;
        discarded= this.hand.remove(place);
        return discarded;
    }

    /**
     * @param c 
     * @param c1 
     * @return
     */
    public void swap(Card c, Card c1) {
        int i,i1,j;
        j=0;
        i=-1;
        i1=-1;
    	//search the index
        for(Card iterator :this.hand) {
        	if(c==iterator)i1=j;
        	if(c1==iterator)i=j;
        		j++;
        }
        //swap index
    	
        if(i!=-1)this.hand.set(i, c1);
    	this.hand.set(i1,c);
        return ;
    }
    public void swap(int c,int c1) {
    	
    	Card card=this.hand.get(c);
    	Card card1=this.hand.get(c1);
    	this.hand.set(c1, card);
    	this.hand.set(c,card1);
    	return;
    }
    public void addCard(Card card) {
    	this.hand.add(card);
    }

    /**
     * censer jouer une carde
     * @param b 
     * @return
     */
    public void play(Board b) {
        // TODO implement here
       
    }

    /**
     * @param otherPlayer 
     * @param s 
     * @param d 
     * @return
     */
    //PART2
    public void giveInformation(HandPlayer otherPlayer, String s, Dialogue d) {
        // TODO implement here
       
    }
    public String showHidden() {
    	StringBuilder builder=new StringBuilder();
    	String newLine=System.lineSeparator();
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append(i+1);
    		builder.append(" ");
    	}
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append("? ");
    	}
    	builder.append(newLine);
    	return builder.toString();
    	
    }

    /**
     * @param rank 
     * @return
     */
     Card selectCard(int rank) {
        return this.hand.get(rank);
      
    }

    /**
     * @return
     */
     int getId() {
        return this.idPlayer;     
    }

    /**
     * @param id 
     * @param hand
     */
    public void HandPlayer(int id, ArrayList<Card> hand) {
       this.idPlayer=id;
       this.hand=hand;
    }
    /*
     * Le joueur décide de l'action à réaliser, 
     * note : sa serait pas con de scinder les actions et les endroits où on balance certaines actions 
     * sa permettrait d'avoir un traitement de la string plus facile
     * */
    public decideAction() {
    	
    }
    /*Le joueur joue une carte à enregistrer dans le baord*/
   selectCardToPlace({
    	Discard dis=new Discard();
    	Board b=new Board();
    	b.
    })

}