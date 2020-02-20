package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {
    Stage window;
    Scene loginPage;
    Button loginButton;

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/atsdb";
            String username = "root";
            String password = "admin";
            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getData(String match, String field, String table) throws Exception {
        try {
            String data = "";
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT " + field + " FROM " + table + " WHERE " + field + " = '" + match + "'");
            ResultSet result = statement.executeQuery();
            while (result.next())
                data = result.getString(field);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("ATS System");
        Label loginText = new Label("ATS System Login");
        TextField usernameTextBox = new TextField();
        PasswordField passwordTextBox = new PasswordField();
        loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(e -> {
            try {
                if (getData(usernameTextBox.getText(),"username","officemanager").equals(usernameTextBox.getText())
                                    && getData(passwordTextBox.getText(),"password","officemanager").equals(passwordTextBox.getText())
                                    && !usernameTextBox.getText().equals("") && !passwordTextBox.getText().equals("")) {
                    window.setScene(OfficeManagerHomepage.getScene());
                } else if (getData(usernameTextBox.getText(),"username","systemadmin").equals(usernameTextBox.getText())
                                    && getData(passwordTextBox.getText(),"password","systemadmin").equals(passwordTextBox.getText())
                                    && !usernameTextBox.getText().equals("") && !passwordTextBox.getText().equals("")) {
                    window.setScene(SystemAdminHomepage.getScene());
                } else if (getData(usernameTextBox.getText(),"username","traveladvisor").equals(usernameTextBox.getText())
                                    && getData(passwordTextBox.getText(),"password","traveladvisor").equals(passwordTextBox.getText())
                                    && !usernameTextBox.getText().equals("") && !passwordTextBox.getText().equals("")) {
                    window.setScene(TravelAdvisorHomepage.getScene());
                } else {
                    System.out.println("username unmatched");
                }
            } catch (Exception el) {
                el.printStackTrace();
            } {

            }
        });

        HBox loginLayout = new HBox(10);
        loginLayout.getChildren().addAll(loginText,usernameTextBox,passwordTextBox,loginButton);
        loginPage = new Scene(loginLayout, 500, 100);

        window.setScene(loginPage);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
