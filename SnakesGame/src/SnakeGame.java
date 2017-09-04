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
	
	public boolean checkColision(DrawingSnake d) {
		if(hadColided(d)) {
			sendToErase(d);
			System.out.println("Colidiu!");
			return true;
		}else return false;
	}
	
	public boolean hadColided(DrawingSnake toCheck) {
		Rectangle rect = toCheck.getSnake().getHead();
		for(DrawingSnake d : snakeList) {
			if(!toCheck.equals(d)) {
				for(Rectangle r : d.getSnake().body) {
					if(rect.equals(r)) return true;
					//Verifica se bateu na borda
					if(rect.getX() <= 20 || rect.getX() >= (board.getWidth() - 20)) return true;
					if(rect.getY() <= 20 || rect.getY() >= (board.getHeight() - 20)) return true;
				}
			}else { //verifica se bateu nele mesmo
				for(int i = 1; i < toCheck.getSnake().body.size(); i++) {
					Rectangle r = toCheck.getSnake().body.get(i);
					if(rect.equals(r)) return true;
				}
			}
		}	
		return false;
	}
	
	public void sendToErase(DrawingSnake d) {		
		//for(Rectangle r: d.getSnake().body) {
			//board.erase(r);
		//}
		//TODO - Falara ainda tirar o body do Screen, ele só não está desennhado mas ainda existe
		//d.getSnake().body.clear();
	}
	
	public static void main(String args[]) {
		SnakeGame game = new SnakeGame();
		game.board.erase();
		
		//ADD COBRINHAS COMO PAREDES?
		
		game.addDSnake(Color.blue, false);
		game.addDSnake(Color.green, false);
		game.addDSnake(Color.yellow, false);
		game.addDSnake(Color.gray, false);
		game.addDSnake(Color.cyan, false);
		game.addDSnake(Color.white, false);
		game.addDSnake(Color.pink, false);

		while (!game.snakeList.isEmpty()) {
			
			for(DrawingSnake d : game.snakeList) {
				Thread t = new Thread(d); 
				t.start();
				if(game.checkColision(d)) {
					d.stop = true;
					//game.snakeList.remove(d);
				}
				game.board.wait(50);
			}
		}
	   	
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