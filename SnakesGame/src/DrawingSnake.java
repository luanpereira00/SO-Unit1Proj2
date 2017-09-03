import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;


public class DrawingSnake implements Runnable {
	public Snake snake;
	private final int width = 10;
    private final int height = 10;
    private Screen screen;
	
	public DrawingSnake (/*Screen screen*/) {
		//this.screen = screen;
	}
	
	public void run () {
		for(Rectangle r : snake.body) {
			screen.fill(r);
		}
	}
	
	public Snake buildSnake (Color color, boolean userOrIA) {
		Dimension start = new Dimension(50,20);
		Dimension end = new Dimension(150,20);
		snake = new Snake(color, createBody(start, end), userOrIA);
		return snake;
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
			body.add(new Rectangle(xPos, yPos, width, height));
			xPos += width;
		}
		return body;
	}
	
	
	public Snake getSnake () {
		return snake;
	}

}