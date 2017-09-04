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
	 * Constructor for SnakeGame classe
	 */
	public SnakeGame(){
		board = new Screen();
		snakeList = new ArrayList<DrawingSnake>();
		pxScale = 10;
		menu = new Menu(board);
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
			//sendToErase(d);
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
		for(Rectangle r: d.getSnake().body) {
			board.erase(r);
		}
		//TODO - Falara ainda tirar o body do Screen, ele só não está desennhado mas ainda existe
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

		
//		while(true) {
//			switch(game.menu.getChoise()) {
//				case 0:
//					break;
//				case 1: //ai
					while (!game.snakeList.isEmpty()) {
						boolean tRemove = false;
						DrawingSnake r = new DrawingSnake(game.board, 10);
						
						for(DrawingSnake d : game.snakeList) {
							Thread t = new Thread(d); 
							t.start();
							if(game.checkColision(d)) {
								d.stop = true;
								tRemove = true;
								r = d;
								break;
								
							}
							game.board.wait(20);
						}
						if(tRemove) {
							game.snakeList.remove(r);
							game.sendToErase(r);
						}
					}
					System.out.println();
//					break;
//				case 2: //Humam player
//					
//					break;			
//			}
//		}
					
		game.board.erase();
   }   
}