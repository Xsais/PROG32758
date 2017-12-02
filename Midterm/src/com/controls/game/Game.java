package com.controls.game;


import com.util.MusicPlayer;
import com.util.fxml.FXMLHelper;
import com.util.game.UseMyCar;
import com.util.info.User;
import com.util.stream.InterruptStream;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;


public class Game extends GridPane implements Initializable {


    @FXML

    private TextArea txaDisplay;


    private List<String> displayStrings = new ArrayList<>();


    private StringBuilder currentString = new StringBuilder();


    private String[] startUpArgs = new String[0];


    private SimpleStringProperty preferredCar = new SimpleStringProperty(this, "preferredCar", "YourPreferedCar");

    private User playingUser;

    private Function<Object, Integer> winningAction;


    private boolean gameRunning;


    private Thread gameThread;


    public Game() {


        try {


            FXMLHelper.loadControl(this).load();

        } catch (IOException e) {


            e.printStackTrace();

        }


        gameThread = new Thread(() -> {

            MusicPlayer mp = new MusicPlayer();

            try {

                System.setOut(new PrintStream(new InterruptStream(i -> catchInterrupts(i))));

                String[] startArgs = new String[startUpArgs.length + 1];

                startArgs[startUpArgs.length] = String.format("--carname=%s", getPreferredCar());

                if (startUpArgs.length != 0) {

                    System.arraycopy(startUpArgs, 0, startArgs, 0, startUpArgs.length);
                }

                UseMyCar.main(startArgs);

            } finally {


                String winner;


                while (displayStrings.size() != 0) {

                    winner = displayStrings.get(0);

                    if (winner.contains("winner")) {


                        winner = winner.substring(winner.lastIndexOf(':') + 1, winner.length() - 2);

                        if (true/*winner.trim().equals(preferredCar.get())*/) {

                            Platform.runLater(() -> {

                                if (winningAction == null) {

                                    return;
                                }
                                winningAction.apply(this);
                            });
                        }

                        gameRunning = false;
                        return;

                    }

                    if (winner != null) {


                        displayStrings.remove(0);

                    }

                }

            }

        });

    }


    public void startGame() {


        if (gameRunning) {


            return;

        }


        gameThread.start();


        gameRunning = true;

    }


    private boolean catchInterrupts(int ch) {

        Platform.runLater(() -> txaDisplay.appendText(Character.toString((char)ch)));

        currentString.append((char) ch);

        if (ch == 10) {

            displayStrings.add(currentString.toString());

            currentString = new StringBuilder();

        }

        return true;
    }


    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  <p>
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */

    @Override

    public void initialize(URL location, ResourceBundle resources) {

    }


    public String[] getStartUpArgs() {


        return startUpArgs;

    }


    public void setStartUpArgs(String[] startUpArgs) {


        this.startUpArgs = startUpArgs;

    }


    public String getPreferredCar() {


        return preferredCar.get();

    }


    public ReadOnlyStringProperty gamePreferredCar() {


        return preferredCar;

    }

    public Function<Object, Integer> getWinningAction() {

        return winningAction;
    }

    public void setWinningAction(Function<Object, Integer> winningAction) {

        this.winningAction = winningAction;
    }

    public User getPlayingUser() {

        return playingUser;
    }

    public void setPlayingUser(User playingUser) {

        this.playingUser = playingUser;
        preferredCar.set(playingUser.getPreferredCar());
    }
}
