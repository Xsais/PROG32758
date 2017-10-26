/*

 * ----------------------------------------------------------------------------+

 * Group Leader: Daniel Hope

 * Member(s): Georgina Luce

 *            Nathaniel Primo

 *            Michael Marc

 * Group #: 1

 * Filename: UserPage.java

 * Main class: 

 * Other Files in this Project:

 *     - 

 * Assignment: 

 * Creation Date: 10, 2017 21

 * Last Modified: 10, 2017 21

 * Java Version: 

 * Description: The representation of a Car object

 * ----------------------------------------------------------------------------+

 */



package com.views.userpage;



import com.util.ConnectToDB;
import com.util.PageController;
import com.util.PageType;
import com.util.PageView;
import com.views.adminpage.AdminPage;
import com.views.userlogin.UserLogin;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;



public class UserPage extends PageView implements Initializable {



    @FXML

    private Button btnLogin;



    @FXML

    private Button btnCreate;



    private ConnectToDB dbConnection;
    
    
    private UserLogin userLogin;

    

    public UserPage(ConnectToDB dbConnection) {



        this.dbConnection = dbConnection;

        try {

            com.util.FXMLHelper.loadControl(this).load();

        } catch (java.io.IOException e) {

            e.printStackTrace();

        }

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

    	userLogin = new UserLogin(dbConnection);
    	  
        btnLogin.setOnAction(p -> pageController.show(userLogin));
			

        /* TODO: User Creation

            btnCreate.setOnAction();

        */
          
    }
    
    public void init(PageController pageController) {

    	super.init(pageController);

    	pageController.registerPage(userLogin);

    }
}
