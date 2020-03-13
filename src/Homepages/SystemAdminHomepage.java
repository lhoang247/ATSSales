package Homepages;

import Admin.BackupAndRestore;
import Admin.ViewStaff;
import Admin.ViewTravelAgents;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Admin.ViewStock;
import General.LoginPage;

import java.util.List;

public class SystemAdminHomepage {

    public static Scene getScene(List<String> fullname) {
        //Windows
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
        button1.setText("View Stocks");
        GridPane.setConstraints(button1, 0, 0);

        button1.setOnAction(e -> {
            try {
                ViewStock.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button2 = new Button();
        button2.setMinSize(200,1);
        button2.setText("View Staff");
        GridPane.setConstraints(button2, 0, 1);

        button2.setOnAction(e -> {
            try {
                ViewStaff.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button5 = new Button();
        button5.setMinSize(200,1);
        button5.setText("View travel agent");
        GridPane.setConstraints(button5, 0, 2);

        button5.setOnAction(e -> {
            try {
                ViewTravelAgents.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        Button button3 = new Button();
        button3.setMinSize(200,1);
        button3.setText("Backup");
        GridPane.setConstraints(button3, 0, 3);

        button3.setOnAction(e -> {
            try {
                BackupAndRestore.Backupdbtosql();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button6 = new Button();
        button6.setMinSize(200,1);
        button6.setText("Restore");
        GridPane.setConstraints(button6, 0, 4);

        button6.setOnAction(e -> {
            try {
                BackupAndRestore.Restoredbfromsql("backup.sql");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button4 = new Button();
        button4.setMinSize(200,1);
        button4.setText("Logout");
        GridPane.setConstraints(button4, 0, 5);
        button4.setOnAction(e -> {
            LoginPage.setScene(LoginPage.loginScene());
                });

        //top border
        VBox homeLayout = new VBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        Label staffID = new Label("Staff ID: " + fullname.get(2));
        homeLayout.getChildren().addAll(welcome,staffID);


        //middle border
        grid.getChildren().addAll(button1,button2,button3,button4,button5,button6);

        //Creating border
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(homeLayout);
        borderPane.setCenter(grid);
        Scene homepage = new Scene(borderPane, 300, 300);
        window.setTitle("System Admin Homepage");
        return homepage;
    }
}
