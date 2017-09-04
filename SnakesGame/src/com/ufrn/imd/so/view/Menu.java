package com.ufrn.imd.so.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu {
	private Screen screen;
	private int choise;
	
	public Menu (Screen screen) {
		this.screen = screen;
		choise = 0;
		setUp();
	}
	
	public void setUp () {
		initialScreen();
	}
	
	public int getChoise () {
		return choise;
	}
	
	public void initialScreen () {
		JButton exit = new JButton("Get out");
		JButton ai = new JButton("Artificial intelligence");
		JButton humanPlayer = new JButton("Human player");
		
		JPanel panel = new JPanel();
		panel.setVisible(true);
		
		panel.add(exit);
		panel.add(ai);
		panel.add(humanPlayer);
		
		
		ActionListener getOut = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		
		ActionListener aiAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = 1; 
				screen.backgroundColor = (Color.black);
				panel.setVisible(false);
				screen.erase();
			}
		};
		ActionListener hpAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = 1;				
				screen.backgroundColor = (Color.black);
				panel.setVisible(false);
				screen.erase();
			}
		};
		
		exit.addActionListener(getOut);
		ai.addActionListener(aiAction);
		humanPlayer.addActionListener(hpAction);
		
		screen.frame.add(panel);
		//screen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.frame.pack();
	}
}
