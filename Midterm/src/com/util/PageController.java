/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: PageController.java
 * Main class: com.init.Main.java
 * Other Files in this Project:
 *     - com.views.StartPage.java
 *     - com.views.StartPage.StartPage.fxml
 *     - com.util.PageView.java
 *     - com.util.FXMLHelper.java
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 14
 * Last Modified: 10, 2017 14
 * Java Version: 1.8.0_141
 * Description: The representation of a Car object
 * ----------------------------------------------------------------------------+
 */

package com.util;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import java.util.stream.Stream;

public abstract class PageController extends Pane {

    protected final List<Pane> pages = new ArrayList<>();

    protected Pane previousPage;

    protected Pane currentPage;

    protected Pane mainPage;

    public PageController() { }

    protected final void initMainPage(Pane page) {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        }

        pageSetUp(page);
        addRegister(page);

        this.mainPage = page;
    }

    private void pageSetUp(Pane page) {

        page.managedProperty().bind(page.visibleProperty());
        page.setVisible(false);
    }


    protected final void showMain() {

        pages.forEach(p -> p.setVisible(false));
        mainPage.setVisible(true);
    }
    /**
     * Register a page to the controller
     *
     * @param page The desired pane to register
     * @throws NullPointerException If the page is null
     * @throws IllegalArgumentException IF the page has already been registered
     */
    public final PageController registerPage(Pane page) throws NullPointerException, IllegalArgumentException {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        } else if (pages.contains(page)) {

            throw new IllegalArgumentException("ERROR: The page specified is already registered");
        }


        pageSetUp(page);
        addRegister(page);

        pages.add(page);

        return this;
    }

    protected abstract void addRegister(Pane page);

    /**
     * Shows the specified page
     *
     * @param page The page in which to be shown
     * @throws IllegalArgumentException If the page was not registered
     */
    protected void showPage(Pane page) throws IllegalArgumentException {

        //Verify the giving pane is registered correctly
        Pane selected = pages.stream().findFirst().filter(p -> p.getId().equals(page.getId())).get();

        if (selected == null) {

            throw new IllegalArgumentException("ERROR: The page was not registered correctly");
        }

        Stream<Pane> paneStream =  pages.stream().filter(p -> !p.getId().equals(page.getId()));
        previousPage = paneStream.findFirst().filter(p -> p.isVisible()).get();

        paneStream.forEach(p -> p.setVisible(false));
        selected.setVisible(true);

        currentPage = selected;
    }

    /**
     * Makes the previous page visible
     *
     * @throws NullPointerException If their was no previous page
     */
    protected void showPrevious() throws NullPointerException {

        if (previousPage == null) {

            if (currentPage == null) {

                throw new  NullPointerException("Error: The requested previous page is null");
            }

            if (mainPage == null) {

                throw new NullPointerException("ERROR: Their is no main page defined");
            }
            showMain();
        }

        pages.forEach(p -> p.setVisible(false));
        previousPage.setVisible(true);
    }
}
