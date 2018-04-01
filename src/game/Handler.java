package game;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class holds all the game objects in an ArrayList that will 
 * be used in the game.
 * @author apalm
 *
 */

public class Handler {

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	GameObject tempObject;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	
	public boolean noPuck() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if (tempObject.getID() == ID.Puck) {
				return true;
			}
		}
		return false;
	}

}
