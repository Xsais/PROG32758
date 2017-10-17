/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: Banner.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 15
 * Last Modified: 10, 2017 15
 * Java Version: 1.8.1_141
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.controls.banner;

import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import com.util.FXMLHelper;

public class Banner extends GridPane implements Initializable {

    @FXML
    private VBox vbCredits;

    @FXML
    private Label lblDesignedBy;

    private final String groupName = "Group 1";

    private final String[] groupMembers = {
            "Daniel Hope",
            "Georgina Luce",
            "Nathaniel Primo",
            "Michael Marc"
    };

    public Banner() {

        FXMLHelper.loadControl(this);
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

        lblDesignedBy.setText(String.format("Game designed by %s", groupName));

        ObservableList<javafx.scene.Node> creditNames = vbCredits.getChildren();
        for (String name : groupMembers) {

            Label nameLabel = new Label(String.format("• %s", name));
            nameLabel.getStyleClass().add("credit-text");

            creditNames.add(nameLabel);
        }
    }
}
