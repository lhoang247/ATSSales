package General;

import javafx.application.Application;
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
