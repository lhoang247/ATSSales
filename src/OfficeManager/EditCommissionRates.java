package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLCommission;
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

//This class displays a window that allows the user to edit the current commission rate.

public class EditCommissionRates {

    public static void display(int staffNumber, String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating new TableView to display commission rates.

        TableView<Data2> table1;


        //Creating new GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);


        //Creating VBox for title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for title.

        Label labelTitle = new Label();
        labelTitle.setText("Edit commission rates");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        //Creating ComboBox for the user to select existing Travel agents in the database.

        ComboBox<String> travelAgents = SQLCommission.getTravelAgents();
        GridPane.setConstraints(travelAgents, 0, 0);


        //Creating another GridPane.

        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 1, 1);


        //Creating columns for table1

        TableColumn<Data2, String> typeColumn2 = new TableColumn<>("Type");
        typeColumn2.setMinWidth(30);
        typeColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> commissionrateColumn2 = new TableColumn<>("Commission Rate");
        commissionrateColumn2.setMinWidth(150);
        commissionrateColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

        //Adding columns to table1.

        table1 = new TableView<>();
        table1.setMinSize(220, 220);
        table1.setMaxSize(220, 220);
        table1.getColumns().addAll(typeColumn2, commissionrateColumn2);
        GridPane.setHalignment(table1, HPos.CENTER);
        GridPane.setConstraints(table1, 0, 1);


        //Creating labels and TextField for user's input.

        Label typeLabel = new Label ("Type: ");
        GridPane.setConstraints(typeLabel, 0, 0);
        GridPane.setHalignment(typeLabel, HPos.RIGHT);

        Label commissionLabel = new Label ("Commission Rate (%): ");
        GridPane.setConstraints(commissionLabel, 0, 1);
        GridPane.setHalignment(commissionLabel, HPos.RIGHT);

        TextField typeField = new TextField();
        typeField.setMaxWidth(70);
        GridPane.setConstraints(typeField, 1, 0);

        TextField commissionField = new TextField();
        commissionField.setMaxWidth(70);
        GridPane.setConstraints(commissionField, 1, 1);


        //Added a listener for the table to allow users
        //to click on blank type they wanted to change the
        //commission for.

        table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);

            //The commission TextField updates when clicking on a row on table1.
            typeField.setText(assignSelection.getData21());
        });


        //Creating HBox to add buttons on the bottom of the form.

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);

        //Creating button to allow user to change the commission rate.

        Button editButton = new Button("Change");
        editButton.setMinSize(130,0);

        //Adding button to the HBox

        hBox.getChildren().addAll(editButton);
        hBox.setAlignment(Pos.CENTER);


        //Added a listener for the ComboBox, allowing the user to select and load the blank types
        //that are associated with the travel agent.

        travelAgents.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            try {
                table1.setItems(SQLCommission.getTypeAndCommission(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }) ;


        //Action listener added for edit button to confirm change

        editButton.setOnAction(e -> {
            try {

                //Button calls a procedure that contains a SQL query.
                //SQL query changes the commission rate.
                SQLCommission.updateCommission(typeField.getText(),commissionField.getText(),(String) travelAgents.getSelectionModel().getSelectedItem());

                //Test if the data inputted by the user was valid.
                int test = Integer.parseInt(commissionField.getText());

                //Success popup.
                ErrorBox.display("Success","The commission has successfully been changed");

                //Refreshes table1 to show that the commission has been updated.
                table1.setItems(SQLCommission.getTypeAndCommission(travelAgents.getSelectionModel().getSelectedItem()));
            } catch (Exception e1) {

                //Error if the user inputted a wrong value.
                ErrorBox.display("Miss Input", "Input was not valid.");
            }
        });


        //Adding assets to the respective GridPane

        gridInfo.getChildren().addAll(typeLabel,commissionLabel,typeField,commissionField);
        grid.getChildren().addAll(travelAgents,table1,gridInfo);


        //Creating BorderPane.

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
