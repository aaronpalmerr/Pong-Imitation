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

	/*
	 * Class instance variables. 
	 */
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	GameObject tempObject;

	/*
	 * Empty constructor.  
	 */
	public Handler() {
		
	}
	
	/**
	 * Sorts through each object in ArrayList and starts them.  
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick();
		}
	}

	/**
	 * Sorts through each object in the ArrayList and renders them.  
	 * @param g
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/**
	 * Adds an object to the ArrayList
	 * @param object
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/**
	 * Removes specified object from ArrayList.
	 * @param object
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	/**
	 * Checks whether a puck is in the ArrayList or not.  
	 * @return
	 */
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
