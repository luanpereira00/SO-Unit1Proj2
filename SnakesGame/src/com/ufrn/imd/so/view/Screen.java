package com.ufrn.imd.so.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Canvas - a class to allow for simple graphical 
 * drawing on a canvas.
 * 
 * @author Michael Kolling (mik)
 * @author Bruce Quig
 *
 * @version 2008.03.30
 */
public class Screen {
    public JFrame frame;
    private CanvasPane canvas;
    public Graphics2D graphic;
    public Color backgroundColor;
    private Image canvasImage;
    private final int width = 1100;
    private final int height = 600;
    
    /**
     * Create a Screen      
     */
    public Screen() {
    	 frame = new JFrame();
         canvas = new CanvasPane();
         frame.setContentPane(canvas);
         canvas.setPreferredSize(new Dimension(width, height));
         backgroundColor = Color.black;
         frame.pack();
         setVisible(true);
         
         firstScreen();
    }

    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
        
    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    private void setVisible(boolean visible) {
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.yellow);
        }
        frame.setVisible(true);
    }

    /**
     * Draw the outline of a given shape onto the canvas.
     * @param  shape  the shape object to be drawn on the canvas
     */
    public void draw(Shape shape) {
        graphic.draw(shape);
        canvas.repaint();
    }
 
    /**
     * Fill the internal dimensions of a given shape with the current 
     * foreground color of the canvas.
     * @param  shape  the shape object to be filled 
     */
    public void fill(Shape shape)
    {
        graphic.fill(shape);
        canvas.repaint();
    }

    /**
     * Fill the internal dimensions of the given rectangle with the current 
     * foreground color of the canvas. This is a convenience method. A similar 
     * effect can be achieved with the "fill" method.
     */
    public void fillRectangle(int xPos, int yPos, int width, int height)
    {
        fill(new Rectangle(xPos, yPos, width, height));
    }

    /**
     * Erase the whole canvas.
     */
    public void erase()
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        canvas.repaint();
        border();
    }

    /**
     * Erase the internal dimensions of the given rectangle. This is a 
     * convenience method. A similar effect can be achieved with
     * the "erase" method.
     */
    //FIXME Remover ou transformar para retangulo 
    public void eraseRectangle(int xPos, int yPos, int width, int height)
    {
        erase(new Rectangle(xPos, yPos, width, height));
    }

    /**
     * Erase a given shape's interior on the screen.
     * @param  shape  the shape object to be erased 
     */
    public void erase(Shape shape)
    {
    	//FIXME Dou muito problema, olhem pra mim
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.fill(shape);              // erase by filling background color
        graphic.setColor(original);
        canvas.repaint();
        setForegroundColor(Color.red);
        border();
    }

    private void firstScreen () {
    	//setFont(new Font("helvetica", Font.BOLD, 14));
//        setForegroundColor(Color.red);
//        drawString("Welcome to Snake's Game", 250, 70);
//    	wait(3000);
    	//erase();
    	
    	//-------------------------------------------------
//    	JButton exit = new JButton("Get out");
//		JButton ai = new JButton("Artificial intelligence");
//		JButton humanPlayer = new JButton("Human player");
//		
//		JPanel panel = new JPanel();
//		
//		panel.add(exit);
//		panel.add(ai);
//		panel.add(humanPlayer);
//		
//		ActionListener getOut = new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		};
//		
//		exit.addActionListener(getOut);
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
    	
    }
    
    //FIXME Definir paredes de forma din�mica (talvez cobrinhas que n�o se mexem?) 
    public void border () {    	
    	// draw the border
    	double thickness = 20;
    	Stroke oldStroke = graphic.getStroke();
    	graphic.setStroke(new BasicStroke((float) thickness));
    	setForegroundColor(Color.red);
    	graphic.drawRect(0, 0, width, height);
    	graphic.setStroke(oldStroke);
    }
    
    
    /**
     * Draws a String on the Canvas.
     * @param  text   the String to be displayed 
     * @param  x      x co-ordinate for text placement 
     * @param  y      y co-ordinate for text placement
     */
    public void drawString(String text, int x, int y)
    {
        graphic.drawString(text, x, y);   
        canvas.repaint();
    }

    /**
     * Erases a String on the Canvas.
     * @param  text     the String to be displayed 
     * @param  x        x co-ordinate for text placement 
     * @param  y        y co-ordinate for text placement
     */
    public void eraseString(String text, int x, int y)
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        //canvas.repaint();
    }
    
    /**
     * Draws an image onto the canvas.
     * @param  image   the Image object to be displayed 
     * @param  x       x co-ordinate for Image placement 
     * @param  y       y co-ordinate for Image placement 
     * @return  returns boolean value representing whether the image was 
     *          completely loaded 
     */
    public boolean drawImage(Image image, int x, int y) {
        boolean result = graphic.drawImage(image, x, y, null);
        canvas.repaint();
        return result;
    }

    /**
     * Sets the foreground color of the Canvas.
     * @param  newColor   the new color for the foreground of the Canvas 
     */
    public void setForegroundColor(Color newColor)
    {
        graphic.setColor(newColor);
    }

    /**
     * Returns the current color of the foreground.
     * @return   the color of the foreground of the Canvas 
     */
    public Color getForegroundColor() {
        return graphic.getColor();
    }

    /**
     * Sets the background color of the Canvas.
     * @param  newColor   the new color for the background of the Canvas 
     */
    public void setBackgroundColor(Color newColor) {
        backgroundColor = newColor;   
        graphic.setBackground(newColor);
    }

    /**
     * Returns the current color of the background
     * @return   the color of the background of the Canvas 
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * changes the current Font used on the Canvas
     * @param  newFont   new font to be used for String output
     */
    public void setFont(Font newFont)
    {
        graphic.setFont(newFont);
    }

    /**
     * Returns the size of the canvas.
     * @return     The current dimension of the canvas
     */
    public Dimension getSize()
    {
        return canvas.getSize();
    }

    /**
     * Waits for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e)
        {
            // ignoring exception at the moment
        }
    }

    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
}