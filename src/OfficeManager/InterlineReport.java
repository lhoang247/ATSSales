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

import java.util.ArrayList;

//This class generates the GridPane to allow the user to see the interline report.
//The parameter of the static method determines the date and type of interline report.

public class InterlineReport {

    public static GridPane interlineGrid(int type, int staffNumber, String dateFrom,String dateTo) {

        //Creating TableViews to display.

        TableView<Data2> table1;
        TableView<Data2> table2, table3;

        //Here we are creating list of TableView.
        //The code calls a function that fills the list with TableViews.
        //The parameter determines if the information is either global or individual report.
        //The parameter also determines the date of the report.

        ArrayList<TableView<Data2>> tableViews = returnReports.returnInterlinereport(type,staffNumber,dateFrom,dateTo);


        //Creating GridPane for tables and labels.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(0);

        //Creating another GridPane.

        GridPane gridInfo = new GridPane();

        //Creating a label for the title of the report.

        Label labelTitle = new Label();

        //This if statement determines the title of the report depending on the type passed by the parameter.
        //Either a Global or individual report.

        if (type == 0) {
            labelTitle.setText("Global Interline Sales Report");
        } else {
            labelTitle.setText("Individual Interline Sales Report");
        }

        //Changing font size of the title label.
        //Also below is other data for the report.

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

        //Adding assets to the GridPane.

        gridInfo.getChildren().addAll(labelTitle, labelAgent, labelAgentNumber, labelAgentPlace, labelReportPeriod);
        GridPane.setConstraints(gridInfo,0,0);
        GridPane.setColumnSpan(gridInfo,2);

        //Creating labels for the subheadings of the TableViews.

        Label labelPaymentType = new Label("FORMS OF PAYMENTS");
        GridPane.setHalignment(labelPaymentType, HPos.CENTER);
        GridPane.setConstraints(labelPaymentType,1,1);

        Label labelCommission = new Label("COMMISSIONS");
        GridPane.setHalignment(labelCommission, HPos.CENTER);
        GridPane.setConstraints(labelCommission,2,1);


        //This try statement is used to calculate the total of each TableView.

        try {
            //table1
            int total = 0;

            table1 = tableViews.get(0);

            //Since the first TableView in the global report has a different amount of columns
            //than the individual report, we need to use a if statement to get the data from the columns.

            if (type == 0) {

                total = 0;

                //Loop is used to add up the total

                for (Data2 item : table1.getItems()) {
                    total = total + Integer.parseInt(item.getData25());
                }

            } else {


                total = 0;

                //Loop is used to add up the total

                for (Data2 item : table1.getItems()) {
                    total = total + Integer.parseInt(item.getData27());
                }
            }

            GridPane.setConstraints(table1,0,3);

            //Adding the total onto a label.

            Label table1Total = new Label("" + total);
            GridPane.setConstraints(table1Total,0,4);
            GridPane.setHalignment(table1Total, HPos.RIGHT);

            //Creating table2 by getting the data from the List of TableViews we got before.

            table2 = tableViews.get(1);

            GridPane.setConstraints(table2,1,3);


            //Loop is used to add up the total

            total = 0;
            for (Data2 item : table2.getItems()) {
                total = total + Integer.parseInt(item.getData25());
            }

            //Creating total label for table 2.

            Label table2Total = new Label("" + total);
            GridPane.setConstraints(table2Total,1,4);
            GridPane.setHalignment(table2Total, HPos.RIGHT);

            //Creating table3.

            table3 = tableViews.get(2);

            GridPane.setConstraints(table3,2,3);


            //Creating total label for table 3.

            total = 0;
            int finaltotal = 0;

            //Creating the report total.

            try {
                for (Data2 item : table1.getItems()) {
                    total = total + Integer.parseInt(item.getData23());
                }

                finaltotal = 0;
                for (int row = 0; row<table3.getItems().size();row++){

                    try {

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

            //Adding the report total to the label.

            double cal = Double.parseDouble(table2Total.getText()) - (total - finaltotal);
            Label table3Total = new Label("Final total = " + cal);
            GridPane.setConstraints(table3Total,2,4);
            GridPane.setHalignment(table3Total, HPos.RIGHT);

            //Adding assets to the GridPane.

            grid.getChildren().addAll(labelCommission,labelPaymentType,gridInfo,table1Total,table2Total,table1,table2,table3,table3Total);

            return grid;

        } catch (Exception e) {
            return null;
        }
    }

    public static TableView getColumns(int type, int staffNumber) throws Exception {

        //This functions returns a commission table view with the commission that are used during that period.

        try {
            TableView<Data2> table = new TableView<>();
            ArrayList<String> array = SQLReport.getUniqueCommissions(type, staffNumber);
            if (array.size() != 0) {

                //Used a loop to add commissions.

                for (int i = 0; i < array.size(); i++) {

                    //Creating the column for commissions.

                    TableColumn<Data2, String> column = new TableColumn<>(array.get(i) + "%\n ");
                    column.setMinWidth(40);
                    column.setCellValueFactory(new PropertyValueFactory<>("data2" + (i + 1)));
                    table.getColumns().add(column);
                }

            } else {

            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

}
