import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class DrawingSnake implements Runnable {
	public Snake snake;
	private final int width = 10;
    private final int height = 10;
    //private Screen screen;
	
	public DrawingSnake ( ) {
		//screen = new Screen();
	}
	
	public void run () {
//		for(Rectangle r : snake.body) {
//			screen.fill(r);
//		}
	}
	
	public Snake buildSnake (Color color, boolean userOrIA) {
		snake = new Snake(color, createBody(), userOrIA);
		return snake;
	}
	
	/**
	 * Creates snake's body for the one that is being created 
	 * @param start The start Dimension of the snake
	 * @param end The end Dimension of the snake
	 * @return Return a ArrayList with snake's body
	 */
	private ArrayList<Rectangle> createBody() {		
		ArrayList<Rectangle> body = new ArrayList<Rectangle>(); 
		
		Random rnd = new Random();
		
		int xPos = rnd.nextInt() % 600 + 100;
        int yPos = rnd.nextInt() % 400 + 100;
        
		for(int i = 0; i < 10; i++) {
			body.add(new Rectangle(xPos, yPos, width, height));
			yPos += 10;
		}
		return body;
	}
	
	
	public Snake getSnake () {
		return snake;
	}

}