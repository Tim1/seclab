package de.timweb.seclab;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum SoundEffect {
	EXPLODE("Explosion.wav"), //
	HIT("Hit.wav"), //
	ASTEROID("Asteroid.wav"), //
	LEVELUP("LevelUp.wav"), //
	MUSIC("music_komp.wav"), //
	SHOOT1("Shoot1.wav"), //
	SHOOT2("Shoot2.wav");

	private static boolean isMuted = false;
	private Clip[] clips;
	private int curser;

	SoundEffect(String file) {
		try {
			URL url = SoundEffect.class.getResource("/sfx/" + file);

			if (file == "Shoot1.wav" || file == "Shoot2.wav")
				clips = new Clip[5];
			else
				clips = new Clip[2];

			for (int i = 0; i < clips.length; i++) {

				AudioInputStream ais = AudioSystem.getAudioInputStream(url);

				AudioFormat format = ais.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clips[i] = (Clip) AudioSystem.getLine(info);
				clips[i].open(ais);

				ais.close();
			}

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (isMuted)
			return;
		new Thread() {
			@Override
			public void run() {
				try {
					clips[curser].stop();
					clips[curser].setFramePosition(0);
					clips[curser].start();
				} catch (Exception e) {
				}
			}
		}.start();

		curser = ++curser % clips.length;

	}

	public void loop() {
		clips[0].loop(-1);
	}

	private void stop() {
		for (Clip c : clips)
			c.stop();
	}

	static void init() {
		values();
	}

	public static void mute() {
		if (!isMuted) {
			SoundEffect.MUSIC.stop();
			isMuted = true;
		} else {
			SoundEffect.MUSIC.loop();
			isMuted = false;
		}
	}
}
