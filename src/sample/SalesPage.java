package sample;

import Entities.Data2;
import SQLqueries.SQLBlanks;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;

public class SalesPage {

    public static void display(int staffNumber ,String role) throws Exception {
        Stage window = new Stage();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setHgap(10);
        grid.setVgap(10);

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
            gridInfo.setPadding(new Insets(30,30,30,30));
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 1, 0);

            Label ticketTypeLabel = new Label ("Ticket Type: ");
            GridPane.setConstraints(ticketTypeLabel, 0, 0);

            Label ticketNumberLabel = new Label ("Ticket Number: ");
            GridPane.setConstraints(ticketNumberLabel, 0, 1);

            Label ticketPriceLabel = new Label ("Ticket Price: ");
            GridPane.setConstraints(ticketPriceLabel, 0, 2);

            Label taxLabel = new Label ("Tax: ");
            GridPane.setConstraints(taxLabel, 0, 3);

            Label exchangeRateLabel = new Label ("Exchange Rate: ");
            GridPane.setConstraints(exchangeRateLabel, 0, 4);

            Label customerEmailLabel = new Label ("Customer Email: ");
            GridPane.setConstraints(customerEmailLabel, 0, 5);

            Label customerPaymentAmountLabel = new Label ("Customer Email: ");
            GridPane.setConstraints(customerPaymentAmountLabel, 0, 6);

            Label paymentMethodAmountLabel = new Label ("Payment Method: ");
            GridPane.setConstraints(paymentMethodAmountLabel, 0, 7);

            Label dateLabel = new Label ("Customer Email: ");
            GridPane.setConstraints(dateLabel, 0, 8);


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

            TextField customerPaidAmountField = new TextField();
            GridPane.setConstraints(customerPaidAmountField, 1, 6);

            HBox checkH = new HBox();
            checkH.setSpacing(10);
            CheckBox checkBox1 = new CheckBox("Cash");
            CheckBox checkBox2 = new CheckBox("Card");
            checkH.getChildren().addAll(checkBox1,checkBox2);
            GridPane.setConstraints(checkH, 1, 7);

            GridPane.setRowSpan(gridInfo,2);
            gridInfo.getChildren().addAll(ticketTypeLabel,ticketNumberLabel,ticketPriceLabel,taxLabel,exchangeRateLabel,customerEmailLabel,paymentMethodAmountLabel,typeField,ticketNumberField,ticketPriceField,taxField,exchangeRateField,customerEmailField,checkH);

            grid.getChildren().addAll(table1,table2,gridInfo);
            Scene scene = new Scene(grid);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {

        }
    }
}
