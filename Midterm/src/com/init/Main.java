/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Main.java
 * Main class: com.init.Main.java
 * Other Files in this Project:
 *     - com.views.StartPage.java
 *     - com.views.StartPage.fxml
 *     - com.util.PageController.java
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description:
 * ----------------------------------------------------------------------------+
 */

package com.init;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = null;
        try {

            scene = new Scene(FXMLLoader.load(getClass().getResource("/com/views/StartPage/StartPage.fxml")));
        } catch (IOException e) {

            e.printStackTrace();
        }

        primaryStage.setTitle("Car Racing Game");

        //Specify the scene to be used for the stage a show the window.
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
