package sample;

import Entities.Data;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ReportPage {
    //Windows

    public static void display() throws Exception {
        Stage window = new Stage();
        TableView<Data> table1, table2, table3, table4, table5;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(0);

        //Label

        Label label3 = new Label("RECIEVED BLANKS");
        GridPane.setConstraints(label3,0,0);
        GridPane.setHalignment(label3, HPos.CENTER);
        GridPane.setColumnSpan(label3,2);

        Label label1 = new Label("AGENT STOCKS");
        GridPane.setConstraints(label1,0,1);
        GridPane.setHalignment(label1, HPos.CENTER);

        Label label2 = new Label("SUB AGENTS");
        GridPane.setConstraints(label2,1,1);
        GridPane.setHalignment(label2, HPos.CENTER);

        Label label4 = new Label("ASSIGNED/USED BLANKS");
        GridPane.setConstraints(label4,2,0);
        GridPane.setHalignment(label4, HPos.CENTER);
        GridPane.setColumnSpan(label4,2);

        Label label5 = new Label("USED AGENTS");
        GridPane.setConstraints(label5,2,1);
        GridPane.setHalignment(label5, HPos.CENTER);
        GridPane.setColumnSpan(label5,2);


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

            GridPane.setConstraints(table1,0,2);


            //table2

            TableColumn<Data ,String> blanktypeColumn2 = new TableColumn<>("TYPE");
            blanktypeColumn2.setMinWidth(50);
            blanktypeColumn2.setCellValueFactory(new PropertyValueFactory<>("data2"));

            TableColumn<Data ,String> blankColumn2 = new TableColumn<>("BLANKS (FROM/TO)");
            blankColumn2.setMinWidth(125);
            blankColumn2.setCellValueFactory(new PropertyValueFactory<>("data1"));

            TableColumn<Data ,String> staffidColumn2 = new TableColumn<>("StaffID");
            staffidColumn2.setMinWidth(50);
            staffidColumn2.setCellValueFactory(new PropertyValueFactory<>("data3"));

            TableColumn<Data ,String> amountColumn2 = new TableColumn<>("AMNT");
            amountColumn2.setMinWidth(40);
            amountColumn2.setCellValueFactory(new PropertyValueFactory<>("data4"));

            table2 = new TableView<>();
            table2.setItems(SQLReport.getReport2());
            table2.getColumns().addAll(staffidColumn2, blanktypeColumn2, blankColumn2, amountColumn2);
            table2.setMaxSize(280,200);

            GridPane.setConstraints(table2,1,2);


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

            GridPane.setConstraints(table3,2,2);


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

            GridPane.setConstraints(table4,3,2);


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

            GridPane.setConstraints(table5,4,2);

            HBox hBox = new HBox();
            hBox.getChildren().addAll(table1,table2,table3);


            grid.getChildren().addAll(label1,label2,label3,label4,label5,table1,table2,table3,table4,table5);
            Scene scene = new Scene(grid);
            window.setScene(scene);
            window.show();


        } catch (Exception e) {
            System.out.println("error");
        }

    }

}
