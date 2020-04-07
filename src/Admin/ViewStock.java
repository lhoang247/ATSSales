package Admin;

import Entities.Data2;
import General.ErrorBox;
import SQLqueries.SQLAdmin;
import SQLqueries.SQLBlanks;
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

//This class generates a window that showcases the stocks in the database
//The windows title is "View Current Stocks"

public class ViewStock {
    public static void display(int staffNumber, String role) throws Exception {

        //Create new window

        Stage window = new Stage();

        //Create new TableViews

        TableView<Data2> table1,table2;

        //Create VBox for title.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        //Creating title label with bigger font as the style.

        Label labelTitle = new Label();
        labelTitle.setText("View Current Stocks");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);
        GridPane.setConstraints(vBox,0,0);


        //HBox is created to display the search function on the page.

        HBox searchHBox = new HBox();
        searchHBox.setPadding(new Insets(10,10,10,10));
        searchHBox.setSpacing(8);
        GridPane.setConstraints(searchHBox,0,1);

        //Creating ComboBox for different blank types.

        Label blanktypeLabel = new Label("Type:");
        ComboBox<String> searchtypeBox = new ComboBox<>();

        //Used ComboBox to limit the amount of blanks the user can select.

        searchtypeBox.getItems().add("101");
        searchtypeBox.getItems().add("201");
        searchtypeBox.getItems().add("420");
        searchtypeBox.getItems().add("440");
        searchtypeBox.getItems().add("444");
        searchtypeBox.getItems().add("451");
        searchtypeBox.getItems().add("452");

        //Creating general labels and TextFields to display on the program.

        Label ticketnumberLabel = new Label("Ticket Number: ");
        TextField ticketnumberField = new TextField();

        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear Search");


        //Adding assets to the HBox that is used for the search function.

        searchHBox.getChildren().addAll(blanktypeLabel,searchtypeBox,ticketnumberLabel,ticketnumberField,searchButton,clearButton);

        //Created new GridPane for the title and search function for layout.

        GridPane titlegrid = new GridPane();
        titlegrid.setVgap(10);
        titlegrid.setHgap(8);
        titlegrid.getChildren().addAll(vBox,searchHBox);

        //Created new GridPane for the TableView that will be generated and for the function
        //to add stocks to the database.

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));




        Label addStocksLabel = new Label("Add blanks to stocks:");
        GridPane.setConstraints(addStocksLabel,0,1);

        //Similar ComboBox as the other blank type.

        ComboBox<String> blanktype = new ComboBox<>();
        blanktype.getItems().add("101");
        blanktype.getItems().add("201");
        blanktype.getItems().add("420");
        blanktype.getItems().add("440");
        blanktype.getItems().add("444");
        blanktype.getItems().add("451");
        blanktype.getItems().add("452");
        GridPane.setConstraints(blanktype,1,1);
        GridPane.setHalignment(blanktype,HPos.CENTER);

        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();
        quantityField.setMaxWidth(50);


        //This HBox is used for adding stocks to the database.
        //Mainly used to display the quantity label and TextField in a proper layout format.

        HBox quantityBox = new HBox();
        quantityBox.getChildren().addAll(quantityLabel,quantityField);
        quantityBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(quantityBox,0,2);
        GridPane.setHalignment(quantityBox,HPos.CENTER);


        //Creating table 1 to showcase the current stocks in the database.
        //Adding Column names to the TableView

        TableColumn<Data2 ,String> typeColumn1 = new TableColumn<>("Type");
        typeColumn1.setMinWidth(50);
        typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

        TableColumn<Data2,String> bundleminColumn1 = new TableColumn<>("From");
        bundleminColumn1.setMinWidth(50);
        bundleminColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

        TableColumn<Data2,String> bundlemaxColumn1 = new TableColumn<>("To");
        bundlemaxColumn1.setMinWidth(50);
        bundlemaxColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

        TableColumn<Data2 ,String> idstaffColumn1 = new TableColumn<>("Staff ID");
        idstaffColumn1.setMinWidth(40);
        idstaffColumn1.setCellValueFactory(new PropertyValueFactory<>("data25"));

        TableColumn<Data2 ,String> receivedDateColumn1 = new TableColumn<>("Date Received");
        receivedDateColumn1.setMinWidth(125);
        receivedDateColumn1.setCellValueFactory(new PropertyValueFactory<>("data26"));


        //Table 1 uses a SQL query to fill data into the table.
        //Columns that were generated before are now added to the table.

        table1 = new TableView<>();
        table1.setMaxSize(500,150);
        table1.setItems(SQLBlanks.getReport1());
        table1.getColumns().addAll(typeColumn1,bundleminColumn1,bundlemaxColumn1,idstaffColumn1,receivedDateColumn1);
        GridPane.setConstraints(table1,0,0);
        GridPane.setColumnSpan(table1,3);


        //Creating HBox for the confirm and remove button.

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        //Creating buttons that will be displayed at the bottom.

        Button confirmButton = new Button("Add Stock");
        Button removeButton = new Button("Remove Stock");
        hBox1.getChildren().addAll(confirmButton, removeButton);


        //Added a listener for the Confirm Button.

        confirmButton.setOnAction(e -> {
            try {
                //This line adds stocks to the database
                SQLAdmin.addBlanksToStocks(blanktype.getValue(),quantityField.getText());

                //Data validation is used here to make sure the data inputted by the user was an integer.
                Integer.parseInt(quantityField.getText());

                //If the data passes the data validation then a success pop up box will appear.
                ErrorBox.display("Success","You have added blanks to stocks");

                //Table 1 is updated to show that the stocks were added.
                table1.setItems(SQLBlanks.getReport1());
            } catch (Exception e1) {

                //An error will occur if the input from the user was not an integer.
                ErrorBox.display("Error","Wrong input.");
            }
        });


        //Listener added for the remove button.

        removeButton.setOnAction(e -> {
            try {

                //Here is a listener for the selected row in the TableView.
                Data2 bundleSelection = table1.getSelectionModel().getSelectedItems().get(0);

                //An sql query is used to check if the blanks that were selected was slready sold or not.
                //The data that is passed in the procedure is the data selected from the TableView.
                if (!SQLAdmin.getStatus(bundleSelection.getData21(),bundleSelection.getData23()).equals("sold")) {

                    //An sql query is used to remove blanks from stocks.
                    SQLAdmin.removeStocks(bundleSelection.getData21(),bundleSelection.getData23(),bundleSelection.getData24());
                    //Pop up message is used to display a success.
                    ErrorBox.display("Success","You have removed the blanks from stocks");
                    //Table1 is refreshed to show that it is updated.
                    table1.setItems(SQLBlanks.getReport1());
                } else {
                    //Error box appears if the blank was already sold.
                    ErrorBox.display("Error","Cannot remove. Blank has been sold.");
                }

            } catch (Exception e1) {
                e1.printStackTrace();
                //Error box appears if there was no selection on the TableView.
                ErrorBox.display("Error","You must select a record.");
            }
        });


        //Listener added for the search button.
        //Used to search for a set or specific blank.
        searchButton.setOnAction(e -> {
            try {

                //Here this if statement is used in order for the user to input a valid ticketnumber.

                if (!ticketnumberField.getText().equals("")) {
                    Integer.parseInt(ticketnumberField.getText());
                }

                //Table1 is updated with the search that the user has asked for.
                table1.setItems(SQLAdmin.getSearchStocks(searchtypeBox.getValue(),ticketnumberField.getText()));
            } catch (Exception e1) {
                //Error if the user inputted a string into the ticket number field.
                ErrorBox.display("Error","Invalid Input.");
            }
        });

        //Listener added for clear button.
        //Used to clear the current search.

        clearButton.setOnAction(e -> {
            try {
                table1.setItems(SQLBlanks.getReport1());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //Adding assets to the grid.

        grid.getChildren().addAll(blanktype,quantityBox,table1,addStocksLabel);


        //Using BorderPane for a better layout.

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(titlegrid);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox1);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
    }
}
