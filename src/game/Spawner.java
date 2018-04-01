package game;

import java.util.Random;

/**
 * Creates a new puck when the old one has left the GUI.  
 * @author apalm
 *
 */

public class Spawner {

	/*
	 * Class instance variables
	 */
	private Handler handler;
	private int scoreKeep = 0;
	private Random r = new Random();
	private int round;

	/*
	 * Constructor
	 */
	public Spawner(Handler handler) {
		this.handler = handler;
		round = 1;
	}
	
	/**
	 * Creates new puck if one doesn't exit in the GameObjects ArrayList.
	 */
	public void tick() {
		
		if (handler.noPuck() == false) {
			handler.addObject(new Puck(Game.WIDTH/2-25, Game.HEIGHT/2-48, ID.Puck, handler));
		}
		

	}
	
	
}
