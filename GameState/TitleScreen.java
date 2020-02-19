package com.bruh.GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

public class TitleScreen extends gameState {
	
	private int currentChoice = 0;
	
	private static Color titleColor; /**Color of the title*/
	private static Font titleFont;	/**Adding a font to the title*/
	
	private Font menuFont; /**A menu for selection whether you want to start the game or exit it*/
	
	public TitleScreen(gameStateManager gsm) {

		this.gsm = gsm;
		try {
			titleColor = new Color(175, 21, 170);
			titleFont = new Font("Times New Roman", Font.PLAIN, 72);
			
			menuFont = new Font("Times New Roman", Font.PLAIN, 24);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this is not used as there is nothing to initiate
	 */
	@Override
	public void init() {}
	
	/**
	 * this is not used as there is nothing to update in current state
	 */
	@Override
	public void update() {}
	
	/**
	 *@param g
	 *@HP
	 *a draw method used to display the title of the screen
	 *includes, title color, title font, start game, exit game
	 */
	@Override
	public void draw(Graphics2D g) {
		
		/**this is the title of the game*/
		g.setColor(titleColor); 
		g.setFont(titleFont);
		g.drawString("Simulation by Hasnain", 50, 100);
		
		/**these are the buttons to start and close the game*/
		g.setFont(menuFont);
		g.drawString("Start", 230, 500);
		g.drawString("Exit", 530, 500);
		
		g.drawString("Use the arrow keys and press space or enter to start!", 180, 400);
		g.drawString("27016428", 50, 25);
		
		/**adding a hovering affect to Start Game and Exit Game*/
		if(currentChoice == 0) {
			g.setColor(Color.GREEN); /**outline color of the rectangle*/
			g.drawOval(195, 450, 120, 80); /**getting the position of the oval right after some trial and error*/	
		}
		else {
			g.setColor(Color.RED);
			g.drawOval(195, 450, 120, 80);
		}
		
		if(currentChoice == 1) {
			g.setColor(Color.GREEN); /**outline color of the rectangle*/
			g.drawOval(485, 450, 120, 80); /**position of the oval on the exit game(this took me time to add in the co-ordinates right)*/
		}
		else {
			g.setColor(Color.RED);
			g.drawOval(485, 450, 120, 80);
		
		}
	}
	
	private void select() {

		if(currentChoice == 0) {
			gsm.setState(gameStateManager.LEVEL);  //starting the stage
		}
		
		if(currentChoice == 1) {
			System.exit(0); //exiting the stage
		}
	}
	
	/**
	 * @param k
	 * this parameter is used to implement key inputs when pressed
	 */
	@Override //NPUT METHODS
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_SPACE) { /**ALLOWS YOU TO ENTER THE LEVEL*/
			select();
		}
		if(k == KeyEvent.VK_ENTER) { /**SAME THING AS ABOVE*/
			select(); /**enter as the select key*/
		}
		if(k == KeyEvent.VK_LEFT) {
			currentChoice = 0; /**left arrow key is set to 0*/
		}
		if(k == KeyEvent.VK_RIGHT) {
			currentChoice = 1; /**right arrow key is set to 1*/
		}
	}
	
	/**
	 * @param k
	 * this is not needed because when the user selects an option, the simulation automatically starts
	 * no key requires an exit
	 */
	@Override
	public void KeyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
