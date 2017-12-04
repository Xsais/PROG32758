package com.controls.game;


import com.util.MusicPlayer;
import com.util.fxml.FXMLHelper;
import com.util.game.UseMyCar;
import com.util.game.state.GameState;
import com.util.info.User;
import com.util.stream.InterruptStream;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import javax.swing.*;
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

    private User playingUser;

    private Function<Object, Integer> winningAction;

    private Function<Object, Integer> losingAction;


    private SimpleObjectProperty<GameState> gameState = new SimpleObjectProperty<>(this, "gameState"
            , GameState.Stopped);

    private Thread gameThread;

    private Timer creditCountdown = new Timer(1000, evt -> Platform.runLater(() -> {

        double tempCredit = playingUser.getCredit();

        playingUser.setCredit(Math.max(0, tempCredit - 0.5));

        if (playingUser.getCredit() <= 0) {

            gameState.set(GameState.Paused);
            JOptionPane.showMessageDialog(null
                    , "You are out of credits, please add more by clicking the credits refill button."
                    , "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
        }

    }));

    public Game() {


        try {


            FXMLHelper.loadControl(this).load();

        } catch (IOException e) {


            e.printStackTrace();

        }

    }

    public void clearDisplay() {

        Platform.runLater(() -> txaDisplay.clear());
    }

    public void getPausedElapsed() {

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

        gameState.addListener((observable, old, newV) -> {

            switch (newV) {

                case Running:
                    creditCountdown.start();
                    if (old == GameState.Paused) {

                        return;
                    }

                    if (playingUser.getCredit() <= 0) {

                        gameState.set(old);
                        JOptionPane.showMessageDialog(null
                                , "You are out of credits, please add more by clicking the credits refill button."
                                , "Car Racing Game", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    txaDisplay.clear();
                    startGame();
                    break;
                case Stopped:

                    creditCountdown.stop();
                    break;
                case Paused:
                    creditCountdown.stop();
                    break;
            }
        });
    }

    private void startGame() {

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

                        if (winner.trim().equals(getPreferredCar())) {

                            Platform.runLater(() -> {

                                if (winningAction == null) {

                                    return;
                                }
                                Platform.runLater(() -> winningAction.apply(this));
                            });
                        } else {

                            if (losingAction == null) {

                                return;
                            }
                            Platform.runLater(() -> losingAction.apply(this));
                        }

                        displayStrings.clear();

                        setGameState(GameState.Stopped);
                        return;

                    }

                    if (winner != null) {


                        displayStrings.remove(0);

                    }

                }

            }

        });

        gameThread.start();
    }

    private boolean catchInterrupts(int ch) {

        if (this.gameState.get() == GameState.Paused) {

            while (this.gameState.get() != GameState.Running) {

                if (this.gameState.get() == GameState.Stopped) {

                    gameThread.stop();
                    return false;
                }
            }
        }

        Platform.runLater(() -> txaDisplay.appendText(Character.toString((char) ch)));

        currentString.append((char) ch);

        if (ch == 10) {

            displayStrings.add(currentString.toString());

            currentString = new StringBuilder();

        }

        return true;
    }

    public String getPreferredCar() {


        return playingUser.getPreferredCar();

    }

    public String[] getStartUpArgs() {


        return startUpArgs;

    }

    public void setStartUpArgs(String[] startUpArgs) {


        this.startUpArgs = startUpArgs;

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
        playingUser.setPreferredCar(playingUser.getPreferredCar());
    }

    public GameState getGameState() {

        return gameState.get();
    }

    public void setGameState(GameState gameState) {

        this.gameState.set(gameState);
    }

    public ReadOnlyObjectProperty<GameState> gameStateProperty() {

        return gameState;
    }

    public Function<Object, Integer> getLosingAction() {

        return losingAction;
    }

    public void setLosingAction(Function<Object, Integer> losingAction) {

        this.losingAction = losingAction;
    }
}
