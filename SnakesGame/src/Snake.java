import java.util.Deque;
import java.awt.Color;
import java.awt.Rectangle;

public class Snake{
	public Deque<Rectangle> body;
	public boolean userOrIA;
	public Color color;

	/**
	* Constructor for Snake class.
	*/
	public Snake(Color color, Deque<Rectangle> body, boolean userOrIA){
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
	
	/**
	* Remove snake's tail
	*/
	private void removeTail(){
		body.removeLast();
	}
	
	/**
	* Add snake's head
	* @param head Snake's head
	*/
	private void addHead(Rectangle head){
		body.addFirst(head);
	}
	
	/**
	* Movement the snake to some direction
	* @param head Rectangle with the next position of the head
	*/
	public void moviment(Rectangle head){	
		addHead(head);
		removeTail();
	}
}
