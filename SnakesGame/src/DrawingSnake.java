import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class DrawingSnake implements Runnable {
	public Snake snake;
	private final int width = 10;
    private final int height = 10;
    private Screen screen;
	
	public DrawingSnake (Screen screen) {
		this.screen = screen;
	}
	
	public void run () {
		for(Rectangle r : snake.body) {
			screen.setForegroundColor(snake.getColor());
			screen.fill(r);
		}
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
		
		int xPos = rnd.nextInt() % 400;// - 100;
        int yPos = rnd.nextInt() % 400;// - 100;
        if(xPos < 0) xPos = (xPos * (-1));
        if(yPos < 0) yPos = (yPos * (-1));
        
        System.out.println(xPos);
        System.out.println(yPos);
        
		for(int i = 0; i < 10; i++) {
			body.add(new Rectangle(xPos, yPos, width, height));
			xPos += 10;
		}
		return body;
	}
	
	
	public Snake getSnake () {
		return snake;
	}

}