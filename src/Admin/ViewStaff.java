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

public class ViewStaff {
    public static void display(int staffNumber, String role) throws Exception {

        try {
            Stage window = new Stage();

            TableView<Data2> table1,table2;

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(10, 10, 10, 10));

            Label labelTitle = new Label();
            labelTitle.setText("View Staff");
            labelTitle.setStyle("-fx-font: 20 arial;");
            vBox.getChildren().addAll(labelTitle);

            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setHgap(8);
            grid.setPadding(new Insets(30,30,30,30));

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

            table1 = new TableView<>();
            table1.setMaxSize(500, 150);
            table1.setItems(SQLAdmin.getStaffDetails());
            table1.getColumns().addAll(staffidColumn1, fnameColumn1, snameColumn1, roleColumn1, emailColumn1);

            GridPane.setConstraints(table1, 0, 0);


            GridPane gridinfo = new GridPane();
            gridinfo.setVgap(10);
            gridinfo.setHgap(8);
            gridinfo.setPadding(new Insets(10,10,10,10));

            GridPane.setConstraints(gridinfo, 1, 0);

            Label fnameLabel = new Label("First Name: ");
            GridPane.setConstraints(fnameLabel,0,0);

            TextField fnameField = new TextField();
            GridPane.setConstraints(fnameField,1,0);

            Label snameLabel = new Label("Surname: ");
            GridPane.setConstraints(snameLabel,0,1);

            TextField snameField = new TextField();
            GridPane.setConstraints(snameField,1,1);


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

            HBox hBox1 = new HBox();
            hBox1.setAlignment(Pos.CENTER);
            hBox1.setSpacing(15);
            hBox1.setPadding(new Insets(10,10,10,10));

            Button createButton = new Button("Create Staff");
            hBox1.getChildren().add(createButton);
            createButton.setMinSize(130,0);
            Button editButton = new Button("Edit Staff");
            hBox1.getChildren().add(editButton);
            editButton.setMinSize(130,0);
            TextField sid = new TextField();

            table1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                Data2 assignSelection = table1.getSelectionModel().getSelectedItems().get(0);
                sid.setText(newSelection.getData21());
                usernameField.clear();
                passwordField.clear();
                fnameField.setText(assignSelection.getData22());
                snameField.setText(assignSelection.getData23());
                emailField.setText(assignSelection.getData25());
            });

            createButton.setOnAction(e -> {
                try {
                    if (SQLAdmin.getStaffUsername(usernameField.getText()).equals(null)) {
                        SQLAdmin.addStaff(fnameField.getText(),snameField.getText(),usernameField.getText(),passwordField.getText(),roleBox.getValue(),emailField.getText());
                        ErrorBox.display("Success","The staff account has successfully been added");
                        table1.setItems(SQLAdmin.getStaffDetails());
                        fnameField.clear();
                        snameField.clear();
                        usernameField.clear();
                        passwordField.clear();
                        emailField.clear();
                    } else {
                        ErrorBox.display("Error","The staff username already exist.");
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            editButton.setOnAction(e -> {
                try {
                    SQLAdmin.editStaff(sid.getText(),fnameField.getText(),snameField.getText(),emailField.getText());
                    ErrorBox.display("Success","The staff account has successfully been changed");
                    table1.setItems(SQLAdmin.getStaffDetails());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            gridinfo.getChildren().addAll(fnameLabel,fnameField,snameLabel,snameField,roleBox,emailLabel,emailField,usernameLabel,usernameField,passwordLabel,passwordField);
            grid.getChildren().addAll(table1,gridinfo);

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
