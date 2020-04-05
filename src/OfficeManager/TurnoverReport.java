package OfficeManager;

import Entities.Data2;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;

public class TurnoverReport {


    public static GridPane turnoverGrid(String dateFrom,String dateTo) {

        ArrayList<TableView<Data2>> tableViews = returnReports.returnTurnoverreport(dateFrom,dateTo);

        TableView<Data2> table1, table2, table3, table4, table5, table6;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(0);

        //Label

        GridPane gridInfo = new GridPane();
        Label labelTitle = new Label("Ticket Stock Turnover Report");
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
        Label label3 = new Label("RECIEVED BLANKS");
        GridPane.setConstraints(label3,0,1);
        GridPane.setHalignment(label3, HPos.CENTER);
        GridPane.setColumnSpan(label3,2);

        Label label1 = new Label("AGENT STOCKS");
        GridPane.setConstraints(label1,0,2);
        GridPane.setHalignment(label1, HPos.CENTER);

        Label label2 = new Label("SUB AGENTS");
        GridPane.setConstraints(label2,1,2);
        GridPane.setHalignment(label2, HPos.CENTER);

        Label label4 = new Label("ASSIGNED/USED BLANKS");
        GridPane.setConstraints(label4,2,1);
        GridPane.setHalignment(label4, HPos.CENTER);
        GridPane.setColumnSpan(label4,2);

        Label label5 = new Label("A S S I G N E D");
        GridPane.setConstraints(label5,2,2);
        GridPane.setHalignment(label5, HPos.CENTER);

        Label labelused = new Label("U S E D");
        GridPane.setConstraints(labelused,3,2);
        GridPane.setHalignment(labelused, HPos.CENTER);

        Label label6 = new Label("FINAL AMOUNTS");
        GridPane.setConstraints(label6,4,1);
        GridPane.setHalignment(label6, HPos.CENTER);
        GridPane.setColumnSpan(label6,2);

        Label label7 = new Label("AGENT'S AMOUNT");
        GridPane.setConstraints(label7,4,2);
        GridPane.setHalignment(label7, HPos.CENTER);

        Label label8 = new Label("SUB AGENT'S AMOUNT");
        GridPane.setConstraints(label8,5,2);
        GridPane.setHalignment(label8, HPos.CENTER);

        Label labelt = new Label("TOTAL: ");
        GridPane.setConstraints(labelt,0,4);
        GridPane.setHalignment(labelt, HPos.LEFT);

        Label label9 = new Label();
        GridPane.setConstraints(label9,0,4);
        GridPane.setHalignment(label9, HPos.RIGHT);

        Label label10 = new Label();
        GridPane.setConstraints(label10,1,4);
        GridPane.setHalignment(label10, HPos.RIGHT);

        Label label11 = new Label();
        GridPane.setConstraints(label11,2,4);
        GridPane.setHalignment(label11, HPos.RIGHT);

        Label label12 = new Label();
        GridPane.setConstraints(label12,3,4);
        GridPane.setHalignment(label12, HPos.RIGHT);

        Label label13 = new Label();
        GridPane.setConstraints(label13,4,4);
        GridPane.setHalignment(label13, HPos.RIGHT);

        Label label14 = new Label();
        GridPane.setConstraints(label14,6,4);
        GridPane.setHalignment(label14, HPos.RIGHT);

        try {
            //table1

            table1 = tableViews.get(0);
            GridPane.setConstraints(table1,0,3);

            int total = 0;
            for (Data2 item : table1.getItems()) {
                total = total + Integer.parseInt(item.getData23());
            }

            label9.setText("" + total + " ");

            GridPane.setConstraints(table1,0,3);
            //table2

            table2 = tableViews.get(1);
            GridPane.setConstraints(table2,1,3);

            total = 0;
            for (Data2 item : table2.getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            label10.setText("" + total + " ");

            //table3

            table3 = tableViews.get(2);
            GridPane.setConstraints(table3,2,3);


            total = 0;
            for (Data2 item : table3.getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            label11.setText("" + total + " ");


            //table4

            table4 = tableViews.get(3);
            GridPane.setConstraints(table4,3,3);

            total = 0;
            for (Data2 item : table4.getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            label12.setText("" + total + " ");

            //table5

            table5 = tableViews.get(4);
            GridPane.setConstraints(table5,4,3);

            total = 0;
            for (Data2 item : table5.getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            label13.setText("" + total + " ");

            //table6

            table6 = tableViews.get(5);
            GridPane.setConstraints(table6,5,3);


            total = 0;
            for (Data2 item : table6.getItems()) {
                total = total + Integer.parseInt(item.getData25());
            }

            label14.setText("" + total + " ");

            HBox hBox = new HBox();
            hBox.getChildren().addAll(table1,table2,table3);

            grid.setHgrow(table1, Priority.ALWAYS);
            grid.setHgrow(table2, Priority.ALWAYS);
            grid.setHgrow(table3, Priority.ALWAYS);
            grid.setHgrow(table4, Priority.ALWAYS);
            grid.setHgrow(table5, Priority.ALWAYS);
            grid.setHgrow(table6, Priority.ALWAYS);
            grid.getChildren().addAll(gridInfo,label1,label2,label3,label4,label5,
                    labelused,label6,label7,label8,labelt,label9,label10,label11,label12,
                    label13,label14,table1,table2,table3,table4,table5,table6);


            return grid;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
