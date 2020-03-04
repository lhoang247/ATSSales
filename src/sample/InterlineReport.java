package sample;

import Entities.Data;
import Entities.Data2;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static sample.SQLReport.getUniqueCommissions;

public class InterlineReport {

    public static GridPane interlineGrid(int type, int staffNumber) {
        TableView<Data> table1;
        TableView<Data2> table2, table3;

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



        try {
            //table1
            TableColumn<Data ,String> blanktypeColumn1 = new TableColumn<>("TYPE");
            blanktypeColumn1.setMinWidth(50);
            blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> blankColumn1 = new TableColumn<>("TICKET\nNUMBER");
            blankColumn1.setMinWidth(10);
            blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> fareAmountColumn1 = new TableColumn<>("USD");
            fareAmountColumn1.setMinWidth(40);
            fareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> exchangeRateColumn1 = new TableColumn<>("USD/BGL");
            exchangeRateColumn1.setMinWidth(40);
            exchangeRateColumn1.setCellValueFactory(new PropertyValueFactory<>("data4"));

            TableColumn<Data ,String> localFareAmountColumn1 = new TableColumn<>("BGL");
            localFareAmountColumn1.setMinWidth(40);
            localFareAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data5"));

            TableColumn<Data ,String> taxColumn1 = new TableColumn<>("TAX");
            taxColumn1.setMinWidth(40);
            taxColumn1.setCellValueFactory(new PropertyValueFactory<>("data6"));

            TableColumn<Data ,String> totalAmountColumn1 = new TableColumn<>("TOTAL\nAMOUNT");
            totalAmountColumn1.setMinWidth(40);
            totalAmountColumn1.setCellValueFactory(new PropertyValueFactory<>("data7"));

            table1 = new TableView<>();
            table1.setMaxSize(405,200);
            table1.setItems(SQLReport.getReport7(type, staffNumber));
            table1.getColumns().addAll(blanktypeColumn1,blankColumn1,fareAmountColumn1, exchangeRateColumn1,localFareAmountColumn1,taxColumn1,totalAmountColumn1);

            GridPane.setConstraints(table1,0,3);


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
            table2.setItems(SQLReport.getReport8(type, staffNumber));
            table2.setMaxSize(307,200);

            GridPane.setConstraints(table2,1,3);

            table3 = getColumns(type, staffNumber);
            table3.setItems(SQLReport.getReport9(type, staffNumber));
            table3.setMaxSize(1000,200);
            GridPane.setConstraints(table3,2,3);



            grid.getChildren().addAll(gridInfo,table1,table2,table3);
            return grid;

        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }

    public static TableView getColumns(int type, int staffNumber) throws Exception {
        try {
            TableView<Data2> table = new TableView<>();
            ArrayList<String> array = SQLReport.getUniqueCommissions(type, staffNumber);
            for (int i = 0; i < array.size() ; i++) {
                TableColumn<Data2 ,String> column = new TableColumn<>(array.get(i) + "%\n ");
                column.setMinWidth(40);
                column.setCellValueFactory(new PropertyValueFactory<>("data2" + (i+1)));
                System.out.println("data2" + (i+1));
                table.getColumns().add(column);
            }

            return table;
        } catch (Exception e) {
            return null;
        }
    }

}
