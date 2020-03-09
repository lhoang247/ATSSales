package sample;

import Entities.Data;
import Entities.Data2;
import SQLqueries.SQLBlanks;
import SQLqueries.SQLReport;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
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

public class AssignBlanks {
    public static void display(int staffNumber ,String role) throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1, table2;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(40);
        grid.setVgap(8);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10,10,10,10));

        Label labelTitle = new Label();
        labelTitle.setText("Assign Blanks");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        Label table1Label = new Label("Current stocks available: ");
        GridPane.setConstraints(table1Label,0,0);
        Label table2Label = new Label("Current staff: ");
        GridPane.setConstraints(table2Label,1,0);
        try {
            //table1

            TableColumn<Data2 ,String> typeColumn1 = new TableColumn<>("Type");
            typeColumn1.setMinWidth(50);
            typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2,String> bundleColumn1 = new TableColumn<>("Bundle");
            bundleColumn1.setMinWidth(50);
            bundleColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

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
            table1.setMaxSize(500,300);
            table1.setItems(SQLBlanks.getReport1());
            table1.getColumns().addAll(typeColumn1,bundleColumn1,bundleminColumn1,bundlemaxColumn1,idstaffColumn1,receivedDateColumn1);

            GridPane.setConstraints(table1,0,1);

            //table2

            TableColumn<Data2 ,String> idstaffColumn2 = new TableColumn<>("Staff ID");
            idstaffColumn2.setMinWidth(75);
            idstaffColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2,String> emailColumn2 = new TableColumn<>("Email");
            emailColumn2.setMinWidth(50);
            emailColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2 ,String> firstnameColumn2 = new TableColumn<>("First Name");
            firstnameColumn2.setMinWidth(40);
            firstnameColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2 ,String> surnameColumn2 = new TableColumn<>("Surname");
            surnameColumn2.setMinWidth(40);
            surnameColumn2.setCellValueFactory(new PropertyValueFactory<>("data24"));

            table2 = new TableView<>();
            table2.setMaxSize(300,300);
            table2.setItems(SQLBlanks.getReport2());
            table2.getColumns().addAll(idstaffColumn2,emailColumn2,firstnameColumn2,surnameColumn2);

            GridPane.setConstraints(table2,1,1);

            HBox selection = new HBox();
            selection.setSpacing(5);
            Label assignLabel = new Label("Assign: ");
            selection.setAlignment(Pos.CENTER);
            TextField assignField = new TextField();
            Label toLabel = new Label("TO: ");
            TextField toField = new TextField();
            selection.getChildren().addAll(assignLabel,assignField,toLabel,toField);

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setSpacing(20);
            Button assignButton = new Button("Assign Blanks");
            assignButton.setMinSize(130,0);
            Button unassignButton = new Button("Unassign Blanks");
            unassignButton.setMinSize(130,0);
            hBox.getChildren().addAll(selection,assignButton, unassignButton);
            hBox.setAlignment(Pos.CENTER);

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
                assignField.setText(assignSelection.getData23() + " - " + assignSelection.getData24());
            });

            table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 toOnClickSelection = table2.getSelectionModel().getSelectedItems().get(0);
                toField.setText(toOnClickSelection.getData21());
            });


            assignButton.setOnAction( e -> {
                try {
                    Data2 bundleSelection = table1.getSelectionModel().getSelectedItems().get(0);
                    Data2 staffIDSelection = table2.getSelectionModel().getSelectedItems().get(0);
                    SQLBlanks.assignBlank(staffIDSelection.getData21(),bundleSelection.getData22());
                    ErrorBox.display("Success","The bundle has successfully been assigned");
                    table1.setItems(SQLBlanks.getReport1());
                } catch (Exception e1) {
                    ErrorBox.display("Error","Must pick from both tables.");
                }
            });

            table1.setItems(SQLBlanks.getReport1());

            unassignButton.setOnAction(e -> {
                try {
                    Data2 bundleSelection = table1.getSelectionModel().getSelectedItems().get(0);
                    SQLBlanks.unassignBlank(bundleSelection.getData22());
                    ErrorBox.display("Success","The bundle has successfully been unassigned");
                    table1.setItems(SQLBlanks.getReport1());
                } catch (Exception e1) {
                    ErrorBox.display("Error","Must pick from both tables.");
                }
            });


            grid.getChildren().addAll(table1,table2,table1Label,table2Label);
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(vBox);
            borderPane.setCenter(grid);
            borderPane.setBottom(hBox);
            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {

        }
    }
}
