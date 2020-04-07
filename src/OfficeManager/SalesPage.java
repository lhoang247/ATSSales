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
import java.util.ArrayList;
import java.util.Calendar;

//This class generate the sale page for both office manager and the travel advisors.
//Main function of this window is to allow the user to record sold blanks or void blanks.

public class SalesPage {

    public static void display(int staffNumber ,String role) throws Exception {

        //Creating a new window.

        Stage window = new Stage();

        //Creating a GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(10);
        grid.setVgap(10);

        //Creating a VBox to add the title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating label for the title.

        Label labelTitle = new Label();
        labelTitle.setText("Report Sales");
        labelTitle.setStyle("-fx-font: 24 arial;");

        //Adding the label to the VBox.

        vBox.getChildren().addAll(labelTitle);

        //Creating TableViews for display.

        TableView<Data2> table1, table2;

        try {

            //Creating table1 columns.

            TableColumn<Data2, String> typeColumn1 = new TableColumn<>("Blank Type");
            typeColumn1.setMinWidth(50);
            typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> minColumn1 = new TableColumn<>("Ticket Number\n(MIN)");
            minColumn1.setMinWidth(50);
            minColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> maxColumn1 = new TableColumn<>("Ticket Number\n(MAX)");
            maxColumn1.setMinWidth(40);
            maxColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            //Adding columns to the table.
            //Filling the table with data.

            table1 = new TableView<>();
            table1.setMaxSize(500, 150);
            table1.setItems(SQLBlanks.salesTable("" + staffNumber));
            table1.getColumns().addAll(typeColumn1, minColumn1, maxColumn1);

            GridPane.setConstraints(table1, 0, 0);


            //Creating columns for table2.

            TableColumn<Data2, String> emailColumn2 = new TableColumn<>("Email");
            emailColumn2.setMinWidth(30);
            emailColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> firstnameColumn2 = new TableColumn<>("First Name");
            firstnameColumn2.setMinWidth(30);
            firstnameColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> surnameColumn2 = new TableColumn<>("Surname");
            surnameColumn2.setMinWidth(40);
            surnameColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> customerTypeColumn2 = new TableColumn<>("Type");
            customerTypeColumn2.setMinWidth(30);
            customerTypeColumn2.setCellValueFactory(new PropertyValueFactory<>("data24"));


            //Adding columns to the table.
            //Filling the table with data.

            table2 = new TableView<>();
            table2.setMaxSize(290, 150);
            table2.setItems(SQLBlanks.getCustomerAccounts());
            table2.getColumns().addAll(emailColumn2, firstnameColumn2, surnameColumn2, customerTypeColumn2);

            GridPane.setConstraints(table2, 0, 1);

            //Creating GridPane to add labels and TextFields.

            GridPane gridInfo = new GridPane();
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 1, 0);


            //Creating labels and TextFields.
            //Labels.

            Label ticketTypeLabel = new Label ("Ticket Type: ");
            GridPane.setConstraints(ticketTypeLabel, 0, 0);
            GridPane.setHalignment(ticketTypeLabel,HPos.RIGHT);

            Label ticketNumberLabel = new Label ("Ticket Number: ");
            GridPane.setConstraints(ticketNumberLabel, 0, 1);
            GridPane.setHalignment(ticketNumberLabel,HPos.RIGHT);

            Label ticketPriceLabel = new Label ("Ticket Price: ");
            GridPane.setConstraints(ticketPriceLabel, 0, 2);
            GridPane.setHalignment(ticketPriceLabel,HPos.RIGHT);

            Label taxLabel = new Label ("Tax: ");
            GridPane.setConstraints(taxLabel, 0, 3);
            GridPane.setHalignment(taxLabel,HPos.RIGHT);

            Label exchangeRateLabel = new Label ("Exchange Rate: ");
            GridPane.setConstraints(exchangeRateLabel, 0, 4);
            GridPane.setHalignment(exchangeRateLabel,HPos.RIGHT);

            Label customerEmailLabel = new Label ("Customer Email: ");
            GridPane.setConstraints(customerEmailLabel, 0, 5);
            GridPane.setHalignment(customerEmailLabel,HPos.RIGHT);

            Label fullyPaidLabel = new Label ("Fully Paid: ");
            GridPane.setConstraints(fullyPaidLabel, 0, 7);
            GridPane.setHalignment(fullyPaidLabel,HPos.RIGHT);

            Label paymentMethodLabel = new Label ("Payment Method: ");
            GridPane.setConstraints(paymentMethodLabel, 0, 8);
            GridPane.setHalignment(paymentMethodLabel,HPos.RIGHT);

            Label customerPaymentAmountLabel = new Label ("Customer Payment Amount: ");
            GridPane.setConstraints(customerPaymentAmountLabel, 0, 6);
            GridPane.setHalignment(customerPaymentAmountLabel,HPos.RIGHT);

            Label creditCardLabel = new Label ("Credit Card Number: ");
            GridPane.setConstraints(creditCardLabel, 0, 10);
            GridPane.setHalignment(creditCardLabel,HPos.RIGHT);

            Label creditCardNameLabel = new Label ("Credit Card Name: ");
            GridPane.setConstraints(creditCardNameLabel, 0, 11);
            GridPane.setHalignment(creditCardNameLabel,HPos.RIGHT);

            Label creditCardExpireLabel = new Label ("Credit Card Expiry Date: ");
            GridPane.setConstraints(creditCardExpireLabel, 0, 12);
            GridPane.setHalignment(creditCardExpireLabel,HPos.RIGHT);

            Label dateLabel = new Label ("Date: ");
            GridPane.setConstraints(dateLabel, 0, 13);
            GridPane.setHalignment(dateLabel,HPos.RIGHT);

            //Labels for destination.

            Label destination1Label = new Label ("Destination 1: ");
            GridPane.setConstraints(destination1Label, 2, 0);
            GridPane.setHalignment(destination1Label,HPos.LEFT);

            Label destination2Label = new Label ("Destination 2: ");
            GridPane.setConstraints(destination2Label, 2, 1);
            GridPane.setHalignment(destination2Label,HPos.LEFT);

            Label destination3Label = new Label ("Destination 3: ");
            GridPane.setConstraints(destination3Label, 2, 2);
            GridPane.setHalignment(destination3Label,HPos.LEFT);

            Label destination4Label = new Label ("Destination 4: ");
            GridPane.setConstraints(destination4Label, 2, 3);
            GridPane.setHalignment(destination4Label,HPos.LEFT);



            //A lot of TextFields for the user to use.

            TextField typeField = new TextField();
            GridPane.setConstraints(typeField, 1, 0);

            TextField ticketNumberField = new TextField();
            GridPane.setConstraints(ticketNumberField, 1, 1);

            TextField ticketPriceField = new TextField();
            GridPane.setConstraints(ticketPriceField, 1, 2);

            TextField taxField = new TextField();
            GridPane.setConstraints(taxField, 1, 3);

            TextField exchangeRateField = new TextField(SQLBlanks.getExchangeRate());
            GridPane.setConstraints(exchangeRateField, 1, 4);

            TextField customerEmailField = new TextField();
            GridPane.setConstraints(customerEmailField, 1, 5);

            //ComboBox created to see if the sale is fully paid or not.

            ComboBox fullypaidBox = new ComboBox();
            fullypaidBox.setValue("yes");
            fullypaidBox.getItems().add("yes");
            fullypaidBox.getItems().add("no");
            GridPane.setConstraints(fullypaidBox, 1, 7);

            //ComboBox created to see if the sale is either paid by cash or card.

            ComboBox paymentMethodBox = new ComboBox();
            paymentMethodBox.getItems().add("cash");
            paymentMethodBox.getItems().add("card");
            GridPane.setConstraints(paymentMethodBox, 1, 8);

            TextField customerPaidAmountField = new TextField();
            GridPane.setConstraints(customerPaidAmountField, 1, 6);

            TextField creditCardField = new TextField();
            GridPane.setConstraints(creditCardField, 1, 10);

            TextField creditCardNameField = new TextField();
            GridPane.setConstraints(creditCardNameField, 1, 11);

            TextField creditCardExpireField = new TextField();
            GridPane.setConstraints(creditCardExpireField, 1, 12);

            //Getting today's date.

            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Calendar calobj = Calendar.getInstance();

            TextField dateField = new TextField(df.format(calobj.getTime()));
            GridPane.setConstraints(dateField, 1, 13);

            //TextField for destination.

            TextField destination1Field = new TextField ();
            GridPane.setConstraints(destination1Field, 3, 0);

            TextField destination2Field = new TextField ();
            GridPane.setConstraints(destination2Field, 3, 1);

            TextField destination3Field = new TextField ();
            GridPane.setConstraints(destination3Field, 3, 2);

            TextField destination4Field = new TextField ();
            GridPane.setConstraints(destination4Field, 3, 3);


            //Creating buttons for the user to confirm sale or void blank.

            Button calculateMaxButton = new Button("Calculate Max with discount");
            GridPane.setConstraints(calculateMaxButton, 2, 6);
            GridPane.setColumnSpan(calculateMaxButton,2);

            Button reportSalesButton = new Button("Record Sale");
            reportSalesButton.setMinSize(130,0);
            Button voidButton = new Button("Void selected blank");
            voidButton.setMinSize(130,0);


            //Creating HBox to add the buttons.

            HBox hBoxButton = new HBox();
            hBoxButton.getChildren().addAll(reportSalesButton,voidButton);
            hBoxButton.setPadding(new Insets(10,10,10,10));
            hBoxButton.setSpacing(20);

            GridPane.setRowSpan(gridInfo,2);


            //Adding assets to the respective GridPanes.
            //A lot of labels and TextFields for GridInfo.

            gridInfo.getChildren().addAll(ticketTypeLabel,ticketNumberLabel,ticketPriceLabel,taxLabel,exchangeRateLabel,
                    customerEmailLabel,paymentMethodLabel,customerPaymentAmountLabel,creditCardField,creditCardLabel,
                    dateLabel,typeField,ticketNumberField,ticketPriceField,taxField,exchangeRateField,customerEmailField,
                    paymentMethodBox,customerPaidAmountField,dateField, calculateMaxButton, fullypaidBox, fullyPaidLabel,
                    creditCardExpireLabel,creditCardExpireField,creditCardNameField,creditCardNameLabel,destination1Label,
                    destination2Label,destination3Label,destination4Label,destination1Field,destination2Field,destination3Field,destination4Field);
            grid.getChildren().addAll(table1,table2,gridInfo);


            //Added a listener for table1 when a user clicks on the TableView.
            //Main function is hide TextFields or disable the TextField depending on the type of blank selected.

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

                //Created a variable which hold the selected row that the user selected.

                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);

                //Automatically fills the type and ticket number TextField with data of the selected ticket.

                typeField.setText(assignSelection.getData21());
                ticketNumberField.setText(assignSelection.getData22());

                //This if statement is used to disable the exchange rate TextField if the blank type is domestic.
                //This allows the user to see that the exchange rate will not affect the sales.
                //Mainly used for clarity.

                if (assignSelection.getData21().equals("444")  || assignSelection.getData21().equals("440")  || assignSelection.getData21().equals("420")) {
                    exchangeRateField.setDisable(false);
                } else {
                    exchangeRateField.setDisable(true);
                }

                //These if statements are used to enable and disable the coupon destination TextFields.
                //Main purpose being limiting the amount of coupons depending on the blank type.

                if (assignSelection.getData21().equals("444")  || assignSelection.getData21().equals("440")) {
                    destination1Field.setVisible(true);
                    destination2Field.setVisible(true);
                    destination3Field.setVisible(true);
                    destination4Field.setVisible(true);
                    destination1Label.setVisible(true);
                    destination2Label.setVisible(true);
                    destination3Label.setVisible(true);
                    destination4Label.setVisible(true);
                } else if (assignSelection.getData21().equals("420")  || assignSelection.getData21().equals("201")) {
                    destination1Field.setVisible(true);
                    destination2Field.setVisible(true);
                    destination3Field.setVisible(false);
                    destination4Field.setVisible(false);
                    destination1Label.setVisible(true);
                    destination2Label.setVisible(true);
                    destination3Label.setVisible(false);
                    destination4Label.setVisible(false);
                } else if (assignSelection.getData21().equals("101")) {
                    destination1Field.setVisible(true);
                    destination2Field.setVisible(false);
                    destination3Field.setVisible(false);
                    destination4Field.setVisible(false);
                    destination1Label.setVisible(true);
                    destination2Label.setVisible(false);
                    destination3Label.setVisible(false);
                    destination4Label.setVisible(false);
                }
            });

            //Adding a listener to allow the user to select a customer.
            //Automatically fills the customer's email TextField when clicking on a record on the TableView.

            table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 toOnClickSelection = table2.getSelectionModel().getSelectedItems().get(0);
                customerEmailField.setText(toOnClickSelection.getData21());
            });

            //Here is a listener for the Payment method ComboBox.
            //This enables and disables the credit card detail fields.

            paymentMethodBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (newValue == "cash") {
                    creditCardField.setDisable(true);
                    creditCardExpireField.setDisable(true);
                    creditCardNameField.setDisable(true);
                } else if (newValue == "card") {
                    creditCardField.setDisable(false);
                    creditCardExpireField.setDisable(false);
                    creditCardNameField.setDisable(false);
                }
            }) ;


            //Adding a action listener for the report sales button.

            reportSalesButton.setOnAction(e -> {

                //This try state,emt is used for data validation.
                //If the user's input doesn't validate the requirements then it will catch and return a error.

                try {

                    //This if statement is used for the fact that a payment can either be fully paid or late payment.
                    //Therefore the SQL query will need to be called with different parameters.

                    if (fullypaidBox.getValue().equals("yes")) {
                        SQLBlanks.reportSales(ticketNumberField.getText(),typeField.getText(),ticketPriceField.getText(),"y",taxField.getText(),exchangeRateField.getText(),customerEmailField.getText(),customerPaidAmountField.getText(), (String) paymentMethodBox.getSelectionModel().getSelectedItem(),dateField.getText());
                    } else if (fullypaidBox.getValue().equals("no")) {
                        SQLBlanks.reportSales(ticketNumberField.getText(),typeField.getText(),ticketPriceField.getText(),"n",taxField.getText(),exchangeRateField.getText(),customerEmailField.getText(),customerPaidAmountField.getText(), (String) paymentMethodBox.getSelectionModel().getSelectedItem(),dateField.getText());
                    }

                    //Testing for data validation.

                    Double.parseDouble(ticketPriceField.getText());
                    Double.parseDouble(taxField.getText());
                    Double.parseDouble(customerPaidAmountField.getText());

                    //This calls a method that contain an SQL query that updates the blank table.
                    //The status changes from "assigned" to "sold"

                    SQLBlanks.soldBlank(ticketNumberField.getText(),typeField.getText());

                    //Creating an array to add the destinations.

                    ArrayList<String> destinations = new ArrayList();

                    //Depending on the blank type, the destinations will be added to the list.

                    if (typeField.getText().equals("444") || typeField.getText().equals("440")) {

                        //4 coupons max

                        destinations.add(destination1Field.getText());
                        destinations.add(destination2Field.getText());
                        destinations.add(destination3Field.getText());
                        destinations.add(destination4Field.getText());

                        //Calling the SQL query to execute.
                        SQLBlanks.addCoupons(destinations,typeField.getText(),ticketNumberField.getText());


                    } else if (typeField.getText().equals("420") || typeField.getText().equals("201")){

                        //2 coupons max

                        destinations.add(destination1Field.getText());
                        destinations.add(destination2Field.getText());

                        //Calling the SQL query to execute.
                        SQLBlanks.addCoupons(destinations,typeField.getText(),ticketNumberField.getText());
                    } else if (typeField.getText().equals("101")){

                        //1 coupon max

                        destinations.add(destination1Field.getText());

                        //Calling the SQL query to execute.
                        SQLBlanks.addCoupons(destinations,typeField.getText(),ticketNumberField.getText());

                    }

                    //Popup occurs when successful.
                    ErrorBox.display("Success","The ticket has successfully been reported");

                    //If the payment was card, then the program will add the card details to the credit card table.

                    if (paymentMethodBox.getValue().equals("card")) {

                        SQLBlanks.createCreditcard(customerEmailField.getText(),creditCardField.getText(),ticketNumberField.getText(),typeField.getText());

                    }

                    //Here we clear the TextFields for quality of life.

                    ticketNumberField.clear();
                    ticketPriceField.clear();
                    taxField.clear();
                    customerEmailField.clear();
                    customerPaidAmountField.clear();
                    creditCardField.clear();
                    creditCardExpireField.clear();
                    creditCardNameField.clear();
                    destination1Field.clear();
                    destination2Field.clear();
                    destination3Field.clear();
                    destination4Field.clear();

                    //Refreshing table1 to show that the database was updated.

                    table1.setItems(SQLBlanks.salesTable("" + staffNumber));

                } catch (Exception e1) {

                    //Error occurs if the data inputted was not valid.
                    ErrorBox.display("Error","The ticket has a the wrong input type.");

                }
            });


            //This action lister calculate the total amount that the customer has to pay.
            //This also includes the total including the discount plan that they were set.

            calculateMaxButton.setOnAction(e -> {
                try {

                    //If the customer email was empty (Casual customer) then the amount will be full price.

                    if (customerEmailField.getText().isEmpty() || ticketPriceField.getText().isEmpty()) {
                        customerPaidAmountField.setText("" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())));
                    } else {

                        //This block of code tries to calculate the total depending on the type of discount plan the customer was set.
                        //Type 1 = fixed
                        //type 2 = flexible

                        try {

                            //Calls a method that executes a SQL query to see what type the discount is.

                            if (SQLCustomers.getDiscountType2(customerEmailField.getText()).equals("1")){

                                //Calculation

                                Double calculate = (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())) * (1 - (Double.parseDouble(SQLBlanks.getFixedDiscount(customerEmailField.getText())) / 100));

                                //Filling the textfield with full price + discount.

                                customerPaidAmountField.setText("" + calculate);
                            } else if (SQLCustomers.getDiscountType2(customerEmailField.getText()).equals("2")) {

                                //Calculation

                                Double calculate = (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())) * (1 - (Double.parseDouble(SQLBlanks.getFlexibleDiscount(customerEmailField.getText(), "" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())))) / 100));

                                //Filling the textfield with full price + discount.

                                customerPaidAmountField.setText("" + calculate);
                            }
                        } catch (Exception nullpointer) {

                            //Just in case.

                            customerPaidAmountField.setText("" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())));
                        }
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });


            //Adding an action listener to allow the user to void blanks
            //Blanks can no longer be sold once voided.

            voidButton.setOnAction(e -> {
                try {

                    //Executes a SQL query that voids the blank.

                    SQLBlanks.voidBlank(ticketNumberField.getText(),typeField.getText());

                    //Refreshes table1 to show that the database has updated.

                    table1.setItems(SQLBlanks.salesTable("" + staffNumber));

                    //Success Popup.

                    ErrorBox.display("Success","The ticket has successfully been voided");

                    //Clear boxes for quality of life.

                    ticketNumberField.clear();
                    ticketPriceField.clear();
                    taxField.clear();
                    customerEmailField.clear();
                    customerPaidAmountField.clear();
                    creditCardField.clear();


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            //Creating BorderPane.

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
