package game;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends GameObject {

	private Random r = new Random();
	private Handler handler;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 125);
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp((int)x, 0, Game.WIDTH - 48); // doesn't allow player to leave room
		y = Game.clamp((int)y, 0, Game.HEIGHT - 160); // doesn't allow player to leave room

		collision();
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Puck) {
				// check if tempObject is an enemy
				if (getBounds().intersects(tempObject.getBounds())) {
					// if this.player intersects with enemy object
					float newVel = tempObject.getVelX() * -1;
					
					if (newVel < 0) {
						newVel -= .66f;
					}
					else {
						newVel += .34f;
					}

					tempObject.setVelX(newVel);
					
				}
			}

			}
			
			
		}


	public void render(Graphics g) {

		g.setColor(Color.cyan.darker());
		g.fillRect((int)x, (int)y, 25, 125);

	}
	
}
