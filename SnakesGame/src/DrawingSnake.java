import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;


public class DrawingSnake implements Runnable {
	public Snake snake;
	private final int width = 10;
    private final int height = 10;
    private Screen screen;
	
	public DrawingSnake () {
		screen = new Screen();
	}
	
	public void run () {
		buildSnake();
//		screen.setForegroundColor(Color.green);
		System.out.println("alow");
		for(Rectangle e : snake.body) {
			screen.fill(e);
		}
	}
	
	public void buildSnake () {
		ArrayList<Rectangle> body = new ArrayList<Rectangle>();
		int xPos = 100, yPos = 100; 
		for(int i = 0; i < 10; i++) { //criando o corpo de uma cobrinha
			screen.setForegroundColor(Color.green);
			Rectangle e = new Rectangle (xPos, yPos, width, height);
			body.add(e);
			xPos += 10;
		}
		snake = new Snake (Color.green, body, true);
	}
	
	public static void main (String[] args) {
		DrawingSnake d = new DrawingSnake();
		Thread t = new Thread(d);
		t.start();
	}
}

//class Main {
//	public static void main (String[] args) {
//		DrawingSnake d = new DrawingSnake();
//		Thread t = new Thread(d);
//		t.start();
//	}
//}