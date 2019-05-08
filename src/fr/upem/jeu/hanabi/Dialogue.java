
import java.util.*;

/**
 * 
 */
public class Dialogue {

    /**
     * Default constructor
     */
    public Dialogue(int nbrJoueur) {
    }

    /**
     * 
     */
    private ArrayList<StringBuilder> shortMemoryGamer;


    /**
     * @param s 
     * @return
     */
    public boolean isFormatCorrect(String s) {
        // TODO implement here
        return false;
    }

    /**
     * @param gamer 
     * @return
     */
    public String sendDialogueToPlayer(int gamer) {
        // TODO implement here
        return "";
    }

    /**
     * @param builder 
     * @return
     */
    public String convertToSpeak(StringBuilder builder) {
        // TODO implement here
        return "";
    }

    /**
     * @param gamer 
     * @param dialogue 
     * @return
     */
    public void recordDialogue(int gamer, void dialogue) {
        // TODO implement here
        return null;
    }

    /**
     * @param s 
     * @param idPlayer
     */
    public void addInformation(String s, int idPlayer) {
        // TODO implement here
    }

    /**
     * Print the tutorial of the game.
     */
    public static void printTutorial() {
    	System.out.println("In order to play to Hanabi with the command line,");
    	System.out.println("A mean for communicate with each player has been set by the developers");
    	System.out.println("This tutorial will show you the different command you can  use :");
    	System.out.println("-Give an intel = i : [PlayerID], [Place of CardIDToGiveIntel] [Value or Color] ; {Other cards which have SAME value or SAME color}");
    	System.out.println("WARNING : if you give more of one information and don't do it for ALL of the cards of a player. The game will precise it to this player.");
    	System.out.println("-Play a card = p : [Place of CardID] [Place of BoardColor]");
    	System.out.println("-Discard a card = d : [Place of CardID]");
    	System.out.println("-Swap two cards = s :  [PlaceOfCardID1] [PlaceOfCardID2]");
    	System.out.println("Each color has a code which represent him in the dialogue :");
    	System.out.println("W:White");
    	System.out.println("G:Green");
    	System.out.println("Y:Yellow");
    	System.out.println("R:Red");
    	System.out.println("B:Blue");
        return ;
    }

}