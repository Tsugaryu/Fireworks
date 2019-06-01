package fr.upem.jeu.hanabi;

//[ipds]\s[:]\s[1-5]\s[1-5]\s[1-5WGYRB]
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 
 */
public class Dialogue {
    /**
     * 
     */
    private StringBuilder shortMemoryGamer;
    
    /*
     * ajoute information donné par un joueur chaque info est séparé par un ;  et est concaténé dans un builder
     *On traite l'information sans le souci de l'action et du joueur à laquelle est destiné à l'intérieur de la méthode
     * */
  public void addMemory(String newInfo) {
    		this.shortMemoryGamer.append(newInfo.substring(6));
    		this.shortMemoryGamer.append(" /");	
  }
  public Dialogue() {
	  this.shortMemoryGamer=new StringBuilder();
	  this.shortMemoryGamer.append("");
  }
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
    public void forget(){
    	System.out.println(this.shortMemoryGamer.toString());
    	if(this.shortMemoryGamer.toString()=="")return;
    	this.shortMemoryGamer.delete(0,this.shortMemoryGamer.toString().length());
    }
    /*
     * Vérifie qu'une information soit correctement groupé (qu on ne puisse pas dire card 1=blue card 2 =1 par exemple 
     * et qu'on oublie pas certain caractère*/
     /**
      * Identifie si un groupe est correct
      * @param s
      * @return true si est en fasse d'un groupe correct
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
     * Identifie si l'information est groupée, sinon cela sgnifie que nous avons affaire à une info unique 
     * @param s
     * @return
     */
    public static boolean isInformationGroup(String s) {
    	Pattern pattern;
   	 	Matcher matcher;
   	 	pattern = Pattern.compile("([1-5]\\s[1-5WGYRB]\\s[;])");//rajouter la fin du motif de répétition
        matcher = pattern.matcher(s);
    	return matcher.find();
    }
    /**
     * @param s 
     * @return
     */
    public static boolean isFormatCorrect(String s) {
        // TODO implement here
    	 Pattern pattern;
    	 Matcher matcher;
    	 pattern = Pattern.compile("([ps]\\s[:]\\s[1-5]\\s[1-5])|([d]\\s[:]\\s[1-5])|([i]\\s[:]\\s[1-5]\\s[1-5]\\s[1-5WGYRB])");//rajouter la fin du motif de répétition
         matcher = pattern.matcher(s);
        return matcher.find() ;
    }

    /**
     * @param gamer 
     * @return
     */
  //  public String sendDialogueToPlayer(int gamer) {
        // TODO implement here
    //    return "";
    //}

    /**
     * @param builder 
     * @return
     */
    private String convertToSpeak(String s) {
    	if(s.length()==0)return "";
    	StringBuilder toSay=new StringBuilder(); 
    	toSay.append("Card ");
		 toSay.append(s.charAt(0));
		 toSay.append(" value is ");
		 System.out.println(s);
    	switch(""+s.charAt(2)) {
    	 case "W":
             toSay.append("White");
             break;
           case "G":
             toSay.append("Green");
             break;
           case "Y":
             toSay.append("Yellow");
             break;
           case "R":
             toSay.append("Red");
             break;
           case "B":
             toSay.append("Blue");
             break;
           default:
        	   toSay.append(s.charAt(2));    
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
    	System.out.println("Each color has a code which represent him in the dialogue");
    	System.out.println("W:White");
    	System.out.println("G:Green");
    	System.out.println("Y:Yellow");
    	System.out.println("R:Red");
    	System.out.println("B:Blue");
        return ;
    }
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
    
    }

}