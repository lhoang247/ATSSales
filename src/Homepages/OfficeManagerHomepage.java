package Homepages;

import OfficeManager.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import General.*;

import java.util.List;

public class OfficeManagerHomepage {

    public static Scene getScene(List<String> fullname) {
        Stage window = new Stage();

        int staffNumber = Integer.parseInt(fullname.get(2));

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,40,40,40));
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
        button4.setText("View customer accounts");
        GridPane.setConstraints(button4, 0, 3);
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
        GridPane.setConstraints(button5, 1, 0);

        button5.setOnAction(e -> {
            try {
                refundSale.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        Button button7 = new Button();
        button7.setMinSize(200,1);
        button7.setText("Set discount");
        GridPane.setConstraints(button7, 1, 1);
        button7.setOnAction(e -> {
            try {
                DiscountPage.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button8 = new Button();
        button8.setMinSize(200,1);
        button8.setText("Edit commissions");
        GridPane.setConstraints(button8, 1, 2);
        button8.setOnAction(e -> {
            try {
                EditCommissionRates.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button9 = new Button();
        button9.setMinSize(200,1);
        button9.setText("Plan Flexible Discount");
        GridPane.setConstraints(button9, 1, 4);
        button9.setOnAction(e -> {
            try {
                CreateFlexibleDiscount.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button10 = new Button();
        button10.setMinSize(200,1);
        button10.setText("Plan Fixed Discount");
        GridPane.setConstraints(button10, 1, 3);
        button10.setOnAction(e -> {
            try {
                CreateFixedDiscount.display(staffNumber,fullname.get(3));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button button6 = new Button();
        button6.setMinSize(200,1);
        button6.setText("Logout");
        GridPane.setConstraints(button6, 0, 4);
        button6.setOnAction(e -> {
            LoginPage.setScene(LoginPage.loginScene());
        });

        VBox homeLayout = new VBox(10);
        Label welcome = new Label("Welcome " + fullname.get(0) + " " + fullname.get(1));
        Label staffID = new Label("Staff ID: " + fullname.get(2));
        homeLayout.getChildren().addAll(welcome ,staffID);
        grid.getChildren().addAll(button1,button2,button3,button4,button5,button6,button7,button8,button9,button10);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(homeLayout);
        borderPane.setCenter(grid);

        Scene homepage = new Scene(borderPane);
        window.setTitle("Office Manager Homepage");
        return homepage;
    }

}
