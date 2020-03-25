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

public class UpdateLatePayment {
    public static void display(int staffNumber ,String role) throws Exception {
        Stage window = new Stage();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(10);
        grid.setVgap(10);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Update Late Payments");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        TableView<Data2> table1, table2;
        try {
            //table1

            TableColumn<Data2, String> typeColumn1 = new TableColumn<>("Customer Email");
            typeColumn1.setMinWidth(50);
            typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> minColumn1 = new TableColumn<>("Blank Type");
            minColumn1.setMinWidth(50);
            minColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> maxColumn1 = new TableColumn<>("Ticket Number");
            maxColumn1.setMinWidth(40);
            maxColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            table1 = new TableView<>();
            table1.setMaxSize(500, 150);
            table1.setItems(SQLCustomers.getLatePaymentwithTicket(staffNumber));
            table1.getColumns().addAll(typeColumn1, minColumn1, maxColumn1);

            GridPane.setConstraints(table1, 0, 0);



            GridPane gridInfo = new GridPane();
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 1, 0);


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

            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Calendar calobj = Calendar.getInstance();
            TextField dateField = new TextField(df.format(calobj.getTime()));
            GridPane.setConstraints(dateField, 1, 5);

            Button calculateMaxButton = new Button("Calculate Max with discount");
            GridPane.setConstraints(calculateMaxButton, 2, 0);

            Button reportSalesButton = new Button("Update Sale");
            reportSalesButton.setMinSize(130,0);
            Button voidButton = new Button("Remove late payment");
            voidButton.setMinSize(130,0);

            HBox hBoxButton = new HBox();
            hBoxButton.getChildren().addAll(reportSalesButton,voidButton);
            hBoxButton.setPadding(new Insets(10,10,10,10));
            hBoxButton.setSpacing(20);
            GridPane.setRowSpan(gridInfo,2);
            gridInfo.getChildren().addAll(paymentMethodLabel,customerPaymentAmountLabel,creditCardField,creditCardLabel,
                    dateLabel,paymentMethodBox,customerPaidAmountField,dateField, calculateMaxButton,
                    creditCardExpireLabel,creditCardExpireField,creditCardName1Field,creditCard1NameLabel);
            grid.getChildren().addAll(table1,gridInfo);


            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
            });

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


            reportSalesButton.setOnAction(e -> {
                try {
                    Data2 toOnClickSelection = table1.getSelectionModel().getSelectedItems().get(0);
                    SQLCustomers.updateLateSales(customerPaidAmountField.getText(), (String) paymentMethodBox.getValue(),dateField.getText(),toOnClickSelection.getData23(),toOnClickSelection.getData22());
                    Double.parseDouble(customerPaidAmountField.getText());
                    if (paymentMethodBox.getValue().equals("card")) {
                        SQLBlanks.createCreditcard(toOnClickSelection.getData21(),creditCardField.getText(),toOnClickSelection.getData23(),toOnClickSelection.getData22());
                    }
                    ErrorBox.display("Success","You have updated the late payment.");
                    table1.setItems(SQLCustomers.getLatePaymentwithTicket(staffNumber));
                } catch (Exception e1) {
                    ErrorBox.display("Error","The ticket has a the wrong input type.");
                }
            });

            calculateMaxButton.setOnAction(e -> {
                try {
                    Data2 toOnClickSelection = table1.getSelectionModel().getSelectedItems().get(0);
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
