package com.bruh.entity; //HERE ARE THE ENEMIES 

import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class EntityRect extends Entity { /**the name of the class was supposed to be EnemyRect but i guess this will do*/ 
	
	private boolean hit;	/**if the object gets hit or not*/
	private long hitTimer; /**the hit timer is 0 so that when they come in contact, the collision is instant*/
	
	private double rad; /**radius*/
	
	private int type;
	/**
	 * 
	 * @param type
	 */
	public EntityRect(int type) {
		
		this.type = type;
		Random num = new Random(); /**a random function to create*/ 
		
		int xwidth = num.nextInt(700);
		
		x = xwidth;
		y = -5;
		
		dx = 0; /**displacement x and y are set to 0 for the rectangular enemies*/
		dy = 0;
		
		moveSpeed = 4;
		
		/**
		 *collision width and height of the rectangle
		 */
		cwidth = 40;  
		cheight = 10;
		
		if(type == 1) { 
				dy = +moveSpeed;
			}
		hit = false;
		hitTimer = 0;
		}
	
	public int getType() {return type;}
	public void hit() {
		hit = true;
		hitTimer = System.nanoTime(); 
	}
	
	public void update() {
		if(type == 1) {
			dy = moveSpeed;
		}
		
		x += dx; 
		y += dy;
	}
	/**
	 * @param g
	 */
	public void draw(Graphics2D g) {
			g.setColor(Color.CYAN);
			g.fillRect((int)x - cwidth, (int)y - cheight, cwidth * 2, cheight * 2); /**fills the rectangle, multiplying the width and height by 2 so that it is entirely filled*/
			
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.red.darker());
			g.drawRect((int)x - cwidth, (int)y - cheight, cwidth * 2, cheight * 2);
		 	g.setStroke(new BasicStroke(1));
			
			g.drawRect((int)x - cwidth, (int)y - cheight, cwidth * 2, cheight * 2);
	}
}
