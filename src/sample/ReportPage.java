package sample;

import Entities.Data;
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


public class ReportPage {


    public static void display(int staffNumber ,String role) throws Exception {
        Stage window = new Stage();
        window.setMaxWidth(700);
        window.setMinWidth(700);
        window.setMaxHeight(500);
        window.setMinHeight(500);
        BorderPane borderPane = new BorderPane();
        Button button1 = new Button();

        ScrollPane sp1 = new ScrollPane(TurnoverReport.turnoverGrid());
        sp1.setFitToHeight(true);
        sp1.fitToHeightProperty();

        ScrollPane sp2 = new ScrollPane(InterlineReport.interlineGrid(0,staffNumber));
        sp2.setFitToHeight(true);
        sp2.fitToHeightProperty();

        ScrollPane sp3 = new ScrollPane(InterlineReport.interlineGrid(1,staffNumber));
        sp3.setFitToHeight(true);
        sp3.fitToHeightProperty();

        ScrollPane sp4 = new ScrollPane(DomesticReport.domesticGrid(0, staffNumber));
        sp4.setFitToHeight(true);
        sp4.fitToHeightProperty();

        ScrollPane sp5 = new ScrollPane(DomesticReport.domesticGrid(1, staffNumber));
        sp5.setFitToHeight(true);
        sp5.fitToHeightProperty();


        //ChoiceBox

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
if (role.equals("m")) {
    choiceBox.getItems().add("Turnover Report");
    choiceBox.getItems().add("Global Interline Report");
    choiceBox.getItems().add("Individual Interline Report");
    choiceBox.getItems().add("Global Domestic Report");
    choiceBox.getItems().add("Individual Domestic Report");
} else {
    choiceBox.getItems().add("Individual Interline Report");
    choiceBox.getItems().add("Individual Domestic Report");
}


        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == "Turnover Report") {
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp1);
            } else if (newValue == "Global Interline Report") {
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp2);
            } else if (newValue == "Individual Interline Report") {
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp3);
            } else if (newValue == "Global Domestic Report") {
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp4);
            } else if (newValue == "Individual Domestic Report") {
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp5);
            }
        }) ;

        button1.setOnAction(e -> {
        });
        HBox box = new HBox();
        box.setSpacing(10);
        box.setPadding(new Insets(15,15,15,15));
        Label choiceBoxLabel = new Label("SELECT Report type: ");
        box.getChildren().addAll(choiceBoxLabel,choiceBox);
        box.setAlignment(Pos.CENTER_RIGHT);
        borderPane.setTop(box);
            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();


    }

}
