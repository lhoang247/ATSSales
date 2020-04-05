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

public class SalesPage {

    public static void display(int staffNumber ,String role) throws Exception {
        Stage window = new Stage();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(10);
        grid.setVgap(10);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Report Sales");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        TableView<Data2> table1, table2;
        try {
            //table1

            TableColumn<Data2, String> typeColumn1 = new TableColumn<>("Blank Type");
            typeColumn1.setMinWidth(50);
            typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> minColumn1 = new TableColumn<>("Ticket Number\n(MIN)");
            minColumn1.setMinWidth(50);
            minColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> maxColumn1 = new TableColumn<>("Ticket Number\n(MAX)");
            maxColumn1.setMinWidth(40);
            maxColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            table1 = new TableView<>();
            table1.setMaxSize(500, 150);
            table1.setItems(SQLBlanks.salesTable("" + staffNumber));
            table1.getColumns().addAll(typeColumn1, minColumn1, maxColumn1);

            GridPane.setConstraints(table1, 0, 0);

            //table2

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

            table2 = new TableView<>();
            table2.setMaxSize(290, 150);
            table2.setItems(SQLBlanks.getCustomerAccounts());
            table2.getColumns().addAll(emailColumn2, firstnameColumn2, surnameColumn2, customerTypeColumn2);

            GridPane.setConstraints(table2, 0, 1);

            GridPane gridInfo = new GridPane();
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 1, 0);



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

            ComboBox fullypaidBox = new ComboBox();
            fullypaidBox.setValue("yes");
            fullypaidBox.getItems().add("yes");
            fullypaidBox.getItems().add("no");
            GridPane.setConstraints(fullypaidBox, 1, 7);

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

            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Calendar calobj = Calendar.getInstance();
            TextField dateField = new TextField(df.format(calobj.getTime()));
            GridPane.setConstraints(dateField, 1, 13);

            Button calculateMaxButton = new Button("Calculate Max with discount");
            GridPane.setConstraints(calculateMaxButton, 2, 6);
            GridPane.setColumnSpan(calculateMaxButton,2);

            Button reportSalesButton = new Button("Record Sale");
            reportSalesButton.setMinSize(130,0);
            Button voidButton = new Button("Void selected blank");
            voidButton.setMinSize(130,0);

            TextField destination1Field = new TextField ();
            GridPane.setConstraints(destination1Field, 3, 0);

            TextField destination2Field = new TextField ();
            GridPane.setConstraints(destination2Field, 3, 1);

            TextField destination3Field = new TextField ();
            GridPane.setConstraints(destination3Field, 3, 2);

            TextField destination4Field = new TextField ();
            GridPane.setConstraints(destination4Field, 3, 3);

            HBox hBoxButton = new HBox();
            hBoxButton.getChildren().addAll(reportSalesButton,voidButton);
            hBoxButton.setPadding(new Insets(10,10,10,10));
            hBoxButton.setSpacing(20);
            GridPane.setRowSpan(gridInfo,2);
            gridInfo.getChildren().addAll(ticketTypeLabel,ticketNumberLabel,ticketPriceLabel,taxLabel,exchangeRateLabel,
                    customerEmailLabel,paymentMethodLabel,customerPaymentAmountLabel,creditCardField,creditCardLabel,
                    dateLabel,typeField,ticketNumberField,ticketPriceField,taxField,exchangeRateField,customerEmailField,
                    paymentMethodBox,customerPaidAmountField,dateField, calculateMaxButton, fullypaidBox, fullyPaidLabel,
                    creditCardExpireLabel,creditCardExpireField,creditCardNameField,creditCardNameLabel,destination1Label,
                    destination2Label,destination3Label,destination4Label,destination1Field,destination2Field,destination3Field,destination4Field);
            grid.getChildren().addAll(table1,table2,gridInfo);


            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
                typeField.setText(assignSelection.getData21());
                ticketNumberField.setText(assignSelection.getData22());
                if (assignSelection.getData21().equals("444")  || assignSelection.getData21().equals("440")  || assignSelection.getData21().equals("420")) {
                    exchangeRateField.setDisable(false);
                } else {
                    exchangeRateField.setDisable(true);
                }

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

            table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 toOnClickSelection = table2.getSelectionModel().getSelectedItems().get(0);
                customerEmailField.setText(toOnClickSelection.getData21());
            });

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


            reportSalesButton.setOnAction(e -> {
                try {
                    if (fullypaidBox.getValue().equals("yes")) {
                        SQLBlanks.reportSales(ticketNumberField.getText(),typeField.getText(),ticketPriceField.getText(),"y",taxField.getText(),exchangeRateField.getText(),customerEmailField.getText(),customerPaidAmountField.getText(), (String) paymentMethodBox.getSelectionModel().getSelectedItem(),dateField.getText());
                    } else if (fullypaidBox.getValue().equals("no")) {
                        SQLBlanks.reportSales(ticketNumberField.getText(),typeField.getText(),ticketPriceField.getText(),"n",taxField.getText(),exchangeRateField.getText(),customerEmailField.getText(),customerPaidAmountField.getText(), (String) paymentMethodBox.getSelectionModel().getSelectedItem(),dateField.getText());
                    }
                    Double.parseDouble(ticketPriceField.getText());
                    Double.parseDouble(taxField.getText());
                    Double.parseDouble(customerPaidAmountField.getText());
                    SQLBlanks.soldBlank(ticketNumberField.getText(),typeField.getText());
                    ArrayList<String> destinations = new ArrayList();
                    if (typeField.getText().equals("444") || typeField.getText().equals("440")) {
                        destinations.add(destination1Field.getText());
                        destinations.add(destination2Field.getText());
                        destinations.add(destination3Field.getText());
                        destinations.add(destination4Field.getText());
                        SQLBlanks.addCoupons(destinations,typeField.getText(),ticketNumberField.getText());
                    } else if (typeField.getText().equals("420") || typeField.getText().equals("201")){
                        destinations.add(destination1Field.getText());
                        destinations.add(destination2Field.getText());
                        SQLBlanks.addCoupons(destinations,typeField.getText(),ticketNumberField.getText());
                    } else if (typeField.getText().equals("101")){
                        destinations.add(destination1Field.getText());
                        SQLBlanks.addCoupons(destinations,typeField.getText(),ticketNumberField.getText());
                    }
                    ErrorBox.display("Success","The ticket has successfully been reported");
                    if (paymentMethodBox.getValue().equals("card")) {
                        SQLBlanks.createCreditcard(customerEmailField.getText(),creditCardField.getText(),ticketNumberField.getText(),typeField.getText());
                    }
                    ticketNumberField.clear();
                    ticketPriceField.clear();
                    taxField.clear();
                    exchangeRateField.clear();
                    customerEmailField.clear();
                    customerPaidAmountField.clear();
                    creditCardField.clear();
                    creditCardExpireField.clear();
                    creditCardNameField.clear();
                    destination1Field.clear();
                    destination2Field.clear();
                    destination3Field.clear();
                    destination4Field.clear();
                    table1.setItems(SQLBlanks.salesTable("" + staffNumber));
                } catch (Exception e1) {
                    ErrorBox.display("Error","The ticket has a the wrong input type.");
                }
            });

            calculateMaxButton.setOnAction(e -> {
                try {
                    if (customerEmailField.getText().isEmpty() || ticketPriceField.getText().isEmpty()) {
                        customerPaidAmountField.setText("" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())));
                    } else {
                        try {
                            if (SQLCustomers.getDiscountType2(customerEmailField.getText()).equals("1")){
                                Double calculate = (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())) * (1 - (Double.parseDouble(SQLBlanks.getFixedDiscount(customerEmailField.getText())) / 100));
                                customerPaidAmountField.setText("" + calculate);
                            } else if (SQLCustomers.getDiscountType2(customerEmailField.getText()).equals("2")) {
                                Double calculate = (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())) * (1 - (Double.parseDouble(SQLBlanks.getFlexibleDiscount(customerEmailField.getText(), "" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())))) / 100));
                                customerPaidAmountField.setText("" + calculate);
                            }
                        } catch (Exception nullpointer) {
                            customerPaidAmountField.setText("" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())));
                        }
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            voidButton.setOnAction(e -> {
                try {
                    SQLBlanks.voidBlank(ticketNumberField.getText(),typeField.getText());
                    table1.setItems(SQLBlanks.salesTable("" + staffNumber));
                    ErrorBox.display("Success","The ticket has successfully been voided");
                    ticketNumberField.clear();
                    ticketPriceField.clear();
                    taxField.clear();
                    exchangeRateField.clear();
                    customerEmailField.clear();
                    customerPaidAmountField.clear();
                    creditCardField.clear();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
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
