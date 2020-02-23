package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class TravelAdvisorHomepage {

    static Scene getScene(List<String> fullname) {
        Stage window = new Stage();

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,75));
        grid.setVgap(8);
        grid.setHgap(10);

        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        homeLayout.getChildren().addAll(welcome);
        Scene homepage = new Scene(homeLayout, 300, 300);
        window.setTitle("Travel Advisor Homepage");
        return homepage;
    }
}
