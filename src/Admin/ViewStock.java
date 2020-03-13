package Admin;

import Entities.Data2;
import SQLqueries.SQLAdmin;
import SQLqueries.SQLCustomers;
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

public class ViewStock {
    public static void display(int staffNumber, String role) throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2;

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Add Stocks");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));

        ComboBox<String> blanktype = new ComboBox<>();
        blanktype.getItems().add("101");
        blanktype.getItems().add("201");
        blanktype.getItems().add("420");
        blanktype.getItems().add("440");
        blanktype.getItems().add("444");
        blanktype.getItems().add("451");
        blanktype.getItems().add("452");
        GridPane.setConstraints(blanktype,0,0);
        GridPane.setColumnSpan(blanktype,2);
        GridPane.setHalignment(blanktype,HPos.CENTER);

        Label quantityLabel = new Label("Quantity: ");
        GridPane.setConstraints(quantityLabel,0,1);

        TextField quantityField = new TextField();
        quantityField.setMaxWidth(50);
        GridPane.setConstraints(quantityField,1,1);



        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        Button confirmButton = new Button("Confirm");
        hBox1.getChildren().add(confirmButton);


        confirmButton.setOnAction(e -> {
            try {
                SQLAdmin.createFlexDiscBand(blanktype.getValue(),quantityField.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        grid.getChildren().addAll(blanktype,quantityLabel,quantityField);

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
