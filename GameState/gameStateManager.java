package com.bruh.GameState;

import java.util.ArrayList;
import java.awt.Graphics2D;


public class gameStateManager {
	
	private ArrayList<gameState> gameStates;
	private int currentState;
	
	public static final int TITLE_SCREEN = 0; 
	public static final int LEVEL = 1;
	
	public gameStateManager() {
		
		gameStates = new ArrayList<gameState>();
		
		currentState = TITLE_SCREEN;
		gameStates.add(new TitleScreen(this));
		gameStates.add(new Level(this));
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).KeyReleased(k);
	}
	
}
