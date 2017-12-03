/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.fxml.page.PageView
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
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
     * Occurs when the application closes peacefully
     *
     * @param evt The WindowEvent associated with the closure
     */
    public abstract void onCloseRequest(WindowEvent evt);

    /**
     * Occurs when the PageView has been requested to open
     *
     * @param sender The Object in which sent the request
     */
    public abstract void onOpen(Object sender, String... args);

    public void dispose(){ }

    /**
     * Passes the reference of the PageController to registered PageView
     *
     * @param pageController The PageController that is the holder of the PageView
     */
    public void init(PageController pageController) {

        this.pageController = pageController;
    }
}
