package de.timweb.seclab.entity;

import java.awt.Graphics;

public abstract class Entity {
	protected float x,y;
	protected int radius;
	protected boolean isAlive = true;
	
	public abstract void update(int delta);
	public abstract void render(Graphics g);

	public boolean isAlive() {
		return isAlive;
	}
	
	public float getPositionX() {
		return x;
	}
	public float getPositionY() {
		return y;
	}
	public float getRadius() {
		return radius;
	}
}
