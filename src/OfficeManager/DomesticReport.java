package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLReport;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import static OfficeManager.InterlineReport.getColumns;

//This class returns a gridPane that has the domestic sales report information.
//The parameters for the static function determines the date of the report.
//This both shows interline and individual reports which is determined by the type passed by the parameter.

public class DomesticReport {

    public static GridPane domesticGrid(int type, int staffNumber,String dateFrom,String dateTo) {

        //Creating TableViews which will be displayed for the reports data.

        TableView<Data2> table1 , table2 , table3;

        //Creating a GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(0);

        //Creating another GridPane.

        GridPane gridInfo = new GridPane();


        //Determining the title of the GridPane depending on the type of report that was requested.

        Label labelTitle = new Label();
        if (type == 0) {
            labelTitle.setText("Global Domestic Sales Report");
        } else {
            labelTitle.setText("Individual Domestic Sales Report");
        }


        //Creating labels for the top of the report.

        labelTitle.setStyle("-fx-font: 24 arial;");
        GridPane.setConstraints(labelTitle,0,0);

        Label labelAgent = new Label("Agent: AIR LINK");
        GridPane.setConstraints(labelAgent,0,1);

        Label labelAgentNumber = new Label("Number: /");
        GridPane.setConstraints(labelAgentNumber,0,2);

        Label labelAgentPlace = new Label("Sales Office Place: ");
        GridPane.setConstraints(labelAgentPlace,0,3);

        Label labelReportPeriod = new Label("Report Period: ");
        GridPane.setConstraints(labelReportPeriod,0,4);

        gridInfo.getChildren().addAll(labelTitle, labelAgent, labelAgentNumber, labelAgentPlace, labelReportPeriod);
        GridPane.setConstraints(gridInfo,0,0);
        GridPane.setColumnSpan(gridInfo,2);

        Label labelPaymentType = new Label("FORMS OF PAYMENTS");
        GridPane.setHalignment(labelPaymentType, HPos.CENTER);
        GridPane.setConstraints(labelPaymentType,1,1);

        Label labelCommission = new Label("COMMISSIONS");
        GridPane.setHalignment(labelCommission, HPos.CENTER);
        GridPane.setConstraints(labelCommission,2,1);

        try {
            //Creating the columns for table1.
            TableColumn<Data2,String> blanktypeColumn1;
            TableColumn<Data2, String> blankColumn1;

            //Determines the column name for the first 2 columns depending on the report type requested.

            if (type == 0) {
                blanktypeColumn1 = new TableColumn<>("STAFFID");
                blankColumn1 = new TableColumn<>("TTL TKT\nNMBR");
            } else {
                blanktypeColumn1 = new TableColumn<>("TYPE");
                blankColumn1 = new TableColumn<>("TICKET\nNUMBER");
            }

            blanktypeColumn1.setMinWidth(50);
            blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            blankColumn1.setMinWidth(10);
            blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> fareAmountColumn1 = new TableColumn<>("FARE\nAMOUNT");
            fareAmountColumn1.setMinWidth(40);
            fareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> taxColumn1 = new TableColumn<>("TAX");
            taxColumn1.setMinWidth(40);
            taxColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

            int total = 0;

            //Adding columns to table1.

            table1 = new TableView<>();
            table1.setMaxSize(235,200);
            table1.setItems(SQLReport.getReport10(type, staffNumber, dateFrom,dateTo));
            table1.getColumns().addAll(blanktypeColumn1, blankColumn1, fareAmountColumn1, taxColumn1);


            //Getting the total of the final column for the report.

            total = 0;
            for (Data2 item : table1.getItems()) {
                total = total + Integer.parseInt(item.getData23()) + Integer.parseInt(item.getData24());
            }

            //Adding the total as text to the report.

            Label table1Total = new Label("" + total);
            GridPane.setConstraints(table1Total,0,4);
            GridPane.setHalignment(table1Total, HPos.RIGHT);

            GridPane.setConstraints(table1,0,2);

            //Creating columns for table2

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

            //Adding columns to table2

            table2 = new TableView<>();
            table2.getColumns().addAll(pmColumn2,cashColumn2,cardColumn2,cardNumberColumn2,totalColumn2);
            table2.setItems(SQLReport.getReport11(type, staffNumber, dateFrom,dateTo));
            table2.setMaxSize(307,200);

            //Getting total for the last column for the sales.

            total = 0;
            for (Data2 item : table2.getItems()) {
                total = total + Integer.parseInt(item.getData25());
            }

            //Adds total as text for the report.

            Label table2Total = new Label("" + total);
            GridPane.setConstraints(table2Total,1,4);
            GridPane.setHalignment(table2Total, HPos.RIGHT);

            GridPane.setConstraints(table2,1,2);

            //Generating table3 as table3 could have a dynamic range of columns.

            table3 = getColumns(type + 2, staffNumber);
            table3.setItems(SQLReport.getReport9(type + 2, staffNumber, dateFrom,dateTo));
            table3.setMaxSize(1000,200);
            GridPane.setConstraints(table3,2,2);



            //Calculating the final sum of the report.

            total = 0;
            int finaltotal = 0;
            try {
                for (Data2 item : table1.getItems()) {
                    total = total + Integer.parseInt(item.getData23());
                }

                finaltotal = 0;
                for (int row = 0; row<table3.getItems().size();row++){

                    try {

                        //Adding up all the data in the third table.
                        for (int j = 0; j < table3.getColumns().size(); j++) {
                            if(table3.getColumns().get(j).getCellData(row) != null) {
                                finaltotal = (int) Double.parseDouble((String) table3.getColumns().get(j).getCellData(row));
                            }
                        }
                    } catch (Exception e) {
                    }

                }
            } catch (Exception e) {

            }


            //Adding the total to the report for display.

            double cal = Double.parseDouble(table2Total.getText()) - (total - finaltotal);
            Label table3Total = new Label("Final total = " + cal);
            GridPane.setConstraints(table3Total,2,4);
            GridPane.setHalignment(table3Total, HPos.RIGHT);


            //Adding assets to the GridPane.

            grid.getChildren().addAll(labelPaymentType,labelCommission,gridInfo,table1,table2,table3,table1Total,table2Total,table3Total);

            return grid;

        } catch (Exception e) {
            return null;
        }
    }

}
