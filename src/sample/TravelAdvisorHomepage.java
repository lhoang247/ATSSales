package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TravelAdvisorHomepage {
    static Scene getScene() {
        Stage window = new Stage();
        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome Travel Advisor.");
        homeLayout.getChildren().addAll(welcome);
        Scene homepage = new Scene(homeLayout, 300, 300);
        window.setTitle("Travel Advisor Homepage");
        return homepage;
    }
}
