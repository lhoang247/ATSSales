package sample;

import Entities.Data2;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import SQLqueries.SQLReport;

import static sample.InterlineReport.getColumns;

public class DomesticReport {

    public static GridPane domesticGrid(int type, int staffNumber) {
        TableView<Data2> table1, table2 , table3;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(0);

        GridPane gridInfo = new GridPane();
        Label labelTitle = new Label();
        if (type == 0) {
            labelTitle.setText("Global Domestic Sales Report");
        } else {
            labelTitle.setText("Individual Domestic Sales Report");
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

        try {
            //table1
            TableColumn<Data2, String> blanktypeColumn1 = new TableColumn<>("TYPE");
            blanktypeColumn1.setMinWidth(50);
            blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> blankColumn1 = new TableColumn<>("TICKET\nNUMBER");
            blankColumn1.setMinWidth(10);
            blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> fareAmountColumn1 = new TableColumn<>("FARE\nAMOUNT");
            fareAmountColumn1.setMinWidth(40);
            fareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> taxColumn1 = new TableColumn<>("TAX");
            taxColumn1.setMinWidth(40);
            taxColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));


            table1 = new TableView<>();
            table1.setMaxSize(235,200);
            table1.setItems(SQLReport.getReport10(type, staffNumber));
            table1.getColumns().addAll(blanktypeColumn1, blankColumn1, fareAmountColumn1, taxColumn1);

            GridPane.setConstraints(table1,0,2);

            //table2

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

            table2 = new TableView<>();
            table2.getColumns().addAll(pmColumn2,cashColumn2,cardColumn2,cardNumberColumn2,totalColumn2);
            table2.setItems(SQLReport.getReport11(type, staffNumber));
            table2.setMaxSize(307,200);

            GridPane.setConstraints(table2,1,2);

            table3 = getColumns(type + 2, staffNumber);
            table3.setItems(SQLReport.getReport9(type + 2, staffNumber));
            table3.setMaxSize(1000,200);
            GridPane.setConstraints(table3,2,2);

            grid.getChildren().addAll(gridInfo,table1,table2,table3);

            return grid;

        } catch (Exception e) {
            return null;
        }
    }

}
