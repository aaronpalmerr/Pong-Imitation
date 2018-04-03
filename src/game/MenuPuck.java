package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


/**
 * Puck object moves across the GUI for the players to stop from passing
 * their object.  If the puck exits the GUI on a player's side, the opposing 
 * player scores a point.  
 * @author apalm
 *
 */

public class MenuPuck extends GameObject {
	
	/*
	 * Class instance variables
	 */
	private Handler handler;
	Random r;
	private int timer = 60;

	/*
	 * Constructor
	 */
	public MenuPuck(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		r = new Random();

		// randomly pick speeds between 6 and -6.
		velX = r.nextInt(6 - (-6) + 1) + -6;
		velY = r.nextInt(6 - (-6) + 1) + -6;
		
		// in the event the speeds are equal,
		if (velX == velY) {
			velX = -5;
			velY = -4;
		}
		if (velX == 0) {
			velX = -4;
		}
		if (velY == 0) {
			velY = 6;
		}
		if (velX == velY) {
			velX = 3;
			velY = -2;
		}
//		
	}

	/**
	 * Moves puck within the GUI.  
	 */
	@Override
	public void tick() {
//		if (timer <= 0) {
//			velX = 0;
//			velY = 0;
//		}
//		else {
//			timer--;
//		}
		y += velY;
		x += velX;
		
		/*
		 * Stops puck from exiting top and bottom of GUI
		 */
		
		// Controls top and bottom borders
		// Keeps enemy from exiting x and y borders
				if (y <= 0 || y >= Game.HEIGHT-48) velY *= -1;
				if (x <= 0 || x >= Game.WIDTH-32) velX *= -1;
		
		/*
		 * Determines which player gets the point based
		 * on x coordinate of the puck when it left the GUI.
		 */
		if (x <= 0) {
			HUD.playerTwoScore++;
		}
		else if (x >= Game.WIDTH-32) { 
			HUD.playerOneScore++;
		}

	}

	/**
	 * Renders graphics
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red.darker());
		g.fillRect((int)x, (int)y, 25, 25);
	}
	
	/**
	 * Marks the boundaries of the puck for collision testing.
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}

}
