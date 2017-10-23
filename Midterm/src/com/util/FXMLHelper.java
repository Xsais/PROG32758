/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: StartPage.java
 * Main class: com.init.Main.java
 * Other Files in this Project:
 *     com
 *      ├── controls
 *      │   └── banner
 *      │       ├── Banner.fxml
 *      │       └── Banner.java
 *      ├── util
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
 * Description: Assist in the loading of FXML controls
 * ----------------------------------------------------------------------------+
 */

package com.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Maintains the required data for the FXMLHelper object
 *
 * @author Nathaniel Primo
 **/

public class FXMLHelper {

    public static <T extends Node> FXMLLoader loadControl(T controller) {

        return loadControl(controller, String.format("%s.fxml"
                , controller.getClass().getSimpleName()));
    }

    public static <T extends Node> FXMLLoader loadControl(T controller, String fxmlName) {

        Class<?> controllerClass = controller.getClass();

        return new FXMLLoader(controllerClass.getResource(fxmlName)) {{

            setRoot(controller);
            setController(controller);
        }};
    }
}
