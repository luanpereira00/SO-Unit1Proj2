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
	* @return Returns snake's head
	*/
	public Rectangle getHead(){
		return body.getFirst();
	}

	/**
	* @return Returns snake's tail
	*/
	public Rectangle getTail(){
		return body.getLast();
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
	public void removeTail(){
		body.removeLast();
	}
	
	/**
	* Add snake's head
	* @param head The snake's head
	*/
	public void addHead(Rectangle head){
		body.addFirst(head);
	}

	public void moviment(Rectangle head){	
		removeTail();
	}
	
	
}
