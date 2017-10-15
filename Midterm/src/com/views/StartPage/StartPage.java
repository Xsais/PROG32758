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
 *     - com.views.StartPage.java
 *     - com.util.PageController.java
 *     - com.util.PageView.java
 *     - com.util.FXMLHelper.java
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description:
 * ----------------------------------------------------------------------------+
 */

package com.views.StartPage;

import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import com.util.FXMLHelper;
import javafx.event.ActionEvent;

public class StartPage extends GridPane implements Initializable {

    private final String groupName = "Group 1";

    private final String[] groupMembers = {
      "Daniel Hope",
      "Georgina Luce",
      "Nathaniel Primo",
      "Michael Marc"
    };

    @FXML
    private Label lblDesignedBy;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnUser;

    @FXML
    private VBox vbCredits;

    public StartPage() {

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

        btnAdmin.setText("Administrator\n(DB Creation and Initialization)");

        btnUser.setText("User\n(Sign-up and Login)");

        ObservableList<javafx.scene.Node> creditNames = vbCredits.getChildren();
        for (String name : groupMembers) {

            Label nameLabel = new Label(String.format("• %s", name));
            nameLabel.getStyleClass().add("credit-text");

            creditNames.add(nameLabel);
        }
    }
}
