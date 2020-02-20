package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SystemAdminHomepage {

    static Scene getScene() {
        Stage window = new Stage();
        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome System Admin.");
        homeLayout.getChildren().addAll(welcome);
        Scene homepage = new Scene(homeLayout, 300, 300);
        window.setTitle("System Admin Homepage");
        return homepage;
    }
}
