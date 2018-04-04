package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles key input from the keyboard. When a key is pressed, the program moves
 * that player in the desired direction. When the key is release, the player
 * should stop. Two boolean Arrays maintain when a key is and isn't pressed to
 * ensure accuracy.
 * 
 * @author apalm
 *
 */

public class KeyInput extends KeyAdapter {

	/*
	 * Class instance variables.
	 */
	private Handler handler;
	private boolean[] keyDown = new boolean[4]; // playerOne's boolean array
	private boolean[] keyDown2 = new boolean[4]; // playerTwo's boolean array
	private final int VELOCITY = 10;

	/*
	 * Constructor
	 */
	public KeyInput(Handler handler) {
		this.handler = handler;

		/*
		 * Set all booleans to false.
		 */
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;

		keyDown2[0] = false;
		keyDown2[1] = false;
		keyDown2[2] = false;
		keyDown2[3] = false;
	}

	/**
	 * Handles when a key is pressed.
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// System.out.println(key);

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			/*
			 * playerOne keys
			 */
			if (tempObject.getID() == ID.Player) {
				// key events for Player

				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-VELOCITY);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(VELOCITY);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
					keyDown[3] = true;
				}

			}

			/*
			 * playerTwo keys.  The velocity of the player 
			 */
			if (tempObject.getID() == ID.Player2) {
				// key events for Player

				if (key == KeyEvent.VK_UP) {
					tempObject.setVelY(-VELOCITY);
					keyDown2[0] = true;
				}
				if (key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(VELOCITY);
					keyDown2[1] = true;
				}
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(0);
					keyDown2[2] = true;
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(0);
					keyDown2[3] = true;
				}

			}

		}
		/*
		 * Game exits on ESC button
		 */
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		/*
		 * Quit mid-game
		 */
		if (Game.gameState == STATE.Game) {
			if (key == KeyEvent.VK_Q) {
				Game.gameState = STATE.End;
				handler.reset();
			}
		}
		
		
	}

	/**
	 * Handles when a keyboard key is released.
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			/*
			 * playerOne released keys
			 */
			if (tempObject.getID() == ID.Player) {
				// key events for Player

				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if (key == KeyEvent.VK_S) {
					keyDown[1] = false;
				}
				if (key == KeyEvent.VK_D) {
					keyDown[2] = false;
				}
				if (key == KeyEvent.VK_A) {
					keyDown[3] = false;
				}

				/*
				 * Vertical movements
				 */
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				}

				/*
				 * Horizontal movements
				 */
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}

			/*
			 * playerTwo release keys
			 */
			if (tempObject.getID() == ID.Player2) {
				// key events for Player2

				if (key == KeyEvent.VK_UP) {
					keyDown2[0] = false;
				}
				if (key == KeyEvent.VK_DOWN) {
					keyDown2[1] = false;
				}
				if (key == KeyEvent.VK_RIGHT) {
					keyDown2[2] = false;
				}
				if (key == KeyEvent.VK_LEFT) {
					keyDown2[3] = false;
				}

				/*
				 * Vertical movements
				 */
				if (!keyDown2[0] && !keyDown2[1]) {
					tempObject.setVelY(0);
				}

				/*
				 * Horizontal movements
				 */
				if (!keyDown2[2] && !keyDown2[3]) {
					tempObject.setVelX(0);
				}
			}

		}

	}

}
