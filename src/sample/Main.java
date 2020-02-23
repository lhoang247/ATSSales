package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {


    public void start(Stage primaryStage) throws Exception{
        LoginPage.setScene(LoginPage.loginScene());
        LoginPage.showScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
