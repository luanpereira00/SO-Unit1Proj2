import java.util.ArrayList;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;

class RunnableDemo implements Runnable {
   private Thread thread;
   private String threadName;
   
   RunnableDemo(String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
      }catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (thread == null) {
         thread = new Thread (this, threadName);
         thread.start ();
      }
   }
}

public class SnakeGame {
	private ArrayList<Snake> snakeList; //An ArrayList with all Snakes playable
	public Screen board; //The board of the game
	public int pxScale; //Pixel scale of rectangles of snake's body
	
	/**
	 * Constructor for SnakeGame classe
	 */
	public SnakeGame(){
		board = new Screen();
		snakeList = new ArrayList<Snake>();
		pxScale = 10;
	}
	
	/**
	 * Creates snake's body for the one that is being created 
	 * @param start The start Dimension of the snake
	 * @param end The end Dimension of the snake
	 * @return Return a ArrayList with snake's body
	 */
	private ArrayList<Rectangle> createBody(Dimension start, Dimension end) {
		ArrayList<Rectangle> body = new ArrayList<Rectangle>();
		int xPos = (int) start.getWidth();
		int yPos = (int) start.getHeight();
		while(xPos < (int) end.getWidth()  && yPos == (int) end.getHeight()) {
			body.add(new Rectangle(xPos, yPos, pxScale, pxScale));
			xPos += pxScale;
		}
		return body;
	}
	
	/**
	 * Add a snake to snakeList's array
	 * @param color The 
	 * @param userOrIA
	 */
	public void addSnake(Color color, boolean userOrIA) {	
		Dimension start = new Dimension(0,20);
		Dimension end = new Dimension(40,20);
		snakeList.add(new Snake(color, createBody(start, end), userOrIA));
	}
	
	public static void main(String args[]) {
		SnakeGame game = new SnakeGame();
		
	   	game.addSnake(Color.yellow, false);
	   	//while(True) {
	   		//board.printSnakes();
			//board.movementSnakes();
			//if(board.ArrayList().isEmpty()){
				//break //finish the loop
			//}
		//}
		//RunnableDemo R1 = new RunnableDemo( "Thread-1");
		//R1.start();
      
		//RunnableDemo R2 = new RunnableDemo( "Thread-2");
		//R2.start();
   }   
}