package game;
import java.awt.Graphics;

import java.awt.Rectangle;

//Used abstract class

public abstract class GameObject {
	protected float x,y;
	protected ID id;
	protected float speedx, speedy;
	
	//you  need these to run the constructor
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	//your abstract methods
	
	public abstract void tick();
	public abstract void render(Graphics g);	
	public abstract Rectangle getBounds();
	
	//your average setters
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public void setSpeedX(int speedx) {
		this.speedx = speedx;
	}
	public void setSpeedY(int speedy) {
		this.speedy = speedy;
	}
	
	//your average getters 
	
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	public ID getID() {
		return this.id;
	}
	public float getSpeedX() {
		return this.speedx;
	}
	public float getSpeedY() {
		return this.speedy;
	}
}
