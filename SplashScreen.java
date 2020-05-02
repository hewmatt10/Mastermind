/*
Matthew Li
Jan 10 2020
Ms Krasteva
The SplashScreen class plays the SplashScreen animation
This class plays the balls bouncing off the floor and back up 
*/
import java.lang.*;
import java.awt.*;
import hsa.Console;
//global variable dictionary
//name      type        purpose
//c         Console     to extend and hava access the Console class
public class SplashScreen extends Thread{
    private Console c;
    //local variable dictionary
    //name      type        purpose
    //itvl      int []      intervals the balls are to go up and down by. The intervals form a quadratic equation which creates the illusion of bounce
    //idx       int         keeps track of where the loading bar is at
    //j         int         loops through itvl, and draws the faces/balls accordingly
    private void draw(){
	final int[] itvl = {1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210}; //the itvl array
	int idx = 280; //declaration of idx
	while (idx < 520)
	{
	    //loops through the itvl array to stimulate "bounce", this is the going downward motion
	    for (int j = 0 ; j <= 19 ; j++)
	    {
		//gets rid of previous balls
		c.setColor (new Color (28, 142, 230));
		c.fillRect (50, 0, 150, 500);
		c.setColor (new Color (28, 142, 230));
		c.fillRect (600, 0, 150, 500);
		//draws new balls in new location
		c.setColor (new Color (255, 0, 0));
		c.fillOval (50, 140 + itvl [j], 150, 150);
		c.setColor (new Color (255, 255, 0));
		c.fillOval (600, 140 + itvl [j], 150, 150);
		//draws the face for red ball
		c.setColor (new Color (255, 255, 255));
		c.fillArc (70, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (130, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (70, 180 + itvl [j], 85, 85, 180, 180);
		c.setColor (new Color (255, 0, 0));
		c.fillArc (72, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (132, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (70, 205 + itvl [j], 85, 30, 180, 180);
		//draws the face for the yellow ball
		c.setColor (new Color (255, 150, 0));
		c.fillArc (70 + 575, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (130 + 575, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (70 + 575, 180 + itvl [j], 85, 85, 180, 180);
		c.setColor (new Color (255, 255, 0));
		c.fillArc (72 + 575, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (132 + 575, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (70 + 575, 205 + itvl [j], 85, 30, 180, 180);
		idx++; //increment idx
		pauseProgram (20); //pause the program for a 20 milliseconds for illusion of animation
	    }
	    //loops through the itvl array to stimulate "bounce", this is the going upward motion
	    for (int j = 19 ; j >= 0 ; j--)
	    {
		//gets rid of previous balls
		c.setColor (new Color (28, 142, 230));
		c.fillRect (50, 0, 150, 500);
		c.setColor (new Color (28, 142, 230));
		c.fillRect (600, 0, 150, 500);
		//draws new balls in new location
		c.setColor (new Color (255, 0, 0));
		c.fillOval (50, 140 + itvl [j], 150, 150);
		c.setColor (new Color (255, 255, 0));
		c.fillOval (600, 140 + itvl [j], 150, 150);
		//draws the face for red ball
		c.setColor (new Color (0, 0, 0));
		c.fillArc (70, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (130, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (70, 180 + itvl [j], 85, 85, 180, 180);
		c.setColor (new Color (255, 0, 0));
		c.fillArc (72, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (132, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (70, 205 + itvl [j], 85, 30, 180, 180);
		//draws the face for the yellow ball
		c.setColor (new Color (142, 36, 199));
		c.fillArc (70 + 575, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (130 + 575, 170 + itvl [j], 25, 50, 0, 180);
		c.fillArc (70 + 575, 180 + itvl [j], 85, 85, 180, 180);
		c.setColor (new Color (255, 255, 0));
		c.fillArc (72 + 575, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (132 + 575, 183 + itvl [j], 21, 27, 0, 180);
		c.fillArc (70 + 575, 205 + itvl [j], 85, 30, 180, 180);
		idx++; //increment idx
		pauseProgram (20); //pause the program for a 20 milliseconds for illusion of animation
	    }
	}
	pauseProgram (400); //pauses the program for 0.4 seconds before entering the main menu
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
    public SplashScreen (Console cn){
	c = cn;
    }
    public void run(){
	draw();
    }
}
