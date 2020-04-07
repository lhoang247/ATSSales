package OfficeManager;

import Entities.Data2;
import SQLqueries.SQLCustomers;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import General.ErrorBox;

//This class generates a window that implements the feature of creating a flexible discount plan,
//The main function of this window is to allow staff to create a flexible discount plan using existing
//bands or to create new ones for their own purposes.

public class CreateFlexibleDiscount {

    public static void display(int staffNumber, String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();

        //Creating 2 TableViews that will be displayed.

        TableView<Data2> table1,table2;

        //Created GridPane for easier layout management.

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setHgap(40);
        grid.setVgap(8);

        //Created VBox for title label.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        Label labelTitle = new Label();

        //Created label for title.

        labelTitle.setText("Flexible Discount Plan");
        labelTitle.setStyle("-fx-font: 24 arial;");
        vBox.getChildren().addAll(labelTitle);

        //Created labels to name the TableViews displayed for more clarity.

        Label currentBands = new Label("Current Bands:");
        GridPane.setConstraints(currentBands, 0, 0);

        Label currentPlan = new Label("Current Plan:");
        GridPane.setConstraints(currentPlan, 2, 0);

        //Another GridPane added for the buttons between the two TableViews for easier formatting.

        GridPane gridInfo = new GridPane();
        gridInfo.setHgap(10);
        gridInfo.setVgap(8);
        GridPane.setConstraints(gridInfo, 1, 1);
        GridPane.setHalignment(gridInfo, HPos.CENTER);
        GridPane.setValignment(gridInfo, VPos.CENTER);

        //Table1's columns created.

        TableColumn<Data2, String> idbandColumn2 = new TableColumn<>("ID");
        idbandColumn2.setMinWidth(30);
        idbandColumn2.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> frombandColumn2 = new TableColumn<>("From");
        frombandColumn2.setMinWidth(30);
        frombandColumn2.setCellValueFactory(new PropertyValueFactory<>("data22"));

        TableColumn<Data2, String> tobandColumn2 = new TableColumn<>("To");
        tobandColumn2.setMinWidth(30);
        tobandColumn2.setCellValueFactory(new PropertyValueFactory<>("data23"));

        //Adding data and columns to table1.

        table1 = new TableView<>();
        table1.setMinSize(135, 220);
        table1.setMaxSize(135, 220);
        table1.setItems(SQLCustomers.viewBands());
        table1.getColumns().addAll(idbandColumn2,frombandColumn2, tobandColumn2);

        GridPane.setHalignment(table1, HPos.CENTER);
        GridPane.setConstraints(table1, 0, 1);


        //Creating the columns for table2.

        TableColumn<Data2, String> idbandColumn3 = new TableColumn<>("ID");
        idbandColumn3.setMinWidth(30);
        idbandColumn3.setMaxWidth(30);
        idbandColumn3.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2, String> frombandColumn3 = new TableColumn<>("From");
        frombandColumn3.setMinWidth(30);
        frombandColumn3.setCellValueFactory(new PropertyValueFactory<>("data22"));

        TableColumn<Data2, String> tobandColumn3 = new TableColumn<>("To");
        tobandColumn3.setMinWidth(30);
        tobandColumn3.setCellValueFactory(new PropertyValueFactory<>("data23"));

        TableColumn<Data2, String> discountColumn3 = new TableColumn<>("Discount");
        discountColumn3.setMinWidth(30);
        discountColumn3.setCellValueFactory(new PropertyValueFactory<>("data24"));

        //Table2 does not require an SQL query to fill in the data as table 2 should be empty at the beginning.

        table2 = new TableView<>();
        table2.setMinSize(260, 220);
        table2.setMaxSize(260, 220);
        table2.getColumns().addAll(idbandColumn3, frombandColumn3, tobandColumn3, discountColumn3);

        GridPane.setHalignment(table2, HPos.CENTER);
        GridPane.setConstraints(table2, 2, 1);


        //Creating label, TextField and buttons for the user to input in.

        Label discountLabel = new Label ("Discount: ");
        GridPane.setConstraints(discountLabel, 0, 0);
        GridPane.setHalignment(discountLabel, HPos.RIGHT);

        TextField discountField = new TextField("0");
        discountField.setMaxWidth(70);
        GridPane.setConstraints(discountField, 1, 0);

        Button moveRight = new Button("->");
        GridPane.setConstraints(moveRight, 0, 1);
        GridPane.setColumnSpan(moveRight,2);
        GridPane.setHalignment(moveRight, HPos.CENTER);

        Button moveLeft = new Button("<-");
        GridPane.setConstraints(moveLeft, 0, 2);
        GridPane.setColumnSpan(moveLeft,2);
        GridPane.setHalignment(moveLeft, HPos.CENTER);



        //Action listener added to allow the user to move the band from the left TableView to the Right TableView.

        moveRight.setOnAction(e -> {
            try {
                //Data validation is added to make sure the discount is an integer.
                Integer.parseInt(discountField.getText());
                //Variable created to get the currect select row in table1.
                Data2 moveSelection = table1.getSelectionModel().getSelectedItems().get(0);
                //The band selected from table1 is copied and added to table2 plus the discount rate the user inputted.
                table2.getItems().add(new Data2(moveSelection.getData21(),moveSelection.getData22(),moveSelection.getData23(),discountField.getText()));

                //The band is removed from table1.
                ObservableList<Data2> deleteSelection, allProducts;
                allProducts = table1.getItems();
                deleteSelection = table1.getSelectionModel().getSelectedItems();
                deleteSelection.forEach(allProducts::remove);

            }catch (Exception e2) {
                //Error if data is not a integer in the TextField.
                ErrorBox.display("Error", "Wrong input type.");
            }

        });


        //Action listener that allows users to revert their changes from the button if they made a mistake.

        moveLeft.setOnAction(e -> {
            try {
            Data2 moveSelection = table2.getSelectionModel().getSelectedItems().get(0);
            //Similar to the other button.
            //Creates new row in table1 and deletes selected row in table2.
            table1.getItems().add(new Data2(moveSelection.getData21(),moveSelection.getData22(),moveSelection.getData23()));

            //Deleting row in table2.
            ObservableList<Data2> deleteSelection, allProducts;
            allProducts = table2.getItems();
            deleteSelection = table2.getSelectionModel().getSelectedItems();
            deleteSelection.forEach(allProducts::remove);
            }catch (Exception e2) {

            }
        });

        //Creating HBox
        //Adding functions such as creating bands or confirming the flexible discount.
        //Creating labels and buttons for user's clarity and use.

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(50);

        Button createButton = new Button("Create Plan");
        createButton.setMinSize(130,0);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(8);

        Button createnewBandButton = new Button("Create New Band");
        createnewBandButton.setMinSize(130,0);

        Label fromLabel = new Label("From:");

        TextField fromField = new TextField();
        fromField.setMaxWidth(50);

        Label toLabel = new Label("To:");

        TextField toField = new TextField();
        toField.setMaxWidth(50);

        //Adding assets to the respective HBoxes.

        hBox.getChildren().addAll(hBox2,createButton);
        hBox2.getChildren().addAll(fromLabel,fromField,toLabel,toField,createnewBandButton);
        hBox.setAlignment(Pos.CENTER);


        //Added action listener to create the discount plan.

        createButton.setOnAction(e -> {
            try {
                //Called the function to added a new id to the Flexible table.
                SQLCustomers.createFlexiblePlan();
                //Used a loop to link the bands and newly created flexible id in the FlexDiscBand table.
                for (int i = 0; i < table2.getItems().size(); i++) {
                    SQLCustomers.createFlexDiscBand(table2.getItems().get(i).getData21(),table2.getItems().get(i).getData24());
                }
                //Discount created.
                ErrorBox.display("Success", "A new flexible discount plan has been added.");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        //Added action listener to create a new band on the go.

        createnewBandButton.setOnAction(e -> {
            try {
                //Validate data that the system provides for the user on the bottom.
                Integer.parseInt(fromField.getText());
                Integer.parseInt(toField.getText());
                //Calls the SQL query if successful.
                //Adds new band to the database.
                SQLCustomers.createFBand(fromField.getText(),toField.getText());
                //Refreshes table1 to showcase that the band has been added to the database.
                //Also can be used immediately after adding.
                table1.setItems(SQLCustomers.viewBands());
            } catch (Exception e1) {
                ErrorBox.display("Error", "Wrong input type.");
            }
        });

        //Adding assets to the GridPanes.

        gridInfo.getChildren().addAll(moveRight,moveLeft,discountLabel,discountField);
        grid.getChildren().addAll(currentBands,table1,gridInfo,table2,currentPlan);

        //Creating BorderPane

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.show();
    }

}
