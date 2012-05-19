package de.timweb.seclab.entity;

import java.awt.Graphics;
import java.awt.Image;

import de.timweb.seclab.Art;
import de.timweb.seclab.Game;

public class Explosion extends Entity {
	public static final int ENEMY = 0;
	public static final int PLAYER = 1;
	public static final int ASTEROID = 2;

	private static final int MAX_LIFETIME = 800;
	private int lifetime = 0;

	private int size;
	private int type;
	private boolean first = false;
	
	public Explosion(float positionX, float positionY, int type) {
		x = positionX;
		y = positionY;
		
		this.type = type;
		if(type == PLAYER)
			first = false;
		else
			first = true;
		
		if(type == ASTEROID)
			size = 48;
		else
			size = 32;
	}

	@Override
	public void update(int delta) {
		lifetime += delta;
		
		if(type == ASTEROID)
			y += 0.1f * delta;

		if(first && lifetime >= MAX_LIFETIME)
			isAlive = false;
	
		if(lifetime >= MAX_LIFETIME){
			lifetime = 0;
			first = true;
			if(type == PLAYER)
				Game.getPlayer().hide();
		}
	}

	@Override
	public void render(Graphics g) {
		if(!isAlive)
			return;
			
		Image img = null;
		if(type == ASTEROID)
			img = Art.img_dust.getSubimage((int)((lifetime/(float)MAX_LIFETIME)*8) * 96, 0, 96, 96);
		else
			img = Art.img_explosion.getSubimage((int)((lifetime/(float)MAX_LIFETIME)*12) * 64, 0, 64, 64);
		g.drawImage(img, (int)x - size, (int)y - size, null);
	}

}
