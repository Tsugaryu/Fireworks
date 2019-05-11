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
        while(b.getBankError().getNumber()!=0 && !(b.getBoard()[0].getValue()==5 && b.getBoard()[1].getValue()==5 && 
        		b.getBoard()[2].getValue()==5 && b.getBoard()[3].getValue()==5 && b.getBoard()[4].getValue()==5)){
        	
        	System.out.println(b.getGamerPlace());
        	
        }
        
    }


}