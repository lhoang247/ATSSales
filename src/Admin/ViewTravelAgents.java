package Admin;

import Entities.Data2;
import SQLqueries.SQLCommission;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewTravelAgents {

        public static void display(int staffNumber, String role) throws Exception {
            Stage window = new Stage();

            TableView<Data2> table1;

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(30, 30, 30, 30));
            grid.setHgap(40);
            grid.setVgap(8);

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(10, 10, 10, 10));

            Label labelTitle = new Label();
            labelTitle.setText("View Travel Agents");
            labelTitle.setStyle("-fx-font: 24 arial;");
            vBox.getChildren().addAll(labelTitle);

            ComboBox<String> travelAgents = SQLCommission.getTravelAgents();
            GridPane.setConstraints(travelAgents, 0, 0);


            GridPane gridInfo = new GridPane();
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 0, 1);


            TableColumn<Data2, String> typeColumn2 = new TableColumn<>("Type");
            typeColumn2.setMinWidth(30);
            typeColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> commissionsColumn2 = new TableColumn<>("Commission");
            commissionsColumn2.setMinWidth(30);
            commissionsColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

            table1 = new TableView<>();
            table1.setMaxSize(200, 220);
            table1.getColumns().addAll(typeColumn2,commissionsColumn2);
            GridPane.setHalignment(table1, HPos.CENTER);
            GridPane.setConstraints(table1, 0, 1);

            TextField typeField = new TextField();
            typeField.setMaxWidth(70);
            GridPane.setConstraints(typeField, 1, 0);

            TextField commissionField = new TextField();
            commissionField.setMaxWidth(70);
            GridPane.setConstraints(commissionField, 1, 1);

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
                typeField.setText(assignSelection.getData21());
            });

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setSpacing(20);
            hBox.setAlignment(Pos.CENTER);

            travelAgents.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                try {
                    table1.setItems(SQLCommission.getTypeAndCommission(newValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }) ;
            gridInfo.getChildren().addAll();
            grid.getChildren().addAll(travelAgents,table1,gridInfo);
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(vBox);
            borderPane.setCenter(grid);
            borderPane.setBottom(hBox);
            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();
            try {

            } catch (Exception e) {

            }
        }
    }
