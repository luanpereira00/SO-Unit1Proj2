import java.util.ArrayList;
import java.awt.Color;
import java.awt.Rectangle;


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
	
	public void checkColision(DrawingSnake d) {
		if(hadColided(d)) {
			sendToErase(d);
		}
	}
	
	public boolean hadColided(DrawingSnake toCheck) {
		Rectangle rect = toCheck.getSnake().getHead();
		for(DrawingSnake d : snakeList) {
			if(!toCheck.equals(d)) {
				for(Rectangle r : d.getSnake().body) {
					if(rect.equals(r)) return true;
				}
			}
		}	
		return false;
	}
	
	public void sendToErase(DrawingSnake d) {
		System.out.println("Colidiu");
	}
	
	public static void main(String args[]) {
		SnakeGame game = new SnakeGame();
		game.board.menu();
		
		//ADD COBRINHAS COMO PAREDES?
		
		game.addDSnake(Color.blue, false);
		game.addDSnake(Color.green, false);
		game.addDSnake(Color.yellow, false);
		game.addDSnake(Color.gray, false);
		game.addDSnake(Color.cyan, false);
		game.addDSnake(Color.white, false);
		game.addDSnake(Color.pink, false);
		
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
				game.checkColision(d);
			}	
			for(DrawingSnake d : game.snakeList) {
				Thread t = new Thread(d);
				t.start();
				game.board.wait(50);
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