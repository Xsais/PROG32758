/*
 * ----------------------------------------------------------------------------+
 * Group Leader: Daniel Hope
 * Member(s): Georgina Luce
 *            Nathaniel Primo
 *            Michael Marc
 * Group #: 1
 * Filename: CreateDataBAse.java
 * Other Files in this Project:
 *     -
 * Assignment: Midterm - Micro-Project 1 (Part 1)
 * Creation Date: 10, 2017 16
 * Last Modified: 11, 2017 26
 * Java Version: 1.8.1_141
 * Description: setializes the DBProg32758 database and prepares it for data entry
 * ----------------------------------------------------------------------------+
 */

package com.views.displaydatabase;

import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageType;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayDataBase extends PageView implements Initializable {

    @FXML
    private Button btnAdmin;

    @FXML
    private TableView<Person> tbUsers;

    @FXML
    private TableColumn<Person, String> tdName;

    @FXML
    private TableColumn<Person, Integer> tdGroup;

    @FXML
    private TableColumn<Person, String> tdLogin;

    @FXML
    private TableColumn<Person, String> tdPassword;

    @FXML
    private TableColumn<Person, String> tdCarName;

    @FXML
    private TableColumn<Person, Integer> tdLogo;

    @FXML
    private TableColumn<Person, Integer> tdScore;

    @FXML
    private TableColumn<Person, Double> tdCredit;

    private ConnectToDB dbConnection;

    /**
     * Displays database in 'page' format
     */
    public DisplayDataBase(ConnectToDB dbConnection) {

        pageType = PageType.POP_UP;

        this.dbConnection = dbConnection;

        pageType = PageType.POP_UP;

        this.dbConnection = dbConnection;

        try {
            // Uses DisplayDataBase.fxml to display database
            FXMLHelper.loadControl(this).load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClose(Object sender, int statusCode) {
        // empty
    }

    @Override
    public void onCloseRequest(WindowEvent evt) {
        // empty
    }

    @Override
    public void onOpen(Object sender, String... args) {

        String query = "SELECT CONCAT(`First_Name`, ' ', `Last_Name`) AS `Name`, `Group`, `Login`, `Password`, " +
                "`Preferred_Car_Name`," +
                "`Logo`, `Score`, `Credits` FROM DBProg32758.Players";

        List<Person> displayedUser = new ArrayList<>();
        try {
            ResultSet res = dbConnection.executeQuerry(query);
            while (res.next()) {

                displayedUser.add(new Person(res.getString(1), res.getInt(2)
                        , res.getString(3), res.getString(4), res.getString(5)
                        , res.getInt(6), res.getInt(7), res.getDouble(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tbUsers.getItems().setAll(displayedUser);
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnAdmin.setOnAction(evt -> pageController.hidePopUps(0));

        tdName.setCellValueFactory(new PropertyValueFactory("name"));
        tdGroup.setCellValueFactory(new PropertyValueFactory("group"));
        tdLogin.setCellValueFactory(new PropertyValueFactory("login"));
        tdPassword.setCellValueFactory(new PropertyValueFactory("password"));
        tdCarName.setCellValueFactory(new PropertyValueFactory("carname"));
        tdLogo.setCellValueFactory(new PropertyValueFactory("logo"));
        tdScore.setCellValueFactory(new PropertyValueFactory("score"));
        tdCredit.setCellValueFactory(new PropertyValueFactory("credits"));
    }

    public class Person {

        private SimpleStringProperty name = new SimpleStringProperty(this, "name");

        private SimpleIntegerProperty group = new SimpleIntegerProperty(this, "group");

        private SimpleStringProperty login = new SimpleStringProperty(this, "login");

        private SimpleStringProperty password = new SimpleStringProperty(this, "password");

        private SimpleStringProperty carname = new SimpleStringProperty(this, "carname");

        private SimpleIntegerProperty logo = new SimpleIntegerProperty(this, "logo");

        private SimpleIntegerProperty score = new SimpleIntegerProperty(this, "score");

        private SimpleDoubleProperty credits = new SimpleDoubleProperty(this, "credits");

        public Person(String name, int group, String login, String password, String carname, int logo, int score
                , double credits) {

            setName(name);
            setGroup(group);
            setLogin(login);
            setPassword(password);
            setCarname(carname);
            setLogo(logo);
            setScore(score);
            setCredits(credits);
        }

        public String getName() {

            return name.get();
        }

        public void setName(String name) {

            this.name.set(name);
        }

        public int getGroup() {

            return group.get();
        }

        public void setGroup(int group) {

            this.group.set(group);
        }

        public String getLogin() {

            return login.get();
        }

        public void setLogin(String login) {

            this.login.set(login);
        }

        public String getPassword() {

            return password.get();
        }

        public void setPassword(String password) {

            this.password.set(password);
        }

        public int getLogo() {

            return logo.get();
        }

        public void setLogo(int logo) {

            this.logo.set(logo);
        }

        public int getScore() {

            return score.get();
        }

        public void setScore(int score) {

            this.score.set(score);
        }

        public String getCarname() {

            return carname.get();
        }

        public void setCarname(String carname) {

            this.carname.set(carname);
        }

        public double getCredits() {

            return credits.get();
        }

        public void setCredits(double credits) {

            this.credits.set(credits);
        }
    }

}