package com.views.displaydatabase;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.views.displaydatabase.DisplayDataBase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataBaseControler implements Initializable {

    @FXML 
    private TableView<DisplayDataBase> tableView;
    private TableColumn<DisplayDataBase, String> Name;
    private TableColumn<DisplayDataBase, String> Group;
    private TableColumn<DisplayDataBase, String> Login;
    private TableColumn<DisplayDataBase, String> Password;
    private TableColumn<DisplayDataBase, String> Logo;
    private TableColumn<DisplayDataBase, String> Score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("name"));
        Group.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("group"));
        Login.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("login"));
        Password.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("password"));
        Logo.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("Logo"));
        Score.setCellValueFactory(new PropertyValueFactory<DisplayDataBase, String>("score"));

        try {
			tableView.getItems().setAll(parseDataList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    private List<DisplayDataBase> parseDataList() throws SQLException{
    	List<DisplayDataBase> numbers = new ArrayList<DisplayDataBase>();
    	ResultSet returnedResult = DisplayDataBase.res;
    	while (returnedResult.next()) {
			System.out.println(returnedResult.getString(1) + "\t" + returnedResult.getString(2) + "\t" + returnedResult.getString(3) + "\t"
					+ returnedResult.getString(4) + "\t" + returnedResult.getString(5) + "\t" + returnedResult.getString(6));
		}
		return numbers;
    }
}