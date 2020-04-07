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

//This class is used to view the current travel agents that are associated with the company.
//Also displays the corresponding commission rate of the ticket types that the travel agents provide.

public class ViewTravelAgents {

        public static void display(int staffNumber, String role) throws Exception {

            //Create new window.

            Stage window = new Stage();

            //Create new TableView

            TableView<Data2> table1;

            //Create GridPane for easier layout management.

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(30, 30, 30, 30));
            grid.setHgap(40);
            grid.setVgap(8);

            //VBox created for the title of the window.

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(10, 10, 10, 10));

            //Create label for the title.

            Label labelTitle = new Label();
            labelTitle.setText("View Travel Agents");
            labelTitle.setStyle("-fx-font: 24 arial;");
            vBox.getChildren().addAll(labelTitle);

            //ComboBox created which is filled with the current travel agents that are associated with the company.
            //This calls an sql query.

            ComboBox<String> travelAgents = SQLCommission.getTravelAgents();
            GridPane.setConstraints(travelAgents, 0, 0);


            //Create another GridPane for layout.
            //Similar to GridBagLayout.

            GridPane gridInfo = new GridPane();
            gridInfo.setHgap(10);
            gridInfo.setVgap(8);
            GridPane.setConstraints(gridInfo, 0, 1);


            //Creating table1
            //Creating the labels that will go with table1.

            TableColumn<Data2, String> typeColumn2 = new TableColumn<>("Type");
            typeColumn2.setMinWidth(30);
            typeColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> commissionsColumn2 = new TableColumn<>("Commission");
            commissionsColumn2.setMinWidth(30);
            commissionsColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));


            //Formatting table1.

            table1 = new TableView<>();
            table1.setMaxSize(200, 220);
            table1.getColumns().addAll(typeColumn2,commissionsColumn2);
            GridPane.setHalignment(table1, HPos.CENTER);
            GridPane.setConstraints(table1, 0, 1);

            //General labels and TextFields.

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


            //This is a listener for the ComboBox
            //Whenever a new value is selected in the ComboBox,
            //The TableView is updated with the travel agents blank types and commission rate.

            travelAgents.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                try {
                    table1.setItems(SQLCommission.getTypeAndCommission(newValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }) ;


            //Adding assets to the GridPanes created.

            gridInfo.getChildren().addAll();
            grid.getChildren().addAll(travelAgents,table1,gridInfo);


            //Creating BorderPane for easier layout.

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
