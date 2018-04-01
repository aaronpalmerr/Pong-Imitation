package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstract class for all gameObjects to inherit from.  They all
 * have an ID, an x and y position, and velocities.  
 * @author apalm
 *
 */

public abstract class GameObject {

	/*
	 * Class instance variables
	 */
	protected float x, y;
	protected ID id;
	protected float velX, velY;

	/*
	 * Constructor
	 */
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * What the object does when running.  
	 */
	public abstract void tick();

	/*
	 * Renders the graphics of the object. 
	 */
	public abstract void render(Graphics g);

	/*
	 * Creates a boundary around of object for collision detection. 
	 */
	public abstract Rectangle getBounds(); // if two rectangles intersect, method returns true

	/**
	 * Sets the x coordinates.  
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Sets the y coordinates.
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Sets the x velocity.
	 * @param vel
	 */
	public void setVelX(float vel) {
		this.velX = vel;
	}

	/**
	 * Sets the y velocity.
	 * @param vel
	 */
	public void setVelY(float vel) {
		this.velY = vel;
	}

	/**
	 * Returns the x coordinate.
	 * @return
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * Returns the y coordinate.
	 * @return
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * Sets the object ID
	 * @param id
	 */
	public void setID(ID id) {
		this.id = id;
	}

	/**
	 * Returns the object ID
	 * @return
	 */
	public ID getID() {
		return this.id;
	}

	/**
	 * Returns the x velocity.  
	 * @return
	 */
	public float getVelX() {
		return this.velX;
	}

	/**
	 * Returns the y velocity. 
	 * @return
	 */
	public float getVelY() {
		return this.velY;
	}
}
