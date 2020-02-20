package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OfficeManagerHomepage {
    public static void display() {
        Stage window = new Stage();
        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome office manager.");
        homeLayout.getChildren().addAll(welcome);
        Scene homepage = new Scene(homeLayout, 300, 300);
        window.setTitle("Office Manager Homepage");
        window.setScene(homepage);
        window.show();
    }

    static Scene getScene() {
        Stage window = new Stage();
        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome office manager.");
        homeLayout.getChildren().addAll(welcome);
        Scene homepage = new Scene(homeLayout, 300, 300);
        window.setTitle("Office Manager Homepage");
        return homepage;
    }

}
