package com.controls.game;



import com.util.MusicPlayer;
import com.util.fxml.FXMLHelper;

import com.util.game.UseMyCar;

import com.util.info.User;

import com.util.stream.InterruptStream;

import javafx.application.Platform;

import javafx.beans.property.ReadOnlyStringProperty;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.ObservableList;

import javafx.concurrent.Service;

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



public class Game extends GridPane implements Initializable {



    @FXML

    private TextArea txaDisplay;



    private List<String> displayStrings = new ArrayList<>();



    private StringBuilder currentString = new StringBuilder();



    private String[] startUpArgs = new String[0];



    private SimpleStringProperty gameWinner = new SimpleStringProperty(this, "gameWinner");



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



                UseMyCar.main(startUpArgs);

            } finally {



                String winner = null;



                while (displayStrings.size() != 0) {

                    if (winner != null) {



                        displayStrings.remove(0);

                        continue;

                    }

                    String temp = displayStrings.get(0);

                    if (temp.contains("winner")) {



                        winner = temp.substring(temp.lastIndexOf(':') + 1, temp.length() - 2);

                    }

                    displayStrings.remove(0);

                }

                String finalWinner = winner;

                Platform.runLater(() -> gameWinner.set(finalWinner.trim()));

                gameRunning = false;

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



        Platform.runLater(() -> txaDisplay.appendText(Character.toString((char) ch)));

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



    public String getGameWinner() {



        return gameWinner.get();

    }



    public ReadOnlyStringProperty gameWinnerProperty() {



        return gameWinner;

    }

}
