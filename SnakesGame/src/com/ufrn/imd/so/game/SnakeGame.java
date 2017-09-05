package com.ufrn.imd.so.game;
import java.util.ArrayList;
import com.ufrn.imd.so.view.*;
import java.awt.Color;
import java.awt.Rectangle;

public class SnakeGame {
	private ArrayList<DrawingSnake> snakeList; //An ArrayList with all Snakes playable
	public Screen board; //The board of the game
	public int pxScale;  //Pixel scale of rectangles of snake's body
	public Menu menu;
	
	/**
	 * Constructor for SnakeGame class
	 */
	public SnakeGame(){
		pxScale = 10;
		board = new Screen(pxScale);
		snakeList = new ArrayList<DrawingSnake>();	
		menu = new Menu(board);
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
		//TODO - Falta ainda tirar o body do Screen, ele só não está desennhado mas ainda existe
		//d.getSnake().body.clear();
	}
	

	public static void main(String args[]) {
		SnakeGame game = new SnakeGame();
		
		game.addDSnake(Color.blue, false);
		game.addDSnake(Color.green, false);
		game.addDSnake(Color.yellow, false);
		game.addDSnake(Color.gray, false);
		game.addDSnake(Color.cyan, false);
		game.addDSnake(Color.white, false);
		game.addDSnake(Color.pink, false);		
		
		//FIXME Interface Toda
//		while(true) {
//			switch(game.menu.getChoise()) {
//				case 0:
//					break;
//				case 1: //ai
					while (!game.snakeList.isEmpty()) {
						//TODO Alerta visual da morte de uma cobrinha
						boolean tRemove = false;
						DrawingSnake r = new DrawingSnake(game.board, 10);
						//game.board.wait(100);
						for(DrawingSnake d : game.snakeList) {
							Thread t = new Thread(d); 
							t.start();
							game.board.wait(50);
							if(game.checkColision(d)) {
								d.stop = true;
								tRemove = true;
								r = d;
								break;								
							}
							//FIXME tempo de espera na thread, muito lento no comeco e rapido demais no fim
							
						}
						
						if(tRemove) {
							//FIXME Resolver remocao das cobrinhas
							game.sendToErase(r);
							game.snakeList.remove(r);
							
						}
					}
					//System.out.println();
//					break;
//				case 2: //Humam player
//					
//					break;			
//			}
			game.board.erase();
			System.exit(0);
		//}			
   }   
}