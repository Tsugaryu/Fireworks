package fr.upem.jeu.hanabi;


import java.io.IOException;
import java.util.*;


/**
 * @author Raphael BOURJOT
 */
public class Main {



    /**
     * @param args 
     * @return
     */
    public static void main(String[] args) {
        Board b=Board.createBoard(2);
        int i=1; // i correspond au numï¿½ro du joueur en train de jouer
        String read = new String ();
        Dialogue.printTutorial();
        int discardOrPlayDone; // discardOrPlayDone=0 signifie que le joueur n°i n'a pas encore fait discard ou play, et discardOrPlayDone=1 signifie que cela a été fait 
        while(!b.end()){
        	discardOrPlayDone=0;
        	System.out.println(b);
        	read=" "; // on initialise read pour qu'on puisse passer dans la boucle suivante
        	while (!HandPlayer.choiceActionIsOk(read)) {
        		try {
        			read=HandPlayer.readActionPlayer();
        		}catch(IOException e) {
        			System.out.println("Fin du jeu problÃ¨me de lecture");
        			return ;
        		}
        	}
        	
        	
        	
        	if (read.charAt(0)=='q') {
        		System.out.println("Goodbye");
        		return ;
        	}
        	ArrayList<HandPlayer> hands=b.getGamerPlace();
        	Deck d=b.getDeck();
        	Card card=d.draw();
        	Discard graveyard=b.getDiscard();
        	int rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
        	
        	if (read.charAt(0)=='s') {
        		int rankCard2=Integer.parseInt(String.valueOf(read.charAt(6)));
        		hands.get(i).swap(rankCardToDo, rankCard2);
        		b.setGamerPlace(hands);
        	}
        	if(read.charAt(0)=='d'){
        		Card discarded = hands.get(i).discard(rankCardToDo);
        		hands.get(i).addCard(card);
        		graveyard.addCard(discarded);
        		b.setDeck(d);
        		b.setGamerPlace(hands);
        		b.setDiscard(graveyard);
        		discardOrPlayDone=1;
        	}
        	else { //si le premier caractere de read est p
        		Card toPlay = hands.get(i).selectCard(rankCardToDo);
        		int whereToPut=Integer.parseInt(String.valueOf(read.charAt(6)));
        		if (!(b.putCard(toPlay,whereToPut))) {
        			Token errors = b.getBankError();
        			errors.removeToken();
        			b.setBankError(errors);
        			graveyard.addCard(toPlay);
        			b.setDiscard(graveyard);
        			
        		}
        		else {
        			hands.get(i).discard(toPlay);
        		}
        		hands.get(i).addCard(card);
        		b.setGamerPlace(hands);
        		discardOrPlayDone=1;
        	}
        	if (discardOrPlayDone==1) { //on change de joueur si le joueur n°i a joué ou défaussé
	        	if (i==1)
	        		i=2;
	        	else
	        		i=1;
        	}
        }
        b.fireworksResult();
    }


}