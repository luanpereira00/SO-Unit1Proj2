import java.util.ArrayList;
import java.awt.Color;


public class SnakeGame {
	private ArrayList<DrawingSnake> snakeList; //An ArrayList with all Snakes playable
	public Screen board; //The board of the game
	public int pxScale;  //Pixel scale of rectangles of snake's body
	
	/**
	 * Constructor for SnakeGame classe
	 */
	public SnakeGame(){
		board = new Screen();
		snakeList = new ArrayList<DrawingSnake>();
		pxScale = 10;
	}
	
	/**
	 * Add a snake to snakeList's array
	 * @param color The 
	 * @param userOrIA
	 */
	public void addDSnake(Color color, boolean userOrIA) {	
		DrawingSnake d = new DrawingSnake(board, pxScale);
		
		snakeList.add(d);
		d.buildSnake(color, userOrIA);
	}
	
	public static void main(String args[]) {
		SnakeGame game = new SnakeGame();
		game.board.menu();
		
		game.addDSnake(Color.blue, false);
		game.addDSnake(Color.green, false);
		game.addDSnake(Color.yellow, false);
		game.addDSnake(Color.gray, false);
		
		for(DrawingSnake d : game.snakeList) {
			Thread t = new Thread(d);
			t.start();
			//game.board.wait(1000);
		}
		game.board.wait(200);
		int i = 0;
		while (true) {
			for(DrawingSnake d : game.snakeList) {
				d.movementSnake();
			}	
			for(DrawingSnake d : game.snakeList) {
				Thread t = new Thread(d);
				t.start();
				game.board.wait(100);
			}
			
			i++;
		}
		
		//game.addDSnake(Color.white, false);
		
//		for(DrawingSnake d  : game.snakeList) {
//			Thread t = new Thread(d);
//			t.start();
//		}
		
	   	
	   	//System.out.println(d.getHeight() + " - " + d.getWidth()); 
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