package sample;

import Entities.Data;
import SQLqueries.SQLReport;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TurnoverReport {

    public static GridPane turnoverGrid() {
        TableView<Data> table1, table2, table3, table4, table5, table6;

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
        GridPane.setConstraints(label2,1,3);
        GridPane.setHalignment(label2, HPos.CENTER);

        Label label4 = new Label("ASSIGNED/USED BLANKS");
        GridPane.setConstraints(label4,2,1);
        GridPane.setHalignment(label4, HPos.CENTER);
        GridPane.setColumnSpan(label4,2);

        Label label5 = new Label("USED AGENTS");
        GridPane.setConstraints(label5,2,2);
        GridPane.setHalignment(label5, HPos.CENTER);
        GridPane.setColumnSpan(label5,2);

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
            TableColumn<Data ,String> blanktypeColumn1 = new TableColumn<>("TYPE");
            blanktypeColumn1.setMinWidth(50);
            blanktypeColumn1.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> blankColumn1 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn1.setMinWidth(125);
            blankColumn1.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> amountColumn1 = new TableColumn<>("AMNT");
            amountColumn1.setMinWidth(40);
            amountColumn1.setCellValueFactory(new PropertyValueFactory<>("data3"));

            table1 = new TableView<>();
            table1.setMaxSize(230,200);
            table1.setItems(SQLReport.getReport1());
            table1.getColumns().addAll(blanktypeColumn1,blankColumn1, amountColumn1);

            GridPane.setConstraints(table1,0,3);

            int total = 0;
            for (Data item : table1.getItems()) {
                total = total + Integer.parseInt(item.getData3());
            }

            label9.setText("" + total + " ");

            GridPane.setConstraints(table1,0,3);
            //table2

            TableColumn<Data ,String> blanktypeColumn2 = new TableColumn<>("TYPE");
            blanktypeColumn2.setMinWidth(50);
            blanktypeColumn2.setCellValueFactory(new PropertyValueFactory<>("data" + 2));

            TableColumn<Data ,String> blankColumn2 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn2.setMinWidth(125);
            blankColumn2.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> staffidColumn2 = new TableColumn<>("StaffID");
            staffidColumn2.setMinWidth(50);
            staffidColumn2.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> amountColumn2 = new TableColumn<>("AMNT");
            amountColumn2.setMinWidth(40);
            amountColumn2.setCellValueFactory(new PropertyValueFactory<>("data4"));

            table2 = new TableView<>();
            table2.setItems(SQLReport.getReport2());
            table2.getColumns().addAll(staffidColumn2, blanktypeColumn2, blankColumn2, amountColumn2);
            table2.setMaxSize(280,200);

            GridPane.setConstraints(table2,1,3);

            total = 0;
            for (Data item : table2.getItems()) {
                total = total + Integer.parseInt(item.getData4());
            }

            label10.setText("" + total + " ");

            //table3

            TableColumn<Data ,String> blanktypeColumn3 = new TableColumn<>("TYPE");
            blanktypeColumn3.setMinWidth(50);
            blanktypeColumn3.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> blankColumn3 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn3.setMinWidth(125);
            blankColumn3.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> staffidColumn3 = new TableColumn<>("StaffID");
            staffidColumn3.setMinWidth(50);
            staffidColumn3.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> amountColumn3 = new TableColumn<>("AMNT");
            amountColumn3.setMinWidth(40);
            amountColumn3.setCellValueFactory(new PropertyValueFactory<>("data4"));

            table3 = new TableView<>();
            table3.setItems(SQLReport.getReport3());
            table3.getColumns().addAll(staffidColumn3, blanktypeColumn3, blankColumn3, amountColumn3);
            table3.setMaxSize(280,200);

            GridPane.setConstraints(table3,2,3);


            total = 0;
            for (Data item : table3.getItems()) {
                total = total + Integer.parseInt(item.getData4());
            }

            label11.setText("" + total + " ");


            //table4

            TableColumn<Data ,String> blanktypeColumn4 = new TableColumn<>("TYPE");
            blanktypeColumn4.setMinWidth(0);
            blanktypeColumn4.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> blankFromColumn4 = new TableColumn<>("BLANKS (FROM)");
            blankFromColumn4.setMinWidth(0);
            blankFromColumn4.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> blankToColumn4 = new TableColumn<>("BLANKS (TO)");
            blankToColumn4.setMinWidth(0);
            blankToColumn4.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> amountColumn4 = new TableColumn<>("AMNT");
            amountColumn4.setMinWidth(0);
            amountColumn4.setCellValueFactory(new PropertyValueFactory<>("data4"));

            table4 = new TableView<>();
            table4.setItems(SQLReport.getReport4());
            table4.getColumns().addAll(blanktypeColumn4, blankFromColumn4, blankToColumn4, amountColumn4);
            table4.setMaxSize(295,200);

            GridPane.setConstraints(table4,3,3);

            total = 0;
            for (Data item : table4.getItems()) {
                total = total + Integer.parseInt(item.getData4());
            }

            label12.setText("" + total + " ");

            //table5

            TableColumn<Data ,String> blanktypeColumn5 = new TableColumn<>("TYPE");
            blanktypeColumn5.setMinWidth(0);
            blanktypeColumn5.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> blankFromColumn5 = new TableColumn<>("BLANKS (FROM)");
            blankFromColumn5.setMinWidth(0);
            blankFromColumn5.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> blankToColumn5 = new TableColumn<>("BLANKS (TO)");
            blankToColumn5.setMinWidth(0);
            blankToColumn5.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> amountColumn5 = new TableColumn<>("AMNT");
            amountColumn5.setMinWidth(0);
            amountColumn5.setCellValueFactory(new PropertyValueFactory<>("data4"));

            table5 = new TableView<>();
            table5.setItems(SQLReport.getReport5());
            table5.getColumns().addAll(blanktypeColumn5, blankFromColumn5, blankToColumn5, amountColumn5);
            table5.setMaxSize(295,200);

            GridPane.setConstraints(table5,4,3);

            total = 0;
            for (Data item : table5.getItems()) {
                total = total + Integer.parseInt(item.getData4());
            }

            label13.setText("" + total + " ");

            //table6

            TableColumn<Data ,String> staffIDColumn6 = new TableColumn<>("CODE");
            staffIDColumn6.setMinWidth(0);
            staffIDColumn6.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> blanktypeColumn6 = new TableColumn<>("TYPE");
            blanktypeColumn6.setMinWidth(0);
            blanktypeColumn6.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> blankFromColumn6 = new TableColumn<>("BLANKS (FROM)");
            blankFromColumn6.setMinWidth(0);
            blankFromColumn6.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> blankToColumn6 = new TableColumn<>("BLANKS (TO)");
            blankToColumn6.setMinWidth(0);
            blankToColumn6.setCellValueFactory(new PropertyValueFactory<>("data4"));

            TableColumn<Data ,String> amountColumn6 = new TableColumn<>("AMNT");
            amountColumn6.setMinWidth(0);
            amountColumn6.setCellValueFactory(new PropertyValueFactory<>("data5"));

            table6 = new TableView<>();
            table6.setItems(SQLReport.getReport6());
            table6.getColumns().addAll(staffIDColumn6, blanktypeColumn6, blankFromColumn6, blankToColumn6, amountColumn6);
            table6.setMaxSize(350,200);

            GridPane.setConstraints(table6,5,3);


            total = 0;
            for (Data item : table6.getItems()) {
                total = total + Integer.parseInt(item.getData5());
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
            grid.getChildren().addAll(gridInfo,label1,label2,label3,label4,label5,label6,label7,label8,labelt,label9,label10,label11,label12,label13,label14,table1,table2,table3,table4,table5,table6);
            return grid;


        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }
}
