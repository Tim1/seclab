package de.timweb.seclab.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

public class SubmitHigscore {
	private static final int MIN_POINTS = 5000;

	public static void submit(int points) {
		if (points < 5000)
			JOptionPane.showMessageDialog(null, "Du musst mindestens " + MIN_POINTS + " erreichen, um einen Highscore zu submitten!",
					"Not good enough", JOptionPane.INFORMATION_MESSAGE);
		else {
			try {
				URL url = new URL("http://tareksaier.bplaced.net/notHP/gamescore/addscore.php?score="+points+"&key="+KeyReader.getSubmitKey());

				URLConnection connection = url.openConnection();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				if(bufferedReader.readLine().contains("Roger")){
					JOptionPane.showMessageDialog(null, "Glückwunsch! Du bist mit " + points + " Punkten auf der Highscoreliste!",
							"Higscore", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
