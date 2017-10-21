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
 *     com
 *      ├── controls
 *      │   └── banner
 *      │       ├── Banner.fxml
 *      │       └── Banner.java
 *      ├── util
 *      │   ├── FXMLHelper.java
 *      │   ├── PageController.java
 *      │   └── PageView.java
 *      └── views
 *          ├── adminPage
 *          │   ├── AdminPage.fxml
 *          │   └── AdminPage.java
 *          ├── pointerPage
 *          │   ├── PointerPage.fxml
 *          │   └── PointerPage.java
 *          └── startPage
 *              ├── StartPage.fxml
 *              └── StartPage.java
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: Command center for the game
 * ----------------------------------------------------------------------------+
 */

package com.init;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import com.util.ConnectToDB;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.util.ConnectToDB;


public class Main extends Application {

    private static ConnectToDB dbConnection;

    public static ConnectToDB getDbConnection() {

        return dbConnection;
    }

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = null;
        try {

            scene = new Scene(FXMLLoader.load(getClass().getResource("/com/views/pointerpage/PointerPage.fxml")));
        } catch (IOException e) {

            e.printStackTrace();
        }

        primaryStage.setTitle("Car Racing Game");

        try {

            dbConnection = new ConnectToDB("localhost","root", "");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,e.getMessage() + "SQL State: "
                            + e.getSQLState() + " ErrorCode: " + e.getErrorCode(), "Car Racing Game",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }

        primaryStage.setOnCloseRequest(c -> dbConnection.closeConnection());

        //Specify the scene to be used for the stage a show the window.
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
