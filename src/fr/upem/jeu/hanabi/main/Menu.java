package fr.upem.jeu.hanabi.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.upem.jeu.hanabi.game.Board;
import fr.upem.jeu.hanabi.game.HandPlayer;
import fr.upem.jeu.hanabi.game.content.Card;
import fr.upem.jeu.hanabi.game.content.Token;
import fr.upem.jeu.hanabi.game.stack.Deck;
import fr.upem.jeu.hanabi.game.stack.Discard;
import fr.upem.jeu.hanabi.io.Dialogue;
import fr.upem.jeu.hanabi.io.Parameter;


/**
 * @author Raphael BOURJOT and Axel DURAND
 */
public class Menu {
	/**
     * Print the menu and let the player choose the mode of the game
     * @return the number of player of the game.
     */
	public static int menu() {
		Parameter param;
		int numberPlayer=0,selectMode=6;
		String read="";
		try{
	        InputStreamReader ise=new InputStreamReader(System.in);
	        BufferedReader be=new BufferedReader(ise);
	        System.out.println("Menu : For select Default parameter enter 1. For loading the parameter file enter 2. For changing it enter 3 ");
	        while(selectMode>3 ||selectMode<1) {
	        	 read=be.readLine();
	             if(!read.contains(" ")) {
	           	  selectMode=Integer.parseInt(read);	  
	             }
	        }
	        switch(selectMode) {
	        case 1 :param=Parameter.loadDefaultParameter();
	        	break;
	        case 2 :param=Parameter.readFileParameter();
	        	break;
	        case 3 :param=Parameter.modifyParameterByGame();
	        	break;
	        default:param=Parameter.loadDefaultParameter();
	        }
	        while(numberPlayer<2 || numberPlayer>param.getMaxPlayer()) {
	          System.out.println("How many players will playing ?");
	          read=be.readLine();
	          if(!read.contains(" ")) {
	        	  numberPlayer=Integer.parseInt(read);	  
	          }
	        
	        }
	        
	        
	      }catch(IOException e){
	        System.err.println("Reading line error,exiting the program");
	        return -1;
	      }
		return numberPlayer;
	}
	
	
	public static void playACard(Board board,Deck d,String read,Discard graveyard, ArrayList<HandPlayer> hands,int i, int rankCardToDo) {
		Card card,discarded;
		card=d.draw();
        discarded = hands.get(i).discard(rankCardToDo);
        int whereToPut=Integer.parseInt(String.valueOf(read.charAt(6)));;
          //erreur lors du placement de carte
        if (!(board.putCard(discarded,whereToPut))) {
          Token errors = board.getBankError();
          errors.removeToken();
          board.setBankError(errors);
          graveyard.addCard(discarded);
          board.setDiscard(graveyard);
        }
        hands.get(i).addCard(card);
        board.setGamerPlace(hands);
	}
	
	public static void giveAnIntel(Board board, String read, ArrayList<HandPlayer> hands, int i) {
		int getter;
		Dialogue dia;
		Token bank=board.getControlBank();
  	  	if(Dialogue.isInformationGroup(read)) {
  		 
  		  if(Dialogue.isInformationGroupCorrect(read)) {
  			 /*vérifier que du texte n'a pas été oublié*/
  			 getter=Integer.parseInt(""+read.charAt(4))-1;
  			 dia= hands.get(getter).getMemory();
       		 dia.addMemory(read);
       		 hands.get(getter).setMemory(dia);
       		 board.setGamerPlace(hands);
  		  }
  		  else {
  			   getter=Integer.parseInt(""+read.charAt(4))-1;
  			  String withoutGroup=read.substring(0, 8);
  			  dia= hands.get(getter).getMemory();
       		 dia.addMemory(withoutGroup);
       		 hands.get(getter).setMemory(dia);
       		 board.setGamerPlace(hands);
  			  
  		  }
  	  }
  	  else {
  		 getter=Integer.parseInt(""+read.charAt(4))-1;
  		 System.out.println(hands);
  		
  		 dia= hands.get(getter).getMemory();
  		 dia.addMemory(read);
  		 hands.get(getter).setMemory(dia);
  		 board.setGamerPlace(hands);
  	  }
  	  try {
  		 dia= hands.get(i).getMemory();
   		 dia.forget();
   		 hands.get(i).setMemory(dia);
   		 board.setGamerPlace(hands);
  		  //gérer les échanges de jeton
     		  bank=board.getControlBank();
  		  bank.removeToken();
      	  board.setControlBank(bank);  
      	  //donnez les informations
  	  }catch(IllegalArgumentException e) {
  		  System.out.println("Vous n'avez plus de jeton ! D�faussez ou jouez une carte.");
  		  i--;
  	  }
	}
	
	public static void discardACard(Board board,int i, int rankCardToDo, ArrayList<HandPlayer> hands, Discard graveyard,Deck d) {
		Token bank=board.getControlBank();
		Card card,discarded;
		card=d.draw();
        discarded = hands.get(i).discard(rankCardToDo);
        hands.get(i).addCard(card);
        graveyard.addCard(discarded);
        board.setDeck(d);
        board.setGamerPlace(hands);
        board.setDiscard(graveyard);
        //get one token for it
    	try {
       	  bank=board.getControlBank();
    	  bank.addToken();
          board.setControlBank(bank);  
          //donnez les informations
    	}catch(IllegalArgumentException e) {
    		System.out.println("Vous avez trop de jeton !donnez une information ou jouez une carte.");
    		i--;
    	}
	}
}
