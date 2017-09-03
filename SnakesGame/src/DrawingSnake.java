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
    private int pxScale;
	
	public DrawingSnake (Screen screen, int pxScale) {
		this.screen = screen;
		this.pxScale = pxScale;
	}
	
	public void run () {
		for(Rectangle r : snake.body) {
			screen.setForegroundColor(snake.getColor());
			screen.fill(r);
		}
	}
	
	public Snake buildSnake (Color color, boolean userOrIA) {
		Dimension start = randDimension();
		Dimension end = randDimensionInLine(start, 10);
		snake = new Snake(color, createBody(start, end), userOrIA);
		System.out.println(snake.getHead().getWidth() + " - " + snake.getHead().getHeight());
		return snake;
	}
	
	/**
	 * Creates a random dimension for start of snake's body
	 * @return Return a dimension
	 */
	public Dimension randDimension() {	
		Random rnd = new Random();
		int xPos = rnd.nextInt();
		int yPos = rnd.nextInt();
		
		if(xPos<0) xPos*=-1;
		if(yPos<0) yPos*=-1;
		
		xPos = xPos % (int) screen.getSize().getWidth()/pxScale;
		yPos = yPos % (int) screen.getSize().getHeight()/pxScale;	
		
		return new Dimension(xPos*pxScale, yPos*pxScale);
	}
	
	/**
	 * Creates a random dimension for end of snake's body based on start
	 * @param start The start of snake's body
	 * @param bodyDimension A variable that sets how long will be the snake
	 * @return Return a dimension
	 */
	public Dimension randDimensionInLine(Dimension start, int bodyDimension) {	
		Random rnd = new Random();
		
		int xPos = (int) start.getWidth();
		int yPos = (int) start.getHeight();
 		
		if(rnd.nextBoolean()) {//creates a horizontal snake
			if(start.getWidth()<(screen.getSize().getWidth()/2)) {
				xPos=xPos + bodyDimension*pxScale;
			} else {
				xPos=xPos - bodyDimension*pxScale;
			}
		}
		else { // creates a vertical snake
			if(start.getHeight()<(screen.getSize().getHeight()/2)) {
				yPos=yPos + bodyDimension*pxScale;
			} else {
				yPos=yPos - bodyDimension*pxScale;
			}
		}		
		return new Dimension(xPos, yPos);
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
		if(yPos == (int) end.getHeight()) { //horizontal snakes
			if(xPos-end.getWidth()<0) { //horizontal snakes headed for left
				while(xPos <= (int) end.getWidth()) {
					body.add(new Rectangle(xPos, yPos, pxScale, pxScale));
					xPos += pxScale;
				}
			} else { //horizontal snakes headed for right
				while(xPos >= (int) end.getWidth()) {
					body.add(new Rectangle(xPos, yPos, pxScale, pxScale));
					xPos -= pxScale;
				}
			}
		} else { //vertical snakes, by consequence
			if(yPos-end.getHeight()<0) { //vertical snakes headed for up
				while(yPos <= (int) end.getHeight()) {
					body.add(new Rectangle(xPos, yPos, pxScale, pxScale));
					yPos += pxScale;
				}
			}
			else { //vertical snakes headed for down
				while(yPos >= (int) end.getHeight()) {
					body.add(new Rectangle(xPos, yPos, pxScale, pxScale));
					yPos -= pxScale;
				}
			}
		}
		
		return body;
	}
	
	
	public Snake getSnake () {
		return snake;
	}

}