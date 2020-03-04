package sample;

import Entities.Data;
import Entities.Data2;
import SQLqueries.SQLBlanks;
import SQLqueries.SQLReport;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        grid.setHgap(60);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10,10,10,10));

        Label labelTitle = new Label();
        labelTitle.setText("Assign Blanks");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        try {
            //table1

            TableColumn<Data2 ,String> typeColumn1 = new TableColumn<>("Type");
            typeColumn1.setMinWidth(50);
            typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2,String> bundleColumn1 = new TableColumn<>("Bundle");
            bundleColumn1.setMinWidth(50);
            bundleColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2 ,String> idstaffColumn1 = new TableColumn<>("Staff ID");
            idstaffColumn1.setMinWidth(40);
            idstaffColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2 ,String> receivedDateColumn1 = new TableColumn<>("Date Received");
            receivedDateColumn1.setMinWidth(125);
            receivedDateColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

            table1 = new TableView<>();
            table1.setMaxSize(500,400);
            table1.setItems(SQLBlanks.getReport1());
            table1.getColumns().addAll(typeColumn1,bundleColumn1,idstaffColumn1,receivedDateColumn1);

            GridPane.setConstraints(table1,0,0);

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
            table2.setMaxSize(300,400);
            table2.setItems(SQLBlanks.getReport2());
            table2.getColumns().addAll(idstaffColumn2,emailColumn2,firstnameColumn2,surnameColumn2);

            GridPane.setConstraints(table2,1,0);



            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setSpacing(20);
            Button assignButton = new Button("Assign Blanks");
            assignButton.setMinSize(130,0);
            Button unassignButton = new Button("Unassign Blanks");
            unassignButton.setMinSize(130,0);
            hBox.getChildren().addAll(assignButton, unassignButton);


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


            grid.getChildren().addAll(table1,table2);
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
