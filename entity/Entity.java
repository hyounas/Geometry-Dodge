package com.bruh.entity; /**An Entity abstract class for the Player, EntityRect, Collectable and Stars classes*/

import java.awt.Graphics2D;
import java.awt.Rectangle;

	/**
	 *  
	 * @author HP
	 * protected variables that can be accessed by other classes if imported to that specific class
	 */
public abstract class Entity {
	protected double x;  
	protected double y;
	protected double dx;
	protected double dy;
	protected int r;
	protected double moveSpeed;
	
	protected int width;
	protected int height;
	
	/**
	 * collision effect with obstacles
	 */
	protected int cwidth;
	protected int cheight;
	
	
	/**
	 *moving the player around
	 *@author HP
	 */
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	
	public int getx() {return (int)x;}
	public int gety() {return (int)y;}
	public int getr() {return (int)r;}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public boolean intersects(Entity e) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = e.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() { 
		return new Rectangle((int)x - cwidth,
				(int)y - cheight , cwidth * 2, cheight * 2); /**this is invisible but it is set there*/
	}
	
	public void update() {}
	
	/**
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g) {}
}