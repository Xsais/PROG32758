/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.init;


import com.views.pointerpage.PointerPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PointerPage loadPage = new PointerPage();

        Scene scene = null;
        try {

            scene = new Scene(loadPage.getLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Car Racing Game");

        //Specify the scene to be used for the stage a show the window.
        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.setOnCloseRequest(evt -> loadPage.close(evt));
    }
}
