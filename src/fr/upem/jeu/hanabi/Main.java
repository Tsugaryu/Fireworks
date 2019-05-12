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
        int i=0; // (i+1) correspond au numï¿½ro du joueur en train de jouer
        String read = new String ();
        Dialogue.printTutorial();
        while(!b.end()){
        	System.out.println(b);
        	read=" "; // on initialise read pour qu'on puisse passer dans la boucle suivante
        	System.out.println("Player " + (i+1));
        	while (!HandPlayer.choiceActionIsOk(read)) {
        		try {
        			read=HandPlayer.readActionPlayer();
        		}catch(IOException e) {
        			System.out.println("Fin du jeu probleme de lecture");
        			return ;
        		}
        	}
        	
        	
        	
        	if (read.charAt(0)=='q') {
        		System.out.println("Goodbye");
        		return ;
        	}
        	
        	
        	ArrayList<HandPlayer> hands=b.getGamerPlace();
        	int rankCardToDo;
        	if (read.charAt(0)=='s') {
        		rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
        		int rankCard2=Integer.parseInt(String.valueOf(read.charAt(6)));
        		hands.get(i).swap(rankCardToDo, rankCard2);
        		b.setGamerPlace(hands);
        	}
        	else if (read.charAt(0)=='a')
        		System.out.println(b.getDiscard());
        	else {
        		rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
        		Deck d=b.getDeck();
            	Card card=d.draw();
            	Discard graveyard=b.getDiscard();
	        	if(read.charAt(0)=='d'){
	        		Card discarded = hands.get(i).discard(rankCardToDo);
	        		hands.get(i).addCard(card);
	        		graveyard.addCard(discarded);
	        		b.setDeck(d);
	        		b.setGamerPlace(hands);
	        		b.setDiscard(graveyard);
	        	}
	        	else { //si le premier caractere de read est p
	        		Card toPlay = hands.get(i).discard(rankCardToDo);
	        		int whereToPut=Integer.parseInt(String.valueOf(read.charAt(6)));
	        		if (!(b.putCard(toPlay,whereToPut))) {
	        			Token errors = b.getBankError();
        			errors.removeToken();
	        			b.setBankError(errors);
	        			graveyard.addCard(toPlay);
	        			b.setDiscard(graveyard);
	        			
	        		}
	        		hands.get(i).addCard(card);
	        		b.setGamerPlace(hands);
	        	}
	        	if (i==0)
		        	i=1;
		        else
		        	i=0;
	        }
        }
        b.fireworksResult();
    }
}