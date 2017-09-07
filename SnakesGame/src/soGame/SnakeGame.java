package soGame;

import java.util.ArrayList;
import java.util.Scanner;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class SnakeGame {
	private ArrayList<DrawingSnake> snakeList; //An ArrayList with all Snakes playable
	public Screen board; //The board of the game
	public int pxScale;  //Pixel scale of rectangles of snake's body
	
	/**
	 * Constructor for SnakeGame class
	 */
	public SnakeGame(){
		pxScale = 10;
		board = new Screen(pxScale);
		snakeList = new ArrayList<DrawingSnake>();	
	}

	
	/**
	 * Add a snake to snakeList's array
	 * @param color The snake color
	 * @param userOrIA A flag that says who control the snake
	 */
	public void addDSnake(Color color, boolean userOrIA) {	
		DrawingSnake d = new DrawingSnake(board, pxScale);
		snakeList.add(d);
		d.buildSnake(color, userOrIA);
	}
	
	/**
	 * Check if the snake present in DrawingSnake collided in something and send to erase if collision is true
	 * @param d The DrawingSnake that has a snake
	 * @return True if collided, false in others cases
	 */
	public boolean checkColision(DrawingSnake d) {
		if(hadColided(d)) {
			//FIXME consertar esse sendToErase
			//sendToErase(d);
			//System.out.println("Colidiu!");
			return true;
		}else return false;
	}
	
	/**
	 * Check if a rectangle passed had collided with some wall
	 * @param rect The rectangle to test
	 * @return True if collided, false in others cases
	 */
	public boolean checkWallColision(Rectangle rect) {
		if(checkTopColision(rect)) return true;
		if(checkBottomColision(rect)) return true;
		if(checkLeftColision(rect)) return true;
		if(checkRightColision(rect)) return true;
		return false;
	}
	
	/**
	 * Check if a rectangle passed had collided in top of screen
	 * @param rect The rectangle to test
	 * @return True if collided, false in others cases
	 */
	private boolean checkTopColision(Rectangle rect) {
		if((int)rect.getY()==0) return true;
		return false;
	}
	
	/**
	 * Check if a rectangle passed had collided in bottom of screen
	 * @param rect The rectangle to test
	 * @return True if collided, false in others cases
	 */
	private boolean checkBottomColision(Rectangle rect) {
		if((int)rect.getY()==board.getHeight()-pxScale) return true;
		return false;
	}
	
	/**
	 * Check if a rectangle passed had collided in left of screen
	 * @param rect The rectangle to test
	 * @return True if collided, false in others cases
	 */
	private boolean checkLeftColision(Rectangle rect) {
		if((int)rect.getX()==0) return true;
		return false;
	}
	
	/**
	 * Check if a rectangle passed had collided in right of screen
	 * @param rect The rectangle to test
	 * @return True if collided, false in others cases
	 */
	private boolean checkRightColision(Rectangle rect) {
		if((int)rect.getX()==board.getWidth()-pxScale) return true;
		return false;
	}
	
	/**
	 * Check if a drawing snake passed had collided with his snake's body
	 * @param toCheck The drawing snake that contains the snake that is going to be checked
	 * @return True if collided, false in others cases
	 */
	public boolean checkSelfColision(DrawingSnake toCheck) {
		Rectangle rect = toCheck.getSnake().getHead();
		for(int i = 1; i < toCheck.getSnake().body.size(); i++) {
			Rectangle r = toCheck.getSnake().body.get(i);
			if(rect.equals(r)) return true;
		}
		return false;
	}
	
	/**
	 * Check if a drawing snake passed had collided with something
	 * @param toCheck
	 * @return
	 */
	public boolean hadColided(DrawingSnake toCheck) {
		Rectangle rect = toCheck.getSnake().getHead();
		if(checkWallColision(rect)) return true;
		if(checkSelfColision(toCheck)) return true;
		
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
		for(Rectangle r: d.getSnake().body) {
			board.erase(r);
		}
	}
	
	public void gameAI () {
		addDSnake(Color.blue, false);
		addDSnake(Color.green, false);
		addDSnake(Color.yellow, false);
		addDSnake(Color.gray, false);
		addDSnake(Color.cyan, false);
		addDSnake(Color.white, false);
		addDSnake(Color.pink, false);
		addDSnake(Color.magenta, false);
		addDSnake(Color.orange, false);
		addDSnake(Color.LIGHT_GRAY, false);
		
		
		while (!snakeList.isEmpty()) {
			//TODO Alerta visual da morte de uma cobrinha
			boolean tRemove = false;
			DrawingSnake r = new DrawingSnake(board, 15);
			//game.board.wait(100);
			for(DrawingSnake d : snakeList) {
				d.printSnake();
				
				Thread t = new Thread(d); 
				t.start();
				//board.wait(10);
				if(checkColision(d)) {
					d.stop = true;
					tRemove = true;
					r = d;
					break;								
				}
				//FIXME tempo de espera na thread, muito lento no comeco e rapido demais no fim
				board.wait(20);
			}
			
			if(tRemove) {
				//FIXME Resolver remocao das cobrinhas
				sendToErase(r);
				snakeList.remove(r);
				
			}
		}
	}
	
	public void gameHP () {
		addDSnake(Color.pink, true);
		
		while (!snakeList.isEmpty()) {
			//TODO Alerta visual da morte de uma cobrinha
			boolean tRemove = false;
			DrawingSnake r = new DrawingSnake(board, 10);
			//game.board.wait(100);
			for(DrawingSnake d : snakeList) {
				Thread t = new Thread(d);
				t.start();
				
				board.wait(100);
				if(checkColision(d)) {
					d.stop = true;
					tRemove = true;
					r = d;
					break;								
				}
				//FIXME tempo de espera na thread, muito lento no comeco e rapido demais no fim
				board.wait(20);
				board.movimentSide = 0;
			}
			
			if(tRemove) {
				//FIXME Resolver remocao das cobrinhas
				sendToErase(r);
				snakeList.remove(r);
			}
		}
		
		board.gameOverScreen();
	}
	
	public void telaChoices () {
		System.out.println("+-------------------------------+");
		System.out.println("|         Game's Options        |");
		System.out.println("+-------------------------------+");
		System.out.println("| ( 1 ) Artifitial Intelligence |");
		System.out.println("|-------------------------------|");
		System.out.println("| ( 2 ) Humam Player            |");
		System.out.println("|-------------------------------|");
		System.out.println("| ( 0 ) Exit                    |");
		System.out.println("+-------------------------------+");
	}

	public static void main(String args[]) {
		SnakeGame game = new SnakeGame();
		boolean exit = false;
		
		//FIXME Interface Toda
		while(!exit) {
			int choice;
			Scanner s = new Scanner(System.in);
			game.telaChoices();
			
			System.out.println("Choose an option: ");
			choice = s.nextInt();
			
			switch(choice) {
				case 0:
					exit = true;
					break;
				case 1: //ai
					game.gameAI();
					break;
				case 2: //Humam player
					game.gameHP();
					break;	
				default:
					System.out.println(" There isn't this choise! ");
			}
		}
		game.board.erase();
		System.exit(0);
   }   
}