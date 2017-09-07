package soGame;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Rectangle;

public class Snake{
	public ArrayList<Rectangle> body;
	public boolean userOrIA;
	public Color color;

	/**
	* Constructor for Snake class.
	*/
	public Snake(Color color, ArrayList<Rectangle> body, boolean userOrIA){
		this.color = color;
		this.userOrIA = userOrIA;
		this.body = body;
	}

	/**
	* @return 'True' if snake is controlled by user and 'False' if snake is controlled by IA.
	*/
	public boolean isUser(){
		return userOrIA;
	}

	/**
	* @return Return snake's color
	*/
	public Color getColor(){
		return color;
	}
	
	public Rectangle getHead() {
		return body.get(0);
	}
	
	public Rectangle getTail() {
		return body.get(1);
	}
	
	/**
	* Remove snake's tail
	*/
	private Rectangle removeTail(){
		Rectangle r = body.get(body.size()-1);
		body.remove(body.size()-1);
		return r;
	}
	
	/**
	* Add snake's head
	* @param head Snake's head
	*/
	public void addHead(Rectangle head){
		body.add(0, head);
	}
	
	/**
	* Movement the snake to some direction
	* @param head Rectangle with the next position of the head
	*/
	public Rectangle moviment(Rectangle head){	
		addHead(head);
		return removeTail();
	}
}
