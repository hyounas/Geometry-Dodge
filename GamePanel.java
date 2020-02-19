package com.bruh; //canvas

import com.bruh.GameState.gameStateManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable, KeyListener{ /**this will add in the attributes into the game class*/

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 800; /**width and height of the screen*/
	public static int HEIGHT = 600;
		
	private Thread thread; /**it is an application thread that allows the user to process all UI events.*/ 
	private int FPS = 60;  /**frames per second of the game*/
		
	private long targetTime = 1000/FPS; 
	private boolean running = false; /**for now its at false*/
	Graphics2D g; /**this is for the graphics*/
		
	private BufferedImage image; /**this is a pre-defined java method that needs to be imported which includes BufferedImage, swing.JPanel*/ 
	private gameStateManager gsm;
		
	public GamePanel() { /**Constructor*/
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true); //making the game run in front instead of in the background
		requestFocus();
	}
		
	public void addNotify() { /**this is used for running the simulation*/
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
		
	private void init() { /**this is the initiate function*/
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); /**this will buffer in each obstacle into the screen*/
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new gameStateManager();
	}
		
	public void run() {
		init();
		/**this part is used to setting up the FPS*/
		long start;
		long elapsed;
		long wait;
			
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
				
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed/1000000;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();				
			}
		}
	}
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

