package fr.upem.jeu.hanabi.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.upem.jeu.hanabi.io.Parameter;

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
}
