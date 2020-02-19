package com.bruh.GameState; /**CREATING A LEVEL IN THE GAME*/

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.bruh.entity.Collectable;
import com.bruh.entity.EntityRect;
import com.bruh.entity.Player;
import com.bruh.entity.Stars;

public class Level extends gameState { /**loading in the predefined methods from gamsState class*/
	
	private boolean ready;
	private int create;
	
	public static int score1;
	public static int TIMER;
	public static int x;
	public static int y;
	
	public int timer; /**adding a scoring system where when the time passes on, you get points for surviving*/
	public int timeStart;
	public int timeElapsed;
	
	private Player player;
	private Font font;
	 
	public static ArrayList<EntityRect> obstacles; /**all these arraylist<> have the same implementation*/
	public static ArrayList<Collectable> collect;
	public static ArrayList<Stars> star;
	
	/**
	 * 
	 * @param gsm
	 */
	public Level(gameStateManager gsm) { 
		this.gsm = gsm;
		init();
		ready = true;
		score1 = timeElapsed + player.score; /**adds in the time and the collectables from the player*/
		TIMER = timeElapsed;
		x = player.getx();
		y = player.gety();
		
		font = new Font("Impact",Font.PLAIN, 24); //font and font size of the scoring system
	}

	@Override /**an arraylist for how many obstacles, collectables and stars spawn in*/
	public void init() {
		player = new Player();
		obstacles = new ArrayList<EntityRect>();
		collect = new ArrayList<Collectable>();
		star = new ArrayList<Stars>();
	}
	
	/**include all three objects in the createEnemy() function*/
	public void createEnemy() {
		Random num = new Random(); /**a random spawn point in the arena*/
		create = num.nextInt(20); 
		int layers = num.nextInt(5); /**spawn rate for the objects*/ 
		if(create < 19) {
			ready = false; 
		}
		else {
			ready = true;
		}
		if(ready) {
			obstacles.add(new EntityRect(1)); /**if ready, the enemies will spawn in*/ 
		}
		else {
			if(create < 1) 
				collect.add(new Collectable());
		}
		if(create < 1) {
			star.add(new Stars(layers));
		}
	}
	
	public void CalScore() { /**a function to calculate the scoring system*/
		if(player.hit != true) {
			timeStart += 1;
			timeElapsed = timeStart / 60 + player.score; 
		}
		if(player.hit) {
			clearScore(0); /**brings the score back to 0 if the player gets hit by the enemy*/ 
		}
		
	}
	
	public void clearScore(int timeElapse) {
		timeElapse = 0;
	}

	@Override
	public void update() { /**this is the update method which will update every object onto the screen*/
		CalScore();
		player.update();
		player.checkCollision(obstacles);
		player.checkCollision2(collect);
		score1 = timeElapsed;
		x = player.getx(); /**to display the position of the player in x axis*/
		y = player.gety(); /**to display the position of the player in y axis*/
		TIMER = timeElapsed;
		if(player.hit) {
			timeStart = 0;
			gsm.setState(gameStateManager.TITLE_SCREEN);
		}
		
		createEnemy();
		
		for(int i = 0; i < star.size(); i++) {
			Stars s = star.get(i);
			s.update();
			if(s.gety() > 600) {
				star.remove(i);
			}
		}
		
		/**a for loop for the collectable*/
		for(int i = 0; i < collect.size(); i++) {  
			Collectable c = collect.get(i);
			c.update();
			if(c.hit == true)	/**if the player hits the collectable, the collectable is removed*/
				collect.remove(i); 
			if(c.gety() > 600) { /**if the collectable reaches the bottom screen, it is removed*/
				collect.remove(i);
			}
		}
		
		for(int i = 0; i < obstacles.size(); i++) {
			EntityRect e = obstacles.get(i);
			e.update();
			if(e.gety() > 600) { /**the rectangle is removed when it reaches the bottom of the screen*/
				obstacles.remove(i);
			}
		}
	}
	
	@Override /**display things on the screen*/
	public void draw(Graphics2D g) { /**always start working from this*/ 
		g.setColor(Color.BLACK); /**Change the color of the stage*/
		g.fillRect(0, 0, 800, 600); /**covers up the entire screen with black colour.*/
		
		player.draw(g);
		
		for(int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(g);
		}
		for(int i = 0; i < collect.size(); i++) {
			collect.get(i).draw(g);
		}
		for(int i = 0; i < star.size(); i++) {
			star.get(i).draw(g);
		}
		
		g.setColor(Color.cyan.darker());
		g.setFont(font);
		g.drawString("SCORE: " + score1, 30, 30);
		
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("Timer: " + timeStart / 60, 30, 60);
		
		g.setColor(Color.BLUE);
		g.setFont(font);
		g.drawString("COORDINATES: " + x + ":" + y, 30, 90);
	}
	
	/**
	 * @param k
	 * input keys for the player
	 */
	@Override /**this is where we make inputs for the player, quiet simple and understandable*/
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if(k == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if(k == KeyEvent.VK_UP) {
			player.setUp(true);
		}
		if(k == KeyEvent.VK_DOWN) {
			player.setDown(true);
		}
	}
	
	/**
	 * @param k
	 * 
	 */
	@Override /**same thing with this, copy and paste and set boolean to false when the key input is released*/
	public void KeyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if(k == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if(k == KeyEvent.VK_UP) {
			player.setUp(false);
		}
		if(k == KeyEvent.VK_DOWN) {
			player.setDown(false);
		}
	}
}
