package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class OfficeManagerHomepage {

    static Scene getScene(List<String> fullname) {
        Stage window = new Stage();

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,75));
        grid.setVgap(8);
        grid.setHgap(10);

        //Buttons

        Button button1 = new Button();
        button1.setMinSize(200,1);
        button1.setText("View Reports");
        GridPane.setConstraints(button1, 0, 0);
        button1.setOnAction(e -> {
            try {
                ReportPage.display();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        homeLayout.getChildren().addAll(welcome ,button1);
        Scene homepage = new Scene(homeLayout, 300, 300);
        window.setTitle("Office Manager Homepage");
        return homepage;
    }

}
