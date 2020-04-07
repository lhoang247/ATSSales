package OfficeManager;

import General.ErrorBox;
import SQLqueries.SQLCustomers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This class generates a window that allows the user to create fixed discount for a customer.
//Very small and simple form.

public class CreateFixedDiscount {

    public static void display(int staffNumber, String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating VBox for the title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for the title.

        Label labelTitle = new Label();
        labelTitle.setText("Fixed Discount Plan");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);


        //Creating HBox for a general label and TextField.

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(6);
        hBox.setPadding(new Insets(10,10,10,10));

        Label discountLabel = new Label("Discount: ");

        //TextField for user's input.

        TextField discountField = new TextField();
        discountField.setMaxWidth(50);


        //Another HBox created for the confirm Button.

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        //Creating Confirm Button.

        Button confirmButton = new Button("Confirm");


        //Adding assets to the respective HBoxes.

        hBox1.getChildren().add(confirmButton);
        hBox.getChildren().addAll(discountLabel,discountField);


        //Action listener used to add the fixed discount plan to the database.

        confirmButton.setOnAction(e -> {
            try {
                //Button calls an SQL query to add the fixed plan to the database.
                SQLCustomers.createFixedPlan(discountField.getText());

                //System parses user's input to make sure the discount plan is valid and not a string.
                Integer.parseInt(discountField.getText());

                //Success pop up when successful.
                ErrorBox.display("Success", "A new flxed discount plan has been added.");
            } catch (Exception e1) {
                //User probably did not enter a integer for the discount plan.
                ErrorBox.display("Error", "Wrong input type.");
            }
        });

        //Creating BorderPane.

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(hBox);
        borderPane.setBottom(hBox1);

        //Creating Scene.

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
        try {

        } catch (Exception e) {

        }
    }
}
