package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLCustomers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import General.ErrorBox;

public class CreateFixedDiscount {

    public static void display(int staffNumber, String role) throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2;

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Fixed Discount Plan");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(6);
        hBox.setPadding(new Insets(10,10,10,10));

        Label discountLabel = new Label("Discount: ");
        TextField discountField = new TextField();
        discountField.setMaxWidth(50);

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        Button confirmButton = new Button("Confirm");
        hBox1.getChildren().add(confirmButton);
        hBox.getChildren().addAll(discountLabel,discountField);


        confirmButton.setOnAction(e -> {
            try {
                SQLCustomers.createFixedPlan(discountField.getText());
                Integer.parseInt(discountField.getText());
                ErrorBox.display("Success", "A new flxed discount plan has been added.");
            } catch (Exception e1) {
                ErrorBox.display("Error", "Wrong input type.");
            }
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(hBox);
        borderPane.setBottom(hBox1);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
        try {

        } catch (Exception e) {

        }
    }
}
