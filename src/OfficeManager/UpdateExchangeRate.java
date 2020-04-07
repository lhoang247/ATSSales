package OfficeManager;

import General.ErrorBox;
import SQLqueries.SQLBlanks;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This class generates a window that allows the user to edit the current exchange rate.
//The form validates the user's input.

public class UpdateExchangeRate {

    public static void display() throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating VBox for title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for title.

        Label labelTitle = new Label();
        labelTitle.setText("Update Exchange Rate");
        labelTitle.setStyle("-fx-font: 20 arial;");

        //Adding label to VBox.

        vBox.getChildren().addAll(labelTitle);
        vBox.setAlignment(Pos.CENTER);


        //Creating GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));


        //Label and TextField

        Label exchangeLabel = new Label("Exchange Rate: ");
        TextField exchangeField = new TextField();

        //HBox created to add the label and TextField

        HBox exchangeBox = new HBox();

        //Adding the items to the HBox.

        exchangeBox.getChildren().addAll(exchangeLabel,exchangeField);

        exchangeBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(exchangeBox,0,0);
        GridPane.setHalignment(exchangeBox, HPos.CENTER);


        //Creating another HBox for the confirm button on the bottom of the window.

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        //Creating button.

        Button confirmButton = new Button("Confirm");

        //Adding button to HBox.

        hBox1.getChildren().addAll(confirmButton);


        //Adding action listener to allow user to change the exchange rate.

        confirmButton.setOnAction(e -> {
            try {

                //Calling a method that executes a SQL query which updates the exchange rate.

                SQLBlanks.updateExchangeRate(exchangeField.getText());

                //Data validation.
                Double.parseDouble(exchangeField.getText());

                //Success popup.
                ErrorBox.display("Success","Exchange rate has been updated.");
            } catch (Exception e1) {

                //Error occurs if wrong input.
                ErrorBox.display("Error","Wrong input.");
            }
        });

        //Adds HBox to the grid.
        grid.getChildren().addAll(exchangeBox);


        //Creating BorderPane.

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox1);

        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.show();

    }
}
