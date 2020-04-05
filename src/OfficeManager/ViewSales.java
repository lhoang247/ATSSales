package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLCommission;
import SQLqueries.SQLCustomers;
import SQLqueries.SQLReport;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewSales {
    public static void display(int staffNumber, String role) throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2,table3;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("View Sales and Coupons");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        ComboBox<String> travelAgents = SQLCommission.getTravelAgents();
        GridPane.setConstraints(travelAgents, 0, 0);


        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 1, 1);


        TableColumn<Data2, String> emailColumn1 = new TableColumn<>("Blank Type");
        emailColumn1.setMinWidth(0);
        emailColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> discountPlanColumn1 = new TableColumn<>("Ticket Number");
        discountPlanColumn1.setMinWidth(0);
        discountPlanColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));


        table1 = new TableView<>();
        table1.setMinSize(0, 220);
        table1.setMaxSize(190, 220);
        table1.getColumns().addAll(emailColumn1,discountPlanColumn1);
        table1.setItems(SQLReport.getReport12(staffNumber));
        GridPane.setHalignment(table1, HPos.CENTER);
        GridPane.setConstraints(table1, 0, 1);


        TableColumn<Data2, String> discountPlanColumn2 = new TableColumn<>("Destinations");
        discountPlanColumn2.setMinWidth(110);
        discountPlanColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        table2 = new TableView<>();
        table2.setMinSize(0, 220);
        table2.setMaxSize(150, 220);
        table2.getColumns().addAll(discountPlanColumn2);
        GridPane.setHalignment(table2, HPos.CENTER);
        GridPane.setConstraints(table2, 1, 1);



        table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Data2 moveSelection = table1.getSelectionModel().getSelectedItems().get(0);
            try {
                    table2.setItems(SQLCustomers.getCoupons(moveSelection.getData21(),moveSelection.getData22()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        grid.getChildren().addAll(table1,table2);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
        try {

        } catch (Exception e) {

        }
    }
}
