package com.util;

import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MusicPlayer {

	// initialize MediaPlayer Object
	public MediaPlayer mp;

	public MusicPlayer() {

		// initialize Media object and add path of mp3 file to it
		Media media = null;
		try {
			media = new Media(getClass().getResource("/com/res/music/thedistance.mp3").toURI().toString());
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}

		// assign Media Object with path to MediaPlayer Object mp
		mp = new MediaPlayer(media);

	}

	// method for playing and pausing mp3 file
	public void play_pauseMusic() {

		// check status of MediaPlayer object and play mp3 file
		if (mp.getStatus().equals(Status.READY) || mp.getStatus().equals(Status.PAUSED)) {

			mp.play();
			// check status of MediaPlayer object and pause mp3 file
		} else if (mp.getStatus().equals(Status.PLAYING)) {

			mp.pause();
		}
		
		//TODO: mp.dispose() at end of game or in event of GameMenu exit (going back to UserLogin
		// or anywhere else aside from closing the program)
	}
}
