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

public class InterlineReport {

    public static GridPane interlineGrid(int type, int staffNumber, String dateFrom,String dateTo) {
        TableView<Data2> table1;
        TableView<Data2> table2, table3;

        ArrayList<TableView<Data2>> tableViews = returnReports.returnInterlinereport(type,staffNumber,dateFrom,dateTo);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(0);

        GridPane gridInfo = new GridPane();
        Label labelTitle = new Label();
        if (type == 0) {
            labelTitle.setText("Global Interline Sales Report");
        } else {
            labelTitle.setText("Individual Interline Sales Report");
        }

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
            //table1
            int total = 0;

            table1 = tableViews.get(0);

            if (type == 0) {

                total = 0;
                for (Data2 item : table1.getItems()) {
                    total = total + Integer.parseInt(item.getData25());
                }

            } else {


                total = 0;
                for (Data2 item : table1.getItems()) {
                    total = total + Integer.parseInt(item.getData27());
                }
            }



            GridPane.setConstraints(table1,0,3);


            Label table1Total = new Label("" + total);
            GridPane.setConstraints(table1Total,0,4);
            GridPane.setHalignment(table1Total, HPos.RIGHT);

            //table2

            table2 = tableViews.get(1);

            GridPane.setConstraints(table2,1,3);

            total = 0;
            for (Data2 item : table2.getItems()) {
                total = total + Integer.parseInt(item.getData25());
            }

            Label table2Total = new Label("" + total);
            GridPane.setConstraints(table2Total,1,4);
            GridPane.setHalignment(table2Total, HPos.RIGHT);


            table3 = tableViews.get(2);

            GridPane.setConstraints(table3,2,3);


            Label table3Total = new Label("" + total);
            GridPane.setConstraints(table2Total,1,4);
            GridPane.setHalignment(table2Total, HPos.RIGHT);


            grid.getChildren().addAll(labelCommission,labelPaymentType,gridInfo,table1Total,table2Total,table1,table2,table3);
            return grid;

        } catch (Exception e) {
            return null;
        }
    }

    public static TableView getColumns(int type, int staffNumber) throws Exception {
        try {
            TableView<Data2> table = new TableView<>();
            ArrayList<String> array = SQLReport.getUniqueCommissions(type, staffNumber);
            if (array.size() != 0) {
                for (int i = 0; i < array.size(); i++) {
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
