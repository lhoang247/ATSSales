package Homepages;

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
import sample.AssignBlanks;
import sample.LoginPage;
import sample.ReportPage;
import sample.SalesPage;

import java.util.List;

public class OfficeManagerHomepage {

    public static Scene getScene(List<String> fullname) {
        Stage window = new Stage();

        int staffNumber = Integer.parseInt(fullname.get(2));

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,40,50));
        grid.setVgap(8);
        grid.setHgap(10);

        //Buttons

        Button button1 = new Button();
        button1.setMinSize(200,1);
        button1.setText("View Reports");
        GridPane.setConstraints(button1, 0, 1);

        button1.setOnAction(e -> {
            try {
                ReportPage.display(staffNumber, fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        GridPane.setConstraints(button1, 0, 0);

        Button button2 = new Button();
        button2.setMinSize(200,1);
        button2.setText("Assign Blanks");
        GridPane.setConstraints(button2, 0, 1);

        button2.setOnAction(e -> {

            try {
                AssignBlanks.display(staffNumber, fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button3 = new Button();
        button3.setMinSize(200,1);
        button3.setText("Report Sales");
        GridPane.setConstraints(button3, 0, 2);
        button3.setOnAction(e -> {
            try {
                SalesPage.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        Button button4 = new Button();
        button4.setMinSize(200,1);
        button4.setText("Logout");
        GridPane.setConstraints(button4, 0, 3);
        button4.setOnAction(e -> {
            LoginPage.setScene(LoginPage.loginScene());
        });

        VBox homeLayout = new VBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        Label staffID = new Label("Staff ID: " + fullname.get(2));
        homeLayout.getChildren().addAll(welcome ,staffID);
        grid.getChildren().addAll(button1,button2,button3,button4);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(homeLayout);
        borderPane.setCenter(grid);

        Scene homepage = new Scene(borderPane, 300, 300);
        window.setTitle("Office Manager Homepage");
        return homepage;
    }

}
