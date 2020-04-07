package Admin;

import Entities.Data2;
import General.ErrorBox;
import SQLqueries.SQLAdmin;
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

/* This class generates a window that produces the page "View Staff"
*
* Main function of this form is to add staff to the database, as well as editing editing staff details.*/


public class ViewStaff {
    public static void display(int staffNumber, String role) throws Exception {

        try {
            //Create new window

            Stage window = new Stage();

            //Create new tableviews

            TableView<Data2> table1,table2;

            //VBox is used to display the title

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(10, 10, 10, 10));

            //Creating label which will have the title. Label will have a bigger font.

            Label labelTitle = new Label();
            labelTitle.setText("View Staff");
            labelTitle.setStyle("-fx-font: 20 arial;");
            vBox.getChildren().addAll(labelTitle);


            //Creating a new GridPane for layout. Mainly used to layout the TableView, labels and buttons

            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(8);
            grid.setPadding(new Insets(30,30,30,30));


            //Creating table 1 which will display the current staff details that are in the database.
            //This excludes passwords.

            TableColumn<Data2, String> staffidColumn1 = new TableColumn<>("Staff ID");
            staffidColumn1.setMinWidth(50);
            staffidColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));

            TableColumn<Data2, String> fnameColumn1 = new TableColumn<>("First Name");
            fnameColumn1.setMinWidth(50);
            fnameColumn1.setCellValueFactory(new PropertyValueFactory<>("data22"));

            TableColumn<Data2, String> snameColumn1 = new TableColumn<>("Surname");
            snameColumn1.setMinWidth(40);
            snameColumn1.setCellValueFactory(new PropertyValueFactory<>("data23"));

            TableColumn<Data2, String> roleColumn1 = new TableColumn<>("Role");
            roleColumn1.setMinWidth(40);
            roleColumn1.setCellValueFactory(new PropertyValueFactory<>("data24"));

            TableColumn<Data2, String> emailColumn1 = new TableColumn<>("Email");
            emailColumn1.setMinWidth(40);
            emailColumn1.setCellValueFactory(new PropertyValueFactory<>("data25"));


            //This part is used to edit the table's dimensions.
            //Also needed to extract data from the SQL queries and adding the columns

            table1 = new TableView<>();
            table1.setMaxSize(500, 150);
            table1.setItems(SQLAdmin.getStaffDetails());
            table1.getColumns().addAll(staffidColumn1, fnameColumn1, snameColumn1, roleColumn1, emailColumn1);


            //Displaying the table in the top left of the GridPane

            GridPane.setConstraints(table1, 0, 0);


            //Creating another GridPane to layout the labels and text fields.
            //This idea is similar to GridBagLayout but more manual.

            GridPane gridinfo = new GridPane();
            gridinfo.setVgap(10);
            gridinfo.setHgap(8);
            gridinfo.setPadding(new Insets(10,10,10,10));

            //Now there is a GridPane inside a GridPane.

            GridPane.setConstraints(gridinfo, 1, 0);


            //Creating general labels and text fields for staff info.

            Label fnameLabel = new Label("First Name: ");
            GridPane.setConstraints(fnameLabel,0,0);

            TextField fnameField = new TextField();
            GridPane.setConstraints(fnameField,1,0);

            Label snameLabel = new Label("Surname: ");
            GridPane.setConstraints(snameLabel,0,1);

            TextField snameField = new TextField();
            GridPane.setConstraints(snameField,1,1);


            //Creating a ComboBox to limit the options the user can pick from.
            ComboBox<String> roleBox = new ComboBox<>();
            GridPane.setConstraints(roleBox,1,2);
            roleBox.getItems().add("admin");
            roleBox.getItems().add("office manager");
            roleBox.getItems().add("travel advisor");

            Label emailLabel = new Label("Email: ");
            GridPane.setConstraints(emailLabel,0,3);

            TextField emailField = new TextField();
            GridPane.setConstraints(emailField,1,3);

            Label usernameLabel = new Label("Username: ");
            GridPane.setConstraints(usernameLabel,0,4);

            TextField usernameField = new TextField();
            GridPane.setConstraints(usernameField,1,4);

            Label passwordLabel = new Label("Password: ");
            GridPane.setConstraints(passwordLabel,0,5);

            PasswordField passwordField = new PasswordField();
            GridPane.setConstraints(passwordField,1,5);


            //This HBox is used for the buttons to either confirm or edit staff details.
            //This will go at the bottom of the window.

            HBox hBox1 = new HBox();
            hBox1.setAlignment(Pos.CENTER);
            hBox1.setSpacing(15);
            hBox1.setPadding(new Insets(10,10,10,10));

            //Creating Buttons.

            Button createButton = new Button("Create Staff");
            hBox1.getChildren().add(createButton);
            createButton.setMinSize(130,0);
            Button editButton = new Button("Edit Staff");
            hBox1.getChildren().add(editButton);
            editButton.setMinSize(130,0);
            TextField sid = new TextField();


            //User is able to interact with the TableView provided.
            //Clicking on a record on the TableView will display data on the given TextFields.
            //This is used for quality of life if the user wants to edit staff details.

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                //Get selected row that was selected.
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
                //Add the data from each column of the selected row to the boxes.
                sid.setText(newSelection.getData21());
                //Clear is used to indicate that the user cannot load the username and password of the staff member.
                //Mainly used for clarity.
                usernameField.clear();
                passwordField.clear();
                fnameField.setText(assignSelection.getData22());
                snameField.setText(assignSelection.getData23());
                emailField.setText(assignSelection.getData25());
            });


            //Create listener
            //Used to add staff to database

            createButton.setOnAction(e -> {
                try {
                    //The program test if the username already exist in the database by calling a function
                    //that outputs a username if the username is already in the database
                    //else it returns null.
                    if (SQLAdmin.getStaffUsername(usernameField.getText()).equals(null)) {
                        //If successful, a new staff member will be added to the database.
                        //Inputs from the TextFields given by the user will be used in the SQL query.
                        SQLAdmin.addStaff(fnameField.getText(),snameField.getText(),usernameField.getText(),passwordField.getText(),roleBox.getValue(),emailField.getText());
                        //Pop up box to show case that the query was successful.
                        ErrorBox.display("Success","The staff account has successfully been added");
                        //Refreshes TableView to showcase that the item was added to the database.
                        table1.setItems(SQLAdmin.getStaffDetails());
                        //Clears user's input for quality of life.
                        fnameField.clear();
                        snameField.clear();
                        usernameField.clear();
                        passwordField.clear();
                        emailField.clear();
                    } else {
                        //An error box occurs if the username already exist.
                        //Nothing happens to the database.
                        ErrorBox.display("Error","The staff username already exist.");
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            editButton.setOnAction(e -> {
                try {
                    //Calling a SQL query to update staff details
                    SQLAdmin.editStaff(sid.getText(),fnameField.getText(),snameField.getText(),emailField.getText());
                    ErrorBox.display("Success","The staff account has successfully been changed");
                    //Reload table 1 to showcase the data was updated.
                    table1.setItems(SQLAdmin.getStaffDetails());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            //Adding all TableViews, labels, TextFields and ComboBoxes to the respective GridPane.

            gridinfo.getChildren().addAll(fnameLabel,fnameField,snameLabel,snameField,roleBox,emailLabel,emailField,usernameLabel,usernameField,passwordLabel,passwordField);
            grid.getChildren().addAll(table1,gridinfo);

            //Creating BorderPane to layout my VBox, GridPane and HBox.

            BorderPane borderPane = new BorderPane();
            borderPane.setTop(vBox);
            borderPane.setCenter(grid);
            borderPane.setBottom(hBox1);
            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {

        }
    }
}
