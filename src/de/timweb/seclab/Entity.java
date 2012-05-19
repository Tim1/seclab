package de.timweb.seclab;

import java.awt.Graphics;

public abstract class Entity {
	protected float x,y;
	protected float radius;
	protected boolean isAlive = true;
	
	public abstract void update(int delta);
	public abstract void render(Graphics g);

	public boolean isAlive() {
		return isAlive;
	}
}
