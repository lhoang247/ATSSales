package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLBlanks;
import SQLqueries.SQLCustomers;
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

//This class displays a window that shows the current Customers.
//You can also add customers.

public class CustomerPage {

    public static void display(int staffNumber, String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating new TableView to be displayed.

        TableView<Data2> table1;

        //Creating GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);

        //Creating VBox for the title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for the title.

        Label labelTitle = new Label();
        labelTitle.setText("Customer Accounts");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);


        //Creating another GridPane.

        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 1, 0);


        //Creating the columns for table1.

        TableColumn<Data2, String> emailColumn2 = new TableColumn<>("Email");
        emailColumn2.setMinWidth(30);
        emailColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> firstnameColumn2 = new TableColumn<>("First\nName");
        firstnameColumn2.setMinWidth(30);
        firstnameColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

        TableColumn<Data2, String> surnameColumn2 = new TableColumn<>("Surname");
        surnameColumn2.setMinWidth(40);
        surnameColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

        TableColumn<Data2, String> customerTypeColumn2 = new TableColumn<>("Type");
        customerTypeColumn2.setMinWidth(30);
        customerTypeColumn2.setCellValueFactory(new PropertyValueFactory<>("data24"));

        //Adding columns and formatting the table for looks.

        table1 = new TableView<>();
        table1.setMinSize(330, 140);
        table1.setMaxSize(330, 140);
        table1.setItems(SQLBlanks.getCustomerAccounts());
        table1.getColumns().addAll(emailColumn2, firstnameColumn2, surnameColumn2, customerTypeColumn2);

        GridPane.setConstraints(table1, 0, 0);


        //Creating labels and textFields to display.

        Label emailLabel = new Label ("Email: ");
        GridPane.setConstraints(emailLabel, 0, 0);
        GridPane.setHalignment(emailLabel, HPos.RIGHT);

        Label firstnameLabel = new Label ("First name: ");
        GridPane.setConstraints(firstnameLabel, 0, 1);
        GridPane.setHalignment(firstnameLabel,HPos.RIGHT);

        Label surnameLabel = new Label ("Surname: ");
        GridPane.setConstraints(surnameLabel, 0, 2);
        GridPane.setHalignment(surnameLabel,HPos.RIGHT);

        Label typeLabel = new Label ("Type: ");
        GridPane.setConstraints(typeLabel, 0, 3);
        GridPane.setHalignment(typeLabel,HPos.RIGHT);

        TextField emailField = new TextField();
        GridPane.setConstraints(emailField, 1, 0);

        TextField firstnameField = new TextField();
        GridPane.setConstraints(firstnameField, 1, 1);

        TextField surnameField = new TextField();
        GridPane.setConstraints(surnameField, 1, 2);

        //Creating ComboBox to limit the amount of selection the user can have.
        //Customers can either be regular or valued.

        ComboBox typeBox = new ComboBox();
        typeBox.getItems().add("regular");
        typeBox.getItems().add("valued");
        typeBox.setValue("regular");

        GridPane.setConstraints(typeBox, 1, 3);

        //Creating HBox for the confirm button.

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);

        Button createButton = new Button("Create");
        createButton.setMinSize(130,0);

        //Adding the button to HBox.

        hBox.getChildren().addAll(createButton);
        hBox.setAlignment(Pos.CENTER);


        //Adding an action listener for the button to add the customer created by the staff.

        createButton.setOnAction(e -> {
            try {
                //System checks if the TextFields provided are empty or not.
                if (!emailField.getText().isEmpty() && !firstnameField.getText().isEmpty() && !surnameField.getText().isEmpty()) {
                    //Calls an SQL query to add a new customer to the database.
                    SQLCustomers.addCustomer(emailField.getText(),firstnameField.getText(),surnameField.getText(), (String) typeBox.getSelectionModel().getSelectedItem());
                    //Pop up for success.
                    ErrorBox.display("Success","Customer Account has been created.");
                    //Table1 is refreshed to show that the customer has been added to the database.
                    table1.setItems(SQLBlanks.getCustomerAccounts());
                } else {
                    ErrorBox.display("Error","Please fill out all boxes.");
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Adding assets to the respective GridPanes.

        gridInfo.getChildren().addAll(emailLabel,firstnameLabel,surnameLabel,typeLabel,emailField,firstnameField,surnameField,typeBox);
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
