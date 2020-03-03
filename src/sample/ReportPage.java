package sample;

import Entities.Data;
import Entities.Data2;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ReportPage {
    //Windows

    public static void display() throws Exception {
        Stage window = new Stage();
        window.setMinWidth(1800);
        BorderPane borderPane = new BorderPane();
        Button button1 = new Button();
        button1.setOnAction(e -> {
            borderPane.getChildren().remove(borderPane.getCenter());;
            borderPane.setCenter(TurnoverReport.turnoverGrid());
        });
        GridPane gridInfo = new GridPane();
        Label labelTitle = new Label("Ticket Stock Turnover Report");
        labelTitle.setStyle("-fx-font: 24 arial;");
        GridPane.setConstraints(labelTitle,0,0);

        Label labelAgent = new Label("Agent: AIR LINK");
        GridPane.setConstraints(labelAgent,0,1);

        Label labelAgentNumber = new Label("Number: /");
        GridPane.setConstraints(labelAgentNumber,0,2);

        Label labelAgentPlace = new Label("Sales Office Place: ");
        GridPane.setConstraints(labelAgentPlace,0,3);

        Label labelReportPeriod = new Label("Report Period: ");
        GridPane.setConstraints(labelReportPeriod,0,4);

        gridInfo.getChildren().addAll(labelTitle, labelAgent, labelAgentNumber, labelAgentPlace, labelReportPeriod,button1);

        borderPane.setTop(gridInfo);
        borderPane.setCenter(InterlineReport.interlineGrid());
            Scene scene = new Scene(borderPane);
            window.setScene(scene);
            window.show();


    }

}
