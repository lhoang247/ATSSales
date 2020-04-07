package OfficeManager;

import Entities.Data2;
import General.ErrorBox;
import SQLqueries.SQLBlanks;
import SQLqueries.SQLCustomers;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//This class generates a window that allows the user to update late payment.
//Similar to the sales page but with half the TextFields.
//You can only select the late payments.

public class UpdateLatePayment {

    public static void display(int staffNumber ,String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating GridPane

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(10);
        grid.setVgap(10);

        //Creating VBox.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating title label.

        Label labelTitle = new Label();
        labelTitle.setText("Update Late Payments");
        labelTitle.setStyle("-fx-font: 24 arial;");

        //Creating title label to the VBox.

        vBox.getChildren().addAll(labelTitle);


        //Creating a new TableView to display.

        TableView<Data2> table1;
        try {

            //table1 displays the late payments.
            //Creating the columns.

            TableColumn<Data2, String> typeColumn1 = new TableColumn<>("Customer Email");
            typeColumn1.setMinWidth(50);
            typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> minColumn1 = new TableColumn<>("Blank Type");
            minColumn1.setMinWidth(50);
            minColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> maxColumn1 = new TableColumn<>("Ticket Number");
            maxColumn1.setMinWidth(40);
            maxColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            //Adding columns to the TableView.

            table1 = new TableView<>();
            table1.setMaxSize(500, 150);
            table1.setItems(SQLCustomers.getLatePaymentwithTicket(staffNumber));
            table1.getColumns().addAll(typeColumn1, minColumn1, maxColumn1);

            GridPane.setConstraints(table1, 0, 0);

            //Creating another GridPane for labels and textFields.

            GridPane gridInfo = new GridPane();
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 1, 0);

            //Labels and TextFields

            Label paymentMethodLabel = new Label ("Payment Method: ");
            GridPane.setConstraints(paymentMethodLabel, 0, 1);
            GridPane.setHalignment(paymentMethodLabel,HPos.RIGHT);

            Label customerPaymentAmountLabel = new Label ("Customer Payment Amount: ");
            GridPane.setConstraints(customerPaymentAmountLabel, 0, 0);
            GridPane.setHalignment(customerPaymentAmountLabel,HPos.RIGHT);

            Label creditCardLabel = new Label ("Credit Card Number: ");
            GridPane.setConstraints(creditCardLabel, 0, 2);
            GridPane.setHalignment(creditCardLabel,HPos.RIGHT);

            Label creditCard1NameLabel = new Label ("Credit Card Name: ");
            GridPane.setConstraints(creditCard1NameLabel, 0, 3);
            GridPane.setHalignment(creditCard1NameLabel,HPos.RIGHT);

            Label creditCardExpireLabel = new Label ("Credit Card Expiry Date: ");
            GridPane.setConstraints(creditCardExpireLabel, 0, 4);
            GridPane.setHalignment(creditCardExpireLabel,HPos.RIGHT);

            Label dateLabel = new Label ("Date: ");
            GridPane.setConstraints(dateLabel, 0, 5);
            GridPane.setHalignment(dateLabel,HPos.RIGHT);

            //Creating ComboBox that has either cash or card.

            ComboBox paymentMethodBox = new ComboBox();
            paymentMethodBox.getItems().add("cash");
            paymentMethodBox.getItems().add("card");
            GridPane.setConstraints(paymentMethodBox, 1, 1);

            TextField customerPaidAmountField = new TextField();
            GridPane.setConstraints(customerPaidAmountField, 1, 0);

            TextField creditCardField = new TextField();
            GridPane.setConstraints(creditCardField, 1, 2);

            TextField creditCardName1Field = new TextField();
            GridPane.setConstraints(creditCardName1Field, 1, 3);

            TextField creditCardExpireField = new TextField();
            GridPane.setConstraints(creditCardExpireField, 1, 4);

            //Getting today's date.

            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Calendar calobj = Calendar.getInstance();

            TextField dateField = new TextField(df.format(calobj.getTime()));
            GridPane.setConstraints(dateField, 1, 5);

            Button calculateMaxButton = new Button("Calculate Max with discount");
            GridPane.setConstraints(calculateMaxButton, 2, 0);

            //Creating buttons

            Button reportSalesButton = new Button("Update Sale");
            reportSalesButton.setMinSize(130,0);

            Button voidButton = new Button("Remove customer's discount");
            voidButton.setMinSize(130,0);

            //Creating HBox.

            HBox hBoxButton = new HBox();

            //Adding buttons to HBox.

            hBoxButton.getChildren().addAll(reportSalesButton,voidButton);
            hBoxButton.setPadding(new Insets(10,10,10,10));
            hBoxButton.setSpacing(20);

            //Adding items to the Grid.

            GridPane.setRowSpan(gridInfo,2);

            gridInfo.getChildren().addAll(paymentMethodLabel,customerPaymentAmountLabel,creditCardField,creditCardLabel,
                    dateLabel,paymentMethodBox,customerPaidAmountField,dateField, calculateMaxButton,
                    creditCardExpireLabel,creditCardExpireField,creditCardName1Field,creditCard1NameLabel);

            grid.getChildren().addAll(table1,gridInfo);

            //Adding a listener for the table to allow the user to select the late payment they want to submit.

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
            });

            //Same function as the one in the sales report.
            //Shows or hides details depending on the user's selection.

            paymentMethodBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (newValue == "cash") {
                    creditCardField.setDisable(true);
                    creditCardExpireField.setDisable(true);
                    creditCardName1Field.setDisable(true);
                } else if (newValue == "card") {
                    creditCardField.setDisable(false);
                    creditCardExpireField.setDisable(false);
                    creditCardName1Field.setDisable(false);
                }
            }) ;


            //Adding a action listener to allow the user to report late payments.

            reportSalesButton.setOnAction(e -> {
                try {
                    //Getting the data that the user has selected from table1.
                    Data2 toOnClickSelection = table1.getSelectionModel().getSelectedItems().get(0);

                    //Calling a method that executes a SQL query.
                    SQLCustomers.updateLateSales(customerPaidAmountField.getText(), (String) paymentMethodBox.getValue(),dateField.getText(),toOnClickSelection.getData23(),toOnClickSelection.getData22());

                    //Data validation.
                    Double.parseDouble(customerPaidAmountField.getText());

                    //Adds card details to the database if needed.
                    if (paymentMethodBox.getValue().equals("card")) {
                        SQLBlanks.createCreditcard(toOnClickSelection.getData21(),creditCardField.getText(),toOnClickSelection.getData23(),toOnClickSelection.getData22());
                    }

                    //Success popup
                    ErrorBox.display("Success","You have updated the late payment.");
                    table1.setItems(SQLCustomers.getLatePaymentwithTicket(staffNumber));
                } catch (Exception e1) {

                    //Error occurs if you have inputted the wrong data type.
                    ErrorBox.display("Error","The ticket has a the wrong input type.");
                }
            });


            //This action is the same as the one in the sales page.
            //This calculates how much the customer should pay including discount.

            calculateMaxButton.setOnAction(e -> {
                try {
                    //Getting the data that the user has selected from table1.
                    Data2 toOnClickSelection = table1.getSelectionModel().getSelectedItems().get(0);

                    //Checks to see if the customer has a fixed or flexible discount.
                    if (SQLCustomers.getDiscountType2(toOnClickSelection.getData21()).equals("1")){

                        Double calculate = (Double.parseDouble(SQLCustomers.getsalesamountandtax(toOnClickSelection.getData22(),toOnClickSelection.getData23())) * (1 - (Double.parseDouble(SQLBlanks.getFixedDiscount(toOnClickSelection.getData21())) / 100)));
                        customerPaidAmountField.setText("" + calculate);

                    } else if (SQLCustomers.getDiscountType2(toOnClickSelection.getData21()).equals("2")) {

                        Double calculate = (Double.parseDouble(SQLCustomers.getsalesamountandtax(toOnClickSelection.getData22(),toOnClickSelection.getData23())) * (1 - (Double.parseDouble(SQLBlanks.getFlexibleDiscount(toOnClickSelection.getData21(), "" + (Double.parseDouble(SQLCustomers.getsalesamountandtax(toOnClickSelection.getData22(),toOnClickSelection.getData23()))))) / 100)));
                        customerPaidAmountField.setText("" + calculate);

                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            //Voids blanks.

            voidButton.setOnAction(e -> {
                Data2 toOnClickSelection = table1.getSelectionModel().getSelectedItems().get(0);
                try {
                    SQLCustomers.updateCustomer(toOnClickSelection.getData21(),toOnClickSelection.getData22(),toOnClickSelection.getData23());
                    ErrorBox.display("Success","You have remove the late payment.");
                    table1.setItems(SQLCustomers.getLatePaymentwithTicket(staffNumber));
                } catch (Exception e1) {
                    ErrorBox.display("Error","Something went wrong.");
                }
            });


            //Creating a BorderPane.

            BorderPane borderPane = new BorderPane();
            borderPane.setTop(vBox);
            borderPane.setCenter(grid);
            borderPane.setBottom(hBoxButton);

            Scene scene = new Scene(borderPane);

            window.setScene(scene);
            window.show();
        } catch (Exception e) {

        }
    }
}
