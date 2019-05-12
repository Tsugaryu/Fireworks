//package fr.upem.jeu.hanabi;


import java.util.*;


/**
 * 
 */
public class Main {



    /**
     * @param args 
     * @return
     */
    public static void main(String[] args) {
        Board b=Board.createBoard(2);
        int i=1; // i correspond au numéro du joueur en train de jouer
        String read = new String ();
        Dialogue.printTutorial();
        while(!b.end()){
        	System.out.println(b);
        	read=" "; // on initialise read pour qu'on puisse passer dans la boucle suivante
        	while (!HandPlayer.choiceActionIsOk(read)) {
        		read=HandPlayer.readActionPlayer();
        	}
        	ArrayList<HandPlayer> hands=b.getGamerPlace();
        	Deck d=b.getDeck();
        	Card c=d.draw();
        	Discard graveyard=b.getDiscard();
        	int rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
        	if(read.charAt(0)=='d'){
        		Card discarded = hands.get(i).discard(rankCardToDo);
        		hands.get(i).addCard(c);
        		graveyard.addCard(discarded);
        		b.setDeck(d);
        		b.setGamerPlace(hands);
        		b.setDiscard(graveyard);
        	}
        	else { //si le premier caractère de read est p
        		Card toPlay = hands.get(i).selectCard(rankCardToDo);
        		int valueColorToDo=Integer.parseInt(String.valueOf(read.charAt(6)));
        		if (!(b.putCard(toPlay,valueColorToDo))) {
        			Token errors = b.getBankError();
        			errors.removeToken();
        			b.setBankError(errors);
        			graveyard.addCard(toPlay);
        			b.setDiscard(graveyard);
        			hands.get(i).addCard(c);
        		}
        		else {
        			int j;
        			for(j=1;j<=5;j++) {
        				Card tmp=hands.get(i).selectCard(j);
        				if (tmp.equals(toPlay))
        					tmp=c;
        			}
        		}
        		
        		b.setGamerPlace(hands);
        	}
        	if (i==1)
        		i=2;
        	else
        		i=1;
        }
        
    }


}