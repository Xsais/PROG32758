/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: PageView.java
 * Main class: 
 * Other Files in this Project:
 *     com
 *      ├── controls
 *      │   └── banner
 *      │       ├── Banner.fxml
 *      │       └── Banner.java
 *      ├── util
 *      │   ├── FXMLHelper.java
 *      │   └── PageController.java
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
 * Description: Abstract class that links all pages
 * ----------------------------------------------------------------------------+
 */

package com.util.fxml.page;

import javafx.scene.layout.GridPane;
import javafx.stage.WindowEvent;

public abstract class PageView extends GridPane {

    // Stores the current status of the page
    protected PageType pageType = PageType.PAGE;

    // A copy of the registering PageController
    protected PageController pageController;

    /**
     * Retrieves the current state of the page
     *
     * @return
     */
    public PageType getPageType() {

        return this.pageType;
    }

    /**
     * Occurs when the PageView has been requested to close
     *
     * @param sender     The object in which closed the PageView
     * @param statusCode The giving status code of the page closure
     */
    public abstract void onClose(Object sender, int statusCode);

    /**
     * Occurs when the application classes peacefully
     *
     * @param evt The WindowEvent associated with the closure
     */
    public abstract void onCloseRequest(WindowEvent evt);

    /**
     * Occurs when the PageView has been requested to open
     *
     * @param sender The Object in which sent the request
     */
    public abstract void onOpen(Object sender);

    /**
     * Passes the reference of the PageController to registered PageView
     *
     * @param pageController The PageController that is the holder of the PageView
     */
    public void init(PageController pageController) {

        this.pageController = pageController;
    }
}
