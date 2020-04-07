package OfficeManager;

import General.ErrorBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//This class displays the reports.
//The user is able to select a report from the ComboBox provided.
//Also allows you to use the export to excel function.

public class ReportPage {

    public static void display(int staffNumber ,String role) throws Exception {

        //Creating new window.

        Stage window = new Stage();
        window.setMaxWidth(700);
        window.setMinWidth(700);
        window.setMaxHeight(600);
        window.setMinHeight(600);

        //Creating new BorderPane.

        BorderPane borderPane = new BorderPane();

        //Creating new Button

        Button button1 = new Button();

        //Getting today's date.

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calobj = Calendar.getInstance();

        //Creating labels and TextFields for the date box.

        Label labelfrom = new Label("Date: ");

        Label labelto = new Label("To: ");

        TextField dateFrom = new TextField(df.format(calobj.getTime()));
        dateFrom.setMaxWidth(90);

        TextField dateTo = new TextField(df.format(calobj.getTime()));
        dateTo.setMaxWidth(90);

        //Creating ChoiceBox
        //This allows the user to pick from a limited selection of reports.
        //The method also has a parameter which is the user's role in the company.
        //The office manager is able to view all reports
        //Where as the Advisor is able to view only the individual reports.

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

        //Adding a listeer to reply to the user's choice.
        //The program displays one of the reports depending on the users selection.

        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {

            //Display TurnOver report.

            if (newValue == "Turnover Report") {
                ScrollPane sp1 = new ScrollPane(TurnoverReport.turnoverGrid(dateFrom.getText(),dateTo.getText()));
                sp1.setFitToHeight(true);
                sp1.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp1);

                //Display global interline report.

            } else if (newValue == "Global Interline Report") {
                ScrollPane sp2 = new ScrollPane(InterlineReport.interlineGrid(0,staffNumber, dateFrom.getText(),dateTo.getText()));
                sp2.setFitToHeight(true);
                sp2.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp2);

                //Display individual interline report.

            } else if (newValue == "Individual Interline Report") {
                ScrollPane sp3 = new ScrollPane(InterlineReport.interlineGrid(1,staffNumber, dateFrom.getText(),dateTo.getText()));
                sp3.setFitToHeight(true);
                sp3.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp3);

                //Display global domestic report.

            } else if (newValue == "Global Domestic Report") {
                ScrollPane sp4 = new ScrollPane(DomesticReport.domesticGrid(0, staffNumber,dateFrom.getText(),dateTo.getText()));
                sp4.setFitToHeight(true);
                sp4.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp4);

                //Display individual domestic report.

            } else if (newValue == "Individual Domestic Report") {
                ScrollPane sp5 = new ScrollPane(DomesticReport.domesticGrid(1, staffNumber,dateFrom.getText(),dateTo.getText()));
                sp5.setFitToHeight(true);
                sp5.fitToHeightProperty();
                borderPane.getChildren().remove(borderPane.getCenter());;
                borderPane.setCenter(sp5);
            }
        }) ;

        //Creating new HBox to display the date boxes and ComboBox.

        HBox box = new HBox();
        box.setSpacing(10);
        box.setPadding(new Insets(15,15,15,15));

        //Creating label.

        Label choiceBoxLabel = new Label("SELECT Report type: ");

        //Adding assets to the HBox.

        box.getChildren().addAll(labelfrom,dateFrom,labelto,dateTo,choiceBoxLabel,choiceBox);
        box.setAlignment(Pos.CENTER_RIGHT);


        //Creating another HBox to add buttons to the bottom of the window.

        HBox box2 = new HBox();
        box2.setSpacing(300);
        box2.setPadding(new Insets(15,15,15,15));

        //Creating buttons.

        Button closeButton = new Button("Exit");

        Label exportfileLabel = new Label("File name: ");
        GridPane.setConstraints(exportfileLabel,0,0);

        TextField exportfilenamefield = new TextField();
        GridPane.setConstraints(exportfilenamefield,1,0);

        Button exportButton = new Button("Export");

        //Creating new GridPane.

        GridPane exportGrid = new GridPane();
        exportGrid.setHgap(8);
        GridPane.setConstraints(exportButton,2,0);

        //Adding assets to the GridPane.

        exportGrid.getChildren().addAll(exportfileLabel,exportfilenamefield,exportButton);


        //Adding a action listener to export the report.

        exportButton.setOnAction(e -> {
            if (choiceBox.getValue().equals("Turnover Report")) {
                Export.export(exportfilenamefield.getText(), returnReports.returnTurnoverreport(dateFrom.getText(),dateTo.getText()),dateFrom.getText(),dateTo.getText());
                ErrorBox.display("Success","You have exported the report.");
            }  else if (choiceBox.getValue().equals("Turnover Report")) {
                Export.export(exportfilenamefield.getText(), returnReports.returnTurnoverreport(dateFrom.getText(),dateTo.getText()),dateFrom.getText(),dateTo.getText());
                ErrorBox.display("Success","You have exported the report.");
            }

        });

        //Adding a action listener to close the window.

        closeButton.setOnAction(e -> window.close());

        //Adding buttons to the HBox.

        box2.getChildren().addAll(closeButton,exportGrid);

        //Adding to the BorderPane.

        borderPane.setTop(box);
        borderPane.setBottom(box2);

        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.show();


    }

}
