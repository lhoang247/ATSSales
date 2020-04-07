package OfficeManager;

import Entities.Data2;
import General.ErrorBox;
import SQLqueries.SQLCustomers;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import static SQLqueries.SQLCustomers.*;

//This class allows the user to set an existing discount plan to a customer.
//Uses table views to display the fixed and flexible discounts.

public class DiscountPage {

    public static void display(int staffNumber, String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating TableViews that will be displayed with data. (SQL)

        TableView<Data2> table1,table2,table3;

        //Creating GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);

        //Creating VBox for title label

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for title.

        Label labelTitle = new Label();
        labelTitle.setText("Set a discount plan for customers");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        //Creating another GridPane.

        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 1, 1);


        //Creating table1's columns

        TableColumn<Data2, String> emailColumn1 = new TableColumn<>("EMAIL");
        emailColumn1.setMinWidth(0);
        emailColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> discountPlanColumn1 = new TableColumn<>("DiscountPlan");
        discountPlanColumn1.setMinWidth(0);
        discountPlanColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

        //Adding columns to table1.
        //Also formatting the table.

        table1 = new TableView<>();
        table1.setMinSize(0, 220);
        table1.setMaxSize(150, 220);
        table1.getColumns().addAll(emailColumn1,discountPlanColumn1);
        table1.setItems(SQLCustomers.getCustomerEmails());

        GridPane.setHalignment(table1, HPos.CENTER);
        GridPane.setConstraints(table1, 0, 1);


        //Creating columns for table2.

        TableColumn<Data2, String> discountPlanColumn2 = new TableColumn<>("Discount Plan");
        discountPlanColumn2.setMinWidth(110);
        discountPlanColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> fColumn2 = new TableColumn<>("Discount Plan");
        fColumn2.setMinWidth(70);
        fColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));


        //Adding columns to table2.

        table2 = new TableView<>();
        table2.setMinSize(0, 220);
        table2.setMaxSize(210, 220);
        table2.getColumns().addAll(discountPlanColumn2,fColumn2);
        table2.setItems(SQLCustomers.getDiscount());

        GridPane.setHalignment(table2, HPos.CENTER);
        GridPane.setConstraints(table2, 1, 1);


        //Creating columns for table3.

        TableColumn<Data2, String> discountColumn3 = new TableColumn<>("Discount");
        discountColumn3.setMinWidth(90);
        discountColumn3.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> FROMColumn3 = new TableColumn<>("FROM");
        FROMColumn3.setMinWidth(30);
        FROMColumn3.setCellValueFactory(new PropertyValueFactory<>("data22"));

        TableColumn<Data2, String> TOColumn3 = new TableColumn<>("TO");
        TOColumn3.setMinWidth(30);
        TOColumn3.setCellValueFactory(new PropertyValueFactory<>("data23"));

        //Adding columns to table3.

        table3 = new TableView<>();
        table3.setMinSize(0, 220);
        table3.setMaxSize(1000, 220);
        table3.getColumns().addAll(discountColumn3,FROMColumn3,TOColumn3);

        GridPane.setHalignment(table3, HPos.CENTER);
        GridPane.setConstraints(table3, 2, 1);


        //Added listener for table2 to update table3 when selecting a row in table2.
        //Table3 is filled with the fixed or flexible discount plan.

        table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Data2 moveSelection = table2.getSelectionModel().getSelectedItems().get(0);

            try {

                //Fills table 3 with a discount plan.

                if (Integer.parseInt(getDiscountType(moveSelection.getData21())) == 1)  {
                    table3.setItems(showFixedDiscount(moveSelection.getData21()));
                } else {
                    table3.setItems(showFlexibleDiscount(moveSelection.getData21()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Creating HBox for the confirm button

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);

        //Creating button

        Button confirmButton = new Button("Confirm");
        confirmButton.setMinSize(130,0);

        //Adding button to HBox

        hBox.getChildren().addAll(confirmButton);
        hBox.setAlignment(Pos.CENTER);


        //Added an action listener for the confirm button to update the customer's account with the new discount plan.

        confirmButton.setOnAction(e -> {
            try {
                //Getting the selected rows from table 1 and 2.
                Data2 emailSelection = table1.getSelectionModel().getSelectedItems().get(0);
                Data2 discountPlanSelection = table2.getSelectionModel().getSelectedItems().get(0);

                //The values in table 1 and 2 and then passed as parameters to update the customer's discount plan.
                SQLCustomers.updateDiscountPlanForCustomer(discountPlanSelection.getData21(),emailSelection.getData21());

                //Success message.
                ErrorBox.display("Success","Customer's discount plan has been updated");
            } catch (Exception e1) {
            }
        });

        //Adding assets to the GridPane.

        grid.getChildren().addAll(table1,table2,table3);

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
