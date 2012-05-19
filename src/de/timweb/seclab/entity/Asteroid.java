package de.timweb.seclab.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import de.timweb.seclab.Art;
import de.timweb.seclab.Game;

public class Asteroid extends Entity {
	public static final int BIG = 0;
	public static final int MEDIUM = 1;
	public static final int SMALL = 2;

	private static final int HURTDAMAGE = 6;
	
	private static final float SPEED = 0.1f;

	private float dx;
	private int hits;
	private Image img;

	public Asteroid(int size, float x, float y,float dx) {
		this.x = x;
		this.y = y;
		this.dx =  dx;
		
		switch (size) {
		case BIG:
			img = Art.img_asteroid_big_break0;
			radius = 48;
			break;
		case MEDIUM:
			img = Art.img_asteroid_medium_break0;
			radius = 24;
			break;
		case SMALL:
			img = Art.img_asteroid_small_break0;
			radius = 12;
			break;
		}

	}

	public Asteroid(int size) {
		this(size, (float) (Math.random() * (Game.WIDTH - 100) + 50), 0,(float) (Math.random() * 0.4 - 0.2));
	}

	@Override
	public void update(int delta) {
		y += SPEED * delta;
		x += SPEED * dx * delta;

		if (hits >= 20) {
			isAlive = false;
			int nextsize;
			if (radius == 48)
				nextsize = MEDIUM;
			else if (radius == 24)
				nextsize = SMALL;
			else
				return;

			int negativDx = dx < 0 ? -1:1;
			Game.addAsteroid(new Asteroid(nextsize, x-radius/4, y,-dx*negativDx));
			Game.addAsteroid(new Asteroid(nextsize, x+radius/4, y, dx*negativDx));
			Game.addEntity(new Explosion(x, y, Explosion.ASTEROID));
		}
		if (y > Game.HEIGHT)
			isAlive = false;
	}

	@Override
	public void render(Graphics g) {
		setImage();
		switch (hits/5) {
		case 0:
			g.setColor(Color.darkGray);
			break;
		case 1:
			g.setColor(Color.gray);
			break;
		case 2:
			g.setColor(Color.white);
			break;
		case 3:
			g.setColor(Color.white);
		}

//		g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
		g.drawImage(img, (int) (x - radius), (int) (y - radius), null);
	}

	private void setImage() {
		switch (radius) {
		case 48:
			switch (hits/5) {
			case 0:
				img = Art.img_asteroid_big_break0;
				break;
			case 1:
				img = Art.img_asteroid_big_break1;
				break;
			case 2:
				img = Art.img_asteroid_big_break2;
				break;
			case 3:
				img = Art.img_asteroid_big_break3;
			}
			break;
		case 24:
			switch (hits/5) {
			case 0:
				img = Art.img_asteroid_medium_break0;
				break;
			case 1:
				img = Art.img_asteroid_medium_break1;
				break;
			case 2:
				img = Art.img_asteroid_medium_break2;
				break;
			case 3:
				img = Art.img_asteroid_medium_break3;
			}
			break;
		case 12:
			switch (hits/5) {
			case 0:
				img = Art.img_asteroid_small_break0;
				break;
			case 1:
				img = Art.img_asteroid_small_break1;
				break;
			case 2:
				img = Art.img_asteroid_small_break2;
				break;
			case 3:
				img = Art.img_asteroid_small_break3;
			}
			break;
		}
	}

	public void hurt(int strength) {
		hits+=strength;
	}

	public int getHurtdamage() {
		if(hits > 20 || !isAlive)
			return 0;
		int faktor = 1;
		switch (radius) {
		case 48:
			faktor = 4;
			break;
		case 24:
			faktor = 2;
			break;
		}
		
		return HURTDAMAGE*faktor;
	}
}
