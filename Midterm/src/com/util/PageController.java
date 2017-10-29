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

import com.controls.banner.Banner;
import com.controls.exitbar.ExitBar;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

public class PageController extends GridPane implements Initializable {

    protected final List<PageView> pages = new ArrayList<>();

    protected final GridPane switchablePages = new GridPane();

    protected final GridPane popOuts = new GridPane();

    private PageView previousPage;

    private PageView currentPage;

    private PageView currentPopup;

    private PageView mainPage;

    private BoxBlur boxBlur = new BoxBlur() {{

        setIterations(2);
    }};

    public PageController() {

    }

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

        page.init(this);

        pages.add(page);
    }


    public final void showMain() {

        switchPages(mainPage);
    }

    /**
     * Register a page to the controller
     *
     * @param pages The desired pane to register
     * @throws NullPointerException     If the page is null
     * @throws IllegalArgumentException IF the page has already been registered
     */
    public final void registerPage(PageView... pages) throws NullPointerException, IllegalArgumentException {

        for (PageView page : pages) {

            registerPage(page);
        }
    }

    /**
     * Register a page to the controller
     *
     * @param page The desired pane to register
     * @throws NullPointerException     If the page is null
     * @throws IllegalArgumentException IF the page has already been registered
     */
    public final void registerPage(PageView page) throws NullPointerException, IllegalArgumentException {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        } else if (pages.contains(page)) {

            throw new IllegalArgumentException("ERROR: The page specified is already registered");
        }


        pageSetUp(page);
        addRegister(page);
    }

    private void addRegister(PageView page) {

        switch (page.getPageType()) {

            case PAGE:

                switchablePages.add(page, 0, 1);
                break;
            case POP_OUT:

                popOuts.add(page, 0, 1);
                break;
        }

        GridPane.setHgrow(page, Priority.ALWAYS);
        GridPane.setVgrow(page, Priority.ALWAYS);
    }

    private void switchPages(PageView page) {

        hidePopOut(-1);

        if (currentPage != null) {

            currentPage.onClose(this, 0);
        }

        previousPage = currentPage;

        pages.forEach(p -> p.setVisible(false));
        page.setVisible(true);

        currentPage = page;
        currentPopup = null;

        popOuts.setVisible(false);
        page.onOpen(this);
    }

    private void switchPopOut(PageView page) {

        hidePopOut(0);

        page.setVisible(true);
        currentPopup = page;

        popOuts.setPrefWidth(page.getPrefWidth());
        popOuts.setPrefHeight(page.getPrefHeight());

        popOuts.setBackground(page.getBackground());

        popOuts.setVisible(true);
        switchablePages.setEffect(boxBlur);

        page.onOpen(this);
    }

    public int hidePopOut(int statusCode) {

        if (currentPopup != null) {

            currentPopup.setVisible(false);
            currentPopup.onClose(this, statusCode);
        }
        currentPopup = null;

        popOuts.setVisible(false);
        switchablePages.setEffect(null);
        return 0;
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or <tt>null</tt> if the
     *                  location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        switchablePages.add(new Banner(), 0, 0);

        switchablePages.setVgap(8.0);
        switchablePages.setOnMouseClicked(p -> {

            if (!popOuts.isVisible()) {

                return;
            }

            hidePopOut(-1);
        });

        popOuts.add(new ExitBar(sender -> hidePopOut(-1)), 0, 0);

        popOuts.getStyleClass().add("pop-out");

        add(switchablePages, 0, 0);

        GridPane.setHgrow(switchablePages, Priority.ALWAYS);
        GridPane.setVgrow(switchablePages, Priority.ALWAYS);

        popOuts.setMinWidth(Double.NEGATIVE_INFINITY);
        popOuts.setMinHeight(Double.NEGATIVE_INFINITY);
        popOuts.setMaxWidth(Double.NEGATIVE_INFINITY);
        popOuts.setMaxHeight(Double.NEGATIVE_INFINITY);

        add(popOuts, 0, 0);
        popOuts.setAlignment(Pos.CENTER);

        GridPane.setHalignment(popOuts, HPos.CENTER);
        GridPane.setValignment(popOuts, VPos.CENTER);
    }

    /**
     * Shows the specified page
     *
     * @param page The page in which to be shown
     * @throws IllegalArgumentException If the page was not registered
     * @deprecated Replaced by {@link #show(PageView)}
     */
    @Deprecated
    public void showPage(PageView page) throws IllegalArgumentException {

        if (page.getPageType() != PageType.PAGE) {

            throw new IllegalArgumentException("ERROR: The giving page is not valid");
        }

        switchPages(page);
    }

    /**
     * Shows the specified page or popout
     *
     * @param page The page in which to be shown
     * @throws IllegalArgumentException If the page was not registered
     */
    public void show(PageView page) throws IllegalArgumentException {

        switch (page.getPageType()) {
            case PAGE:

                switchPages(page);
                break;
            case POP_OUT:

                switchPopOut(page);
                break;
        }
    }

    /**
     * Shows the specified popout
     *
     * @param page The page in which to be shown
     * @throws IllegalArgumentException If the page was not registered
     * @deprecated Replaced by {@link #show(PageView)}
     */
    @Deprecated
    public void showPopout(PageView page) throws IllegalArgumentException {

        if (page.getPageType() != com.util.PageType.POP_OUT) {

            throw new IllegalArgumentException("ERROR: The giving page is not valid");
        }

        switchPopOut(page);
    }

    /**
     * Makes the previous page visible
     *
     * @throws NullPointerException If their was no previous page
     */
    public void showPrevious() throws NullPointerException {

        if (previousPage == null) {

            if (currentPage == null) {

                throw new NullPointerException("Error: The requested previous page is null");
            }

            if (mainPage == null) {

                throw new NullPointerException("ERROR: Their is no main page defined");
            }
            showMain();
        }

        switchPages(previousPage);
    }

    public void close(javafx.stage.WindowEvent evt) {

        pages.forEach(page -> page.onCloseRequest(evt));
    }
}
