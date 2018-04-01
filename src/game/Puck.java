package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Puck extends GameObject {
	
	private Handler handler;
	Random r;

	public Puck(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		r = new Random();

//		velX = -4;
//		velY = 4;
		
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

	@Override
	public void tick() {
		y += velY;
		x += velX;
		

		// Keeps enemy from exiting x and y borders
		
		//top and bottom borders
		if (y <= 0 || y >= Game.HEIGHT-48) {
			velY *= -1;
		}
		
		// left and right borders
		if (x <= 0  || x >= Game.WIDTH-32) { 
			handler.removeObject(this);
		}
		
		
		if (x <= 0) {
			HUD.playerTwoScore++;
			handler.removeObject(this);
		}
			
		else if (x >= Game.WIDTH-32) { 
			HUD.playerOneScore++;
			handler.removeObject(this);
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
