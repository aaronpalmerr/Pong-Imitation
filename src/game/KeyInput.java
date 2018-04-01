package game;

import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private boolean[] keyDown2 = new boolean[4];

	public KeyInput(Handler handler) {
		this.handler = handler;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		
		keyDown2[0] = false;
		keyDown2[1] = false;
		keyDown2[2] = false;
		keyDown2[3] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println(key);

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// playerOne keys
			if (tempObject.getID() == ID.Player) {
				// key events for Player

				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
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
			
			// playerTwo keys
			if (tempObject.getID() == ID.Player2) {
				// key events for Player

				if (key == KeyEvent.VK_UP) {
					tempObject.setVelY(-5);
					keyDown2[0] = true;
				}
				if (key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(5);
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
		if (key == KeyEvent.VK_ESCAPE) { // game exits on ESC button
			System.exit(1);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

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

				// vertical movement
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				}

				// horizontal movment
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}
			
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

				// vertical movement
				if (!keyDown2[0] && !keyDown2[1]) {
					tempObject.setVelY(0);
				}

				// horizontal movment
				if (!keyDown2[2] && !keyDown2[3]) {
					tempObject.setVelX(0);
				}
			}

		}

	}

}
