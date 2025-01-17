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

	//erase (black background)
	public static void erase(int r, int c, int size){
	  go(r,c);
	  System.out.print("\033[0m");
	  for(int i = 0; i < size;i++){
	    System.out.print(" ");
	  }
	}

  /*Overloaded Colorize methods.
  c1,c2 and c3 are any color modifiers such as bold/color/background color etc.
  */
  public static String colorize(String text){
    return ("\u001b[m" +text+"\u001b[0m");
  }
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

    go(8,1);
		for (int i = 0; i < 81; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(8, i+1);
		}

    go(19,1);
		for (int i = 0; i < 81; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(19, i+1);
		}

    go(26,1);
		for (int i = 0; i < 81; i++){
			System.out.print(colorize(" ", WHITE+BACKGROUND));
			go(26, i+1);
		}
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    go(startRow, startCol);
		System.out.println(colorize(s));
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
	  /*
	  if(text.length() != 0) {
		  Scanner in = new Scanner(text);
		  String word = in.next();
		  String line = "";
			int textSize = col + width;
			int textLine = row;
			while(in.hasNext()) {
				if(line.length() + word.length() <= width) {
					line+=word;
					word = in.next();
				}else {
					drawText(line, textLine, col);
					line = "";
					textLine++;
				}
			}
	  }
		*/
	  if(text != null) {
		int textIndex =0;
		int textLength = text.length();
		 for (int i = row; i < height+row && textIndex < textLength; i++){
			for (int j = col; j < width+col; j ++){
		        if(textIndex < textLength){
		        	if(textIndex > textLength-3) {
			          if(j == width+col - 2 && text.charAt(textIndex) != ' '){
			              drawText(Character.toString(text.charAt(textIndex)), i, j+1);
			              drawText(Character.toString('-'), i, j+2);
			              j++;
			              textIndex++;
			            }else {
			  						drawText(Character.toString(text.charAt(textIndex)), i, j+1);
			  						textIndex++;
			            }
			        }else {
			        	if(j == width+col - 2 && text.charAt(textIndex) != ' ' && text.charAt(textIndex+1) != ' '){
				              drawText(Character.toString(text.charAt(textIndex)), i, j+1);
				              drawText(Character.toString('-'), i, j+2);
				              j++;
				              textIndex++;
				            }else {
				  						drawText(Character.toString(text.charAt(textIndex)), i, j+1);
				  						textIndex++;
				            }
			        	
			        }
	        	}
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
        TextBox(startRow,30,20,78, party.get(0).toString() + " - " + party.get(0).getType());
        erase(startRow+1, 2, 78);erase(startRow+2, 2, 78);
        TextBox(startRow+1,30,20,78, "HP: ");
        TextBox(startRow+1,34,20,78, colorByPercent(party.get(0).getHP(), party.get(0).getmaxHP()));
        TextBox(startRow+2,30,20,78, "PP: ");
        TextBox(startRow+2,34,20,78, colorByPercent(party.get(0).getSpecial(), party.get(0).getSpecialMax()));
        if(party.get(0).getPoisonStatus() == true){
          status+="PSN ";
        }
        if(party.get(0).getSleepStatus() == true){
          status+="SLP ";
        }
        if(party.get(0).getBurnStatus() == true){
          status+="BRN ";
        }
        TextBox(startRow+3,30,20,78, status);
        status = "";
      }
      if(party.size() == 2){
        TextBox(startRow,15,20,78, party.get(0).toString() + " - " + party.get(0).getType());
        erase(startRow+1, 2, 78);
        erase(startRow+2, 2, 78);
        TextBox(startRow+1,15,20,78, "HP: ");
        TextBox(startRow+1,19,20,78, colorByPercent(party.get(0).getHP(), party.get(0).getmaxHP()));
        TextBox(startRow+2,15,20,78, "PP: ");
        TextBox(startRow+2,19,20,78, colorByPercent(party.get(0).getSpecial(), party.get(0).getSpecialMax()));
        if(party.get(0).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(0).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(0).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,15,20,78, status);
        status = "";

        TextBox(startRow,45,20,78, party.get(1).toString() + " - " + party.get(1).getType());
        TextBox(startRow+1,45,20,78, "HP: ");
        TextBox(startRow+1,49,20,78, colorByPercent(party.get(1).getHP(), party.get(1).getmaxHP()));
        TextBox(startRow+2,45,20,78, "PP: ");
        TextBox(startRow+2,49,20,78,colorByPercent(party.get(1).getSpecial(), party.get(1).getSpecialMax()));
        if(party.get(1).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(1).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(1).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,45,20,78, status);
        status = "";
      }
      if(party.size() == 3){
        TextBox(startRow,2,20,78, party.get(0).toString() + " - " + party.get(0).getType());
        erase(startRow+1, 2, 78);
        erase(startRow+2, 2, 78);
        TextBox(startRow+1,2,20,78, "HP: ");
        TextBox(startRow+1,6,20,78, colorByPercent(party.get(0).getHP(), party.get(0).getmaxHP()));
        TextBox(startRow+2,2,20,78, "PP: ");
        TextBox(startRow+2,6,20,78, colorByPercent(party.get(0).getSpecial(), party.get(0).getSpecialMax()));
        if(party.get(0).getPoisonStatus() == true){
          status +="PSN ";
        }
        if(party.get(0).getSleepStatus() == true){
          status +="SLP ";
        }
        if(party.get(0).getBurnStatus() == true){
          status +="BRN ";
        }
        TextBox(startRow+3,2,20,78, status);
        status = "";

        TextBox(startRow,28,20,78, party.get(1).toString() + " - " + party.get(1).getType());
        TextBox(startRow+1,28,20,78, "HP: " );
        TextBox(startRow+1,32,20,78, colorByPercent(party.get(1).getHP(), party.get(1).getmaxHP()));
        TextBox(startRow+2,28,20,78, "PP: ");
        TextBox(startRow+2,32,20,78, colorByPercent(party.get(1).getSpecial(), party.get(1).getSpecialMax()));
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
        TextBox(startRow+3,28,20,78, status);
        status = "";

        TextBox(startRow,55,20,78, party.get(2).toString() + " - " + party.get(2).getType());
        TextBox(startRow+1,55,20,78, "HP: ");
        TextBox(startRow+1,59,20,78,colorByPercent(party.get(2).getHP(), party.get(2).getmaxHP()));
        TextBox(startRow+2,55,20,78, "PP: ");
        TextBox(startRow+2,59,20,78, colorByPercent(party.get(2).getSpecial(), party.get(2).getSpecialMax()));
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
        TextBox(startRow+3,55,20,78, status);
        status = "";
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = hp+"/"+maxHP;
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


		go(28,3);

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      go(28,3);

      //show cursor
      showCursor();

      String input = in.nextLine();

      //clear the text that was written
      erase(27, 3, 60);
			erase(28, 3, 60);

      return input;
  }
  
  public static void setRelations(Adventurer main, ArrayList<Adventurer> enemy, ArrayList<Adventurer> friend) {
	  for(int i = 0; i < enemy.size(); i++) {
		  main.addFoe(enemy.get(i));
		  
	  }
	  for(int i = 0; i < friend.size(); i++) {
		  
		  main.addFriend((friend.get(i)));
	  }
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
    int enemySize = (int)(Math.random() * 3);
    	if(enemySize == 2) {
    		 enemies.add(createRandomAdventurer());
    		enemies.add(createRandomAdventurer());
    		enemies.add(createRandomAdventurer());
    	}
    	if(enemySize == 1) {
    		 enemies.add(createRandomAdventurer());
    		enemies.add(createRandomAdventurer());
    	}
    	if(enemySize == 0) {
    		 enemies.add(new Boss());
   
    }
   
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    party.add(createRandomAdventurer());
		party.add(createRandomAdventurer());
		party.add(createRandomAdventurer());
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
		
		for(int i = 0; i < enemies.size(); i++) {
			setRelations(enemies.get(i), party, enemies);
		}
		for(int i = 0; i < party.size(); i++) {
			setRelations(party.get(i), enemies, party);
		}

    boolean partyTurn = true;
		boolean wait = false;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen();//initial state.
		drawParty(enemies, 3);
		drawParty(party, 21);

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support1/support2/quit";
		TextBox(27, 2, 80, 1, preprompt);


    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
			drawParty(enemies, 3);
			drawParty(party, 21);
      input = userInput(in);

      TextBox(29, 2, 80, 1, "Turn: " + (whichPlayer) + (whichOpponent) + partyTurn);

      //example debug statment
      /*party.get(0).setSleepStatus(true);
      party.get(0).setPoisonStatus(true);
      party.get(1).setBurnStatus(true);
      party.get(2).setPoisonStatus(true);
      enemies.get(0).setSleepStatus(true);
      enemies.get(1).setSleepStatus(true);
      enemies.get(1).setPoisonStatus(true);
      enemies.get(1).setBurnStatus(true);
      party.get(0).applyDamage(20);
      party.get(1).applyDamage(1);
      party.get(2).applyDamage(10);*/




      //TextBox(2,2,28,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input

      if(partyTurn){
				String partyType = party.get(whichPlayer).getType();

        //Process user input for the last Adventurer:
        if(party.get(whichPlayer).getHP() > 0 && input.startsWith("attack ") || input.startsWith("a ")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (input.endsWith("1")){
            erase(9,2, 40);
            erase(10,2,40);
						erase(11,2,40);
            TextBox(9, 2, 37, 20, party.get(whichPlayer).attack(enemies.get(0)));
          }
          if (input.endsWith("2")){
            erase(9,2, 40);
            erase(10,2,40);
						erase(11,2,40);
            TextBox(9, 2, 37, 20,party.get(whichPlayer).attack(enemies.get(1)));
          }
          if (input.endsWith("3")){
            erase(9,2, 40);
            erase(10,2,40);
						erase(11,2,40);
            TextBox(9, 2, 37, 20,party.get(whichPlayer).attack(enemies.get(2)));
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(party.get(whichPlayer).getHP() > 0 &&  input.startsWith("special ") || input.startsWith("sp" )){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (partyType.equals("Fire") || partyType.equals("Grass")){
            if (input.endsWith("1")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).specialAttack(enemies.get(0)));
            }
            if (input.endsWith("2")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).specialAttack(enemies.get(1)));
            }
            if (input.endsWith("3")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).specialAttack(enemies.get(2)));
            }
          }
          else{
            if (input.endsWith("1")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).specialAttack(party.get(0)));
            }
            if (input.endsWith("2")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).specialAttack(party.get(1)));
            }
            if (input.endsWith("3")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20,party.get(whichPlayer).specialAttack(party.get(2)));
            }
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(party.get(whichPlayer).getHP() > 0 && input.startsWith("support1 ") || input.startsWith("su1 ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          if (partyType.equals("Fire") || partyType.equals("Grass")){
            if (input.endsWith("1")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).support(enemies.get(0)));
            }
            if (input.endsWith("2")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).support(enemies.get(1)));
            }
            if (input.endsWith("3")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).support(enemies.get(2)));
            }
          }
          else{
            if (input.endsWith("1")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).support(party.get(0)));
            }
            if (input.endsWith("2")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).support(party.get(1)));
            }
            if (input.endsWith("3")){
	            erase(9,2, 40);
	            erase(10,2,40);
							erase(11,2,40);
              TextBox(9, 2, 37, 20, party.get(whichPlayer).support(party.get(2)));
            }
          }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(party.get(whichPlayer).getHP() > 0 &&  input.equals("support2") || input.startsWith("su2")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          erase(9,2, 40);
          erase(10,2,40);
					erase(11,2,40);
          TextBox(9, 2, 37, 20, party.get(whichPlayer).support());
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        if(input.equals("quit")) {
      	  quit();
        }
				whichPlayer++;



        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:

          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support1/support2/quit";
					TextBox(27, 2, 80, 1, prompt);

        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
					erase(27,2, 70);
          String prompt = "Press enter to see monster's turn";
					TextBox(27, 2, 80, 1, prompt);

          partyTurn = false;
					whichOpponent = 0;
					whichPlayer = 0;

        }
				if (input.equals("")){
					wait = true;
				}
        //done with one party member
      }
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      if (!partyTurn){
        if (input.equals("") && whichOpponent < enemies.size()){
          int rollPerson = (int)(Math.random()*enemies.size());
          int rollAttack = (int)(Math.random()*4);
          String enemyType = enemies.get(whichOpponent).getType();

          if (rollAttack == 0){
	          erase(9, 41, 40);
	          erase(10, 41, 40);
						erase(11, 41, 40);
            TextBox(9, 41, 37, 20, "Enemy " + enemies.get(whichOpponent).attack(party.get(rollPerson)));
          }
          if (enemies.get(whichOpponent).getHP() > 0 &&  rollAttack == 1){
	          erase(9, 41, 40);
	          erase(10, 41, 40);
						erase(11, 41, 40);
            if (enemyType.equals("Fire") || enemyType.equals("Grass")){
              TextBox(9, 41, 37, 20, "Enemy " +enemies.get(whichOpponent).specialAttack(party.get(rollPerson)));
            }
            else{
		          erase(9, 41, 40);
		          erase(10, 41, 40);
							erase(11, 41, 40);
              TextBox(9, 41, 37, 20, "Enemy " +enemies.get(whichOpponent).specialAttack(enemies.get(rollPerson)));
            }
          }
          if (enemies.get(whichOpponent).getHP() > 0 && rollAttack == 2){
            if (enemyType.equals("Fire") || enemyType.equals("Grass")){
		          erase(9, 41, 40);
		          erase(10, 41, 40);
							erase(11, 41, 40);
              TextBox(9, 41, 37, 20, "Enemy " + enemies.get(whichOpponent).support(party.get(rollPerson)));
            }
            else{
		          erase(9, 41, 40);
		          erase(10, 41, 40);
							erase(11, 41, 40);
              TextBox(9, 41, 37, 20, "Enemy " + enemies.get(whichOpponent).support(enemies.get(rollPerson)));
            }
          }
          if (enemies.get(whichOpponent).getHP() > 0 && rollAttack == 3){
	          erase(9, 41, 40);
	          erase(10, 41, 40);
						erase(11, 41, 40);
            TextBox(9, 41, 37, 20, "Enemy " + enemies.get(whichOpponent).support());
          }
					whichOpponent++;

					if(whichOpponent < enemies.size()){
	          String prompt = "Press enter to see monster's turn";
						TextBox(27, 2, 80, 1, prompt);
					}
					else{
						partyTurn = true;
						whichPlayer = 0;
						
						int start = 9;
						for(int i = 0; i < enemies.size(); i++) {
							 if (input.equals("")){
								 if(enemies.get(i).getBurnStatus()) {
			 	          erase(start, 41, 40);
			 	          erase(start+1, 41, 40);
			 						
									TextBox(start, 41, 37, 20, "Enemy " + enemies.get(i).applyBurn());
									start+=2;
								 }
							 }
							 if (input.equals("")){
								 if(enemies.get(i).getPoisonStatus()) {
 			 	          erase(start, 41, 40);
 			 	          erase(start+1, 41, 40);
 			 						
									 TextBox(start, 41, 37, 20, "Enemy " + enemies.get(i).applyPoison());
									 start+=2;
								 }
							 }
							 if (input.equals("")){
								 if(enemies.get(i).setSeededStatus()) {
 			 	          erase(start, 41, 40);
 			 	          erase(start+1, 41, 40);
 			 						
									 TextBox(start, 41, 37, 20, "Enemy " + enemies.get(i).applySeed());
									 start = 9;
								 }
							 }
						}
						
						for(int i = 0; i < party.size(); i++) {
							 if (input.equals("")){
								 if(party.get(i).getBurnStatus()) {
									 erase(start, 2, 40);
						 	          erase(start+1, 2, 40);
									 TextBox(9, 2, 37, 20, party.get(i).applyBurn());
									 start+=2;
								 }
							 }
							 if (input.equals("")){ 
								 if(party.get(i).getBurnStatus()) {
									 erase(start, 2, 40);
						 	          erase(start+1, 2, 40);
									 TextBox(9, 2, 37, 20, party.get(i).applyPoison());
									 start+=2;
								 }
							 }
							 if (input.equals("")){
								 if(party.get(i).getBurnStatus()) {
									 erase(start, 41, 40);
						 	          erase(start+1, 41, 40);
									 TextBox(9, 2, 37, 20, party.get(i).applySeed());
									 start = 9;
								 }
									 
								 
							 }
						}
						
						int totalhp = 0;
						for(int i = 0;  i < enemies.size(); i++) {
							totalhp+=enemies.get(i).getHP();
						}
						if(totalhp == 0) {
							erase(9, 41, 40);
					        erase(10, 41, 40);
							erase(9,2, 40);
				            erase(10,2,40);
				            TextBox(27, 2, 80, 1, "Party has won!");
				            if (input.equals("")){
				            	quit();
				            }else {
				            	quit();
				            }
						}
						totalhp = 0;
						for(int i = 0;  i < party.size(); i++) {
							totalhp+=party.get(i).getHP();
						}
						if(totalhp == 0) {
							erase(9, 41, 40);
					        erase(10, 41, 40);
							erase(9,2, 40);
				            erase(10,2,40);
				            TextBox(27, 2, 80, 1, "Enemies have won!");
				            if (input.equals("")){
				            	quit();
				            }else {
				            	quit();
				            }
						}
						totalhp = 0;
						
						
						
	          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support1/support2/quit";
						TextBox(27, 2, 80, 1, prompt);
					}
					/*else {
            enemies.get(whichOpponent).support(party.get(rollPerson));
					}*/

				}
      }

		}
	}
}
