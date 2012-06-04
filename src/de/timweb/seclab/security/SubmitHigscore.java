package de.timweb.seclab.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

public class SubmitHigscore {
	private static final int MIN_POINTS = 3000;

	public static void submit(int points) {
		if (points < MIN_POINTS)
			JOptionPane.showMessageDialog(null, "Du musst mindestens " + MIN_POINTS + " erreichen, um einen Highscore zu submitten!",
					"Not good enough", JOptionPane.INFORMATION_MESSAGE);
		else {
			try {
				URL url = new URL("http://tareksaier.bplaced.net/notHP/gamescore/addscore.php?score="+points+"&key="+KeyReader.getSubmitKey());

				URLConnection connection = url.openConnection();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				if(bufferedReader.readLine().contains("Roger")){
					JOptionPane.showMessageDialog(null, "Glueckwunsch! Du bist mit " + points + " Punkten auf der Highscoreliste!",
							"Higscore", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Da ist leider was schief gelaufen",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
