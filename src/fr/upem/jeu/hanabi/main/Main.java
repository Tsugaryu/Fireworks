package fr.upem.jeu.hanabi.main;


import java.io.IOException;
import java.util.*;

import fr.upem.jeu.hanabi.game.Board;
import fr.upem.jeu.hanabi.game.HandPlayer;
import fr.upem.jeu.hanabi.game.content.Card;
import fr.upem.jeu.hanabi.game.content.Token;
import fr.upem.jeu.hanabi.game.stack.Deck;
import fr.upem.jeu.hanabi.game.stack.Discard;
import fr.upem.jeu.hanabi.io.Dialogue;



/**
 * @author Raphael BOURJOT and Axel DURAND
 */
public class Main {

	public static void clearScreen() {  
		System.out.print("\033\143");
	}  

    public static void main(String[] args) {
      int numberPlayer=Menu.menu();
      String read ="";
      Deck d;
      Card card;
      Discard graveyard;
      Card discarded;
      Token bank;
      Dialogue dia;
      
      ArrayList<HandPlayer> hands;
      
      Board board=Board.createBoard(numberPlayer);
      hands=board.getGamerPlace();
      int i=0; // (i+1) correspond au numero du joueur en train de jouer
      Dialogue.printTutorial();
      //boucle de tour de joueur
      while(!board.end()){
    	  System.out.println(board);
    	  read=" "; // on initialise read pour qu'on puisse passer dans la boucle suivante
    	  System.out.println("It is Player " + (i+1)+" turn");
    	  System.out.println("Action :");
    	  System.out.println(hands.get(i).getMemory().toString());

    	  while (!Dialogue.isFormatCorrect(read)) {
    		  try {
    			  read=HandPlayer.readActionPlayer();
    		  }catch(IOException e) {
    			  System.out.println("Fin du jeu probleme de lecture");
    			  return ;
    		  }
          }
          if (read.charAt(0)=='q') { /*On ne peut pas mettre les cas 'q' et 'a' dans le switch car
           							read n'est donc que de taille 1, or on fait rankCardToDo=...read.charAt(4), ce qui ferait donc planter le programme*/
        	  System.out.println("Goodbye");
        	  board.fireworksResult();
        	  return ;
          }
          else if (read.charAt(0)=='a') {	
        	  System.out.println(board.getDiscard());
        	  i--; //Cela permettra au joueur qui a demandÈ ‡ afficher la dÈfausse de pouvoir jouer son tour par la suite.
          }
          else {
	          int rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
	          d=board.getDeck();
	          graveyard=board.getDiscard();
	          /*D√©terminer l'action*/
	          switch(read.charAt(0)) {
	          	case 's':
	            int rankCardSecondAction=Integer.parseInt(String.valueOf(read.charAt(6)));
	            hands.get(i).swap(rankCardToDo, rankCardSecondAction);
	            board.setGamerPlace(hands);
	            i--;
	            break;
	            
	            case 'q':
	            System.out.println("Goodbye");
	            
	            return ;
	            case 'd':
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
	            
	            break;
	            case 'p':
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
	            break;
	            case 'i':
	        	  //dit l'info a quelqu'un l'enregistrer dans son systeme de dialogue
	        	  /*
	        	   * pour cela, regarder l'id du =dit joueur 
	        	   * */
	        	  int getter;
	        	  if(Dialogue.isInformationGroup(read)) {
	        		 
	        		  if(Dialogue.isInformationGroupCorrect(read)) {
	        			 /*v√©rifier que du texte n'a pas √©t√© oubli√©*/
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
	        		  //g√©rer les √©changes de jeton
	           		  bank=board.getControlBank();
	        		  bank.removeToken();
	            	  board.setControlBank(bank);  
	            	  //donnez les informations
	        	  }catch(IllegalArgumentException e) {
	        		  System.out.println("Vous n'avez plus de jeton ! DÈfaussez ou jouez une carte.");
	        		  i--;
	        	  }
	        	 
	          break;
	        }
        }
        /*Changement de tour*/
          if(i==board.getGamerPlace().size()-1) {
        	  i=0;
          }
          else
        	  i++;
          board.setTurn(i);
       
        
      }
        board.fireworksResult();
    }
  }