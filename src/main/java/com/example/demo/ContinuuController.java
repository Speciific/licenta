package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContinuuController implements Initializable {

    @FXML
    private Button bar1;
    @FXML
    private Button bar2;
    @FXML
    private AnchorPane panelside;


    @FXML
    private VBox vbo;
    @FXML
    private HBox hbo;


    @FXML
    private Label labelIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panelside.setTranslateX(-160);
        bar1.setVisible(true);
        bar2.setVisible(false);
        labelIcon.setVisible(false);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
/*
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/javafx1", "root", "numaistiuparola");
            preparedStatement = connection.prepareStatement("select nume_clasa from clase where user_id = 1");
            resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    String materie = resultSet.getString("nume_clasa");
                    listView.getItems().add(materie);

                }

        } catch (SQLException e){
            e.printStackTrace();
        }
*/

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/javafx1", "root", "numaistiuparola");
            preparedStatement = connection.prepareStatement("select nume_clasa from clase where user_id = 1");
            resultSet = preparedStatement.executeQuery();
            List<Button> buttonlist = new ArrayList<>(); //our Collection to hold newly created Buttons

            while(resultSet.next()){
                String materie = resultSet.getString("nume_clasa"); //extract button text, adapt the String to the columnname that you are interested in
                buttonlist.add(new Button(materie));
            }

            vbo.getChildren().clear();
            //hbo.getChildren().clear();
            vbo.getChildren().addAll(buttonlist);
            //hbo.getChildren().addAll(buttonlist);

            for (int i = 0; i < buttonlist.size(); i++) {
                //System.out.println(buttonlist.get(i).getText());
                buttonlist.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        DBUtils.changeScene2(event, "sign-up.fxml", "Sign Up!");
                    }
                });
            }


        } catch (SQLException e){
            e.printStackTrace();
        }



    }

    @FXML
    private ImageView imageIcon;



    @FXML
    private void entered(MouseEvent event){

        //nu vede ca intra dar vede ca iese?????????
        imageIcon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/javafx1", "root", "numaistiuparola");
                    //preparedStatement = connection.prepareStatement("select nume_clasa from clase where user_id = 1");
                    preparedStatement = connection.prepareStatement("select username from users where user_id = 1");
                    resultSet = preparedStatement.executeQuery();
                    List<Button> buttonlist = new ArrayList<>(); //our Collection to hold newly created Buttons
                    //ArrayList<User> userList = new ArrayList<User>();

                    while(resultSet.next()){
                        String user = resultSet.getString("username"); //extract button text, adapt the String to the columnname that you are interested in
                        buttonlist.add(new Button(user));
                    }

                    labelIcon.setText(buttonlist.get(0).getText());
                    labelIcon.setVisible(true);

                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });

        imageIcon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/javafx1", "root", "numaistiuparola");
                    preparedStatement = connection.prepareStatement("select nume_clasa from clase where user_id = 1");
                    //preparedStatement = connection.prepareStatement("select username from users where username = a");
                    resultSet = preparedStatement.executeQuery();
                    List<Button> buttonlist = new ArrayList<>(); //our Collection to hold newly created Buttons

                    while(resultSet.next()){
                        String materie = resultSet.getString("nume_clasa"); //extract button text, adapt the String to the columnname that you are interested in
                        buttonlist.add(new Button(materie));
                    }

                    labelIcon.setText(buttonlist.get(0).getText());
                    labelIcon.setVisible(false);

                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });


    }


    @FXML
    private void run2(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(panelside);

        slide.setToX(-160);
        slide.play();

        panelside.setTranslateX(0);

        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    @FXML
    private void run1(MouseEvent event){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(panelside);

        slide.setToX(0);
        slide.play();

        panelside.setTranslateX(-160);

        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(false);
            bar2.setVisible(true);
        });
    }

}
