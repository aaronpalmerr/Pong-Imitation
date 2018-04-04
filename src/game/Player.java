package game;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Player objects represent the human players in the game.  
 * @author apalm
 *
 */

public class Player extends GameObject {

	/*
	 * Class instance variables
	 */
	private Handler handler;

	/*
	 * Constructor
	 */
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	/**
	 * Returns the boundaries of the player for collision testing.  
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 125);
	}

	/**
	 * Moves player vertically over GUI.
	 */
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp((int)x, 0, Game.WIDTH - 48); // doesn't allow player to leave room
		y = Game.clamp((int)y, 0, Game.HEIGHT - 160); // doesn't allow player to leave room

		collision();
	}

	/**
	 * Detects collisions and adjusts the puck accordingly.  
	 */
	private void collision() {
	
		// Sort through GameObjects ArrayList
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// if object is the puck
			if (tempObject.getID() == ID.Puck) {
				
				// if puck intersects with the player
				if (getBounds().intersects(tempObject.getBounds())) {
					
					AudioPlayer.getSound("player").play();
					
					// change direction of the puck
					float newVel = tempObject.getVelX() * -1;
					
					// increase the velocity of the puck
					if (newVel < 0) {
						newVel -= .66f;
					}
					else {
						newVel += .34f;
					}

					// set the new velocity to the puck
					tempObject.setVelX(newVel);
					
				}
			}

			}
			
			
		}

	/**
	 * Renders the player object in the GUI.  
	 */
	public void render(Graphics g) {

		g.setColor(Color.cyan.darker());
		g.fillRect((int)x, (int)y, 25, 125);

	}
	
}
