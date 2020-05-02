/*
Matthew Li
Jan 10 2020
Ms Krasteva
This is the Final draft of my ISP, which is Mastermind. Mastermind is a game where the user tries to guess a colour sequence, in my game, it is a sequence of 4.
The purpose of the program is for the user to have a comfortable and interative time with the console, allowing free choice and comfortable gameplay.
The program offers many different options for the user and playing the game Mastermind is explained in detail in instructions().

The first screen is the splashScreen. The splashScreen is just a screen that the user can view some nice graphics and relax. The program will transition to the main menu shortly after.

The second screen is the mainMenu, where the user can choose their option they want to go to. They click either 1,2,3, or 4 and enter to confirm.
There holds a couple of conditions in this screen in order to continue. Firstly, any input that is not 1,2,3,4, or '\n' will be ignored, this is errortrapping useless input.
Next, if the enters '\n' without pressing 1,2,3, or 4 prior to pressing '\n', then it will pop and error prompting the user to enter a valid option before continuing.

The game prompts for input next. First it asks for the user to select their mode, either 4,5,or 6 colours to choose from. Similar to the mechanism above, bad input is ignored,
and enter is pressed in order to confirm. Good input must be entered in order for the user to continue. Second prompt is for the username. The user can enter any character
of ascii value from 33 to 126, and the ascii value 8. The range of values is any character with a solid character as a value, and thus can be added to the username,
the 8, is used as backspace and deletes one character from the username, if they entered a username of length longer than 1. Lastly, the last prompt is for 'Y' or 'N' to
cheating on the game, if they enter 'Y' or 'N' it is stored, if backspace, the currently stored is deleted, if enter, it confirms or if nothing is stored, there is an error.

The user plays the game, entering a sequence of 4 numbers each corresponding to a colour, if input doesn't correspond with a number, then it is ignored and not pasted onto the screen,
if it is, then append to the String indicating sequence, and '\n' to confirm under the condition that the user has entered exactly a sequence of 4 characters.

The screen after that tells the user their score, and the user pressed enter to continue.

The instructions screen simply pastes the instructions, where the user presses '\n' to continue.

The high scores screen first prompts for the user to enter which mode's leaderboards they wish to see, and then prints the leaderboards out for them. The way to get input is the same
as how the level's are selected before a game, just click 1,2, or 3, and enter to confirm. After viewing the high score, they can choose to either clear the file or not, if they do, 
all high scores are cleared.

Lastly, there is a goodbye screen where the user can enter '\n' to exit, and there is a goodbye message.
*/
import java.lang.*;
import java.awt.*;
import hsa.Console;
import java.io.*;
import javax.swing.JOptionPane;
//global variable dictionary
//name          type            purpose
//c             Console         Creates an instance of the class Console, where everything is drawn on
//level         int             Tells the program which mode the user chose to play this game on
//score         int             Tells the program what score the user got after they played their game, also used to store in a txt file for the program to see high scores
//win           boolean         Tells the program if the user has won the game
//cheating      boolean         If the user wants to cheat on this game or not
//user          String          The username of the current player
//fileNames     String []       Stores the name of each of the files, each storing the high scores for each level
//op            char            The option the user chose in the main menu
//selectColours Color []        The total possible colours to choose from when choosing a colour
public class Mastermind extends Thread
{
    Console c;
    int level, score;
    boolean win, cheating;
    String user, fileNames[] = {"level1.txt", "level2.txt", "level3.txt"};
    char op;
    final Color[] selectColours = {new Color (255, 0, 0), new Color (255, 145, 0), new Color (255, 255, 0), new Color (0, 255, 0), new Color (0, 0, 255), new Color (145, 55, 155) };
    //the splashScreen method
    //draws the splashScreen for the program
    //local variable dictionary
    //name      type            purpose
    //ss        SplashScreen    to access the SplashScreen class that allows for the SplashScreen animation to play
    public void splashScreen ()
    {
	title (); //draws the title
	SplashScreen ss = new SplashScreen(c);//access to the SplashScreen class
	ss.start();//start running
	pauseProgram(6000);//wait for the SplashScreen animation to finish before continuing
    }
    //the mainMenu method
    //generates the main menu and asks where the user wants to go
    //local variable dictionary
    //name      type        purpose
    //gry       int         stores a shade of grey, used in rectangles in the buttons of the main menu
    //x         char        temporary user input, depending on what x is, the program will decide what to do
    //i         int         looping variable, loops the vertical column to draw the buttons
    public void mainMenu ()
    {
	title (); //draws the title
	//draws new balls for happy faces
	c.setColor (new Color (0, 255, 0));
	c.fillOval (50, 140, 150, 150);
	c.setColor (new Color (0, 0, 255));
	c.fillOval (600, 140, 150, 150);
	//draws the face for green ball
	c.setColor (new Color (0, 150, 0));
	c.fillArc (70, 170, 25, 50, 0, 180);
	c.fillArc (130, 170, 25, 50, 0, 180);
	c.fillArc (70, 180, 85, 85, 180, 180);
	c.setColor (new Color (0, 255, 0));
	c.fillArc (72, 183, 21, 27, 0, 180);
	c.fillArc (132, 183, 21, 27, 0, 180);
	c.fillArc (70, 205, 85, 30, 180, 180);
	//draws the face for the blue ball
	c.setColor (new Color (28,217,176));
	c.fillArc (70 + 575, 170, 25, 50, 0, 180);
	c.fillArc (130 + 575, 170, 25, 50, 0, 180);
	c.fillArc (70 + 575, 180, 85, 85, 180, 180);
	c.setColor (new Color (0, 0, 255));
	c.fillArc (72 + 575, 183, 21, 27, 0, 180);
	c.fillArc (132 + 575, 183, 21, 27, 0, 180);
	c.fillArc (70 + 575, 205, 85, 30, 180, 180);
	int gry = 255; //declaration
	//loops vertically, generating the buttons
	for (int i = 130 ; i <= 355 ; i += 75)
	{
	    c.setColor (new Color (gry, gry, gry));
	    c.fillRect (250, i, 300, 50);
	    gry -= 31;
	} //second round of looping vertically, again generating the buttons
	for (int i = 140 ; i <= 365 ; i += 75)
	{
	    c.setColor (new Color (gry, gry, gry));
	    c.fillRect (262, i, 275, 30);
	    gry -= 31;
	} //printing the text onto the screen, this will indicate for the user as to where to go
	c.setFont (new Font ("Anton", 1, 24));
	c.setColor (new Color (65, 59, 219));
	c.drawString (" 1) Play Game", 262, 165);
	c.setColor (new Color (154, 59, 219));
	c.drawString (" 2) Instructions", 262, 240);
	c.setColor (new Color (177, 59, 219));
	c.drawString (" 3) View High Scores", 262, 315);
	c.setColor (new Color (219, 59, 209));
	c.drawString (" 4) Quit Program", 262, 390);
	//declaration of x
	char x;
	//loop while true, break when the user has entered a valid option and presses enter
	do
	{
	    x = c.getChar (); //gets input
	    //if the user enters 1, draw a yellow outline around their option
	    if (x == '1')
	    {
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 125, 310, 60);
		//prompt for the user to press enter
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		op = x;
	    } //if the user enters 2, draw a yellow outline around their option
	    else if (x == '2')
	    {
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 200, 310, 60);
		//prompt for the user to press enter
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		op = x;
	    } //if the user enters 3, draw a yellow outline around their option
	    else if (x == '3')
	    {
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 275, 310, 60);
		//prompt for the user to press enter
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		op = x;
	    } //if the user enters 4, draw a yellow outline around their option
	    else if (x == '4')
	    {
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 350, 310, 60);
		//prompt for the user to press enter
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		op = x;
	    }
	    //redrawing the buttons and text is needed after the yellow box is drawn or else it'll just be a yellow box; loop the same way
	    //we did before and just generate the buttons with rect and then draw the message again
	    gry = 255;
	    for (int i = 130 ; i <= 355 ; i += 75)
	    {
		c.setColor (new Color (gry, gry, gry));
		c.fillRect (250, i, 300, 50);
		gry -= 32;
	    }
	    for (int i = 140 ; i <= 365 ; i += 75)
	    {
		c.setColor (new Color (gry, gry, gry));
		c.fillRect (262, i, 275, 30);
		gry -= 32;
	    }
	    c.setFont (new Font ("Anton", 1, 24));
	    c.setColor (new Color (65, 59, 219));
	    c.drawString (" 1) Play Game", 262, 165);
	    c.setColor (new Color (154, 59, 219));
	    c.drawString (" 2) Instructions", 262, 240);
	    c.setColor (new Color (177, 59, 219));
	    c.drawString (" 3) View High Scores", 262, 315);
	    c.setColor (new Color (219, 59, 209));
	    c.drawString (" 4) Quit Program", 262, 390);
	    //if the user presses enter and op has not been filled with a valid option, then that means the user must enter something valid before continuing
	    if (x == '\n' && !(op == '1' || op == '2' || op == '3' || op == '4'))
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid choice", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    } //if the user has entered something valid and then clicks enter, then break
	    else if (x == '\n')
		break;
	}
	while (true);
    }


    //the askData method
    //asks the user for input
    //local variable dictionary
    //name      type        purpose
    //gry       int         A variable keeping track of grey to draw buttons for user input
    //i         int         An iterative variable iterating through for loops to get the desired placement of graphics
    //x         char        A variable holding the value of temporary user input
    //tempUser  String      A variable keepign track of the temporary username the user enters
    //save      char        A variable storing the temporary option the user chose
    public void askData ()
    {
	title (); //draws the title
	//draws new balls for happy faces
	c.setColor (new Color (0, 255, 255));
	c.fillOval (50, 140, 150, 150);
	c.setColor (new Color (255, 0, 255));
	c.fillOval (600, 140, 150, 150);
	//draws the face for teal ball
	c.setColor (new Color (0, 150, 0));
	c.fillArc (70, 170, 25, 50, 0, 180);
	c.fillArc (130, 170, 25, 50, 0, 180);
	c.fillArc (70, 180, 85, 85, 180, 180);
	c.setColor (new Color (0, 255, 255));
	c.fillArc (72, 183, 21, 27, 0, 180);
	c.fillArc (132, 183, 21, 27, 0, 180);
	c.fillArc (70, 205, 85, 30, 180, 180);
	//draws the face for the pink ball
	c.setColor (new Color (140, 50, 110));
	c.fillArc (70 + 575, 170, 25, 50, 0, 180);
	c.fillArc (130 + 575, 170, 25, 50, 0, 180);
	c.fillArc (70 + 575, 180, 85, 85, 180, 180);
	c.setColor (new Color (255, 0, 255));
	c.fillArc (72 + 575, 183, 21, 27, 0, 180);
	c.fillArc (132 + 575, 183, 21, 27, 0, 180);
	c.fillArc (70 + 575, 205, 85, 30, 180, 180);
	int gry = 255; //declaration of the grey variable
	//draws the buttons for user choice
	for (int i = 130 ; i <= 280 ; i += 75)
	{
	    c.setColor (new Color (gry, gry, gry));
	    c.fillRect (250, i, 300, 50);
	    gry -= 31;
	}
	//draws the buttons for user choice
	for (int i = 140 ; i <= 290 ; i += 75)
	{
	    c.setColor (new Color (gry, gry, gry));
	    c.fillRect (262, i, 275, 30);
	    gry -= 31;
	}
	//draws the text for the user to see which game mode they want to play in
	c.setFont (new Font ("Anton", 1, 24));
	c.setColor (new Color (65, 59, 219));
	c.drawString (" 1) 4 colours", 262, 165);
	c.setColor (new Color (154, 59, 219));
	c.drawString (" 2) 5 colours", 262, 240);
	c.setColor (new Color (177, 59, 219));
	c.drawString (" 3) 6 colours", 262, 315);
	level = 0; //set level to 0;
	char x; //declaring char x
	//loops for the user to get input, if the user enters their choice and then enter, then it breaks out of the loop
	do
	{
	    x = c.getChar (); //getting user input
	    //if the user enters '1'
	    if (x == '1')
	    {
		//clear background
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		//draw the yellow highlighting around the box
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 125, 310, 60);
		//draw the prompt for the user to press enter to continue
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		level = 1; //set colour to 1
	    }
	    //if the user enters '2'
	    else if (x == '2')
	    {
		//clear background
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		//draw the yellow highlighting around the box
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 200, 310, 60);
		//draw the prompt for the user to press enter to continue
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		level = 2; //sets level to 2
	    }
	    //if the user enters '3'
	    else if (x == '3')
	    {
		//clear background
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		//draw the yellow highlighting around the box
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 275, 310, 60);
		//draw the prompt for the user to press enter to continue
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		level = 3; //sets level to 3
	    }
	    gry = 255; //resets gry to the rgb of white
	    //redraws the buttons
	    for (int i = 130 ; i <= 280 ; i += 75)
	    {
		c.setColor (new Color (gry, gry, gry));
		c.fillRect (250, i, 300, 50);
		gry -= 32;
	    }
	    for (int i = 140 ; i <= 290 ; i += 75)
	    {
		c.setColor (new Color (gry, gry, gry));
		c.fillRect (262, i, 275, 30);
		gry -= 32;
	    } //redraws the text for the user to see which option to choose
	    c.setFont (new Font ("Anton", 1, 24));
	    c.setColor (new Color (65, 59, 219));
	    c.drawString (" 1) 4 colours", 262, 165);
	    c.setColor (new Color (154, 59, 219));
	    c.drawString (" 2) 5 colours", 262, 240);
	    c.setColor (new Color (177, 59, 219));
	    c.drawString (" 3) 6 colours", 262, 315);
	    //if the user presses enter without choosing a valid option
	    if (x == '\n' && !(level == 1 || level == 2 || level == 3))
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid choice", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    } //if the user has a valid option and presses enter
	    else if (x == '\n')
		break;
	}
	while (true);
	//redraws the title
	title ();
	//draws the prompt for user input and an input bar to ask for the user's username
	c.setColor (new Color (170, 170, 170));
	c.fillRect (0, 200, 800, 40);
	c.setColor (new Color (210, 210, 210));
	c.fillRect (5, 205, 340, 30);
	c.setColor (new Color (255, 255, 255));
	c.fillRect (350, 205, 445, 30);
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Please enter your username: ", 10, 230); //prompt for username
	//declaring variable for the username the user enters
	String tempUser = "";
	do
	{
	    x = c.getChar (); //user input
	    //if the length of the username is 0, in other words, if the user has not entered a username yet, and clicks enter, this username cannot be saved and thus is an error
	    if (x == '\n' && tempUser.length () < 1)
	    {
		JOptionPane.showMessageDialog (null, "Please enter a username longer than 0 characters.", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    }
	    //if the user enters enter and has a valid username
	    else if (x == '\n')
	    {
		break;
	    }
	    //if the user clicks backspace
	    else if (x == 8)
	    {
		//if the username is already at length 0, then delete nothing, or else, delete the last character by doing a substring from 0 to tempUser.length()-1
		tempUser = tempUser.substring (0, (tempUser.length () > 0) ? tempUser.length () - 1:
		0);
		c.setColor (new Color (255, 255, 255));
		c.fillRect (350, 205, 445, 30);
		c.setColor (new Color (0, 0, 0));
		c.drawString (tempUser, 355, 230);
	    }
	    //if the character the user is not in this range of ascii, it means it has no real definition, and thus is an invalid key
	    else if (x < 32 || x > 126)
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid username that includes valid characters.", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    }
	    //if the length is too long, it cannot be displayed on high score screen properly, so this is a restriction for the user to enter a username less than 24 characters
	    else if (tempUser.length () > 24)
	    {
		JOptionPane.showMessageDialog (null, "Please enter a username less than 24 characters.", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    }
	    //if there is no issue whatsoever with what the user enters, then save it and tell the user what their current username is
	    else
	    {
		tempUser += x;
		c.setColor (new Color (255, 255, 255));
		c.fillRect (350, 205, 445, 30);
		c.setColor (new Color (0, 0, 0));
		c.drawString (tempUser, 355, 230);
	    }
	}
	while (true);
	//save user as tempUser
	user = tempUser;
	//redraws the title
	title ();
	//prompt for the user to input and the input bar
	c.setColor (new Color (170, 170, 170));
	c.fillRect (0, 200, 800, 40);
	c.setColor (new Color (210, 210, 210));
	c.fillRect (5, 205, 375, 30);
	c.setColor (new Color (255, 255, 255));
	c.fillRect (385, 205, 445, 30);
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Do you want to cheat [Y] or [N]?", 10, 230); //prompt for the user to input
	char save = ' '; //declaring the variable save
	//loop while the user has not confirmed their response
	do
	{
	    x = c.getChar (); //get user input
	    //if the user presses backspace
	    if (x == 8)
	    {
		//save is reset to empty and we show the user that they have an empty input bar
		save = ' ';
	    }
	    else if (x == 'Y' || x == 'y')
		save = 'Y';                             //if the user enters 'Y' or 'y', we save 'Y' to save
	    else if (x == 'N' || x == 'n')
		save = 'N';                            //if the user enters 'N' or 'n', we save 'N' to save
	    else if (x == '\n' && (save == 'Y' || save == 'N'))
		break;                                                  //if the user has entered a valid option and clicks enter
	    //if the user hasm't entered a valid option and clicks enter
	    else if (x == '\n')
		JOptionPane.showMessageDialog (null, "Please enter [Y] or [N] to the question.", "Error", JOptionPane.ERROR_MESSAGE);                   //error message
	    //draw the currently saved option onto the screen.
	    c.setColor (new Color (255, 255, 255));
	    c.fillRect (385, 205, 445, 30);
	    c.setColor (new Color (0, 0, 0));
	    c.drawString (save + "", 390, 230);
	}
	while (true);
	if (save == 'Y')
	    cheating = true;               //if the user wants to cheat, set cheating to true
	else
	    cheating = false;     //or else set it to false
    }


    //the playGame method
    //this method plays the game, allowing the user to guess the sequence of colours
    //local variable dictionary
    //name      type        purpose
    //i         int         a looping variable to loop vertically to draw coloured circles and iterate through loops
    //j         int         a looping variable to loop horizontally to draw coloured circles and iterate through loops
    //seq       Color []    the colour sequence that is generated by the program and that the user is to guess
    //red       int         number of red balls to draw to indicate right colour and place
    //white     int         number of white balls to draw to indicate right colour but not place
    //cur       Color []    the colour sequence that the user enters as a guess
    public void playGame ()
    {
	//draw the background
	c.setColor (new Color (28, 142, 230));
	c.fillRect (0, 0, 800, 500);
	//draw the board
	c.setColor (new Color (112, 58, 28));
	c.fillRect (0, 0, 250, 500);
	c.setColor (new Color (191, 84, 38));
	for (int i = 5 ; i <= 455 ; i += 50)
	{
	    c.fillRect (5, i, 190, 40);
	    c.fillRect (208, i + 3, 34, 34);
	} //draw the holes for sequence of colours
	c.setColor (new Color (237, 195, 121));
	for (int i = 5 ; i <= 455 ; i += 50)
	{
	    for (int j = 5 ; j <= 155 ; j += 50)
	    {
		c.fillOval (j, i, 40, 40);
	    }
	} //draw the places for the dots indicating correctness
	c.setColor (new Color (0, 0, 0));
	for (int i = 0 ; i <= 450 ; i += 50)
	{
	    c.fillOval (211, i + 11, 8, 8);
	    c.fillOval (211, i + 31, 8, 8);
	    c.fillOval (231, i + 11, 8, 8);
	    c.fillOval (231, i + 31, 8, 8);
	}
	//gets the sequence to be guessed
	Color[] seq = generate (level);
	//if the user chose to cheat, draw out the sequence at the bottom right corner to see
	if (cheating)
	{
	    c.setColor (seq [0]);
	    c.fillOval (760, 490, 10, 10);
	    c.setColor (seq [1]);
	    c.fillOval (770, 490, 10, 10);
	    c.setColor (seq [2]);
	    c.fillOval (780, 490, 10, 10);
	    c.setColor (seq [3]);
	    c.fillOval (790, 490, 10, 10);
	}
	//draw template for the user to see options
	c.setColor (new Color (49, 202, 222));
	c.fillRect (365, 40, 320, 170);
	c.setColor (new Color (54, 135, 179));
	c.fillRect (370, 45, 310, 160);
	c.setColor (new Color (45, 57, 135));
	c.fillRect (375, 50, 300, 150);
	c.setFont (new Font ("Anton", 1, 20));
	//if the user chose first level
	if (level == 1)
	{
	    //tells the user the 4 options and their keys
	    c.setColor (selectColours [0]);
	    c.drawString ("\'1\' for red", 375, 70);
	    c.setColor (selectColours [1]);
	    c.drawString ("\'2\' for orange", 375, 90);
	    c.setColor (selectColours [2]);
	    c.drawString ("\'3\' for yellow", 375, 110);
	    c.setColor (selectColours [3]);
	    c.drawString ("\'4\' for green", 375, 130);
	}
	//if the user chose the second level
	else if (level == 2)
	{
	    //tells the user the 5 options and their keys
	    c.setColor (selectColours [0]);
	    c.drawString ("\'1\' for red", 375, 70);
	    c.setColor (selectColours [1]);
	    c.drawString ("\'2\' for orange", 375, 90);
	    c.setColor (selectColours [2]);
	    c.drawString ("\'3\' for yellow", 375, 110);
	    c.setColor (selectColours [3]);
	    c.drawString ("\'4\' for green", 375, 130);
	    c.setColor (selectColours [4]);
	    c.drawString ("\'5\' for blue", 375, 150);
	}
	//if the user chose the third level
	else
	{
	    //tells the user the 6 options and their keys
	    c.setColor (selectColours [0]);
	    c.drawString ("\'1\' for red", 375, 70);
	    c.setColor (selectColours [1]);
	    c.drawString ("\'2\' for orange", 375, 90);
	    c.setColor (selectColours [2]);
	    c.drawString ("\'3\' for yellow", 375, 110);
	    c.setColor (selectColours [3]);
	    c.drawString ("\'4\' for green", 375, 130);
	    c.setColor (selectColours [4]);
	    c.drawString ("\'5\' for blue", 375, 150);
	    c.setColor (selectColours [5]);
	    c.drawString ("\'6\' for purple", 375, 170);
	}
	//set score to 100 and win to false
	score = 100;
	win = false;
	for (int i = 455 ; i >= 5 ; i -= 50)
	{
	    //get input and that is the user's guess
	    Color[] cur = input ();
	    //draw in the guess
	    c.setColor (cur [0]);
	    c.fillOval (5, i, 40, 40);
	    c.setColor (cur [1]);
	    c.fillOval (55, i, 40, 40);
	    c.setColor (cur [2]);
	    c.fillOval (105, i, 40, 40);
	    c.setColor (cur [3]);
	    c.fillOval (155, i, 40, 40);
	    //if user wins
	    if (isWon (cur, seq))
	    {
		//set win to true and fill in the current row with all red dots to indicate that you won and break
		win = true;
		c.setColor (new Color (255, 0, 0));
		c.fillOval (211, i + 6, 8, 8);
		c.fillOval (211, i + 26, 8, 8);
		c.fillOval (231, i + 6, 8, 8);
		c.fillOval (231, i + 26, 8, 8);
		break;
	    }
	    //if you haven't won yet
	    else
	    {
		//declare red and white to 0
		int red = 0, white = 0;
		if (seq [0] == cur [0])
		    red++;                  //if first colour is right colour and right place
		else if (seq [0] == cur [1] || seq [0] == cur [2] || seq [0] == cur [3])
		    white++;                                                           //if first colour is right colour and wrong place
		if (seq [1] == cur [1])
		    red++;                  //if second colour is right colour and right place
		else if (seq [1] == cur [0] || seq [1] == cur [2] || seq [1] == cur [3])
		    white++;                                                           //if second colour is right colour and wrong place
		if (seq [2] == cur [2])
		    red++;                  //if third colour is right colour and right place
		else if (seq [2] == cur [0] || seq [2] == cur [1] || seq [2] == cur [3])
		    white++;                                                           //if third colour is right colour and wrong place
		if (seq [3] == cur [3])
		    red++;                  //if fourth colour is right colour and right place
		else if (seq [3] == cur [0] || seq [3] == cur [1] || seq [3] == cur [2])
		    white++;                                                           //if fourth colour is right colour and wrong place
		if (red == 3)
		{ //if there are three reds
		    if (white == 1)
		    { //if theres one white
			//fill in 3 reds and 1 white
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
			c.fillOval (211, i + 26, 8, 8);
			c.setColor (new Color (255, 255, 255));
			c.fillOval (231, i + 26, 8, 8);
		    }
		    else
		    { //if theres no whites
			//draw only 3 reds
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
			c.fillOval (211, i + 26, 8, 8);
		    }
		}
		else if (red == 2)
		{ //if theres 2 reds
		    if (white == 2)
		    { //if theres 2 whites
			//draw 2 reds and 2 whites
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
			c.setColor (new Color (255, 255, 255));
			c.fillOval (211, i + 26, 8, 8);
			c.fillOval (231, i + 26, 8, 8);
		    }
		    else if (white == 1)
		    { //if theres 1 white
			//draw 2 reds and 1 white
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
			c.setColor (new Color (255, 255, 255));
			c.fillOval (211, i + 26, 8, 8);
		    }
		    else
		    { //if theres no whites
			//draw only 2 reds
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
		    }
		}
		else if (red == 1)
		{ //if theres 1 red
		    if (white == 3)
		    { //if theres 3 whites
			//draw 1 red and 3 whites
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.setColor (new Color (255, 255, 255));
			c.fillOval (231, i + 6, 8, 8);
			c.fillOval (211, i + 26, 8, 8);
			c.fillOval (231, i + 26, 8, 8);
		    }
		    else if (white == 2)
		    { //if theres 2 whites
			//draw 1 red and 2 whites
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.setColor (new Color (255, 255, 255));
			c.fillOval (231, i + 6, 8, 8);
			c.fillOval (211, i + 26, 8, 8);
		    }
		    else if (white == 1)
		    { //if theres 1 white
			//draw 1 red and 1 white
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
			c.setColor (new Color (255, 255, 255));
			c.fillOval (231, i + 6, 8, 8);
		    }
		    else
		    { //if theres no whites
			//draw only 1 red
			c.setColor (new Color (255, 0, 0));
			c.fillOval (211, i + 6, 8, 8);
		    }
		}
		else
		{ //if there are 0 reds
		    if (white == 4)
		    { //if there are 4 whites
			//draw 4 whites
			c.setColor (new Color (255, 255, 255));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
			c.fillOval (211, i + 26, 8, 8);
			c.fillOval (231, i + 26, 8, 8);
		    }
		    else if (white == 3)
		    { //if there are 3 whites
			//draw 3 whites
			c.setColor (new Color (255, 255, 255));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
			c.fillOval (211, i + 26, 8, 8);
		    }
		    else if (white == 2)
		    { //if there are 2 whites
			//draw 2 whites
			c.setColor (new Color (255, 255, 255));
			c.fillOval (211, i + 6, 8, 8);
			c.fillOval (231, i + 6, 8, 8);
		    }
		    else if (white == 1)
		    { //if there is 1 white
			//draw only one white
			c.setColor (new Color (255, 255, 255));
			c.fillOval (211, i + 6, 8, 8);
		    } //not in code, but if there are 0 whites and 0 reds, nothing updates
		}
	    }
	    //for every time the user hasn't guessed the sequence
	    score -= 10;
	}
	//fill in the template for the message and sequence to be displayed on
	c.setColor (new Color (49, 202, 222));
	c.fillRect (365, 40, 320, 170);
	c.setColor (new Color (54, 135, 179));
	c.fillRect (370, 45, 310, 160);
	c.setColor (new Color (45, 57, 135));
	c.fillRect (375, 50, 300, 150);
	c.setColor (new Color (49, 202, 222));
	c.fillRect (290, 240, 470, 120);
	c.setColor (new Color (54, 135, 179));
	c.fillRect (295, 245, 460, 110);
	c.setColor (new Color (45, 57, 135));
	c.fillRect (300, 250, 450, 100);
	//tell the user what the sequence was
	c.setFont (new Font ("Anton", 1, 20));
	c.setColor (new Color (49, 202, 222));
	c.drawString ("Sequence: ", 340, 310);
	for (int i = 0 ; i <= 3 ; i++)
	{
	    c.setColor (seq [i]);
	    c.fillOval (525 + i * 50, 275, 50, 50);
	}
	c.setColor (new Color (49, 202, 222));
	//if the user won without cheating
	if (win && !cheating)
	{
	    c.drawString ("Congratulations", 440, 130);
	}
	//if the user won but cheated
	else if (win && cheating)
	{
	    score = 0; //set the score to 0
	    c.drawString ("Congrates, cheater", 435, 130); //message for the user
	}
	//if the user did not guess the sequence
	else
	{
	    score = 0; //set score to 0
	    c.drawString ("Better luck next time", 430, 130); //message for the user
	}
	//prompt and button for the user to click enter to continue
	c.setColor (new Color (142, 36, 199));
	c.fillRect (375, 415, 300, 50);
	c.setColor (new Color (204, 78, 222));
	c.fillRect (380, 420, 290, 40);
	c.setColor (new Color (219, 132, 215));
	c.fillRect (385, 425, 280, 30);
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Press enter to continue", 391, 450); //prompt to press enter to continue
	while (c.getChar () != '\n');                        //do nothing until the user presses enter
    }


    //the generate method
    //generates a Color array at random
    //local variable dictionary
    //name      type        purpose
    //ret       Color []    the final Color array returned
    //id        int []      the random index corresponding to an index in selectColours
    //In my game, the sequence will never have 2 repeating colours, thus, I check if there is and then rechoose if the colour/id has already been chosen.
    private Color[] generate (int lvl)
    {
	//declarations
	Color[] ret = new Color [4];
	int[] id = new int [4];
	//first colour can be anything
	id [0] = (int) (Math.random () * (lvl + 3));
	//second colour can be anything other than the first, must loop until it is
	do
	{
	    id [1] = (int) (Math.random () * (lvl + 3));
	}
	while (id [1] == id [0]);
	//third colour can be anything other than the first two, must loop until it is
	do
	{
	    id [2] = (int) (Math.random () * (lvl + 3));
	}
	while (id [2] == id [0] || id [2] == id [1]);
	//last colour must be different from the rest, must loop until it is
	do
	{   
	    id [3] = (int) (Math.random () * (lvl + 3));
	}
	while (id [3] == id [0] || id [3] == id [1] || id [3] == id [2]);
	//fill in ret with the corresponding colours in selectColours with the chosen id's
	ret [0] = selectColours [id [0]];
	ret [1] = selectColours [id [1]];
	ret [2] = selectColours [id [2]];
	ret [3] = selectColours [id [3]];
	//return ret
	return ret;
    }


    //the input method
    //gets the user's input as a sequence of 4 numbers
    //local variable dictionary
    //name      type        purpose
    //x         char        get input from the user character by character
    //temp      String      storing the sequence the user enters as a String
    private Color[] input ()
    {
	//prompt for the user to get input
	c.setColor (new Color (49, 202, 222));
	c.fillRect (290, 240, 470, 120);
	c.setColor (new Color (54, 135, 179));
	c.fillRect (295, 245, 460, 110);
	c.setColor (new Color (45, 57, 135));
	c.fillRect (300, 250, 450, 100);
	c.setColor (new Color (255, 255, 255));
	c.fillRect (325, 300, 400, 30);
	c.setColor (new Color (49, 202, 222));
	c.setFont (new Font ("Anton", 1, 20));
	c.drawString ("Your sequence as 4 numbers please: ", 325, 280);
	//declaring variables
	char x;
	String temp = "";
	//while true
	do
	{
	    x = c.getChar ();
	    //if the user enters backspace
	    if (x == 8)
	    { //if there is something to delete, then delete the last character of the current String
		if (temp.length () != 0)
		{
		    temp = temp.substring (0, temp.length () - 1);
		    c.setColor (new Color (255, 255, 255));
		    c.fillRect (325, 300, 400, 30);
		    c.setColor (new Color (0, 0, 0));
		    c.drawString (temp, 330, 325);
		}
	    } //if the enters a valid option, then append it to the String and tell the user what they chose
	    else if (x - '0' > 0 && x - '0' <= level + 3 && temp.length () < 4)
	    {
		temp += x;
		c.setColor (new Color (255, 255, 255));
		c.fillRect (325, 300, 400, 30);
		c.setColor (new Color (0, 0, 0));
		c.drawString (temp, 330, 325);
	    } //if the user doesn't enter a valid sequence of 4 and clicks enter
	    else if (x == '\n' && temp.length () != 4)
	    {
		JOptionPane.showMessageDialog (null, "Please enter your sequence as 4 numbers.", "Error", JOptionPane.ERROR_MESSAGE);
	    } //if the user enters a valid sequence and enter
	    else if (x == '\n')
		break;
	}
	while (true);
	Color[] ret = {selectColours [temp.charAt (0) - '1'], selectColours [temp.charAt (1) - '1'], selectColours [temp.charAt (2) - '1'], selectColours [temp.charAt (3) - '1'] };
	return ret;
    }


    //isWon method
    //checks if two arrays of colours are the same
    //local variable dictionary
    //name      type        purpose
    //ori       Color []    the original colour array, the winning array
    //arr       Color []    the colour array being checked with the winning array
    private boolean isWon (Color ori[], Color arr[])
    {
	//if all elements of the array are the same, return true, else return faLSE
	if (ori [0] == arr [0] && ori [1] == arr [1] && ori [2] == arr [2] && ori [3] == arr [3])
	    return true;
	else
	    return false;
    }


    //the displayScore method
    //displays the score the user scored this round
    public void displayScore ()
    {
	//draws the title
	title ();
	//sets the font to Anton
	c.setFont (new Font ("Anton", 1, 24));
	//if the user doesn't cheat, then print out a message, printing their score
	if (!cheating)
	{
	    //sets colour to black, first shadow
	    c.setColor (new Color (0, 0, 0));
	    c.drawString ("Your score is " + score + " this round!", 249, 244);
	    //sets colour to white, second shadow
	    c.setColor (new Color (255, 255, 255));
	    c.drawString ("Your score is " + score + " this round!", 247, 242);
	    //sets colour to yellow, word block
	    c.setColor (new Color (255, 255, 0));
	    c.drawString ("Your score is " + score + " this round!", 245, 240);
	}
	//if the user does cheat, remind the user they cheated
	else
	{
	    //sets colour to black, first shadow
	    c.setColor (new Color (0, 0, 0));
	    c.drawString ("Your score is " + score + " this round, you cheated!", 175, 240);
	    //sets colour to white, second shadow
	    c.setColor (new Color (255, 255, 255));
	    c.drawString ("Your score is " + score + " this round, you cheated!", 177, 242);
	    //sets colour to yellow, word block
	    c.setColor (new Color (255, 255, 0));
	    c.drawString ("Your score is " + score + " this round, you cheated!", 175, 244);
	}
	//sets the colour to different shades of purple and then rectangles and prompt for the user to enter '\n' to continue
	c.setColor (new Color (142, 36, 199));
	c.fillRect (250, 415, 300, 50);
	c.setColor (new Color (204, 78, 222));
	c.fillRect (255, 420, 290, 40);
	c.setColor (new Color (219, 132, 215));
	c.fillRect (260, 425, 280, 30);
	//sets colour to yellow and font to Anton and prints prompt for the user to enter '\n' to continue
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Press enter to continue", 266, 450);
	//loop while the user doesnt enter '\n'
	while (c.getChar () != '\n')
	    ;
	if (!cheating)
	    storeScore ();            //redundance in storing score if the user was cheating, so just only store their score if they were not cheating
    }


    //the storeScore method
    //stores the score of the user playing the game
    //local variable dictionary
    //name          type                    purpose
    //leaderboards  String [] []            The current leaderboards read from the file
    //br            BufferedReader          Instance of the BufferedReader class in order to read from files
    //pw            PrintWriter             Instance of the PrintWriter class in order to write to files
    public void storeScore ()
    {
	String[] [] leaderboards = new String [10] []; //current leaderboards
	try
	{
	    BufferedReader br = new BufferedReader (new FileReader (fileNames [level - 1])); //Instance of BufferedReader class
	    //loops for top 10 in the leaderboards
	    for (int i = 0 ; i < 10 ; i++)
	    {
		leaderboards [i] = new String [2];
		leaderboards [i] [0] = br.readLine ();
		leaderboards [i] [1] = br.readLine ();
	    }
	    br.close (); //closes stream
	}
	//check for IOException that comes with BufferedReader, and NullPointerException, which may come with reading a line that doesn't exist
	catch (IOException e)
	{
	}
	catch (NullPointerException e)
	{
	}
	try
	{
	    PrintWriter pw = new PrintWriter (new FileWriter (fileNames [level - 1])); //Instance of PrintWriter class
	    //loop for top 10
	    for (int i = 0 ; i < 10 ; i++)
	    {
		if (Integer.parseInt (leaderboards [i] [1]) < score)
		{
		    pw.println (user); //if score goes on the leaderboard, add user and score
		    pw.println (score);
		}
		if (Integer.parseInt (leaderboards [i] [1]) < 0)
		{
		    pw.println (leaderboards [i] [0]); //add the current leaderboard info back to old one
		    pw.println (leaderboards [i] [1]);
		    break; //detects the placeholder, which value is -1
		}
		pw.println (leaderboards [i] [0]); //add the current leaderboard info back to old one
		pw.println (leaderboards [i] [1]);
	    }
	    pw.close (); //close stream
	}
	catch (IOException e)
	{
	}
    }


    //the highScores method
    //displays the top 10 high scores
    //local variable dictionary
    //name          type                    purpose
    //gry           int                     A variable keeping track of grey to draw buttons for user input
    //i             int                     An iterative variable iterating through for loops to get the desired placement of graphics
    //x             char                    A variable holding the value of temporary user input
    //save          char                    Temporary variable saving if the user chooses to clear file or not
    //id            int                     The level the user wants to view the high score of
    //br            BufferedReader          Input stream from the inputted BufferedReader class
    public void highScores ()
    {
	title (); //draws the title
	//draws new balls for happy faces
	c.setColor (new Color (184, 75, 169));
	c.fillOval (50, 140, 150, 150);
	c.setColor (new Color (222, 151, 20));
	c.fillOval (600, 140, 150, 150);
	//draws the face for magenta ball
	c.setColor (new Color (106, 14, 227));
	c.fillArc (70, 170, 25, 50, 0, 180);
	c.fillArc (130, 170, 25, 50, 0, 180);
	c.fillArc (70, 180, 85, 85, 180, 180);
	c.setColor (new Color (184, 75, 169));
	c.fillArc (72, 183, 21, 27, 0, 180);
	c.fillArc (132, 183, 21, 27, 0, 180);
	c.fillArc (70, 205, 85, 30, 180, 180);
	//draws the face for the orange ball
	c.setColor (new Color (227, 14, 149));
	c.fillArc (70 + 575, 170, 25, 50, 0, 180);
	c.fillArc (130 + 575, 170, 25, 50, 0, 180);
	c.fillArc (70 + 575, 180, 85, 85, 180, 180);
	c.setColor (new Color (222, 151, 20));
	c.fillArc (72 + 575, 183, 21, 27, 0, 180);
	c.fillArc (132 + 575, 183, 21, 27, 0, 180);
	c.fillArc (70 + 575, 205, 85, 30, 180, 180);
	int gry = 255; //declaration of the grey variable
	//draws the buttons for user choice
	for (int i = 130 ; i <= 280 ; i += 75)
	{
	    c.setColor (new Color (gry, gry, gry));
	    c.fillRect (250, i, 300, 50);
	    gry -= 31;
	}
	//draws the buttons for user choice
	for (int i = 140 ; i <= 290 ; i += 75)
	{
	    c.setColor (new Color (gry, gry, gry));
	    c.fillRect (262, i, 275, 30);
	    gry -= 31;
	}
	//draws the text for the user to see which game mode they want to play in
	c.setFont (new Font ("Anton", 1, 24));
	c.setColor (new Color (65, 59, 219));
	c.drawString (" 1) 4 colours", 262, 165);
	c.setColor (new Color (154, 59, 219));
	c.drawString (" 2) 5 colours", 262, 240);
	c.setColor (new Color (177, 59, 219));
	c.drawString (" 3) 6 colours", 262, 315);
	int id = 0; //set id to 0;
	char x; //declaring char x
	//loops for the user to get input, if the user enters their choice and then enter, then it breaks out of the loop
	do
	{
	    x = c.getChar (); //getting user input
	    //if the user enters '1'
	    if (x == '1')
	    {
		//clear background
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		//draw the yellow highlighting around the box
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 125, 310, 60);
		//draw the prompt for the user to press enter to continue
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		id = 1; //set colour to 1
	    }
	    //if the user enters '2'
	    else if (x == '2')
	    {
		//clear background
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		//draw the yellow highlighting around the box
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 200, 310, 60);
		//draw the prompt for the user to press enter to continue
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		id = 2; //sets level to 2
	    }
	    //if the user enters '3'
	    else if (x == '3')
	    {
		//clear background
		c.setColor (new Color (28, 142, 230));
		c.fillRect (245, 125, 310, 285);
		//draw the yellow highlighting around the box
		c.setColor (new Color (255, 255, 0));
		c.fillRect (245, 275, 310, 60);
		//draw the prompt for the user to press enter to continue
		c.setColor (new Color (142, 36, 199));
		c.fillRect (250, 415, 300, 50);
		c.setColor (new Color (204, 78, 222));
		c.fillRect (255, 420, 290, 40);
		c.setColor (new Color (219, 132, 215));
		c.fillRect (260, 425, 280, 30);
		c.setColor (new Color (255, 255, 0));
		c.setFont (new Font ("Anton", 1, 24));
		c.drawString ("Press enter to continue", 266, 450);
		id = 3; //sets level to 3
	    }
	    gry = 255; //resets gry to the rgb of white
	    //redraws the buttons
	    for (int i = 130 ; i <= 280 ; i += 75)
	    {
		c.setColor (new Color (gry, gry, gry));
		c.fillRect (250, i, 300, 50);
		gry -= 32;
	    }
	    for (int i = 140 ; i <= 290 ; i += 75)
	    {
		c.setColor (new Color (gry, gry, gry));
		c.fillRect (262, i, 275, 30);
		gry -= 32;
	    } //redraws the text for the user to see which option to choose
	    c.setFont (new Font ("Anton", 1, 24));
	    c.setColor (new Color (65, 59, 219));
	    c.drawString (" 1) 4 colours", 262, 165);
	    c.setColor (new Color (154, 59, 219));
	    c.drawString (" 2) 5 colours", 262, 240);
	    c.setColor (new Color (177, 59, 219));
	    c.drawString (" 3) 6 colours", 262, 315);
	    //if the user presses enter without choosing a valid option
	    if (x == '\n' && !(id == 1 || id == 2 || id == 3))
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid choice", "Error", JOptionPane.ERROR_MESSAGE); //error message
	    } //if the user has a valid option and presses enter
	    else if (x == '\n')
		break;
	}
	while (true);
	title ();
	//sets font and colour
	c.setFont (new Font ("Anton", 1, 15));
	c.setColor (new Color (255, 255, 255));
	try
	{
	    BufferedReader br = new BufferedReader (new FileReader (fileNames [id - 1])); //reads from file
	    //loops the top 10 places
	    for (int i = 0 ; i < 10 ; i++)
	    {
		//displays the top 10 places
		String temp1 = br.readLine ();
		String temp2 = br.readLine ();
		if (Integer.parseInt (temp2) < 0)
		    break;                             //checks if the value there is just a placeholder or not
		c.drawString (temp1, 50, 130 + 30 * i);
		c.drawString (temp2, 400, 130 + 30 * i);
	    }
	    br.close ();
	}
	//catch the exceptions that may occur when reading the file
	catch (IOException ioe)
	{
	} //catch IOException from BufferedReader
	catch (NullPointerException npe)
	{
	} //catch NullPointerException when reading a line that doesn't exist in the file.
	catch (NumberFormatException nfe)
	{
	} //catches the NumberFormatException when a String of value null is parsed
	//sets the colour to different shades of purple and then rectangles and prompt for the user to enter '\n' to continue
	c.setColor (new Color (142, 36, 199));
	c.fillRect (250, 415, 300, 50);
	c.setColor (new Color (204, 78, 222));
	c.fillRect (255, 420, 290, 40);
	c.setColor (new Color (219, 132, 215));
	c.fillRect (260, 425, 280, 30);
	//sets colour to yellow and font to Anton and prints prompt for the user to enter '\n' to continue
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Press enter to continue", 266, 450);
	//loop while the user doesnt enter '\n'
	while (c.getChar () != '\n');
	//redraws the title
	title ();
	//prompt for the user to input and the input bar
	c.setColor (new Color (170, 170, 170));
	c.fillRect (0, 200, 800, 40);
	c.setColor (new Color (210, 210, 210));
	c.fillRect (5, 205, 375, 30);
	c.setColor (new Color (255, 255, 255));
	c.fillRect (385, 205, 445, 30);
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Want to clear file [Y] or [N]?", 10, 230); //prompt for the user to input
	char save = ' '; //declaring the variable save
	//loop while the user has not confirmed their response
	do
	{
	    x = c.getChar (); //get user input
	    //if the user presses backspace
	    if (x == 8)
	    {
		//save is reset to empty and we show the user that they have an empty input bar
		save = ' ';
	    }
	    else if (x == 'Y' || x == 'y')
		save = 'Y';                             //if the user enters 'Y' or 'y', we save 'Y' to save
	    else if (x == 'N' || x == 'n')
		save = 'N';                            //if the user enters 'N' or 'n', we save 'N' to save
	    else if (x == '\n' && (save == 'Y' || save == 'N'))
		break;                                                  //if the user has entered a valid option and clicks enter
	    //if the user hasm't entered a valid option and clicks enter
	    else if (x == '\n')
		JOptionPane.showMessageDialog (null, "Please enter [Y] or [N] to the question.", "Error", JOptionPane.ERROR_MESSAGE);                   //error message
	    //draw the currently saved option onto the screen.
	    c.setColor (new Color (255, 255, 255));
	    c.fillRect (385, 205, 445, 30);
	    c.setColor (new Color (0, 0, 0));
	    c.drawString (save + "", 390, 230);
	}
	while (true);
	//if the user chooses to clear file
	if(save == 'Y'){
	    try{ 
		//write a placeholder to the file
		PrintWriter pw = new PrintWriter(new FileWriter(fileNames[id-1]));
		pw.println("placeholder");
		pw.println("-1");
		pw.close();//close printwriter
	    }
	    catch(IOException ioe){}//catch in and out exception for printwriter class
	}
    }


    //the instructions method
    //prints the instructions
    public void instructions ()
    {
	//draws the title
	title ();
	//sets the font to Anton and colour to white and draws the instructions out for the user to read
	c.setFont (new Font ("Anton", 1, 15));
	c.setColor (new Color (255, 255, 255));
	c.drawString (" 1 ) The computer will pick a sequence of 4 colours from the number of choices in colour from your level.", 0, 130);
	c.drawString (" 2 ) The goal is for you to guess the sequence of colors, in other words, the right colors in the right place.", 0, 160);
	c.drawString (" 3 ) To guess, you enter a sequence of numbers corresponding to the colour you wish to put in that place.", 0, 190);
	c.drawString (" 4 ) For each correct colour in the correct place, a red dot will be next to the sequence.", 0, 220);
	c.drawString (" 5 ) For each correct colour in the wrong place, a white dot will be next to the sequence.", 0, 250);
	c.drawString (" 5 ) To win the game, you will need to guess all correct colours in the correct places.", 0, 280);
	c.drawString (" 6 ) Your score will depend on which row you guess the correct sequence from.", 0, 310);
	c.drawString (" 7 ) To lose the game, you must guess all incorrect sequences for the amount of rows the game provides.", 0, 340);
	c.drawString (" 8 ) Please remember that there are no repeats of colours in a particular sequence.", 0, 370);
	c.drawString (" 9 ) Good luck and remember to have fun.", 0, 400);
	//sets the colour to different shades of purple and then rectangles and prompt for the user to enter '\n' to continue
	c.setColor (new Color (142, 36, 199));
	c.fillRect (250, 415, 300, 50);
	c.setColor (new Color (204, 78, 222));
	c.fillRect (255, 420, 290, 40);
	c.setColor (new Color (219, 132, 215));
	c.fillRect (260, 425, 280, 30);
	//sets colour to yellow and font to Anton and prints prompt for the user to enter '\n' to continue
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Press enter to continue", 266, 450);
	//loop while the user doesnt enter '\n'
	while (c.getChar () != '\n')
	    ;

    }


    //the goodbye method
    //prints out a goodbye message and prompts the user to exit
    public void goodbye ()
    {
	//draws the title
	title ();
	//sets the font
	c.setFont (new Font ("Anton", 1, 24));
	//sets the colour to black and print out the message, this is the shadow
	c.setColor (new Color (0, 0, 0));
	c.drawString ("This game was made by Matthew Li", 204, 204);
	c.drawString ("Thanks for playing this game", 244, 284);
	//sets the colour to white and prints out the message, this is the second shadow
	c.setColor (new Color (255, 255, 255));
	c.drawString ("This game was made by Matthew Li", 202, 202);
	c.drawString ("Thanks for playing this game", 242, 282);
	//sets the colour to yellow and prints out the goodbye message, this is the block message
	c.setColor (new Color (255, 255, 0));
	c.drawString ("This game was made by Matthew Li", 200, 200);
	c.drawString ("Thanks for playing this game", 240, 280);
	//sets the colour to different shades of purple and draws the box for prompt for '\n' being pressed
	c.setColor (new Color (28, 142, 230));
	c.fillRect (265, 350, 265, 150);
	c.setColor (new Color (142, 36, 199));
	c.fillRect (250, 415, 300, 50);
	c.setColor (new Color (204, 78, 222));
	c.fillRect (255, 420, 290, 40);
	c.setColor (new Color (219, 132, 215));
	c.fillRect (260, 425, 280, 30);
	//sets the colour to yellow and prints out the prompt for enter to be pressed
	c.setColor (new Color (255, 255, 0));
	c.setFont (new Font ("Anton", 1, 24));
	c.drawString ("Press enter to exit", 290, 450);
	//loop while the user doesnt enter '\n'
	while (c.getChar () != '\n')
	    ;
	//exit the console
	c.close ();
    }


    //pause program method
    //pauses the program
    //local variable dictionary
    //name      type        purpose
    //time      int         the amount of time the thread sleeps for
    public void pauseProgram (int time)
    {
	try
	{
	    Thread.sleep (time);
	}
	catch (Exception e)
	{
	}
    }


    //title method
    //the default title, prints the title every time
    public void title ()
    {
	//sets colour to blue and then sets background to blue
	c.setColor (new Color (28, 142, 230));
	c.fillRect (0, 0, 800, 500);
	//sets the font to Anton and colour to black
	c.setFont (new Font ("Anton", 1, 60));
	c.setColor (new Color (0, 0, 0));
	//draws title in black, shadow
	c.drawString ("Mastermind", 226, 76);
	//draws title in white, second shadow
	c.setColor (new Color (255, 255, 255));
	c.drawString ("Mastermind", 223, 73);
	//draws the title in yellow, this is the blocked title displayed
	c.setColor (new Color (255, 255, 0));
	c.drawString ("Mastermind", 220, 70);
    }


    //construction method
    public Mastermind ()
    {
	c = new Console (25, 100, "Mastermind");
    }


    //main method

    public static void main (String[] args)
    {
	//instance of Mastermind class
	Mastermind mm = new Mastermind ();
	//splashscreen plays
	mm.splashScreen ();
	//while the user does not choose exit
	while (true)
	{
	    //displays main menu
	    mm.mainMenu ();
	    //if the user chooses to play the game
	    if (mm.op == '1')
	    {
		//ask for data
		mm.askData ();
		//plays the game
		mm.playGame ();
		//shows the user their score for that game
		mm.displayScore ();
	    }
	    //if the user chooses to view the instructions
	    else if (mm.op == '2')
	    {
		//displays the instructions to the user
		mm.instructions ();
	    }
	    //if the user chooses to view high scores
	    else if (mm.op == '3')
	    {
		//displays high scores
		mm.highScores ();
	    }
	    //if the user chooses to quit the program
	    else
	    {
		//break out of the loop and view goodbye
		break;
	    }
	    //resets the option character variable every time
	    mm.op = ' ';
	}
	//goodbye screen
	mm.goodbye ();
    }
}




