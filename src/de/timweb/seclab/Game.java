package de.timweb.seclab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.timweb.seclab.entity.Asteroid;
import de.timweb.seclab.entity.EnemyShip;
import de.timweb.seclab.entity.Entity;
import de.timweb.seclab.entity.Explosion;
import de.timweb.seclab.entity.Player;
import de.timweb.seclab.security.SubmitHigscore;

public class Game extends JFrame implements Runnable, KeyListener {
	public static final int FPS_TARGET = 120;
	public static final int DELTA_TARGET = 1000 / FPS_TARGET;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 640;

	private static int asteroidrate = 1;

	private int delta;
	private Image doubleBuffer;
	private Graphics g;
	private Image bgImg;
	private int bgY;
	private Font fontNormal;
	private Font fontGameOver;
	private boolean enter;
	private static int level;

	public static boolean dir_left, dir_right, dir_up, dir_down;
	private static boolean isGameOver = false;
	private boolean isSubmitted = false;
	private boolean dialogSkip;
	private static boolean isShooting;

	private static int lastFace;
	private static int lastPoint;
	private static int lastAsteroid;
	private static int lastLevelUP;
	private static Player player;
	private static EnemyShip enemy;
	private static Explosion explosion;
	private static ArrayList<Entity> entities;
	private static ArrayList<Asteroid> asteroids;

	public Game() {
		super("Seclab Asteroids");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		addKeyListener(this);

		doubleBuffer = createImage(WIDTH, HEIGHT);
		g = doubleBuffer.getGraphics();

		fontNormal = new Font("Arial", Font.BOLD, 16);
		fontGameOver = new Font("Arial", Font.BOLD, 96);
		g.setFont(fontNormal);

		SoundEffect.init();
		Art.init();
		bgImg = Art.img_background;
		restart();
	}

	private void restart() {
		explosion = null;
		isGameOver = false;
		isSubmitted = false;
		level = 0;
		lastPoint = 0;
		lastAsteroid = 0;
		lastLevelUP = 0;

		asteroidrate = 1;
		player = new Player();
		enemy = new EnemyShip(WIDTH / 2);
		entities = new ArrayList<Entity>();
		asteroids = new ArrayList<Asteroid>();
	}

	@Override
	public void run() {
		long timeOld = System.currentTimeMillis();
		delta = 0;

		SoundEffect.MUSIC.loop();

		while (true) {
			timeOld = System.currentTimeMillis();

			update(delta);
			render();

			long timepassed = System.currentTimeMillis() - timeOld;
			if (timepassed < DELTA_TARGET) {
				try {
					Thread.sleep(DELTA_TARGET - timepassed);
				} catch (InterruptedException e) {
				}
			}
			delta = (int) (System.currentTimeMillis() - timeOld);
		}

	}

	private void render() {
		g.clearRect(0, 0, WIDTH, HEIGHT);

		g.drawImage(bgImg, 0, bgY / 20 - 640, null);
		g.drawImage(bgImg, 0, bgY / 20, null);

		if (level == 0) {
			if (lastFace < 250)
				g.drawImage(Art.img_dialog_1a, 0, 0, null);
			else
				g.drawImage(Art.img_dialog_1b, 0, 0, null);
		}
		if (level == 2) {
			if (lastFace < 250)
				g.drawImage(Art.img_dialog_2a, 0, 0, null);
			else
				g.drawImage(Art.img_dialog_2b, 0, 0, null);
		}
		if (level == 4) {
			if (lastFace < 250)
				g.drawImage(Art.img_dialog_3a, 0, 0, null);
			else
				g.drawImage(Art.img_dialog_3b, 0, 0, null);
		}

		if (level == 0 || level == 2 || level == 4) {
			g.setColor(Color.white);
			g.drawString("<M> to Mute/Unmute", 450, 45);
			g.drawString("Continue with <Enter>", 250, 600);

			this.getGraphics().drawImage(doubleBuffer, 0, 0, null);
			return;
		}

		for (Entity e : entities)
			e.render(g);
		for (Entity e : asteroids)
			e.render(g);

		if (enemy != null && level >= 5)
			enemy.render(g);

		player.render(g);

		g.setColor(Color.green);
		g.drawString("Points: " + player.getPoints(), 10, 45);
		g.drawString("Health: " + player.getHealth() + " %", 10, 70);

		g.setColor(Color.red);
		g.drawString("Level: " + level, 540, 45);

		if (explosion != null) {
			explosion.render(g);
			if (!explosion.isAlive()) {
				g.setColor(Color.red);
				g.setFont(fontGameOver);
				g.drawString("GAME OVER", 10, 350);
				g.setFont(fontNormal);
				g.drawString("Enter to restart", 250, 400);
			}
		}

		this.getGraphics().drawImage(doubleBuffer, 0, 0, null);
	}

	private void update(int delta) {

		if (!player.isAlive())
			isGameOver = true;

		if (explosion != null && !explosion.isAlive() && !isSubmitted) {
			isSubmitted = true;
			SubmitHigscore.submit(player.getPoints());
		}

		if (isGameOver && explosion == null)
			explosion = new Explosion(player.getPositionX(), player.getPositionY(), Explosion.PLAYER);
		if (explosion != null)
			explosion.update(delta);

		if (isGameOver) {
			if (enter)
				restart();
			return;
		}

		if (level == 0 || level == 2 || level == 4) {
			lastFace += delta;
			lastFace %= 500;
			if (dialogSkip)
				increaseDifficulty();
			dialogSkip = false;
			return;
		}

		player.update(delta);

		if (lastAsteroid > 1000 * 1000 / asteroidrate && level >= 3) {
			lastAsteroid = 0;
			asteroids.add(new Asteroid(Asteroid.BIG));
		}
		lastAsteroid += delta;

		if (lastPoint > 666) {
			player.addPoints(3);
			lastPoint = 0;
		}
		lastPoint += delta;

		if ((level == 1 || level == 3) && lastLevelUP > 7500)
			increaseDifficulty();

		if (lastLevelUP > 15 * 1000)
			increaseDifficulty();
		lastLevelUP += delta;

		for (int i = 0; i < asteroids.size(); i++) {
			Entity e = asteroids.get(i);
			if (!e.isAlive())
				asteroids.remove(i);
			else
				e.update(delta);
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (!e.isAlive())
				entities.remove(i);
			else
				e.update(delta);
		}

		if (enemy != null && level >= 5)
			enemy.update(delta);
		if (enemy != null && !enemy.isAlive()) {
			addEntity(new Explosion(enemy.getPositionX(), enemy.getPositionY(), Explosion.ENEMY));
			enemy = new EnemyShip((enemy.getPositionX() + Game.WIDTH / 2) % Game.WIDTH);
			increaseDifficulty();
		}

		bgY += delta;
		bgY %= HEIGHT * 20;
	}

	public static void main(String[] args) {
		Game game = new Game();


		Thread thread = new Thread(game);
		thread.start();

	}

	public int getFPS() {
		return delta > 0 ? 1000 / delta : Integer.MAX_VALUE;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			dir_left = true;
			break;
		case KeyEvent.VK_RIGHT:
			dir_right = true;
			break;
		case KeyEvent.VK_UP:
			dir_up = true;
			break;
		case KeyEvent.VK_DOWN:
			dir_down = true;
			break;
		case KeyEvent.VK_SPACE:
			isShooting = true;
			break;
		case KeyEvent.VK_ENTER:
			enter = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			dir_left = false;
			break;
		case KeyEvent.VK_RIGHT:
			dir_right = false;
			break;
		case KeyEvent.VK_UP:
			dir_up = false;
			break;
		case KeyEvent.VK_DOWN:
			dir_down = false;
			break;
		case KeyEvent.VK_SPACE:
			isShooting = false;
			break;
		case KeyEvent.VK_ENTER:
			enter = false;
			if (level == 0 || level == 2 || level == 4)
				dialogSkip = true;
			break;
		case KeyEvent.VK_M:
			SoundEffect.mute();
			break;
		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

	public static void addAsteroid(Asteroid asteroid) {
		asteroids.add(asteroid);
	}

	public static void addEntity(Entity e) {
		entities.add(e);
	}

	public static void increaseDifficulty() {
		if (level <= 6) {
			asteroidrate = 500;
			lastAsteroid = 0;
		}

		lastLevelUP = 0;
		level++;
		SoundEffect.LEVELUP.play();
		asteroidrate *= 1.2;
	}

	public static boolean isShooting() {
		return isShooting;
	}

	public static Player getPlayer() {
		return player;
	}

	public static EnemyShip getEnemy() {
		return enemy;
	}

}
