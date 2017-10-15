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
 *     com
 *      ├── controls
 *      │   └── banner
 *      │       ├── Banner.fxml
 *      │       └── Banner.java
 *      ├── util
 *      │   ├── FXMLHelper.java
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
 * Description: Controls the visibility of each registered page
 * ----------------------------------------------------------------------------+
 */

package com.util;

import java.util.List;
import java.util.ArrayList;

public abstract class PageController {

    protected final List<PageView> pages = new ArrayList<>();

    protected PageView previousPage;

    protected PageView currentPage;

    protected PageView mainPage;

    public PageController() { }

    protected final void initMainPage(PageView page) {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        }

        pageSetUp(page);
        addRegister(page);

        this.mainPage = page;
    }

    private void pageSetUp(PageView page) {

        page.managedProperty().bind(page.visibleProperty());
        page.setVisible(false);
        pages.add(page);

        page.init(this);
    }


    public final void showMain() {

        switchPages(mainPage);
    }
    /**
     * Register a page to the controller
     *
     * @param page The desired pane to register
     * @throws NullPointerException If the page is null
     * @throws IllegalArgumentException IF the page has already been registered
     */
    public final PageController registerPage(PageView page) throws NullPointerException, IllegalArgumentException {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        } else if (pages.contains(page)) {

            throw new IllegalArgumentException("ERROR: The page specified is already registered");
        }


        pageSetUp(page);
        addRegister(page);

        return this;
    }

    private void switchPages(PageView page) {

        previousPage = currentPage;

        pages.forEach(p -> p.setVisible(false));
        page.setVisible(true);

        currentPage = page;
    }

    protected abstract void addRegister(PageView page);

    /**
     * Shows the specified page
     *
     * @param page The page in which to be shown
     * @throws IllegalArgumentException If the page was not registered
     */
    public void showPage(PageView page) throws IllegalArgumentException {

        //Verify the giving pane is registered correctly
        PageView selected = pages.stream().filter(p -> p.equals(page)).toArray(PageView[]::new)[0];

        if (selected == null) {

            throw new IllegalArgumentException("ERROR: The page was not registered correctly");
        }

        switchPages(selected);
    }

    /**
     * Makes the previous page visible
     *
     * @throws NullPointerException If their was no previous page
     */
    public void showPrevious() throws NullPointerException {

        if (previousPage == null) {

            if (currentPage == null) {

                throw new  NullPointerException("Error: The requested previous page is null");
            }

            if (mainPage == null) {

                throw new NullPointerException("ERROR: Their is no main page defined");
            }
            showMain();
        }

        switchPages(previousPage);
    }
}
