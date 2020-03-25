package OfficeManager;

import Entities.Data2;
import General.ErrorBox;
import SQLqueries.SQLBlanks;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateExchangeRate {
    public static void display() throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2;

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Update Exchange Rate");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);
        vBox.setAlignment(Pos.CENTER);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));

        ComboBox<String> blanktype = new ComboBox<>();


        Label exchangeLabel = new Label("Exchange Rate: ");
        TextField exchangeField = new TextField();

        HBox exchangeBox = new HBox();
        exchangeBox.getChildren().addAll(exchangeLabel,exchangeField);
        exchangeBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(exchangeBox,0,0);
        GridPane.setHalignment(exchangeBox, HPos.CENTER);


        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        Button confirmButton = new Button("Confirm");
        hBox1.getChildren().addAll(confirmButton);


        confirmButton.setOnAction(e -> {
            try {
                SQLBlanks.updateExchangeRate(exchangeField.getText());
                Double.parseDouble(exchangeField.getText());
                ErrorBox.display("Success","Exchange rate has been updated.");
            } catch (Exception e1) {
                ErrorBox.display("Error","Wrong input.");
            }
        });

        grid.getChildren().addAll(exchangeBox);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox1);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
        try {

        } catch (Exception e) {

        }
    }
}
