/*

 * ----------------------------------------------------------------------------+

 * Group Leader: Daniel Hope

 * Member(s): Georgina Luce

 *            Nathaniel Primo

 *            Michael Marc

 * Group #: 1

 * Filename: MusicPlayer.java

 * Other Files in this Project:

 *     -

 * Assignment: Midterm - Micro-Project 1 (Part 1)

 * Creation Date: 10, 2017 16

 * Last Modified: 11, 2017 20

 * Java Version: 1.8.1_141

 * Description: Creates media player object and loads mp3 file from resources folder

 * ----------------------------------------------------------------------------+

 */

package com.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

import java.net.URISyntaxException;

/**
 * MusicPlayer class allows the playing of one predefined song with the ability to start, pause, and restart the song
 * from the paused state.
 */
public class MusicPlayer {

    // initialize MediaPlayer Object
    public MediaPlayer mp;

    private boolean playing;

    private boolean loaded;

    // constructor for MusicPlayer
    public MusicPlayer() {

        // initialize Media object and add path of mp3 file to it
        Media media = null;

        // retrieve song from resources folder
        try {
            media = new Media(getClass().getResource("/com/res/music/mp3-noise.mp3").toURI().toString());

            // catch exception and print stack trace
        } catch (URISyntaxException e) {

            e.printStackTrace();
        }

        // assign Media Object with path to MediaPlayer Object mp
        mp = new MediaPlayer(media);

    }

    public boolean isPlaying() {

        return mp.getStatus().equals(Status.PLAYING);
    }

    public void setPlaying(boolean playing) {

        this.playing = playing;

        if (!loaded) {

            loaded = mp.getStatus().equals(Status.READY);
            if (!loaded) {

                return;
            }
        }

        if (playing) {
            mp.play();
        } else {

            mp.pause();
        }
    }

    public Duration getPosition() {

        return mp.getCurrentTime();
    }

    public void setPosition(Duration position) {

        mp.setStartTime(position);
    }

    /**
     * Method for playing and pausing mp3 file
     */
    public void play_pauseMusic() {

        // check status of MediaPlayer object and play/pause mp3 file
        setPlaying(!playing);
    }

    /**
     * Handles disposing of the music
     */
    public void dispose() {

        mp.dispose();
    }

    public boolean isLoaded() {

        return loaded;
    }
}
