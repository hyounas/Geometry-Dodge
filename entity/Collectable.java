package com.bruh.entity;


import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class Collectable extends Entity {
	
	public boolean hit = false;
	
	public Collectable() {
		Random num = new Random();
		int xwidth = num.nextInt(700);
		x = xwidth;
		y = -5;
		r = 10;
		
		dx = 0;
		dy = 0;
		
		cwidth = r; /**these are for the collisions, where c stands for collision.*/
		cheight = r;
				
	}
	
	public void hit() { /**hit method to see IF the hit actually takes place*/
		hit = true;
	}
	
	public void update() { /**important to update the game everytime*/ 
		dy = +5; /**speed of the collectable*/ 
		
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) { /**this is where we code the collectables*/
		g.setColor(Color.green);
		g.fillOval((int)x - r,(int)y - r, 2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
	}
}
