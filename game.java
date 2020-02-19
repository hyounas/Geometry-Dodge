package com.bruh;

import javax.swing.JFrame;

public class game {
	
	public static void main(String[] args) {
		JFrame display = new JFrame("27016428"); 
		display.setContentPane(new GamePanel());
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setResizable(true); /**the frame can be adjusted if needed*/
		display.pack(); 
		display.setVisible(true); /**setting the canvas visible or not, if false, the canvas wont be visible but that doesn't mean the code isn't running*/
		display.setLocationRelativeTo(null); /**this will put the game in the middle of the screen*/
	}
}
