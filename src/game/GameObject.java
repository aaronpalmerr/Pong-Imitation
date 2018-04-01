package game;

import java.awt.Graphics; 
import java.awt.Rectangle;


public abstract class GameObject {


	protected float x, y;
	protected ID id;
	protected float velX, velY;

	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds(); // if two rectangles intersect, method returns true


	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setVelX(float vel) {
		this.velX = vel;
	}

	public void setVelY(float vel) {
		this.velY = vel;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getID() {
		return this.id;
	}

	public float getVelX() {
		return this.velX;
	}

	public float getVelY() {
		return this.velY;
	}
}
