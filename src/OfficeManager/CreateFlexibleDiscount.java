package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLCustomers;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import General.ErrorBox;

public class CreateFlexibleDiscount {

    public static void display(int staffNumber, String role) throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Flexible Discount Plan");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);


        Label currentBands = new Label("Current Bands:");
        GridPane.setConstraints(currentBands, 0, 0);

        Label currentPlan = new Label("Current Plan:");
        GridPane.setConstraints(currentPlan, 2, 0);
        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 1, 1);
        GridPane.setHalignment(gridInfo, HPos.CENTER);
        GridPane.setValignment(gridInfo, VPos.CENTER);

        TableColumn<Data2, String> idbandColumn2 = new TableColumn<>("ID");
        idbandColumn2.setMinWidth(30);
        idbandColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> frombandColumn2 = new TableColumn<>("From");
        frombandColumn2.setMinWidth(30);
        frombandColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

        TableColumn<Data2, String> tobandColumn2 = new TableColumn<>("To");
        tobandColumn2.setMinWidth(30);
        tobandColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

        table1 = new TableView<>();
        table1.setMinSize(135, 220);
        table1.setMaxSize(135, 220);
        table1.setItems(SQLCustomers.viewBands());
        table1.getColumns().addAll(idbandColumn2,frombandColumn2, tobandColumn2);
        GridPane.setHalignment(table1, HPos.CENTER);
        GridPane.setConstraints(table1, 0, 1);

        TableColumn<Data2, String> idbandColumn3 = new TableColumn<>("ID");
        idbandColumn3.setMinWidth(30);
        idbandColumn3.setMaxWidth(30);
        idbandColumn3.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> frombandColumn3 = new TableColumn<>("From");
        frombandColumn3.setMinWidth(30);
        frombandColumn3.setCellValueFactory(new PropertyValueFactory<>("data22"));

        TableColumn<Data2, String> tobandColumn3 = new TableColumn<>("To");
        tobandColumn3.setMinWidth(30);
        tobandColumn3.setCellValueFactory(new PropertyValueFactory<>("data23"));

        TableColumn<Data2, String> discountColumn3 = new TableColumn<>("Discount");
        discountColumn3.setMinWidth(30);
        discountColumn3.setCellValueFactory(new PropertyValueFactory<>("data24"));

        table2 = new TableView<>();
        table2.setMinSize(260, 220);
        table2.setMaxSize(260, 220);
        table2.getColumns().addAll(idbandColumn3, frombandColumn3, tobandColumn3, discountColumn3);
        GridPane.setHalignment(table2, HPos.CENTER);
        GridPane.setConstraints(table2, 2, 1);


        Label discountLabel = new Label ("Discount: ");
        GridPane.setConstraints(discountLabel, 0, 0);
        GridPane.setHalignment(discountLabel, HPos.RIGHT);

        TextField discountField = new TextField("0");
        discountField.setMaxWidth(70);
        GridPane.setConstraints(discountField, 1, 0);

        Button moveRight = new Button("->");
        GridPane.setConstraints(moveRight, 0, 1);
        GridPane.setColumnSpan(moveRight,2);
        GridPane.setHalignment(moveRight, HPos.CENTER);

        Button moveLeft = new Button("<-");
        GridPane.setConstraints(moveLeft, 0, 2);
        GridPane.setColumnSpan(moveLeft,2);
        GridPane.setHalignment(moveLeft, HPos.CENTER);

        moveRight.setOnAction(e -> {
            try {
                Data2 moveSelection = table1.getSelectionModel().getSelectedItems().get(0);
                table2.getItems().add(new Data2(moveSelection.getData21(),moveSelection.getData22(),moveSelection.getData23(),discountField.getText()));
                ObservableList<Data2> deleteSelection, allProducts;
                allProducts = table1.getItems();
                deleteSelection = table1.getSelectionModel().getSelectedItems();
                deleteSelection.forEach(allProducts::remove);
            }catch (Exception e2) {

            }

        });


        moveLeft.setOnAction(e -> {
            try {
            Data2 moveSelection = table2.getSelectionModel().getSelectedItems().get(0);
            table1.getItems().add(new Data2(moveSelection.getData21(),moveSelection.getData22(),moveSelection.getData23()));
            ObservableList<Data2> deleteSelection, allProducts;
            allProducts = table2.getItems();
            deleteSelection = table2.getSelectionModel().getSelectedItems();
            deleteSelection.forEach(allProducts::remove);
            }catch (Exception e2) {

            }
        });


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);
        Button createButton = new Button("Create Plan");
        createButton.setMinSize(130,0);
        hBox.getChildren().addAll(createButton);
        hBox.setAlignment(Pos.CENTER);

        createButton.setOnAction(e -> {
            try {
                SQLCustomers.createFlexiblePlan();
                for (int i = 0; i < table2.getItems().size(); i++) {
                    SQLCustomers.createFlexDiscBand(table2.getItems().get(i).getData21(),table2.getItems().get(i).getData24());
                }
                ErrorBox.display("Success", "A new flexible discount plan has been added.");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        gridInfo.getChildren().addAll(moveRight,moveLeft,discountLabel,discountField);
        grid.getChildren().addAll(currentBands,table1,gridInfo,table2,currentPlan);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
        try {

        } catch (Exception e) {

        }
    }

}
