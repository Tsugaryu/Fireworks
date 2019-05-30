package fr.upem.jeu.hanabi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
      int numberPlayer=0;
      String read ="";
      Deck d;
      Card card;
      Discard graveyard;
      Card discarded;
      try{
        InputStreamReader ise=new InputStreamReader(System.in);
        BufferedReader be=new BufferedReader(ise);
        while(numberPlayer==0 || numberPlayer>5) {
          System.out.println("How many players will playing ?");
          read=be.readLine();
          numberPlayer=Integer.parseInt(read);
        }
        
        
      }catch(IOException e){
        System.err.println("Reading line error,exiting the program");
        return;
      }
      Board board=Board.createBoard(numberPlayer);
        int i=0; // (i+1) correspond au numero du joueur en train de jouer
        Dialogue.printTutorial();
        //boucle de tour de joueur
        while(!board.end()){
          System.out.println(board);
          read=" "; // on initialise read pour qu'on puisse passer dans la boucle suivante
          System.out.println("It is Player " + (i+1)+" turn");
          while (!Dialogue.isFormatCorrect(read)) {
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
          //on récupère les mains de chaque joueur 
          ArrayList<HandPlayer> hands=board.getGamerPlace();
          int rankCardToDo=Integer.parseInt(String.valueOf(read.charAt(4)));;
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
          /*  case 'i':;
          break;*/
        }
         
        /*Changement de tour*/
          if(i==board.getGamerPlace().size()-1) {
        	  i=0;
          }
          else
        	  i++;
        
      }
        board.fireworksResult();
    }
  }