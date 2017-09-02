import java.util.Deque;

public class Snake{
	public Deque<Square> body;
	public boolean userOrIA;
	public Color color;

	/**
	* Constructor for Snake class.
	*/
	public Snake(Color color, Deque<Square> body, boolean userOrIA){
		this.color = color;
		this.userOrIA = userOrIA;
		this.body = body;
	}

	/**
	* @return Returns snake's head
	*/
	public Square getHead(){
		return body.getFirst();
	}

	/**
	* @return Returns snake's tail
	*/
	public Square getTail(){
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
	public void addHead(Square head){
		body.addFirst(head);
	}

	public void moviment(Square head){
		
		removeTail();
	}
	
	
}
