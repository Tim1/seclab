package de.timweb.seclab.entity;

import java.awt.Graphics;
import java.awt.Image;

import de.timweb.seclab.Art;
import de.timweb.seclab.Game;

public class Player extends Entity {
	private static final float SPEED = 0.3f;
	private static final int SHOOTRATE = 200;

	
	private float acc_horz;
	private float acc_vert;
	
	
	private int lastHurt = 1000;
	private int lastShoot;
	private int lastTriple;
	private int health = 100;
	private int points = 0;
	private Image sprite;
	private boolean isHiden = false;

	public Player() {
		sprite = Art.img_ship;
		radius = 20;

		x = Game.WIDTH / 2 - radius;
		y = Game.HEIGHT - radius * 2;
	}

	@Override
	public void update(int delta) {
		checkforCollision();

		if (Game.isShooting() && ((lastShoot > 25 * 1000 / SHOOTRATE))) {
			lastShoot = 0;
			Game.addEntity(new Bullet(x - 11, y, -1));
			Game.addEntity(new Bullet(x + 12, y, -1));
		}

		lastShoot += delta;
		lastHurt += delta;
		
		move(delta);
		
		
		if (x < radius)
			x = radius;
		if (x > Game.WIDTH-radius)
			x = Game.WIDTH-radius;
		if (y < radius+Game.HEIGHT/3)
			y = radius+Game.HEIGHT/3;
		if (y > Game.HEIGHT-radius)
			y = Game.HEIGHT-radius;
	}

	private void move(int delta) {
		if (Game.dir_left)
			acc_horz -= delta/750f;
		if (Game.dir_right)
			acc_horz += delta/750f;
		if (Game.dir_up)
			acc_vert -= delta/750f;
		if (Game.dir_down)
			acc_vert += delta/750f;
		
		if(acc_horz > 1)
			acc_horz = 1;
		if(acc_horz < -1)
			acc_horz = -1;
		if(acc_vert > 1)
			acc_vert = 1;
		if(acc_vert < -1)
			acc_vert = -1;
		
		if(acc_vert > 0)
			acc_vert -= delta/2000f;
		if(acc_vert < 0)
			acc_vert += delta/2000f;
		if(acc_horz > 0)
			acc_horz -= delta/2000f;
		if(acc_horz < 0)
			acc_horz += delta/2000f;
			
		this.y += acc_vert*delta;
		this.x += acc_horz*delta;
		
	}

	private void checkforCollision() {
		for (Asteroid a : Game.getAsteroids()) {
			double dx = Math.abs(x - a.getPositionX());
			double dy = Math.abs(y - a.getPositionY());

			if (Math.sqrt(dx * dx + dy * dy) < (a.getRadius() + radius/3)) {
				health -= a.getHurtdamage();
				if(health < 0){
					health = 0;
					isAlive = false;
				}
				a.hurt(100);
				lastHurt = 0;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(isHiden  || (lastHurt < 1000 && (lastHurt/50) % 2 ==0  && isAlive))
			return;
			
		g.drawImage(sprite, (int) (x - radius), (int) (y - radius), null);
	}

	public int getHealth() {
		return health;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points){
		this.points += points;
	}
	
	public void hurt() {
		health -= Bullet.DAMAGE;
		if(health < 0){
			health = 0;
			isAlive = false;
		}
		lastHurt = 0;
	}
	public void hide() {
		isHiden = true;
	}
}
