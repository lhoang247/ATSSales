package Homepages;

import Entities.Data2;
import OfficeManager.*;
import SQLqueries.SQLCustomers;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import General.*;

import java.util.List;

public class TravelAdvisorHomepage {

    public static Scene getScene(List<String> fullname) throws Exception {
        Stage window = new Stage();

        int staffNumber = Integer.parseInt(fullname.get(2));

        TableView table1;

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,40,50));
        grid.setVgap(8);
        grid.setHgap(10);

        //Buttons

        //Buttons

        Button button1 = new Button();
        button1.setMinSize(200,1);
        button1.setText("View Reports");
        GridPane.setConstraints(button1, 0, 0);

        button1.setOnAction(e -> {
            try {
                ReportPage.display(staffNumber, fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });


        Button button3 = new Button();
        button3.setMinSize(200,1);
        button3.setText("Report Sales");
        GridPane.setConstraints(button3, 0, 1);
        button3.setOnAction(e -> {
            try {
                SalesPage.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button4 = new Button();
        button4.setMinSize(200,1);
        button4.setText("View customer accounts");
        GridPane.setConstraints(button4, 0, 2);
        button4.setOnAction(e -> {
            try {
                CustomerPage.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button5 = new Button();
        button5.setMinSize(200,1);
        button5.setText("Refund sale");
        GridPane.setConstraints(button5, 0, 3);

        button5.setOnAction(e -> {
            try {
                refundSale.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button6 = new Button();
        button6.setMinSize(200,1);
        button6.setText("Update Late Payments");
        GridPane.setConstraints(button6, 0, 4);
        button6.setOnAction(e -> {
            try {
                UpdateLatePayment.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button7 = new Button();
        button7.setMinSize(200,1);
        button7.setText("Update Exchange Rate");
        GridPane.setConstraints(button7, 0, 5);
        button7.setOnAction(e -> {
            try {
                UpdateExchangeRate.display();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button8 = new Button();
        button8.setMinSize(200,1);
        button8.setText("Logout");
        GridPane.setConstraints(button8, 0, 6);
        button8.setOnAction(e -> {
            LoginPage.setScene(LoginPage.loginScene());
        });

        TableColumn<Data2, String> emailColumn = new TableColumn<>("Late Payment Customers");
        emailColumn.setMinWidth(180);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("data21"));

        table1 = new TableView<>();
        table1.setMinSize(190, 80);
        table1.setMaxSize(190, 80);
        table1.setItems(SQLCustomers.getLatePayment(staffNumber));
        table1.getColumns().addAll(emailColumn);
        GridPane.setConstraints(table1, 0, 9);
        GridPane.setHalignment(table1, HPos.CENTER);

        VBox homeLayout = new VBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        Label staffID = new Label("Staff ID: " + fullname.get(2));
        homeLayout.getChildren().addAll(welcome ,staffID);
        grid.getChildren().addAll(button1,button3,button4,button5,button6,table1,button7,button8);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(homeLayout);
        borderPane.setCenter(grid);

        Scene homepage = new Scene(borderPane, 300, 420);
        window.setTitle("Travel Advisor Homepage");
        return homepage;
    }
}
