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

		
		velX = -4;
		velY = 4;
		
//		velX = r.nextInt(7-(-7)+(-7));
//		velY = r.nextInt(7-(-7)+(-7));
//		if (velX == velY) {
//			velX = 3;
//			velY = 2;
//		}
//		if (velX == 0) {
//			velX = 1;
//		}
//		if (velY == 0) {
//			velY = 1;
//		}
//		if (velX == velY) {
//			velX = 3;
//			velY = 2;
//		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

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

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red.darker());
		g.fillRect((int)x, (int)y, 25, 25);
	}

}
