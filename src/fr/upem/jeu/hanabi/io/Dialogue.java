package fr.upem.jeu.hanabi.io;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 	Class which permits to player to dialog witch each of the them through the system.
 * Contains also the memory of one player.
 *@author Axel DURAND
 *@version 1.0
 */
public class Dialogue {
    /**
     * Actual Memory of a gamer.
     */
    private StringBuilder shortMemoryGamer;
    
    /*
     * ajoute information donné par un joueur chaque info est séparé par un ;  et est concaténé dans un builder
     *On traite l'information sans le souci de l'action et du joueur à laquelle est destiné à l'intérieur de la méthode
     * */
  /**
   * Add information which are given by another player.
   * @param newInfo new information to memorize.  
   * */
  public void addMemory(String newInfo) {
    		this.shortMemoryGamer.append(newInfo.substring(6));
    		this.shortMemoryGamer.append(" /");	// char which separe each information
  }
  /**
   * Constructor of Dialogue.
   * */
  public Dialogue() {
	  this.shortMemoryGamer=new StringBuilder();
	  this.shortMemoryGamer.append("");
  }
  /**
   * Give to the player all informations he got from the others.
   * @return
   * The information in human speaking
   * */
  @Override
  public String toString() {
	  
	  String getDialogue=this.shortMemoryGamer.toString();
	  String newLine=System.lineSeparator();
	  StringBuilder toSay=new StringBuilder();
	  Pattern p = Pattern.compile("/");
	  String[] eachMessage= p.split(getDialogue);
	  String [] group;
	  for(String s : eachMessage) {
		  if(Dialogue.isInformationGroup(s)) {
			  System.out.println("IS A GROUP");
			  group=this.createGroup(s);
			 
			  for(String subgroup : group) {
				  System.out.println(subgroup); 
				  toSay.append(this.convertToSpeak(subgroup));
				  toSay.append(newLine);  
			  }
		  }
		  else {
			  System.out.println(s);
			  toSay.append(this.convertToSpeak(s));
			  toSay.append(newLine);  
		  }
		  
	  }
	  return toSay.toString();
  }
  	private String[] createGroup(String s) {
  		 Pattern p = Pattern.compile("; ");
  		 return p.split(s);
  	}
  	/**
  	 * Erase the @see shortMemoryGamer contents
  	 * */
    public void forget(){
    	System.out.println(this.shortMemoryGamer.toString());
    	if(this.shortMemoryGamer.toString()=="")return;
    	this.shortMemoryGamer.delete(0,this.shortMemoryGamer.toString().length());
    }
   
     /**
      * Identify if a group of information is correct
      * @param s which contain at least a group of information
      * @return true if we got a correct group
      */
    public static boolean isInformationGroupCorrect(String s) {
    	char correctGroup=s.charAt(8);
    	String comparante=s.substring(8);
    	Pattern pattern;
   	 	Matcher matcher;
   	 	System.out.println(correctGroup);
   	 	pattern = Pattern.compile("(([1-5]\\s["+correctGroup+"])(\\s[;]\\s[1-5]\\s["+correctGroup+"]){1,5})");//rajouter la fin du motif de répétition
        matcher = pattern.matcher(comparante);
    	return matcher.find();
    }
    /**
     * Identify if the information is grouped  
     * @param s String which contain at least one information
     * @return true if information is grouped . False if information is 'alone'.
     */
    public static boolean isInformationGroup(String s) {
    	Pattern pattern;
   	 	Matcher matcher;
   	 	pattern = Pattern.compile("([1-5]\\s[1-5WGYRB]\\s[;])");//rajouter la fin du motif de répétition
        matcher = pattern.matcher(s);
    	return matcher.find();
    }
    /**
     * Check if the color format in the parameter file is correct
     * @param format 
     * format which need to be checked.
     * @return true if the format is correct
     * */
    public static boolean isColorFormatCorrect(String format) {
    	Pattern pat;
		Matcher match;
		 pat = Pattern.compile("(\\w*-)+");
         match = pat.matcher(format);
        return match.find() ;
    }
    /**
     * Check if the family number of card format in the parameter file is correct
     * @param format 
     *format which need to be checked.
     * @return true if the format is correct
     * */
    public static boolean isFamilyNumberFormatCorrect(String format) {
    	Pattern pat;
		Matcher match;
		 pat = Pattern.compile("(([1-9])|([1-9](\\d+))=\\d-)+");
         match = pat.matcher(format);
        return match.find() ;
    }
    /**
     * Check if the action given by a player are wrote in the correct format.
     * @param format to check
     * @return true if the format is correct.
     */
    public static boolean isFormatCorrect(String format) {
        // TODO implement here
    	 Pattern pattern;
    	 Matcher matcher;
    	 Parameter param=Parameter.getInstance();
    	 int maxValue=param.getNumberOfValueAvailableByFamily().length;
    	 String[] forPattern=param.getColorFamily();
    	 String letterPattern="";
    	 for(String a : forPattern) {
    		 letterPattern+=a.charAt(0);
    	 }
    	 
    	 pattern = Pattern.compile("([aq])|([ps]\\s[:]\\s[1-"+maxValue+"]\\s[1-"+maxValue+"])|([d]\\s[:]\\s[1-"+maxValue+"])|([i]\\s[:]\\s[1-"+maxValue+"]\\s[1-"+maxValue+"]\\s[1-"+maxValue+letterPattern+"])");//rajouter la fin du motif de répétition
         matcher = pattern.matcher(format);
        return matcher.find() ;
    }
    /**
     * @param s the string containing the information
     * @return a string which format is :Card PlaceId value is Value or Color.
     */
    private String convertToSpeak(String s) {
    	if(s.length()==0)return "";
    	StringBuilder toSay=new StringBuilder(); 
    	toSay.append("Card ");
		 toSay.append(s.charAt(0));
		 toSay.append(" value is ");
		 //System.out.println(s);
		 Parameter param=Parameter.getInstance();
		 String[] color=param.getColorFamily();
		 /*for(String a : color) {
			 System.out.println(a);
		 }*/
		 for(int i=0;i<color.length;i++) {
			 if(color[i].charAt(0)==s.charAt(2)) {
				 System.out.println("les valeurs sont égales");
				 toSay.append(color[i]);
				 break;
			 }
			 else if(i==color.length-1) {
				 toSay.append(s.charAt(2));    
			 }
		 }
    	return toSay.toString();
    }

    /**
     * Print the tutorial of the game.
     */
    public static void printTutorial() {
    	System.out.println("In order to play to Hanabi with the command line,");
    	System.out.println("A mean for communicate with each player has been set by the developers");
    	System.out.println("This tutorial will show you the different command you can  use :");
    	System.out.println("-Give an intel = i : [PlayerID] [Place of CardIDToGiveIntel] [Value or Color] ; {Other cards which have SAME value or SAME color}");
    	System.out.println("WARNING : if you give more of one information and don't do it for ALL of the cards of a player. The game will precise it to this player.");
    	System.out.println("-Play a card = p : [Place of CardID] [Place of BoardColor]");
    	System.out.println("-Discard a card = d : [Place of CardID]");
    	System.out.println("-Swap two cards = s :  [PlaceOfCardID1] [PlaceOfCardID2]");
    	System.out.println("-Print the graveyard = a");
    	System.out.println("-Leave the game = q");
    	System.out.println("Each color has a code which represent him in the dialogue, this the first letter of his color. Example :");
    	System.out.println("W:White");
    	System.out.println("G:Green");
    	System.out.println("Y:Yellow");
    	System.out.println("R:Red");
    	System.out.println("B:Blue");
        return ;
    }
    /*
    //Main de test
    public static void main(String[] args) {
		String testCorrect="s : 1 5";
		String testFaux ="s : 1  5";
		Dialogue.printTutorial();
		StringBuilder sb=new StringBuilder();
		System.out.println(Dialogue.isFormatCorrect(testCorrect));
		System.out.println(Dialogue.isFormatCorrect(testFaux));
		System.out.println(sb.toString());
		String testGroupeCorrectVide="i : 1 2 G ;";
		String testGroupeCorrectRempli="i : 3 4 2 ; 3 3 ; 1 2";
		System.out.println(Dialogue.isInformationGroup(testGroupeCorrectVide));
		System.out.println(Dialogue.isInformationGroup(testGroupeCorrectRempli));
		System.out.println(Dialogue.isInformationGroupCorrect(testGroupeCorrectRempli));
		Dialogue d=new Dialogue();
		d.addMemory("i : 1 2 G ; 5 G ; 1 G ; 3 G");
		d.addMemory("i : 1 4 R ");
		d.addMemory("i : 1 3 2 ");
		d.addMemory("i : 1 5 4 ");
		System.out.println(d);
		d.forget();
		System.out.println("lal "+d);
		d.forget();
		String colorCheck="White-Blue-Red-Yellow-Green-Chrome";
		String FamilyChecking="1=3-2=2-3=2-4=2-5=1";
		System.out.println("t "+Dialogue.isColorFormatCorrect(colorCheck));
		System.out.println("u "+Dialogue.isFamilyNumberFormatCorrect(FamilyChecking));
    
    }
    */

}