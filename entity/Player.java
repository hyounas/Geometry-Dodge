package com.bruh.entity; 

import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


import com.bruh.GamePanel;

public class Player extends Entity{
	
	public int score = 0;
	public boolean hit = false;
	
	public Player() {
		
		x = GamePanel.WIDTH / 2; /**The arena's width and height are divided by 2 so that the player starts from the center of the arena*/
		y = GamePanel.HEIGHT / 2;
		r = 15; /**radius for the circle*/
		
		dx = 0;
		dy = 0;
		moveSpeed = 5;
		
		cwidth = 15; /**this is the collision width and height*/
		cheight = 15;
	}
	/**
	 * @return
	 * returns the score 	
	 */
	public int getScore() {return score;}
	
	/**
	 * 
	 * @param b
	 * these are the inputs to move the player around
	 */
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	
	public void addScore(int i) {score += i;}
	
	public void hit() {
		hit = true;
	}
	/**
	 * 
	 * @param obstacles
	 */
	public void checkCollision(ArrayList<EntityRect> obstacles) { /**checking whether the collision takes place with the player*/
		for(int i = 0; i <obstacles.size(); i++) {
			EntityRect e = obstacles.get(i);
			
			if(intersects(e)) {
				e.hit();
				hit();
			}
		}
	}
	/**
	 * 
	 * @param collect
	 * an arraylist for the collectable, when hit with player, user gets 5 points
	 */
	public void checkCollision2(ArrayList<Collectable> collect) { /**this is the collision with the collectable*/ 
		for(int i = 0; i <collect.size(); i++) { 
			Collectable c = collect.get(i);
			
			if(intersects(c)) { /**if the player hits the collides with the collectable, the collectable will be removed*/
				c.hit();
				score += 5; /**when its removed, a score of 5 is added to the variable score*/
			}
		}
	}
	
	/**the change in displacement when the key is pressed*/
	public void update() {
		if(left) {
			dx = -moveSpeed; 
		}
		if(right) {
			dx = moveSpeed;
		}
		if(up) {
			dy = -moveSpeed;
		}
		if(down) {
			dy = +moveSpeed;
		}
		x += dx;
		y += dy;
		
		/**i found out that the character was going out of the screen, so i added these functions to stop that from happening*/
		if(x < r) x = r;
		if(y < r) y = r;
		
		if(x > GamePanel.WIDTH - r) { //r is the radius
			x = GamePanel.WIDTH - r;
		}
		if(y > GamePanel.HEIGHT - r) {
			y = GamePanel.HEIGHT - r;
		}
		
		dx = 0;
		dy = 0;
	}
	
	/**
	 * @param g
	 * a parameter g for the graphics
	 * 
	 */
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)x - r, (int)y - r, 2*r, 2*r);
		g.setStroke(new BasicStroke(3));
		
		/**this was used for collision testing purposes g.drawRect((int)x - cwidth, (int)y - cheight, cwidth * 2, 2 * cheight);*/
	}
}
