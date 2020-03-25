package Admin;

import Entities.Data2;
import General.ErrorBox;
import SQLqueries.SQLAdmin;
import SQLqueries.SQLBlanks;
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

public class ViewStock {
    public static void display(int staffNumber, String role) throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2;

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("View Current Stocks");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);
        GridPane.setConstraints(vBox,0,0);

        HBox searchHBox = new HBox();
        searchHBox.setPadding(new Insets(10,10,10,10));
        searchHBox.setSpacing(8);
        GridPane.setConstraints(searchHBox,0,1);

        Label blanktypeLabel = new Label("Type:");
        ComboBox<String> searchtypeBox = new ComboBox<>();

        searchtypeBox.getItems().add("101");
        searchtypeBox.getItems().add("201");
        searchtypeBox.getItems().add("420");
        searchtypeBox.getItems().add("440");
        searchtypeBox.getItems().add("444");
        searchtypeBox.getItems().add("451");
        searchtypeBox.getItems().add("452");

        Label ticketnumberLabel = new Label("Ticket Number: ");
        TextField ticketnumberField = new TextField();

        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear Search");

        searchHBox.getChildren().addAll(blanktypeLabel,searchtypeBox,ticketnumberLabel,ticketnumberField,searchButton,clearButton);

        GridPane titlegrid = new GridPane();
        titlegrid.setVgap(10);
        titlegrid.setHgap(8);
        titlegrid.getChildren().addAll(vBox,searchHBox);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));


        Label addStocksLabel = new Label("Add blanks to stocks:");
        GridPane.setConstraints(addStocksLabel,0,1);
        ComboBox<String> blanktype = new ComboBox<>();
        blanktype.getItems().add("101");
        blanktype.getItems().add("201");
        blanktype.getItems().add("420");
        blanktype.getItems().add("440");
        blanktype.getItems().add("444");
        blanktype.getItems().add("451");
        blanktype.getItems().add("452");
        GridPane.setConstraints(blanktype,1,1);
        GridPane.setHalignment(blanktype,HPos.CENTER);

        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();
        quantityField.setMaxWidth(50);

        HBox quantityBox = new HBox();
        quantityBox.getChildren().addAll(quantityLabel,quantityField);
        quantityBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(quantityBox,0,2);
        GridPane.setHalignment(quantityBox,HPos.CENTER);
        TableColumn<Data2 ,String> typeColumn1 = new TableColumn<>("Type");
        typeColumn1.setMinWidth(50);
        typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2,String> bundleminColumn1 = new TableColumn<>("From");
        bundleminColumn1.setMinWidth(50);
        bundleminColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

        TableColumn<Data2,String> bundlemaxColumn1 = new TableColumn<>("To");
        bundlemaxColumn1.setMinWidth(50);
        bundlemaxColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

        TableColumn<Data2 ,String> idstaffColumn1 = new TableColumn<>("Staff ID");
        idstaffColumn1.setMinWidth(40);
        idstaffColumn1.setCellValueFactory(new PropertyValueFactory<>("data25"));

        TableColumn<Data2 ,String> receivedDateColumn1 = new TableColumn<>("Date Received");
        receivedDateColumn1.setMinWidth(125);
        receivedDateColumn1.setCellValueFactory(new PropertyValueFactory<>("data26"));


        table1 = new TableView<>();
        table1.setMaxSize(500,150);
        table1.setItems(SQLBlanks.getReport1());
        table1.getColumns().addAll(typeColumn1,bundleminColumn1,bundlemaxColumn1,idstaffColumn1,receivedDateColumn1);
        GridPane.setConstraints(table1,0,0);
        GridPane.setColumnSpan(table1,3);


        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        Button confirmButton = new Button("Add Stock");
        Button removeButton = new Button("Remove Stock");
        hBox1.getChildren().addAll(confirmButton, removeButton);


        confirmButton.setOnAction(e -> {
            try {
                SQLAdmin.createFlexDiscBand(blanktype.getValue(),quantityField.getText());
                Integer.parseInt(quantityField.getText());
                ErrorBox.display("Success","You have added blanks to stocks");
                table1.setItems(SQLBlanks.getReport1());
            } catch (Exception e1) {
                ErrorBox.display("Error","Wrong input.");
            }
        });

        removeButton.setOnAction(e -> {
            try {
                Data2 bundleSelection = table1.getSelectionModel().getSelectedItems().get(0);
                SQLAdmin.removeStocks(bundleSelection.getData21(),bundleSelection.getData23(),bundleSelection.getData24());
                ErrorBox.display("Success","You have removed the blanks from stocks");
                table1.setItems(SQLBlanks.getReport1());
            } catch (Exception e1) {
                e1.printStackTrace();
                ErrorBox.display("Error","You must select a record.");
            }
        });

        searchButton.setOnAction(e -> {
            try {
                if (!ticketnumberField.getText().equals("")) {
                    Integer.parseInt(ticketnumberField.getText());
                }
                table1.setItems(SQLAdmin.getSearchStocks(searchtypeBox.getValue(),ticketnumberField.getText()));
            } catch (Exception e1) {
                ErrorBox.display("Error","Invalid Input.");
            }
        });

        clearButton.setOnAction(e -> {
            try {
                table1.setItems(SQLBlanks.getReport1());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        grid.getChildren().addAll(blanktype,quantityBox,table1,addStocksLabel);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(titlegrid);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox1);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
    }
}
