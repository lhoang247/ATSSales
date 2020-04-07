package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLBlanks;
import SQLqueries.SQLReport;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import General.ErrorBox;

//This class is used to generate the refund window.
//Main function is to allow staff to refund their reported tickets.

public class refundSale {

    public static void display(int staffNumber, String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating TableView to display.

        TableView<Data2> table1;

        //Creating GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);

        //Creating VBox for the label title.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for the title.

        Label labelTitle = new Label();
        labelTitle.setText("Refund sale");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        //Creating another GridPane for labels and TextFields.

        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 0, 1);


        //Creating columns for the TableView.

        TableColumn<Data2, String> typeColumn2 = new TableColumn<>("Type");
        typeColumn2.setMinWidth(30);
        typeColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> ticketnumberColumn2 = new TableColumn<>("Ticket Number");
        ticketnumberColumn2.setMinWidth(30);
        ticketnumberColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

        //Adding items to the TableView.

        table1 = new TableView<>();
        table1.setMinSize(150, 140);
        table1.setMaxSize(150, 140);
        table1.setItems(SQLReport.getReport12(staffNumber));
        table1.getColumns().addAll(typeColumn2, ticketnumberColumn2);

        GridPane.setHalignment(table1, HPos.CENTER);
        GridPane.setConstraints(table1, 0, 0);


        //Creating labels and TextFields.

        Label emailLabel = new Label ("Ticket Number: ");
        GridPane.setConstraints(emailLabel, 0, 0);
        GridPane.setHalignment(emailLabel, HPos.RIGHT);

        TextField emailField = new TextField();
        emailField.setMaxWidth(70);
        emailField.setEditable(false);
        GridPane.setConstraints(emailField, 1, 0);

        //Added a listener for the TableView to print out the selected item by the user in the TextField provided for better clarity.

        table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
            emailField.setText(assignSelection.getData22());

        });

        //Creating HBox for the confirm button.

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);

        //Creating button.

        Button createButton = new Button("Confirm");
        createButton.setMinSize(130,0);

        //Adding button to the HBox.

        hBox.getChildren().addAll(createButton);
        hBox.setAlignment(Pos.CENTER);

        //Addding listener for the button to refund the sale.

        createButton.setOnAction(e -> {
            try {
                //Calling a SQL query.
                SQLBlanks.refundSale(emailField.getText());
                ErrorBox.display("Success","The sale has been refunded");
                //Refreshing table to show that the database was updated.
                table1.setItems(SQLReport.getReport12(staffNumber));
            } catch (Exception e1) {
                ErrorBox.display("Error","Must pick from the tables.");
                e1.printStackTrace();
            }
        });

        //Adding assets to the respective GridPanes.

        gridInfo.getChildren().addAll(emailLabel,emailField);
        grid.getChildren().addAll(table1,gridInfo);

        //Creating BorderPane.

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.show();

    }
}
