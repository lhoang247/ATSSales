package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

public class LoginPage {
    public static Stage window = new Stage();

    public static Scene loginScene() {

        Scene loginPage;
        Button loginButton;
        window.setTitle("ATS System");

        //GridPane Layout

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,75));
        grid.setVgap(8);
        grid.setHgap(10);

        //adding image

        Image img = new Image("Images/TeamLogo.png");
        HBox logo = new HBox();
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(100);
        imageView.setFitWidth(400);
        logo.getChildren().add(imageView);

        //Username Label

        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel,0,0);

        //Password label

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel,0,1);

        Label loginText = new Label("ATS System Login");

        //Login Textfield

        TextField usernameTextBox = new TextField();
        GridPane.setConstraints(usernameTextBox,1,0);

        //Passoword Textfield

        PasswordField passwordTextBox = new PasswordField();
        GridPane.setConstraints(passwordTextBox,1,1);

        //Login button

        loginButton = new Button();
        loginButton.setText("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            try
            {
                // Login check
                if (SQL.getLoginDetails(usernameTextBox.getText(),"username").equals(usernameTextBox.getText())
                        && SQL.getLoginDetails((passwordTextBox.getText()),"password").equals(passwordTextBox.getText())
                        && !usernameTextBox.getText().equals("") && !passwordTextBox.getText().equals("")) {

                    //role check

                    //checking if manager
                    if (SQL.getRole(usernameTextBox.getText()).equals("m")) {
                        window.setScene(OfficeManagerHomepage.getScene(SQL.getFandSname(usernameTextBox.getText(),"username")));

                        //checking if travel advisor
                    } else if (SQL.getRole(usernameTextBox.getText()).equals("t")) {
                        window.setScene(TravelAdvisorHomepage.getScene(SQL.getFandSname(usernameTextBox.getText(),"username")));

                        //checking if administrator
                    } else if (SQL.getRole(usernameTextBox.getText()).equals("a")) {
                        window.setScene(SystemAdminHomepage.getScene(SQL.getFandSname(usernameTextBox.getText(),"username")));
                    }
                }

                // login does not exist in database
                else {
                    ErrorBox.display("Wrong Login", "Wrong Login. Please try again.");
                }
            } catch (Exception el) {
                el.printStackTrace();
            }
        });

        //Adding objects to gridPane

        grid.getChildren().addAll(usernameLabel, usernameTextBox, passwordLabel,passwordTextBox, loginButton);


        // Using borderPane for layout

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(logo);
        borderPane.setBottom(grid);

        //Adding borderPane to scene
        //also setting size to window

        loginPage = new Scene(borderPane, 400, 225);
        return loginPage;
    }

    public static void setScene(Scene s) {
        window.setScene(s);
    }

    public static void showScene() {
        window.show();
    }
}
