/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.MusicPlayer
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

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
			media = new Media(getClass().getResource("/com/res/music/mp3-noise.mp3").toURI()
					.toString());
		} catch(URISyntaxException e) {
			
			e.printStackTrace();
		}
		
		// assign Media Object with path to MediaPlayer Object mp
		mp = new MediaPlayer(media);
		
	}
	
	// method for playing and pausing mp3 file
	public void play_pauseMusic() {
		
		// check status of MediaPlayer object and play mp3 file
		if(mp.getStatus().equals(Status.READY) || mp.getStatus().equals(Status.PAUSED)) {
			
			mp.play();
			// check status of MediaPlayer object and pause mp3 file
		} else if(mp.getStatus().equals(Status.PLAYING)) {
			
			mp.pause();
		}
	}
	
	public void dispose() {
		
		mp.dispose();
	}
}
