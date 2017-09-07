package soGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class DrawingSnake implements Runnable {
	private Snake snake;
    private Screen screen;
    private int pxScale;
    public boolean stop;
	
	public DrawingSnake (Screen screen, int pxScale) {
		this.screen = screen;
		this.pxScale = pxScale;
		stop = false; 
	}
	
	public void run () {
		printSnake();
		
		if(!stop) movementSnake();
	}
	
	public void buildSnake (Color color, boolean userOrIA) {
		Dimension start = randDimension();
		Dimension end = randDimensionInLine(start, 10);
		snake = new Snake(color, createBody(start, end), userOrIA);
	}
	
	//FIXME Impedir que sejam criadas proximas a parede
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
	 * @param howManyBodyPart A variable that sets how long will be the snake body
	 * @return Return a dimension
	 */
	public Dimension randDimensionInLine(Dimension start, int howManyBodyPart) {	
		Random rnd = new Random();
		
		int xPos = (int) start.getWidth();
		int yPos = (int) start.getHeight();
 		
		if(rnd.nextBoolean()) {//creates a horizontal snake
			if(start.getWidth()<(screen.getSize().getWidth()/2)) {
				xPos=xPos + howManyBodyPart*pxScale;
			} else {
				xPos=xPos - howManyBodyPart*pxScale;
			}
		}
		else { // creates a vertical snake
			if(start.getHeight()<(screen.getSize().getHeight()/2)) {
				yPos=yPos + howManyBodyPart*pxScale;
			} else {
				yPos=yPos - howManyBodyPart*pxScale;
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
	
	public void printSnake() {
		for(Rectangle r : snake.body) {
			screen.setForegroundColor(snake.getColor());
			screen.fill(r);
		}
	}
	
	/**
	 * Movement the snake in this class
	 */
	private void movementSnake() {
		Rectangle rect = chooseNextHeadPosition();
		Rectangle r = snake.moviment(rect);
		screen.erase(r);
		screen.movimentSide = 0;
	}
	
	/**
	 * Choose by random which one direction will be the next for snake
	 * @return The removed tail
	 */
	private Rectangle chooseNextHeadPosition() {
		Random rnd = new Random();
		int chooser = rnd.nextInt();
		
		if(!snake.userOrIA) { // false = IA
			if (chooser<0) chooser*=-1;
			chooser = chooser % 10; 
			if(chooser>1) { 			//80%
				return moveForward();
			} else if(chooser == 0) { 	//10%
				return moveToLeft();
			} else { 					//10%
				return moveToRight();
			}
		} else { // true = user
			// 0 = forward
			// 1 = left
			// 2 = left
			// 3 = right
			// 4 = right
			
			//FIXME Aplicar boas pr�ticas, acoplamento, coes�o e modulariza��o
			if(snake.getHead().getX()==snake.getTail().getX()) {	
				//If a snake is on vertical
				if(snake.getHead().getY()-snake.getTail().getY()>0) { 
					//If a snake is headed for down
					if (screen.movimentSide == 0 || screen.movimentSide == 1 || screen.movimentSide == 4) return moveForward();
					if (screen.movimentSide == 2) return moveToRight();
					if (screen.movimentSide == 3) return moveToLeft();
				}
				else {													
					//If a snake is headed for up, by consequence
					if (screen.movimentSide == 0 || screen.movimentSide == 1 || screen.movimentSide == 4) return moveForward();
					if (screen.movimentSide == 2) return moveToLeft();
					if (screen.movimentSide == 3) return moveToRight();
				}		
			}
			
			else { 													
				//If a snake is on horizontal, by consequence
				if(snake.getHead().getX()-snake.getTail().getX()>0) { 
					//If a snake is headed for right
					if (screen.movimentSide == 0) return moveForward();
					if (screen.movimentSide == 1) return moveToLeft();
					if (screen.movimentSide == 2) return moveForward();
					if (screen.movimentSide == 3) return moveForward();
					if (screen.movimentSide == 4) return moveToRight();
				}
				else {													
					//If a snake is headed for left, by consequence
					if (screen.movimentSide == 0) return moveForward();
					if (screen.movimentSide == 1) return moveToRight();
					if (screen.movimentSide == 2) return moveForward();
					if (screen.movimentSide == 3) return moveForward();
					if (screen.movimentSide == 4) return moveToLeft();
				}
			}
			return moveForward();
		}
	}
	
	/**
	 * Move a snake for front, based on for where it is headed
	 * @return The removed tail
	 */
	private Rectangle moveForward() {
		int xPos=0;
		int yPos=0;
		
		if(snake.getHead().getX()==snake.getTail().getX()) {	
			//If a snake is on vertical
			if(snake.getHead().getY()-snake.getTail().getY()>0) { 
				//If a snake is headed for down
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()+pxScale;
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {													
				//If a snake is headed for up, by consequence
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()-pxScale;
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}		
		}
		
		else { 													
			//If a snake is on horizontal, by consequence
			if(snake.getHead().getX()-snake.getTail().getX()>0) { 
				//If a snake is headed for right
				xPos = (int)snake.getHead().getX()+pxScale;
				yPos = (int)snake.getHead().getY();
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {													
				//If a snake is headed for left, by consequence
				xPos = (int)snake.getHead().getX()-pxScale;
				yPos = (int)snake.getHead().getY();
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
		}
	}
	
	/**
	 * Move a snake for left, based on for where it is headed
	 * @return The removed tail
	 */
	private Rectangle moveToLeft() {
		int xPos=0;
		int yPos=0;
		if(snake.getHead().getX()==snake.getTail().getX()) {	
			//If a snake is on vertical
			if(snake.getHead().getY()-snake.getTail().getY()>0) { 
				//If a snake is headed for down
				xPos = (int)snake.getHead().getX()+pxScale;
				yPos = (int)snake.getHead().getY();
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {															
				//If a snake is headed for up, by consequence
				xPos = (int)snake.getHead().getX()-pxScale;
				yPos = (int)snake.getHead().getY();
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}		
		}
		else { 
			//If a snake is on horizontal, by consequence
			if(snake.getHead().getX()-snake.getTail().getX()>0) { 
				//If a snake is headed for right
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()-pxScale;
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {														
				//If a snake is headed for left, by consequence
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()+pxScale;
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
		}
	}
	
	/**
	 * Move a snake for right, based on for where it is headed
	 * @return The removed tail
	 */
	private Rectangle moveToRight() {
		int xPos=0;
		int yPos=0;
		if(snake.getHead().getX()==snake.getTail().getX()) {	
			//If a snake is on vertical
			if(snake.getHead().getY()-snake.getTail().getY()>0) { 
				//If a snake is headed for down
				xPos = (int)snake.getHead().getX()-pxScale;
				yPos = (int)snake.getHead().getY();
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {															
				//If a snake is headed for up, by consequence
				xPos = (int)snake.getHead().getX()+pxScale;
				yPos = (int)snake.getHead().getY();
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}		
		}
		else {
			//If a snake is on horizontal, by consequence
			if(snake.getHead().getX()-snake.getTail().getX()>0) { 
				//If a snake is headed for right
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()+pxScale;
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
			else {														
				//If a snake is headed for left, by consequence
				xPos = (int)snake.getHead().getX();
				yPos = (int)snake.getHead().getY()-pxScale;
				return new Rectangle(xPos, yPos,pxScale, pxScale);
			}
		}
	}
	
	/**
	 * Return the snake
	 * @return The snake
	 */
	public Snake getSnake () {
		return snake;
	}

}