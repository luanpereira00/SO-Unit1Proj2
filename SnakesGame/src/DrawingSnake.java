import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class DrawingSnake implements Runnable {
	public Snake snake;
    private Screen screen;
    private int pxScale;
	
	public DrawingSnake (Screen screen, int pxScale) {
		this.screen = screen;
		this.pxScale = pxScale;
	}
	
	public void run () {
		for(Rectangle r : snake.body) {
			screen.setForegroundColor(Color.black);
			screen.fill(r);
			screen.setForegroundColor(snake.getColor());
			screen.fill(r);
		}
	}
	
	public Snake buildSnake (Color color, boolean userOrIA) {
		Dimension start = randDimension();
		Dimension end = randDimensionInLine(start, 10);
		snake = new Snake(color, createBody(start, end), userOrIA);
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
					body.add(0, new Rectangle(xPos, yPos, pxScale, pxScale));
					xPos += pxScale;
				}
			} else { //horizontal snakes headed for right
				while(xPos >= (int) end.getWidth()) {
					body.add(0, new Rectangle(xPos, yPos, pxScale, pxScale));
					xPos -= pxScale;
				}
			}
		} 
		else { //vertical snakes, by consequence
			if(yPos-end.getHeight()<0) { //vertical snakes headed for up
				while(yPos <= (int) end.getHeight()) {
					body.add(0, new Rectangle(xPos, yPos, pxScale, pxScale));
					yPos += pxScale;
				}
			}
			else { //vertical snakes headed for down
				while(yPos >= (int) end.getHeight()) {
					body.add(0, new Rectangle(xPos, yPos, pxScale, pxScale));
					yPos -= pxScale;
				}
			}
		}
		
		return body;
	}
	
	public void movementSnake() {
		Rectangle rect = chooseNextHeadPosition(snake);
		//System.out.println("Rect " + rect.getX()  + " <> " + rect.getY());
		snake.moviment(rect);
	}
	
	private Rectangle chooseNextHeadPosition(Snake snake) {
		Rectangle nextPos = new Rectangle();
		Random rnd = new Random();
		int chooser = rnd.nextInt();
		if (chooser<0) chooser*=-1;
		chooser = chooser % 10; //0 or 4 ==front, 1==left, 2==right
		//System.out.println("chooser " + chooser);
		//System.out.println(snake.getHead().getX() + " snake " + snake.getHead().getY());
		if(chooser>1) {
			nextPos = moveForward(snake);
		} else if(chooser == 0) {
			nextPos = moveToLeft(snake);
		} else {
			nextPos = moveToRight(snake);
		}
		//System.out.println(nextPos.getWidth() + " nextPos " + nextPos.getHeight());
		return nextPos;
	}
	
	private Rectangle moveForward(Snake snake) {
		Rectangle rect = new Rectangle();
		int xPos=0;
		int yPos=0;
		
		if(snake.getHead().getX()==snake.getTail().getX()) {	//vertical snakes
			if(snake.getHead().getY()-snake.getTail().getY()>0) { //downing snakes
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()+pxScale;
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {															//upping snakes
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()-pxScale;
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}		
		}
		
		if(snake.getHead().getY()==snake.getTail().getY()) { //horizontal snakes
			if(snake.getHead().getX()-snake.getTail().getX()>0) { //for right snakes
				xPos = (int)snake.getHead().getX()+pxScale;
				yPos = (int)snake.getHead().getY();
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {														//for left snakes
				xPos = (int)snake.getHead().getX()-pxScale;
				yPos = (int)snake.getHead().getY();
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
		}
		//System.out.println(xPos + " forward " + yPos);
		return rect;
	}
	
	private Rectangle moveToLeft(Snake snake) {
		Rectangle rect = new Rectangle();
		int xPos=0;
		int yPos=0;
		if(snake.getHead().getX()==snake.getTail().getX()) {	//vertical snakes
			if(snake.getHead().getY()-snake.getTail().getY()>0) { //downing snakes
				xPos = (int)snake.getHead().getX()+pxScale;
				yPos = (int)snake.getHead().getY();
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {															//upping snakes
				xPos = (int)snake.getHead().getX()-pxScale;
				yPos = (int)snake.getHead().getY();
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}		
		}
		
		if(snake.getHead().getY()==snake.getTail().getY()) { //horizontal snakes
			if(snake.getHead().getX()-snake.getTail().getX()>0) { //for right snakes
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()-pxScale;
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {														//for left snakes
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()+pxScale;
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
		}
		//System.out.println(xPos + " to left " + yPos);
		return rect;
	}
	
	private Rectangle moveToRight(Snake snake) {
		Rectangle rect = new Rectangle();
		int xPos=0;
		int yPos=0;
		if(snake.getHead().getX()==snake.getTail().getX()) {	//vertical snakes
			if(snake.getHead().getY()-snake.getTail().getY()>0) { //downing snakes
				xPos = (int)snake.getHead().getX()-pxScale;
				yPos = (int)snake.getHead().getY();
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {															//upping snakes
				xPos = (int)snake.getHead().getX()+pxScale;
				yPos = (int)snake.getHead().getY();
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}		
		}
		
		if(snake.getHead().getY()==snake.getTail().getY()) { //horizontal snakes
			if(snake.getHead().getX()-snake.getTail().getX()>0) { //for right snakes
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()+pxScale;
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {														//for left snakes
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()-pxScale;
				rect = new Rectangle(xPos, yPos,pxScale, pxScale);
			}
		}
		//System.out.println(xPos + " to right " + yPos);
		return rect;
	}
	
	
	public Snake getSnake () {
		return snake;
	}

}