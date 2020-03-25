package General;

import Admin.BackupAndRestore;
import Entities.Data2;
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

public class RestorePage {
    public static void display() throws Exception {
        Stage window = new Stage();

        TableView<Data2> table1,table2;

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label();
        labelTitle.setText("Restore Data");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);
        vBox.setAlignment(Pos.CENTER);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));

        ComboBox<String> blanktype = new ComboBox<>();


        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();

        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel,passwordField);
        passwordBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(passwordBox,0,0);
        GridPane.setHalignment(passwordBox,HPos.CENTER);
        TableColumn<Data2 ,String> typeColumn1 = new TableColumn<>("Type");
        typeColumn1.setMinWidth(50);
        typeColumn1.setCellValueFactory(new PropertyValueFactory<>("data21"));





        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        Button confirmButton = new Button("Restore");
        hBox1.getChildren().addAll(confirmButton);


        confirmButton.setOnAction(e -> {
            try {
                if (passwordField.getText().equals("password123")) {
                    BackupAndRestore.Restoredbfromsql("backup.sql");
                } else {
                    ErrorBox.display("Denied", "Access Denied.");
                }
            } catch (Exception e1) {
                ErrorBox.display("Error",".");
            }
        });

        grid.getChildren().addAll(passwordBox);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox1);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
        try {

        } catch (Exception e) {

        }
    }
}
