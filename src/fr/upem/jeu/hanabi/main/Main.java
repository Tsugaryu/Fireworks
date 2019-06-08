package fr.upem.jeu.hanabi.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
public class Main {

	public static void clearScreen() {  
		System.out.print("\033\143");
	}  

    public static void main(String[] args) {
      int numberPlayer=0,selectMode=6;
      String read ="";
      Deck d;
      Card card;
      Discard graveyard;
      Card discarded;
      Token bank;
      Dialogue dia;
      Parameter param;
      ArrayList<HandPlayer> hands;
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
        return;
      }
      Board board=Board.createBoard(numberPlayer);
      hands=board.getGamerPlace();
        int i=0; // (i+1) correspond au numero du joueur en train de jouer
        Dialogue.printTutorial();
        //boucle de tour de joueur
        while(!board.end()){
          System.out.println(board);
          read=" "; // on initialise read pour qu'on puisse passer dans la boucle suivante
          System.out.println("It is Player " + (i+1)+" turn");
          System.out.println("Information :");
          System.out.println(hands.get(i).getMemory().toString());
          
          while (!Dialogue.isFormatCorrect(read)) {
            try {
              read=HandPlayer.readActionPlayer();
            }catch(IOException e) {
              System.out.println("Fin du jeu probleme de lecture");
              return ;
            }
          }
          System.out.println("String rentree "+read);
          if (read.charAt(0)=='q') {
            System.out.println("Goodbye");
            return ;
          }
          
          int rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));
          d=board.getDeck();
          graveyard=board.getDiscard();
          /*Déterminer l'action*/
          
          switch(read.charAt(0)) {
            case 's':
            int rankCardSecondAction=Integer.parseInt(String.valueOf(read.charAt(6)));
            hands.get(i).swap(rankCardToDo, rankCardSecondAction);
            board.setGamerPlace(hands);
            i--;
            break;
            case 'a':
            System.out.println(board.getDiscard());
            break;
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
        		  System.out.println("Vous n'avez plus de jeton ! défaussez ou jouez une carte.");
        		  i--;
        	  }
        	 
          break;
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