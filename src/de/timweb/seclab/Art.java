package de.timweb.seclab;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art {
	public static Image img_dialog_1a;
	public static Image img_dialog_1b;
	public static Image img_dialog_2a;
	public static Image img_dialog_2b;
	public static Image img_dialog_3a;
	public static Image img_dialog_3b;
	
	public static Image img_background;
	public static Image img_ship;
	public static Image img_enemyship;
	public static Image img_bullet;
	public static BufferedImage img_explosion;
	public static BufferedImage img_dust;
	public static Image img_asteroid_big_break0;
	public static Image img_asteroid_big_break1;
	public static Image img_asteroid_big_break2;
	public static Image img_asteroid_big_break3;
	public static Image img_asteroid_medium_break0;
	public static Image img_asteroid_medium_break1;
	public static Image img_asteroid_medium_break2;
	public static Image img_asteroid_medium_break3;
	public static Image img_asteroid_small_break0;
	public static Image img_asteroid_small_break1;
	public static Image img_asteroid_small_break2;
	public static Image img_asteroid_small_break3;

	// public static Image img_asteroid_medium;
	// public static Image img_asteroid_small;

	public static void init() {
		try {
			img_dialog_1a = ImageIO.read(Art.class.getResource("/res/dialog_1a.png"));
			img_dialog_1b = ImageIO.read(Art.class.getResource("/res/dialog_1b.png"));
			img_dialog_2a = ImageIO.read(Art.class.getResource("/res/dialog_2a.png"));
			img_dialog_2b = ImageIO.read(Art.class.getResource("/res/dialog_2b.png"));
			img_dialog_3a = ImageIO.read(Art.class.getResource("/res/dialog_3a.png"));
			img_dialog_3b = ImageIO.read(Art.class.getResource("/res/dialog_3b.png"));
			
			img_background = ImageIO.read(Art.class.getResource("/res/bg.png"));
			img_ship = ImageIO.read(Art.class.getResource("/res/ship.png"));
			img_enemyship = ImageIO.read(Art.class.getResource("/res/enemy.png"));
			img_explosion = ImageIO.read(Art.class.getResource("/res/explode.png"));
			img_dust = ImageIO.read(Art.class.getResource("/res/dust.png"));
			
			
			img_asteroid_big_break0 = ImageIO.read(Art.class.getResource("/res/asteroid_break0.png"));
			img_asteroid_medium_break0 = img_asteroid_big_break0.getScaledInstance(img_asteroid_big_break0.getHeight(null) / 2,
					img_asteroid_big_break0.getHeight(null) / 2, BufferedImage.SCALE_SMOOTH);
			img_asteroid_small_break0 = img_asteroid_big_break0.getScaledInstance(img_asteroid_big_break0.getHeight(null) / 4,
					img_asteroid_big_break0.getHeight(null) / 4, BufferedImage.SCALE_SMOOTH);

			img_asteroid_big_break1 = ImageIO.read(Art.class.getResource("/res/asteroid_break1.png"));
			img_asteroid_medium_break1 = img_asteroid_big_break1.getScaledInstance(img_asteroid_big_break1.getHeight(null) / 2,
					img_asteroid_big_break1.getHeight(null) / 2, BufferedImage.SCALE_SMOOTH);
			img_asteroid_small_break1 = img_asteroid_big_break1.getScaledInstance(img_asteroid_big_break1.getHeight(null) / 4,
					img_asteroid_big_break1.getHeight(null) / 4, BufferedImage.SCALE_SMOOTH);
			
			img_asteroid_big_break2 = ImageIO.read(Art.class.getResource("/res/asteroid_break2.png"));
			img_asteroid_medium_break2 = img_asteroid_big_break2.getScaledInstance(img_asteroid_big_break2.getHeight(null) / 2,
					img_asteroid_big_break2.getHeight(null) / 2, BufferedImage.SCALE_SMOOTH);
			img_asteroid_small_break2 = img_asteroid_big_break2.getScaledInstance(img_asteroid_big_break2.getHeight(null) / 4,
					img_asteroid_big_break2.getHeight(null) / 4, BufferedImage.SCALE_SMOOTH);
			
			img_asteroid_big_break3 = ImageIO.read(Art.class.getResource("/res/asteroid_break3.png"));
			img_asteroid_medium_break3 = img_asteroid_big_break3.getScaledInstance(img_asteroid_big_break3.getHeight(null) / 2,
					img_asteroid_big_break3.getHeight(null) / 2, BufferedImage.SCALE_SMOOTH);
			img_asteroid_small_break3 = img_asteroid_big_break3.getScaledInstance(img_asteroid_big_break3.getHeight(null) / 4,
					img_asteroid_big_break3.getHeight(null) / 4, BufferedImage.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
