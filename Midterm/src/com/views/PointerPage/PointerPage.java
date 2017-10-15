/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: PointerPage.java
 * Main class: 
 * Other Files in this Project:
 *     - 
 * Assignment: 
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.views.PointerPage;

import javafx.fxml.Initializable;
import com.util.PageController;
import com.views.StartPage.StartPage;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class PointerPage extends PageController implements Initializable {

    @FXML
    private GridPane pagesRegistry;

    private ObservableList<Node> pagesChildren;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        pagesChildren = pagesRegistry.getChildren();
        initMainPage(new StartPage());
        showMain();
    }

    @Override
    protected void addRegister(Pane page) {

        pagesChildren.add(page);
    }
}
