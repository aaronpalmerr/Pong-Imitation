package game;

import java.util.Random;


public class Spawner {

	private Handler handler;
	private int scoreKeep = 0;
	private Random r = new Random();
	private int round;

	public Spawner(Handler handler) {
		this.handler = handler;
		round = 1;
	}
	
	public void tick() {
		
		if (handler.noPuck() == false) {
			handler.addObject(new Puck(Game.WIDTH/2-25, Game.HEIGHT/2-48, ID.Puck, handler));
		}
		

		
	}
	
	
}
