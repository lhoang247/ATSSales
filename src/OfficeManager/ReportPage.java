package OfficeManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ReportPage {


    public static void display(int staffNumber ,String role) throws Exception {
        Stage window = new Stage();
        window.setMaxWidth(700);
        window.setMinWidth(700);
        window.setMaxHeight(600);
        window.setMinHeight(600);
        BorderPane borderPane = new BorderPane();
        Button button1 = new Button();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calobj = Calendar.getInstance();

        Label labelfrom = new Label("Date: ");
        Label labelto = new Label("To: ");
        TextField dateFrom = new TextField(df.format(calobj.getTime()));
        dateFrom.setMaxWidth(90);
        TextField dateTo = new TextField(df.format(calobj.getTime()));
        dateTo.setMaxWidth(90);
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
                ScrollPane sp1 = new ScrollPane(TurnoverReport.turnoverGrid(dateFrom.getText(),dateTo.getText()));
                sp1.setFitToHeight(true);
                sp1.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp1);
            } else if (newValue == "Global Interline Report") {
                ScrollPane sp2 = new ScrollPane(InterlineReport.interlineGrid(0,staffNumber, dateFrom.getText(),dateTo.getText()));
                sp2.setFitToHeight(true);
                sp2.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp2);
            } else if (newValue == "Individual Interline Report") {
                ScrollPane sp3 = new ScrollPane(InterlineReport.interlineGrid(1,staffNumber, dateFrom.getText(),dateTo.getText()));
                sp3.setFitToHeight(true);
                sp3.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp3);
            } else if (newValue == "Global Domestic Report") {
                ScrollPane sp4 = new ScrollPane(DomesticReport.domesticGrid(0, staffNumber,dateFrom.getText(),dateTo.getText()));
                sp4.setFitToHeight(true);
                sp4.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp4);
            } else if (newValue == "Individual Domestic Report") {
                ScrollPane sp5 = new ScrollPane(DomesticReport.domesticGrid(1, staffNumber,dateFrom.getText(),dateTo.getText()));
                sp5.setFitToHeight(true);
                sp5.fitToHeightProperty();
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
        box.getChildren().addAll(labelfrom,dateFrom,labelto,dateTo,choiceBoxLabel,choiceBox);
        box.setAlignment(Pos.CENTER_RIGHT);

        HBox box2 = new HBox();
        box2.setSpacing(10);
        box2.setPadding(new Insets(15,15,15,15));
        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e -> window.close());
        box2.getChildren().addAll(closeButton);

        borderPane.setTop(box);
        borderPane.setBottom(box2);
            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();


    }

}
