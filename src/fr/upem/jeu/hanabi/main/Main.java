package fr.upem.jeu.hanabi.main;


import java.io.IOException;
import java.util.*;

import fr.upem.jeu.hanabi.game.Board;
import fr.upem.jeu.hanabi.game.HandPlayer;
import fr.upem.jeu.hanabi.game.stack.Deck;
import fr.upem.jeu.hanabi.game.stack.Discard;
import fr.upem.jeu.hanabi.io.Dialogue;



/**
 * @author Raphael BOURJOT and Axel DURAND
 */
public class Main {

	
	
    public static void main(String[] args) {
      int numberPlayer=Menu.menu();
      String read ="";
      Deck d;
      Discard graveyard;
      
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
        	  i--; //Cela permettra au joueur qui a demandé à afficher la défausse de pouvoir jouer son tour par la suite.
          }
          else {
	          int rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
	          d=board.getDeck();
	          graveyard=board.getDiscard();
	          /*DÃ©terminer l'action*/
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
	            Menu.discardACard(board, i, rankCardToDo, hands, graveyard, d);
	            
	            break;
	            case 'p':
	            Menu.playACard(board, d, read, graveyard, hands, i, rankCardToDo);
	            break;
	            case 'i':
	        	  //dit l'info a quelqu'un l'enregistrer dans son systeme de dialogue
	        	  /*
	        	   * pour cela, regarder l'id du dit joueur 
	        	   * */
	        	  Menu.giveAnIntel(board, read, hands, i);
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