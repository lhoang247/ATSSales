package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLReport;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static OfficeManager.InterlineReport.getColumns;

//This class returns a list of TableViews.
//The TableViews contain the content of the report requested by the user.
//It names the columns and calls the SQL query

public class returnReports {

    public static ArrayList<TableView<Data2>> returnTurnoverreport(String dateFrom,String dateTo) {

        try {

            //Creating TableViews to add to the list of TableViews.

            TableView<Data2> table1, table2, table3, table4, table5, table6;

            //Creating table1

            TableColumn<Data2, String> blanktypeColumn1 = new TableColumn<>("TYPE");
            blanktypeColumn1.setMinWidth(50);
            blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> blankColumn1 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn1.setMinWidth(125);
            blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> amountColumn1 = new TableColumn<>("AMNT");
            amountColumn1.setMinWidth(40);
            amountColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            //Adding columns to the table.
            //Filling table with data.

            table1 = new TableView<>();
            table1.setMaxSize(230, 200);
            table1.setItems(SQLReport.getReport1(dateFrom, dateTo));
            table1.getColumns().addAll(blanktypeColumn1, blankColumn1, amountColumn1);


            //Creating table2

            TableColumn<Data2, String> blanktypeColumn2 = new TableColumn<>("TYPE");
            blanktypeColumn2.setMinWidth(50);
            blanktypeColumn2.setCellValueFactory(new PropertyValueFactory<>("data2" + 2));

            TableColumn<Data2, String> blankColumn2 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn2.setMinWidth(125);
            blankColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> staffidColumn2 = new TableColumn<>("StaffID");
            staffidColumn2.setMinWidth(50);
            staffidColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> amountColumn2 = new TableColumn<>("AMNT");
            amountColumn2.setMinWidth(40);
            amountColumn2.setCellValueFactory(new PropertyValueFactory<>("data24"));

            //Adding columns to the table.
            //Filling table with data.

            table2 = new TableView<>();
            table2.setItems(SQLReport.getReport2(dateFrom, dateTo));
            table2.getColumns().addAll(staffidColumn2, blanktypeColumn2, blankColumn2, amountColumn2);
            table2.setMaxSize(280, 200);


            //Creating table3

            TableColumn<Data2, String> blanktypeColumn3 = new TableColumn<>("TYPE");
            blanktypeColumn3.setMinWidth(50);
            blanktypeColumn3.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> blankColumn3 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn3.setMinWidth(125);
            blankColumn3.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> staffidColumn3 = new TableColumn<>("StaffID");
            staffidColumn3.setMinWidth(50);
            staffidColumn3.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> amountColumn3 = new TableColumn<>("AMNT");
            amountColumn3.setMinWidth(40);
            amountColumn3.setCellValueFactory(new PropertyValueFactory<>("data24"));

            //Adding columns to the table.
            //Filling table with data.

            table3 = new TableView<>();
            table3.setItems(SQLReport.getReport3());
            table3.getColumns().addAll(staffidColumn3, blanktypeColumn3, blankColumn3, amountColumn3);
            table3.setMaxSize(280, 200);


            //Creating table4

            TableColumn<Data2, String> blanktypeColumn4 = new TableColumn<>("TYPE");
            blanktypeColumn4.setMinWidth(0);
            blanktypeColumn4.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> blankFromColumn4 = new TableColumn<>("BLANKS (FROM)");
            blankFromColumn4.setMinWidth(0);
            blankFromColumn4.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> blankToColumn4 = new TableColumn<>("BLANKS (TO)");
            blankToColumn4.setMinWidth(0);
            blankToColumn4.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> amountColumn4 = new TableColumn<>("AMNT");
            amountColumn4.setMinWidth(0);
            amountColumn4.setCellValueFactory(new PropertyValueFactory<>("data24"));

            //Adding columns to the table.
            //Filling table with data.

            table4 = new TableView<>();
            table4.setItems(SQLReport.getReport4());
            table4.getColumns().addAll(blanktypeColumn4, blankFromColumn4, blankToColumn4, amountColumn4);
            table4.setMaxSize(295, 200);

            //Creating table5

            TableColumn<Data2, String> blanktypeColumn5 = new TableColumn<>("TYPE");
            blanktypeColumn5.setMinWidth(0);
            blanktypeColumn5.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> blankFromColumn5 = new TableColumn<>("BLANKS (FROM)");
            blankFromColumn5.setMinWidth(0);
            blankFromColumn5.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> blankToColumn5 = new TableColumn<>("BLANKS (TO)");
            blankToColumn5.setMinWidth(0);
            blankToColumn5.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> amountColumn5 = new TableColumn<>("AMNT");
            amountColumn5.setMinWidth(0);
            amountColumn5.setCellValueFactory(new PropertyValueFactory<>("data24"));

            //Adding columns to the table.
            //Filling table with data.

            table5 = new TableView<>();
            table5.setItems(SQLReport.getReport5());
            table5.getColumns().addAll(blanktypeColumn5, blankFromColumn5, blankToColumn5, amountColumn5);
            table5.setMaxSize(295, 200);


            //Creating table6

            TableColumn<Data2, String> staffIDColumn6 = new TableColumn<>("CODE");
            staffIDColumn6.setMinWidth(0);
            staffIDColumn6.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> blanktypeColumn6 = new TableColumn<>("TYPE");
            blanktypeColumn6.setMinWidth(0);
            blanktypeColumn6.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> blankFromColumn6 = new TableColumn<>("BLANKS (FROM)");
            blankFromColumn6.setMinWidth(0);
            blankFromColumn6.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> blankToColumn6 = new TableColumn<>("BLANKS (TO)");
            blankToColumn6.setMinWidth(0);
            blankToColumn6.setCellValueFactory(new PropertyValueFactory<>("data24"));

            TableColumn<Data2, String> amountColumn6 = new TableColumn<>("AMNT");
            amountColumn6.setMinWidth(0);
            amountColumn6.setCellValueFactory(new PropertyValueFactory<>("data25"));

            //Adding columns to the table.
            //Filling table with data.

            table6 = new TableView<>();
            table6.setItems(SQLReport.getReport6());
            table6.getColumns().addAll(staffIDColumn6, blanktypeColumn6, blankFromColumn6, blankToColumn6, amountColumn6);
            table6.setMaxSize(350, 200);


            //Creating the list of TableViews.

            ArrayList<TableView<Data2>> tableviews = new ArrayList<>();

            //Adding the TableViews to the list.

            tableviews.add(table1);
            tableviews.add(table2);
            tableviews.add(table3);
            tableviews.add(table4);
            tableviews.add(table5);
            tableviews.add(table6);

            //Returning the list.

            return tableviews;

        } catch (Exception e) {

            return null;

        }

    }


    //This method returns the interline sales report.
    //This can either return individual or global report.

    public static ArrayList<TableView<Data2>> returnInterlinereport(int type, int staffNumber, String dateFrom,String dateTo) {

        try {

            //Creating TableViews to add to the list.

            TableView<Data2> table1, table2, table3;

            //Creating the list of TableViews.

            ArrayList<TableView<Data2>> tableviews = new ArrayList<>();


            //Creating columns for table1.
            //Here i predefined the columns as i will pass them in a if statement to decide if the title is either
            //a individual report or a global report.

            TableColumn<Data2 ,String> blanktypeColumn1;
            TableColumn<Data2 ,String> blankColumn1;
            TableColumn<Data2 ,String> fareAmountColumn1;
            TableColumn<Data2 ,String> exchangeRateColumn1;
            TableColumn<Data2 ,String> localFareAmountColumn1;
            TableColumn<Data2 ,String> taxColumn1;
            TableColumn<Data2 ,String> totalAmountColumn1;

            //Creating table1.

            table1 = new TableView<>();

            //Initialized total to calculate the total of each TableView's last column.

            int total = 0;

            //Type 0 = global
            //Type 1 = individual

            if (type == 0) {

                //Creating the columns and naming them.

                blanktypeColumn1 = new TableColumn<>("StaffID");
                blanktypeColumn1.setMinWidth(50);
                blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

                blankColumn1 = new TableColumn<>("TTL TK\nNUMR");
                blankColumn1.setMinWidth(10);
                blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

                fareAmountColumn1 = new TableColumn<>("FARE");
                fareAmountColumn1.setMinWidth(40);
                fareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

                exchangeRateColumn1 = new TableColumn<>("TAX");
                exchangeRateColumn1.setMinWidth(40);
                exchangeRateColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

                localFareAmountColumn1 = new TableColumn<>("TOTAL");
                localFareAmountColumn1.setMinWidth(40);
                localFareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data25"));


                //Filling table1 with data.
                //Adding the columns to the table.

                table1.setMaxSize(275,200);
                table1.setItems(SQLReport.getReport7(type, staffNumber,dateFrom,dateTo));
                table1.getColumns().addAll(blanktypeColumn1,blankColumn1,fareAmountColumn1, exchangeRateColumn1,localFareAmountColumn1);

            } else {

                //Creating columns for the individual report.

                blanktypeColumn1 = new TableColumn<>("TYPE");
                blanktypeColumn1.setMinWidth(50);
                blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

                blankColumn1 = new TableColumn<>("TICKET\nNUMBER");
                blankColumn1.setMinWidth(10);
                blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

                fareAmountColumn1 = new TableColumn<>("USD");
                fareAmountColumn1.setMinWidth(40);
                fareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

                exchangeRateColumn1 = new TableColumn<>("USD/BGL");
                exchangeRateColumn1.setMinWidth(40);
                exchangeRateColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

                localFareAmountColumn1 = new TableColumn<>("BGL");
                localFareAmountColumn1.setMinWidth(40);
                localFareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data25"));

                taxColumn1 = new TableColumn<>("TAX");
                taxColumn1.setMinWidth(40);
                taxColumn1.setCellValueFactory(new PropertyValueFactory<>("data26"));

                totalAmountColumn1 = new TableColumn<>("TOTAL\nAMOUNT");
                totalAmountColumn1.setMinWidth(40);
                totalAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data27"));

                //Adding columns to the table.
                //Filling table with data.

                table1.setMaxSize(405,200);
                table1.setItems(SQLReport.getReport7(type, staffNumber, dateFrom,dateTo));
                table1.getColumns().addAll(blanktypeColumn1,blankColumn1,fareAmountColumn1, exchangeRateColumn1,localFareAmountColumn1,taxColumn1,totalAmountColumn1);

            }

            GridPane.setConstraints(table1,0,3);


            //Creating table2
            //This one does not need a if statement as both reports share the same column names.

            TableColumn<Data2 ,String> pmColumn2 = new TableColumn<>("PAYMENT\nMETHOD");
            pmColumn2.setMinWidth(50);
            pmColumn2.setCellValueFactory(new PropertyValueFactory<>("data2" + 1));

            TableColumn<Data2 ,String> cashColumn2 = new TableColumn<>("CASH");
            cashColumn2.setMinWidth(10);
            cashColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2 ,String> cardColumn2 = new TableColumn<>("CARD");
            cardColumn2.setMinWidth(40);
            cardColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2 ,String> cardNumberColumn2 = new TableColumn<>("CARD\nNUMBER");
            cardNumberColumn2.setMinWidth(40);
            cardNumberColumn2.setCellValueFactory(new PropertyValueFactory<>("data24"));

            TableColumn<Data2 ,String> totalColumn2 = new TableColumn<>("TOTAL");
            totalColumn2.setMinWidth(40);
            totalColumn2.setCellValueFactory(new PropertyValueFactory<>("data25"));


            //Adding columns to the table.
            //Filling table with data.

            table2 = new TableView<>();
            table2.getColumns().addAll(pmColumn2,cashColumn2,cardColumn2,cardNumberColumn2,totalColumn2);
            table2.setItems(SQLReport.getReport8(type, staffNumber, dateFrom,dateTo));
            table2.setMaxSize(307,200);



            //Calling a method that feels the commission table with columns.
            //Adding columns to the table.
            //Filling table with data.

            table3 = getColumns(type, staffNumber);
            table3.setItems(SQLReport.getReport9(type, staffNumber, dateFrom,dateTo));
            table3.setMaxSize(1000,200);

            //Adding tables to the list.

            tableviews.add(table1);
            tableviews.add(table2);
            tableviews.add(table3);


            //Returning the list.

            return tableviews;

        } catch (Exception e) {
            return null;
        }

    }
}
