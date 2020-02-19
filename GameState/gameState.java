package com.bruh.GameState;

import java.awt.Graphics2D;


public abstract class gameState {
	
	protected gameStateManager gsm;
	
	public abstract void init(); /**initiate*/
	public abstract void update(); /**an update abstract method to input object/obstacles onto the screen and keeping them updated*/
	public abstract void draw(Graphics2D g); /**a draw abstract method for graphics which other classes can inherit*/
	public abstract void keyPressed(int k); /**an abstract method for the TitleScreen keyboard inputs to select either start or exit the simulation*/ 
	public abstract void KeyReleased(int k);
}
