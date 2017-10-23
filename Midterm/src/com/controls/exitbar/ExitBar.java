/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: ExitBar.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 21
 * Last Modified: 10, 2017 21
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.controls.exitbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class ExitBar extends GridPane implements Initializable {

    @FXML
    private Pane pneClose;

    private Function<Object, Integer> exitAction;

    public ExitBar(Function<Object, Integer> exitAction) {

        try {
            com.util.FXMLHelper.loadControl(this).load();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        this.exitAction = exitAction;
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

        pneClose.setOnMouseClicked(p -> {

            try {

                exitAction.apply(this);
            } catch (Exception e) {

                e.printStackTrace();
            }
        });
    }
}
