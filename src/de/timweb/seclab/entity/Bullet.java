package de.timweb.seclab.entity;

import java.awt.Color;
import java.awt.Graphics;

import de.timweb.seclab.Game;

public class Bullet extends Entity{
	public static final int DAMAGE = 1;
	private static final float SPEED = 0.6f;
	private float dx;
	private int dy;
	
	public Bullet(float x, float y, int direction) {
		this.x = x;
		this.y = y;
		dy = direction;
	}

	@Override
	public void update(int delta) {
		y += dy*SPEED *delta;
		x += SPEED * dx * delta;
		
		for(Asteroid a:Game.getAsteroids()){
			double dx = Math.abs(x-a.getPositionX());
			double dy = Math.abs(y-a.getPositionY());
			
			if(Math.sqrt(dx*dx + dy*dy) < a.getRadius()){
				if(this.dy == 1)
					a.hurt(10);
				else
					a.hurt(1);
				isAlive = false;
				if(this.dy == -1)
					Game.getPlayer().addPoints(-1);
			}
		}
		
		if(dy == 1){
			Player p =Game.getPlayer();
			double dx = Math.abs(x-p.getPositionX());
			double dy = Math.abs(y-p.getPositionY());
			
			if(Math.sqrt(dx*dx + dy*dy) < p.getRadius()){
				p.hurt();
				isAlive = false;
			}
		}else{
			EnemyShip e =Game.getEnemy();
			double dx = Math.abs(x-e.getPositionX());
			double dy = Math.abs(y-e.getPositionY());

			if(Math.sqrt(dx*dx + dy*dy) < e.getRadius()){
				e.hurt();
				isAlive = false;
			}
		}
		
		
		
		if(x < 0 || x > Game.WIDTH || y < 0)
			isAlive = false;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);

		int size = 4;
		if(dy == 1)
			size = 8;
		
		g.fillOval((int)x-size/2,(int) y-size/2, size, size);
	}

}
