package com.bruh.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Stars extends Entity{ /**this class is created for the background*/
	/**
	 * 
	 * @param layer
	 * 
	 */
	public Stars(int layer) {
		Random num = new Random();
		int xwidth = num.nextInt(800); /**setting up the width and height for the background*/
		int yheight = num.nextInt(600);
		x = xwidth;
		y = yheight;
		r = 2; /**radius of the star*/
		
		/**displacement x and y*/
		dx = 0;
		dy = 0;
		
		if(layer == 0) {  
			moveSpeed = 0;
		}
		if(layer == 1) {
			moveSpeed = 1;
		}
		if(layer == 2) {
			moveSpeed = 2;
		}
	}

	public void update(){
		dy = moveSpeed;
		
		x += dx;
		y += dy;
	}
	/**
	 * @param g
	 * displaying the stars onto the screen
	 */
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)x-r, (int)y-r, r*2, r*2); /** x - radius && y - r so that the fill is exactly on the obstacle and radius is multiple by 2, so that it is completely filled*/
		g.setStroke(new BasicStroke(3));
	}
}
