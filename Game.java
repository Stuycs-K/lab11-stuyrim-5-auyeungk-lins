import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  /*Base colors*/
  public static final int BLACK = 30;
  public static final int RED = 31;
  public static final int GREEN = 32;
  public static final int YELLOW = 33;
  public static final int BLUE = 34;
  public static final int MAGENTA = 35;
  public static final int CYAN = 36;
  public static final int WHITE = 37;

  /*Text modifiers to be ADDED to a color*/
  public static final int BACKGROUND = 10;
  public static final int BRIGHT = 60;

  /*Text modifiers that are separate from color*/
  public static final int BOLD = 1;
  public static final int UNDERLINE = 4;
  public static final int INVERTED = 7;

  /*Reset colors*/
  public static void reset(){
    System.out.print("\u001b[0m");
  }


  public static void hideCursor(){
    System.out.print("\u001b[?25l");
  }

  public static void showCursor(){
    System.out.print("\u001b[?25h");
  }

  /*Move the cursor to a specified row/col on the terminal*/
  public static void go(int row,int col){
    System.out.print("\u001b[" + row + ";" + col + "f");
  }

  /*Erases all text on the terminal.*/
  public static void clear(){
    System.out.print("\u001b[2J");
  }

  /*Overloaded Colorize methods.
  c1,c2 and c3 are any color modifiers such as bold/color/background color etc.
  */
  public static String colorize(String text,int c1){
    return ("\u001b[" + c1 + "m"+text+"\u001b[0m");
  }
  public static String colorize(String text,int c1,int c2){
    return ("\u001b[" + c1 + ";" + c2 + "m"+text+"\u001b[0m");
  }
  public static String colorize(String text,int c1,int c2,int c3){
    return ("\u001b[" + c1 + ";" + c2 + ";" + c3 + "m"+text+"\u001b[0m");
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		go(1,1);
		for (int i = 0; i < 81; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(0, i+1);
		}

		go(2,1);
		for (int i = 0; i < 31; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(i+1, 0);
		}

		go(2,80);
		for (int i = 0; i < 31; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(i+1, 80);
		}

		go(30,1);
		for (int i = 0; i < 81; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(30, i+1);
		}
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    go(startRow, startCol);
		System.out.println(colorize(s, WHITE));
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		int textIndex = 0;
		int textLength = text.length();
		for (int i = row; i < height+row && textIndex < textLength; i++){
			for (int j = col; j < width+col; j ++){
        if(textIndex != textLength){
				drawText(Character.toString(text.charAt(textIndex)), i, j+1);
				textIndex++;
      }
			}
		}
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
			int roll = (int)(Math.random()*3);
			if (roll == 0){
				return new Charizard();
			}
			if (roll == 1){
				return new Venusaur();
			}
			return new Blastoise();
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    	String status = "";
      if(party.size() == 1){
        TextBox(startRow,6,22,78, party.get(0).toString() + " - " + party.get(0).getType());
        TextBox(startRow+1,6,22,78, "HP: " + colorByPercent(party.get(0).getHP(), party.get(0).getmaxHP()));
        TextBox(startRow+2,6,22,78, "PP: " + colorByPercent(party.get(0).getSpecial(), party.get(0).getSpecialMax()));
        if(party.get(0).getPoisonStatus() == true){
          status+="PSN ";
        }
        if(party.get(0).getSleepStatus() == true){
          status+="SLP ";
        }
        if(party.get(0).getBurnStatus() == true){
          status+="BRN ";
        }
        TextBox(startRow+3,6,22,78, status);
      }
      if(party.size() == 2){
        TextBox(startRow,3,18,78, party.get(0).toString() + " - " + party.get(0).getType());
        TextBox(startRow+1,3,18,78, "HP: " + colorByPercent(party.get(0).getHP(), party.get(0).getmaxHP()));
        TextBox(startRow+2,3,18,78, "PP: " + colorByPercent(party.get(0).getSpecial(), party.get(0).getSpecialMax()));
        if(party.get(0).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(0).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(0).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,3,18,78, status);
        status = "";

        TextBox(startRow,16,12,78, party.get(1).toString() + " - " + party.get(1).getType());
        TextBox(startRow+1,16,12,78, "HP: " + colorByPercent(party.get(1).getHP(), party.get(1).getmaxHP()));
        TextBox(startRow+2,16,12,78, "PP: " + colorByPercent(party.get(1).getSpecial(), party.get(1).getSpecialMax()));
        if(party.get(1).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(1).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(1).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,16,12,78, status);
        status = "";
      }
      if(party.size() == 3){
        TextBox(startRow,2,18,78, party.get(0).toString() + " - " + party.get(0).getType());
        TextBox(startRow+1,2,18,78, "HP: " + colorByPercent(party.get(0).getHP(), party.get(0).getmaxHP()));
        TextBox(startRow+2,2,18,78, "PP: " + colorByPercent(party.get(0).getSpecial(), party.get(0).getSpecialMax()));
        if(party.get(0).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(0).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(0).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,2,18,78, status);
        status = "";

        TextBox(startRow,15,18,78, party.get(1).toString() + " - " + party.get(1).getType());
        TextBox(startRow+1,15,18,78, "HP: " + colorByPercent(party.get(1).getHP(), party.get(1).getmaxHP()));
        TextBox(startRow+2,15,18,78, "PP: " + colorByPercent(party.get(1).getSpecial(), party.get(1).getSpecialMax()));
        status = "";
        if(party.get(1).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(1).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(1).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,15,18,78, status);
        status = "";
        
        TextBox(startRow,22,18,78, party.get(2).toString() + " - " + party.get(2).getType());
        TextBox(startRow+1,22,18,78, "HP: " + colorByPercent(party.get(2).getHP(), party.get(2).getmaxHP()));
        TextBox(startRow+2,22,18,78, "PP: " + colorByPercent(party.get(2).getSpecial(), party.get(2).getSpecialMax()));
        status = "";
        if(party.get(2).getPoisonStatus() == true){
          status += "PSN ";
        }
        if(party.get(2).getSleepStatus() == true){
          status += "SLP ";
        }
        if(party.get(2).getBurnStatus() == true){
          status+="BRN ";
        }
        TextBox(startRow+3,22,18,78, status);
        status = "";
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    if((double)(hp/maxHP) < 0.25) {
    	colorize(output, RED);
    }
    // under 75% : yellow
    if((double)(hp/maxHP) < 0.75) {
    	colorize(output, YELLOW);
    }
    // otherwise : white
    return output;
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(){

    drawBackground();
		//TextBox(2, 4, 10, 10, "This is a very very long text!");

    //draw player party

    //draw enemy party

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location

      //show cursor

      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    enemies.add(createRandomAdventurer());
		enemies.add(createRandomAdventurer());
		enemies.add(createRandomAdventurer());
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    party.add(createRandomAdventurer());
		party.add(createRandomAdventurer());
		party.add(createRandomAdventurer());
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen();//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      drawParty(enemies, 2);
      drawParty(party, 6);
      //TextBox(2,2,28,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      String partyType = party.get(whichPlayer).getType();
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.startsWith("attack ") || input.startsWith("a ")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (input.endsWith("1")){
            party.get(whichPlayer).attack(enemies.get(0));
          }
          if (input.endsWith("2")){
            party.get(whichPlayer).attack(enemies.get(1));
          }
          if (input.endsWith("3")){
            party.get(whichPlayer).attack(enemies.get(2));
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special ") || input.startsWith("sp" )){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (partyType.equals("Fire") || partyType.equals("Grass")){
            if (input.endsWith("1")){
              party.get(whichPlayer).specialAttack(enemies.get(0));
            }
            if (input.endsWith("2")){
              party.get(whichPlayer).specialAttack(enemies.get(1));
            }
            if (input.endsWith("3")){
              party.get(whichPlayer).specialAttack(enemies.get(2));
            }
          }
          else{
            if (input.endsWith("1")){
              party.get(whichPlayer).specialAttack(party.get(0));
            }
            if (input.endsWith("2")){
              party.get(whichPlayer).specialAttack(party.get(1));
            }
            if (input.endsWith("3")){
              party.get(whichPlayer).specialAttack(party.get(2));
            }
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("support1 ") || input.startsWith("su1 ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (partyType.equals("Fire") || partyType.equals("Grass")){
            if (input.endsWith("1")){
              party.get(whichPlayer).support(enemies.get(0));
            }
            if (input.endsWith("2")){
              party.get(whichPlayer).support(enemies.get(1));
            }
            if (input.endsWith("3")){
              party.get(whichPlayer).support(enemies.get(2));
            }
          }
          else{
            if (input.endsWith("1")){
              party.get(whichPlayer).support(party.get(0));
            }
            if (input.endsWith("2")){
              party.get(whichPlayer).support(party.get(1));
            }
            if (input.endsWith("3")){
              party.get(whichPlayer).support(party.get(2));
            }
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("support2 ") || input.startsWith("su2 ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          party.get(whichPlayer).support();
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        int rollPerson = (int)(Math.random()*3);
        int rollAttack = (int)(Math.random()*4);
        String enemyType = enemies.get(whichOpponent).getType();
        if (rollAttack == 0){
          enemies.get(whichOpponent).attack(party.get(rollPerson));
        }
        if (rollAttack == 1){
          if (enemyType.equals("Fire") || enemyType.equals("Grass")){
            enemies.get(whichOpponent).specialAttack(party.get(rollPerson));
          }
          else{
            enemies.get(whichOpponent).specialAttack(enemies.get(rollPerson));
          }
        }
        if (rollAttack == 2){
          if (enemyType.equals("Fire") || enemyType.equals("Grass")){
            enemies.get(whichOpponent).support(party.get(rollPerson));
          }
          else{
            enemies.get(whichOpponent).support(enemies.get(rollPerson));
          }
        }
        if (rollAttack == 3){
          enemies.get(whichOpponent).support();
        }
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen();


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
