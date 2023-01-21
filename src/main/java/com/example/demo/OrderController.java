package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private HBox hboxx;

    List<String> listOfSomething = null;
    //@FXML
//    private Pane panedynamic;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private ObservableList<Button> buttons = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hboxx.getChildren().add((Node) buttons);
    }

    @FXML
    void acttable(ActionEvent event) throws SQLException {

        String sqlQuery = "SELECT * FROM restauranttables;";
        List<Button> buttonlist = new ArrayList<>(); //our Collection to hold newly created Buttons
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/javafx1", "root", "numaistiuparola");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) { //iterate over every row returned
                String restaurant = resultSet.getString("restaurantname"); //extract button text, adapt the String to the columnname that you are interested in
                buttonlist.add(new Button(restaurant));

            }
            hboxx.getChildren().clear(); //remove all Buttons that are currently in the container
            hboxx.getChildren().addAll(buttonlist); //then add all your Buttons that you just created

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            resultSet.close();
            statement.close();

        }
    }

    @FXML
    void actlogout(ActionEvent event) {

    }

    @FXML
    void actnew(ActionEvent event) {

    }

    @FXML
    void actorder(ActionEvent event) {

    }

    @FXML
    void actreports(ActionEvent event) {

    }

    @FXML
    void actstock(ActionEvent event) {

    }



    @FXML
    void actusers(ActionEvent event) {

    }

}
