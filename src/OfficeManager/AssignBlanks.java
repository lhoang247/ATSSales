package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLBlanks;
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

//This class is used to generate the "Assign Blanks" window.
//The main function of this page is to assign blanks that are not sold to staff members.
//You can also reassign blanks to another staff member.
//Or unassign the blank from the staff member completely.

public class AssignBlanks {

    public static void display(int staffNumber ,String role) throws Exception {

        //Creating a new window.

        Stage window = new Stage();

        //Creating TableViews that will be displayed.

        TableView<Data2> table1, table2;

        //Creating GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(40);
        grid.setVgap(8);

        //VBox created for title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10,10,10,10));

        //Creating title label.

        Label labelTitle = new Label();
        labelTitle.setText("Assign Blanks");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);


        //Creating labels for the two TableViews for better clarity.

        Label table1Label = new Label("Current stocks available: ");
        GridPane.setConstraints(table1Label,0,0);
        Label table2Label = new Label("Current staff: ");
        GridPane.setConstraints(table2Label,1,0);
        try {
            //Creating table1 which displays the blanks that are in stock

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

            TableColumn<Data2 ,String> assignedDateColumn1 = new TableColumn<>("Date Assigned");
            assignedDateColumn1.setMinWidth(125);
            assignedDateColumn1.setCellValueFactory(new PropertyValueFactory<>("data27"));

            //Formatting table1.

            table1 = new TableView<>();
            table1.setMaxSize(500,300);
            table1.setItems(SQLBlanks.getReport1());
            table1.getColumns().addAll(typeColumn1,bundleminColumn1,bundlemaxColumn1,idstaffColumn1,receivedDateColumn1,assignedDateColumn1);

            GridPane.setConstraints(table1,0,1);

            //Creating table2 which displays the current office managers and advisors in the database.
            //This part is creating the columns and associating the values of the column to the entity created.

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


            //Filling table2 with data and columns.

            table2 = new TableView<>();
            table2.setMaxSize(300,300);
            table2.setItems(SQLBlanks.getReport2());
            table2.getColumns().addAll(idstaffColumn2,emailColumn2,firstnameColumn2,surnameColumn2);

            GridPane.setConstraints(table2,1,1);


            //HBox created to showcase the data selected in the two TableViews generated.

            HBox selection = new HBox();
            selection.setSpacing(5);

            //Creating general labels and TextFields to show the data selected in the TableView.

            Label assignLabel = new Label("Assign: ");
            selection.setAlignment(Pos.CENTER);
            TextField assignField = new TextField();
            Label toLabel = new Label("TO: ");
            TextField toField = new TextField();
            Label quantityLabel = new Label("Quantity: ");
            TextField quantityField = new TextField();
            quantityField.setMaxWidth(50);

            //Adding the assets to the HBox.

            selection.getChildren().addAll(assignLabel,assignField,toLabel,toField,quantityLabel,quantityField);


            //Another HBox created for the assign and unassigned buttons.

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setSpacing(20);

            //Creating general buttons for the HBox.

            Button assignButton = new Button("Assign Blanks");
            assignButton.setMinSize(130,0);
            Button unassignButton = new Button("Unassign Blanks");
            unassignButton.setMinSize(130,0);
            hBox.getChildren().addAll(selection,assignButton, unassignButton);
            hBox.setAlignment(Pos.CENTER);


            //Listener added to get the selected row the user clicked on in the table1

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);

                //Fills the TextField with the selected data.
                assignField.setText(assignSelection.getData23() + " - " + assignSelection.getData24());
            });


            //Listener added to get the selected row the user clicked on in the table2

            table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 toOnClickSelection = table2.getSelectionModel().getSelectedItems().get(0);

                //Fills the TextField with the selected data.
                toField.setText(toOnClickSelection.getData21());
            });


            //Action listener added to confirm the user's request.

            assignButton.setOnAction( e -> {
                try {
                    //Created variables to get the data that the user has selected on both TableViews.
                    Data2 bundleSelection = table1.getSelectionModel().getSelectedItems().get(0);
                    Data2 staffIDSelection = table2.getSelectionModel().getSelectedItems().get(0);

                    //Button calls an SQL query to assign blanks to the staff member.
                    SQLBlanks.assignBlank(staffIDSelection.getData21(),bundleSelection.getData22(), Integer.parseInt(bundleSelection.getData23()), Integer.parseInt(bundleSelection.getData23()) + Integer.parseInt(quantityField.getText()), bundleSelection.getData21());
                    //Pop up box appears when the user has successfully assigned blanks to the staff member.
                    ErrorBox.display("Success","The bundle has successfully been assigned");
                    //Refreshes table1 to show that the data has been updated.
                    table1.setItems(SQLBlanks.getReport1());
                } catch (Exception e1) {
                    //An error box occurs if the user has not selected a value in the table from both tables
                    ErrorBox.display("Error","Must pick from both tables.");
                }
            });


            //Action listener added to unassigned blanks from the staff member.

            unassignButton.setOnAction(e -> {
                try {
                    //Similar case with the other button.
                    Data2 bundleSelection = table1.getSelectionModel().getSelectedItems().get(0);
                    //Button calls an SQL query to update the database,
                    //Changes status from "assigned" to "stock".
                    SQLBlanks.unassignBlank(bundleSelection.getData22(), bundleSelection.getData21(), bundleSelection.getData23(), bundleSelection.getData24());

                    ErrorBox.display("Success","The bundle has successfully been unassigned");
                    table1.setItems(SQLBlanks.getReport1());
                } catch (Exception e1) {

                    ErrorBox.display("Error","Must pick from the blank table.");
                }
            });

            //Added assets to the GridPane

            grid.getChildren().addAll(table1,table2,table1Label,table2Label);

            //Creating BorderPane.

            BorderPane borderPane = new BorderPane();
            borderPane.setTop(vBox);
            borderPane.setCenter(grid);
            borderPane.setBottom(hBox);

            //Creating Scene.

            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {

        }
    }
}
