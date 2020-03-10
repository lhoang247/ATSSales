package sample;

import Entities.Data2;
import SQLqueries.SQLBlanks;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

            Label paymentMethodLabel = new Label ("Payment Method: ");
            GridPane.setConstraints(paymentMethodLabel, 0, 7);
            GridPane.setHalignment(paymentMethodLabel,HPos.RIGHT);

            Label customerPaymentAmountLabel = new Label ("Customer Payment Amount: ");
            GridPane.setConstraints(customerPaymentAmountLabel, 0, 6);
            GridPane.setHalignment(customerPaymentAmountLabel,HPos.RIGHT);

            Label creditCardLabel = new Label ("Credit Card: ");
            GridPane.setConstraints(creditCardLabel, 0, 8);
            GridPane.setHalignment(creditCardLabel,HPos.RIGHT);

            Label dateLabel = new Label ("Date: ");
            GridPane.setConstraints(dateLabel, 0, 9);
            GridPane.setHalignment(dateLabel,HPos.RIGHT);

            TextField typeField = new TextField();
            GridPane.setConstraints(typeField, 1, 0);


            TextField ticketNumberField = new TextField();
            GridPane.setConstraints(ticketNumberField, 1, 1);

            TextField ticketPriceField = new TextField();
            GridPane.setConstraints(ticketPriceField, 1, 2);

            TextField taxField = new TextField();
            GridPane.setConstraints(taxField, 1, 3);

            TextField exchangeRateField = new TextField();
            GridPane.setConstraints(exchangeRateField, 1, 4);

            TextField customerEmailField = new TextField();
            GridPane.setConstraints(customerEmailField, 1, 5);

            ComboBox paymentMethodBox = new ComboBox();
            paymentMethodBox.getItems().add("cash");
            paymentMethodBox.getItems().add("card");
            GridPane.setConstraints(paymentMethodBox, 1, 7);

            TextField customerPaidAmountField = new TextField();
            GridPane.setConstraints(customerPaidAmountField, 1, 6);

            TextField creditCardField = new TextField();
            GridPane.setConstraints(creditCardField, 1, 8);

            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Calendar calobj = Calendar.getInstance();
            TextField dateField = new TextField(df.format(calobj.getTime()));
            GridPane.setConstraints(dateField, 1, 9);

            Button calculateMaxButton = new Button("Calculate Max with discount");
            GridPane.setConstraints(calculateMaxButton, 2, 6);

            Button reportSalesButton = new Button("Record Sale");
            reportSalesButton.setMinSize(130,0);
            Button voidButton = new Button("Void selected blank");
            voidButton.setMinSize(130,0);

            HBox hBoxButton = new HBox();
            hBoxButton.getChildren().addAll(reportSalesButton,voidButton);
            hBoxButton.setPadding(new Insets(10,10,10,10));
            hBoxButton.setSpacing(20);
            GridPane.setRowSpan(gridInfo,2);
            gridInfo.getChildren().addAll(ticketTypeLabel,ticketNumberLabel,ticketPriceLabel,taxLabel,exchangeRateLabel,
                    customerEmailLabel,paymentMethodLabel,customerPaymentAmountLabel,creditCardField,creditCardLabel,
                    dateLabel,typeField,ticketNumberField,ticketPriceField,taxField,exchangeRateField,customerEmailField,
                    paymentMethodBox,customerPaidAmountField,dateField, calculateMaxButton);
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
            });

            table2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 toOnClickSelection = table2.getSelectionModel().getSelectedItems().get(0);
                customerEmailField.setText(toOnClickSelection.getData21());
            });

            paymentMethodBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (newValue == "cash") {
                    creditCardField.setDisable(true);
                } else if (newValue == "card") {
                    creditCardField.setDisable(false);
                }
            }) ;


            reportSalesButton.setOnAction(e -> {
                try {
                    SQLBlanks.reportSales(ticketNumberField.getText(),typeField.getText(),ticketPriceField.getText(),"y",taxField.getText(),exchangeRateField.getText(),customerEmailField.getText(),customerPaidAmountField.getText(), (String) paymentMethodBox.getSelectionModel().getSelectedItem(),dateField.getText());
                    SQLBlanks.soldBlank(ticketNumberField.getText());
                    ErrorBox.display("Success","The ticket has successfully been reported");
                    ticketNumberField.clear();
                    ticketPriceField.clear();
                    taxField.clear();
                    exchangeRateField.clear();
                    customerEmailField.clear();
                    customerPaidAmountField.clear();
                    creditCardField.clear();
                    table1.setItems(SQLBlanks.salesTable("" + staffNumber));
                } catch (Exception e1) {
                }
            });

            calculateMaxButton.setOnAction(e -> {
                try {
                    if (customerEmailField.getText().isEmpty() || ticketPriceField.getText().isEmpty()) {
                        customerPaidAmountField.setText("" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())));
                    } else {
                        if (SQLBlanks.getFixedDiscount(customerEmailField.getText()) == null) {
                            customerPaidAmountField.setText("" + (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())));
                        } else {
                            Double calculate = (Double.parseDouble(ticketPriceField.getText()) + Double.parseDouble(taxField.getText())) * (1 - (Double.parseDouble(SQLBlanks.getFixedDiscount(customerEmailField.getText())) / 100));
                            customerPaidAmountField.setText("" + calculate);
                        }
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            voidButton.setOnAction(e -> {
                try {
                    SQLBlanks.voidBlank(ticketNumberField.getText());
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
