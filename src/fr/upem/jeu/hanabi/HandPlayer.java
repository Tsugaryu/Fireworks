package fr.upem.jeu.hanabi;


import java.util.*;
import java.io.*;

/**
 * @author Raphael BOURJOT
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
    private Dialogue effectue;


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
        discarded= this.hand.remove(place-1);
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
    	
    	Card card=this.selectCard(c);
    	Card card1=this.selectCard(c1);
    	this.hand.set(c1-1, card);
    	this.hand.set(c-1,card1);
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
    public void play(Board b,int rankCardPlayed, char colorPlayed) {
        
       
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
    	builder.append("Hand of Player ");
    	builder.append(this.idPlayer);
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
    @Override
    public String toString() {
    	StringBuilder builder=new StringBuilder();
    	String newLine=System.lineSeparator();
    	builder.append("Hand of Player ");
      	builder.append(newLine);
    	builder.append(this.idPlayer);
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append(i+1);
    		builder.append("  ");
    		
    	}
    	builder.append(newLine);
    	for(int i=0;i<this.hand.size();i++) {
    		builder.append(this.hand.get(i));
    		builder.append(" ");
    	}
    	builder.append(newLine);
    	return builder.toString();
    	
    }

    /**
     * @param rank 
     * @return
     */
     Card selectCard(int rank) {
        return this.hand.get(rank-1);
      
    }
     /**
      * @param rank 
      * @return
      */
      static Card selectCard(int rank,HandPlayer player) {
         return player.selectCard(rank);
       
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
    public HandPlayer(int id, ArrayList<Card> hand) {
       this.idPlayer=id;
       this.hand=hand;
    }
    /*
     * Le joueur décide de l'action à réaliser, 
     * note : sa serait pas con de scinder les actions et les endroits où on balance certaines actions 
     * sa permettrait d'avoir un traitement de la string plus facile
     * */
/*    public decideAction() {
    	
    }*/
    /*Le joueur joue une carte à enregistrer dans le baord*/
 /*  selectCardToPlace({
    	Discard dis=new Discard();
    	Board b=new Board();
    	b.
    })*/
    
    public static String readActionPlayer() throws IOException{
		String s="";
		try{
		InputStreamReader ise=new InputStreamReader(System.in);
		BufferedReader be=new BufferedReader(ise);
		s=be.readLine();
		return s;
		}catch(IOException e){
			throw e;
		}
		
	}
    
    public static boolean choiceActionIsOk(String s) {
    	int i,j;
    	if (s.equals("q"))
    		return true;
    	for (i=1;i<=5;i++) {
    		if (s.equals("d : "+i))
    			return true;
    		for (j=1;j<=5;j++) {
    			if (s.equals("p : "+ i + " " + j))
    				return true;
    			if (s.equals("s : "+ i + " " + j))
    				return true;
    		}
    	}
    	return false;    	
    }
    

    
    public static void main(String[] args) {
    	ArrayList<Card> carte=new ArrayList<Card>();
    	carte.add(new Card(1,4));
    	carte.add(new Card(2,3));
    	carte.add(new Card(4,2));
    	carte.add(new Card(0,3));
    	HandPlayer play=new HandPlayer(1,carte);
    	System.out.println(play);
    	System.out.println("Vrsion caché");
    	System.out.println(play.showHidden());
    	Card toDis=play.hand.get(3-1);
    	Card dis=play.discard(3);
    	if(!toDis.equals(dis)) {
    		System.out.println("Test of discard not pass");
    		return ;
    	}
    	play.addCard(dis);
    	//play.play(BOARD);
    	Card tomSelect=play.hand.get(0);
    	Card selected =play.selectCard(1);
    	if(!tomSelect.equals(selected)) {
    		System.out.println("Test of card select  not pass");
    		return ;
    	}
    	Card s1=play.selectCard(1);
    	Card s2=play.selectCard(2);
    	play.swap(1, 2);

    
    	
    	if(!(play.selectCard(1).equals(s2) && play.selectCard(2).equals(s1))) {
    		System.out.println("Test du swap nn passé");
    		return;
    	}
    
    	
    }

}