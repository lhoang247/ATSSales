package General;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//This class creates a window that generates a pop up box when called.
//The main use is when a user presses a button and needs a response from the system.
//The error box is flexible as the message is determined by the data in the parameter.

public class ErrorBox {

    public static void display(String title, String message) {

        //Create new window.

        Stage window = new Stage();

        //Used define a modal window that blocks events from being delivered to any other application window.

        window.initModality(Modality.APPLICATION_MODAL);

        //Parameter is passed to name the title of the window.

        window.setTitle(title);

        //Creating general label and buttons.

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("close");


        //An action listener is used to close the window when pressed.

        closeButton.setOnAction(e -> window.close());

        //Formatting the text and button in a VBox.

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        //Formatting the window size.

        Scene scene = new Scene(layout,250,150);
        window.setScene(scene);

        //The user cannot interact with other windows until this window is closed.

        window.showAndWait();
    }
}
