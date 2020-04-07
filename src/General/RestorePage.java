package General;

import Admin.BackupAndRestore;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


//This class is used to show the small window that is used when pressing the restore function in the login page.

public class RestorePage {

    public static void display() throws Exception {

        //Creating a new window.

        Stage window = new Stage();

        //Creating VBox.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));


        //Creating General label for Title.

        Label labelTitle = new Label();
        labelTitle.setText("Restore Data");
        labelTitle.setStyle("-fx-font: 20 arial;");
        vBox.getChildren().addAll(labelTitle);
        vBox.setAlignment(Pos.CENTER);


        //GridPane created for easier layout design.

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(8);
        grid.setPadding(new Insets(30,30,30,30));


        //Creating general label and TextField for the user's input and visibility.

        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();


        //HBox created for the layout of the label and TextField/

        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel,passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        //Adding HBox to GridPane.

        GridPane.setConstraints(passwordBox,0,0);
        GridPane.setHalignment(passwordBox,HPos.CENTER);


        //HBox created for Confirm Button.

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(6);
        hBox1.setPadding(new Insets(10,10,10,10));

        //Creating general button.

        Button confirmButton = new Button("Restore");
        hBox1.getChildren().addAll(confirmButton);


        //Action listener added for the confirm button.
        //Purpose is to restore the database with the backup file.

        confirmButton.setOnAction(e -> {
            try {

                //Since the database could be corrupted, the password for the restore function is hard coded.

                if (passwordField.getText().equals("password123")) {

                    //Restore procedure is called,
                    BackupAndRestore.Restoredbfromsql("backup.sql");
                } else {
                    //Error box appears if the user has inputted the wrong password.
                    ErrorBox.display("Denied", "Access Denied.");
                }
            } catch (Exception e1) {
                ErrorBox.display("Error",".");
            }
        });

        //Adding assets to the GridPane.

        grid.getChildren().addAll(passwordBox);


        //Creating BorderPane

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
