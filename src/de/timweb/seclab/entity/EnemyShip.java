package de.timweb.seclab.entity;

import java.awt.Graphics;
import java.awt.Image;

import de.timweb.seclab.Art;
import de.timweb.seclab.Game;
import de.timweb.seclab.SoundEffect;

public class EnemyShip extends Entity {
	private static final int SHOOTRATE = 50;
	private static final float SPEED = 0.4f;

	private int lastShoot;
	private int health = 10;
	private int lastDirChange;

	private float dx;
	private Image img;

	public EnemyShip(float x) {
		this.x = x;
		y = 50;

		img = Art.img_enemyship;
		radius = 20;
	}

	@Override
	public void update(int delta) {
		if (lastDirChange > 500) {
			dx = (float) (Math.random() - 0.5);
			lastDirChange = 0;
		}

		x += dx * SPEED * delta;

		if (x < radius)
			x = radius;
		if (x > Game.WIDTH - radius)
			x = Game.WIDTH - radius;

		if (lastShoot > 25 * 1000 / SHOOTRATE) {
			lastShoot = 0;
			SoundEffect.SHOOT2.play();
			Game.addEntity(new Bullet(x, y, 1));
		}
		lastDirChange += delta;
		lastShoot += delta;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int) (x - radius), (int) (y - radius), null);
	}

	public void hurt() {
		health -= Bullet.DAMAGE;
		if(health <= 0 && isAlive){
			isAlive = false;
			Game.getPlayer().addPoints(300);
		}
		
		Game.getPlayer().addPoints(20);
	}

	public int getHealth() {
		return health;
	}
}
