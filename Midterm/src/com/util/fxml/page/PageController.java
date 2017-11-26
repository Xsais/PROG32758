/*
 * ----------------------------------------------------------------------------------------------+
 *  * Group Leader: Daniel Hope
 *  * Member(s):
 *  *     - Georgina Luce
 *  *     - Nathaniel Primo
 *  *     - Michael Marc
 *  * Group #: 1
 *  * Filename: com.util.fxml.page.PageController
 *  * Main class: com.init.Main
 *  * Assignment: Midterm
 *  * Creation Date:
 *  * Last Modified: 11/1/17 8:34 PM
 *  * Java Version: 1.8.0_141
 *  * Description:
 * ----------------------------------------------------------------------------------------------+
 */

package com.util.fxml.page;

import com.controls.exitbar.ExitBar;
import com.util.fxml.NodeConstraint;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PageController extends GridPane implements Initializable {

    // Stores all of the validly registered pages
    protected final List<PageView> pages = new ArrayList<>();

    // Stores all of the nodes the are permanently visible
    protected final List<NodeConstraint> nodeConstraints = new ArrayList<>();

    protected final GridPane switchablePages = new GridPane();

    // Stores all of the validly registered pop-ups
    protected final GridPane popUps = new GridPane();

    private PageView previousPage;

    private PageView currentPage;

    private PageView currentPopup;

    private PageView mainPage;

    //Stores the Blur effect to be used with Pop Ups
    private BoxBlur boxBlur = new BoxBlur() {{

        setIterations(2);
    }};

    /**
     * Initializes the PageController
     */
    public PageController() {

    }

    /**
     * Initializes the PageController and assigns the Nodes to be visible
     *
     * @param nodeConstraints Node that are required to be visible
     */
    public PageController(NodeConstraint... nodeConstraints) {

        // Sets up Nodes to be displayed to the user
        for (NodeConstraint nodeConstraint : nodeConstraints) {

            int columnSpan = nodeConstraint.getColumnSpan();
            int rowSpan = nodeConstraint.getRowSpan();

            this.nodeConstraints.add(nodeConstraint);

            if (columnSpan != -1 && rowSpan != -1) {

                switchablePages.add(nodeConstraint.getNode(), nodeConstraint.getColumnIndex(),
                        nodeConstraint.getRowIndex(), columnSpan, rowSpan);
                return;
            }
            switchablePages.add(nodeConstraint.getNode(), nodeConstraint.getColumnIndex(),
                    nodeConstraint.getRowIndex());
        }
    }

    /**
     * Retrieves the first node that matches the SimpleName
     *
     * @param simpleName The SimpleName of the Node
     * @return The first Node the matches the SimpleName
     */
    public NodeConstraint getNode(String simpleName) {

        for (NodeConstraint nodeConstraint : nodeConstraints) {

            if (nodeConstraint.getNode().getClass().getSigners().equals(simpleName)) {

                return nodeConstraint;
            }
        }
        return null;
    }

    /**
     * Retrieves all of the nodes matching a giving name
     *
     * @param simpleName The SimpleName of the Node
     * @return All Nodes matching the SimpleName
     */
    public NodeConstraint[] getNodes(String simpleName) {

        return nodeConstraints.stream().filter(n -> n.getNode().getClass().getSigners().equals
                (simpleName)).toArray(NodeConstraint[]::new);
    }

    /**
     * Registers the main page
     *
     * @param page The desired main page
     */
    protected final void initMainPage(PageView page) {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        }

        // Sets up the page to be visually displayed
        pageSetUp(page);

        // Adds the page to all valid registers
        addRegister(page);

        this.mainPage = page;
    }

    /**
     * Sets the page for the first use
     *
     * @param page THe desired page
     */
    private void pageSetUp(PageView page) {

        page.managedProperty().bind(page.visibleProperty());
        page.setVisible(false);

        GridPane.setHgrow(page, Priority.ALWAYS);
        GridPane.setVgrow(page, Priority.ALWAYS);

        // Executes a callback to the giving Page
        page.init(this);

        pages.add(page);
    }

    /**
     * Register a page with the PageControllers
     *
     * @param page The desired Page to register
     */
    private void addRegister(PageView page) {

        switch (page.getPageType()) {

            case PAGE:

                switchablePages.add(page, 0, 1);
                break;
            case POP_UP:

                popUps.add(page, 0, 1);
                break;
        }
    }

    /**
     * Shows the defined PageControllers main page
     */
    public final void showMain() {

        if (mainPage == null) {

            return;
        }
        switchPages(mainPage);
    }

    /**
     * Register a page to the controller
     *
     * @param pages The desired pane to register
     * @throws NullPointerException     If the page is null
     * @throws IllegalArgumentException IF the page has already been registered
     */
    public final void registerPage(PageView... pages) throws NullPointerException,
                                                             IllegalArgumentException {

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
    public final void registerPage(PageView page) throws NullPointerException,
                                                         IllegalArgumentException {

        if (page == null) {

            throw new NullPointerException("ERROR: The page specified cannot be null");
        } else if (pages.contains(page)) {

            throw new IllegalArgumentException("ERROR: The page specified is already registered");
        }

        // Sets up the page to be visually displayed
        pageSetUp(page);

        // Adds the page to all valid registers
        addRegister(page);
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

        switchablePages.setPrefHeight(800);
        switchablePages.setVgap(8.0);
        switchablePages.setOnMouseClicked(p -> {

            if (!popUps.isVisible()) {

                return;
            }

            hidePopUps(-1);
        });

        popUps.add(new ExitBar(sender -> hidePopUps(-1)), 0, 0);

        popUps.getStyleClass().add("pop-out");

        add(switchablePages, 0, 0);

        GridPane.setHgrow(switchablePages, Priority.ALWAYS);
        GridPane.setVgrow(switchablePages, Priority.ALWAYS);

        popUps.setMinWidth(Double.NEGATIVE_INFINITY);
        popUps.setMinHeight(Double.NEGATIVE_INFINITY);
        popUps.setMaxWidth(Double.NEGATIVE_INFINITY);
        popUps.setMaxHeight(Double.NEGATIVE_INFINITY);

        add(popUps, 0, 0);
        popUps.setAlignment(Pos.CENTER);

        GridPane.setHalignment(popUps, HPos.CENTER);
        GridPane.setValignment(popUps, VPos.CENTER);
    }

    /**
     * Hides a pop-out from view
     *
     * @param statusCode The status code in wich it exited with
     * @return The codee in which the method exited with
     */
    public int hidePopUps(int statusCode) {

        if (currentPopup != null) {

            currentPopup.setVisible(false);
            currentPopup.onClose(this, statusCode);
        }
        currentPopup = null;

        popUps.setVisible(false);
        switchablePages.setEffect(null);
        return 0;
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
     * Switches the currently displayed page
     *
     * @param page The desired registered Pae
     */
    private void switchPages(PageView page) {

        // Force hides all pop ups from view
        hidePopUps(-1);

        if (currentPage != null) {

            currentPage.onClose(this, 0);
        }

        previousPage = currentPage;

        pages.forEach(p -> p.setVisible(false));
        page.setVisible(true);

        currentPage = page;
        currentPopup = null;

        popUps.setVisible(false);
        page.onOpen(this);
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
            case POP_UP:

                switchPopUp(page);
                break;
        }
    }

    /**
     * Switch to display the giving registered pop-out
     *
     * @param page The desired pop-out Page
     */
    private void switchPopUp(PageView page) {

        hidePopUps(0);

        page.setVisible(true);
        currentPopup = page;

        popUps.setPrefWidth(page.getPrefWidth());
        popUps.setPrefHeight(page.getPrefHeight());

        popUps.setBackground(page.getBackground());

        popUps.setVisible(true);
        switchablePages.setEffect(boxBlur);

        // Request that the page prepares for display
        page.onOpen(this);
    }

    /**
     * Shows the specified popout
     *
     * @param page The page in which to be shown
     * @throws IllegalArgumentException If the page was not registered
     * @deprecated Replaced by {@link #show(PageView)}
     */
    @Deprecated
    public void showPopup(PageView page) throws IllegalArgumentException {

        if (page.getPageType() != com.util.fxml.page.PageType.POP_UP) {

            throw new IllegalArgumentException("ERROR: The giving page is not valid");
        }

        switchPopUp(page);
    }

    /**
     * Makes the previous page visible
     *
     * @throws NullPointerException If their was no previous page
     */
    public void showPrevious() throws NullPointerException {

        if (previousPage == null) {

            // Shows Main Page is no other page to display
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

    /**
     * Request closure from all validly registered pages
     *
     * @param evt The Window closing event
     */
    public void close(WindowEvent evt) {

        pages.forEach(page -> page.onCloseRequest(evt));
    }
}
