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
 * Description: Initializes the DBProg32758 database and prepares it for data entry
 * ----------------------------------------------------------------------------+
 */

package com.views.displaydatabase;

import com.util.fxml.FXMLHelper;
import com.util.fxml.page.PageView;
import com.util.jdbc.ConnectToDB;
import com.views.adminpage.AdminPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

import javax.swing.*;
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

    private List<Person> personList = new ArrayList<>();

    private AdminPage adminPage;

    private TableView<Person> tableView;

    private TableColumn<Person, String> Name;

    private TableColumn<Person, String> Group;

    private TableColumn<Person, String> Login;

    private TableColumn<Person, String> Password;

    private TableColumn<Person, String> Logo;

    private TableColumn<Person, String> Score;

    /**
     * Displays database in 'page' format
     */
    public DisplayDataBase(ConnectToDB dbConnection) {

        try {
            // Uses DisplayDataBase.fxml to display database
            FXMLHelper.loadControl(this).load();

            String query = "SELECT CONCAT(`First_Name`, ' ', `Last_Name`) AS `Name`, `Group`, `Login`, `Password`, " +
                    "`Preferred_Car_Name`" +
                    "`Logo`, `Score`, `Credits` FROM DBProg32758.Players";

            // Run Query
            ResultSet res = dbConnection.executeQuerry(query);

            while (res.next()) {

                personList.add(new Person(res.getString(1), res.getInt(2)
                        , res.getString(3), res.getString(4), res.getString(5)
                        , res.getInt(6), res.getInt(7), res.getDouble(8)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage() + "SQL State: " + ex.getSQLState() + " ErrorCode: " + ex.getErrorCode(),
                    "Car Racing Game", javax.swing.JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        btnAdmin.setOnAction(evt -> pageController.show(adminPage));
		Name.setCellValueFactory(new PropertyValueFactory("name"));
        Group.setCellValueFactory(new PropertyValueFactory("group"));
        Login.setCellValueFactory(new PropertyValueFactory("login"));
        Password.setCellValueFactory(new PropertyValueFactory("password"));
        Logo.setCellValueFactory(new PropertyValueFactory("Logo"));
        Score.setCellValueFactory(new PropertyValueFactory("score"));
        
        tableView.getItems().setAll(personList);
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
        // empty
    }

    private class Person {

        private String name;

        private int group;

        private String login;

        private String password;

        private String carname;

        private int logo;

        private int score;

        private double credits;

        public Person(String name, int group, String login, String password, String carname, int logo, int score
                , double credits) {

            initName(name);
            initGroup(group);
            initLogin(login);
            initPassword(password);
            initCarname(carname);
            initLogo(logo);
            initScore(score);
            initCredits(credits);
        }

        public String getName() {

            return name;
        }

        private void initName(String name) {

            this.name = name;
        }

        public int getGroup() {

            return group;
        }

        private void initGroup(int group) {

            this.group = group;
        }

        public String getLogin() {

            return login;
        }

        private void initLogin(String login) {

            this.login = login;
        }

        public String getPassword() {

            return password;
        }

        private void initPassword(String password) {

            this.password = password;
        }

        public int getLogo() {

            return logo;
        }

        private void initLogo(int logo) {

            this.logo = logo;
        }

        public int getScore() {

            return score;
        }

        private void initScore(int score) {

            this.score = score;
        }

        public String getCarname() {

            return carname;
        }

        private void initCarname(String carname) {

            this.carname = carname;
        }

        public double getCredits() {

            return credits;
        }

        private void initCredits(double credits) {

            this.credits = credits;
        }
    }

}