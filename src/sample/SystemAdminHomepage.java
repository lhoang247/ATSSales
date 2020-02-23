package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class SystemAdminHomepage {

    static Scene getScene(List<String> fullname) {
        //Windows
        Stage window = new Stage();

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,40,50));
        grid.setVgap(8);
        grid.setHgap(10);

        //Buttons

        Button button1 = new Button();
        button1.setMinSize(200,1);
        button1.setText("View Stocks");
        GridPane.setConstraints(button1, 0, 0);

        Button button2 = new Button();
        button2.setMinSize(200,1);
        button2.setText("View Staff");
        GridPane.setConstraints(button2, 0, 1);

        Button button3 = new Button();
        button3.setMinSize(200,1);
        button3.setText("Backup");
        GridPane.setConstraints(button3, 0, 2);

        Button button4 = new Button();
        button4.setMinSize(200,1);
        button4.setText("Logout");
        GridPane.setConstraints(button4, 0, 3);
        button4.setOnAction(e -> {
            LoginPage.setScene(LoginPage.loginScene());
                });

        //top border
        HBox homeLayout = new HBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        homeLayout.getChildren().addAll(welcome);


        //middle border
        grid.getChildren().addAll(button1,button2,button3,button4);

        //Creating border
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcome);
        borderPane.setCenter(grid);
        Scene homepage = new Scene(borderPane, 300, 300);
        window.setTitle("System Admin Homepage");
        return homepage;
    }
}
