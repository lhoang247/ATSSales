package SQLqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//This class contains the connection to the database.
//The program uses MySQL and a local server that our system hosts.

public class SQL {

    public static Connection getConnection() throws Exception {

        //Connecting with MySQL.

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

    //This method is used to match the login details.

    public static ArrayList<String> getLoginDetails(String username, String password) throws Exception {
        try {
            ArrayList<String> data = new ArrayList<>();

            //Calling the connection method.
            Connection con = getConnection();

            //SQL statement.
            PreparedStatement statement = con.prepareStatement("SELECT username,password FROM staff WHERE username = '" + username + "' AND password = '" + password + "';");
            //Executing statement.
            ResultSet result = statement.executeQuery();

            //While loop to go through all the data.
            while (result.next()) {
                data.add(result.getString(1));
                data.add(result.getString(2));
                return data;
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns a string that is the role of the account.

    public static String getRole(String match) throws Exception {
        try {
            String data = "";
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT role FROM staff WHERE username = '" + match + "'");
            ResultSet result = statement.executeQuery();
            while (result.next())
                data = result.getString("role");
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns a list of information about the user who is logging in.

    public static List<String> getFandSname(String match, String field) throws Exception {
        List<String> data = new ArrayList();
        try {
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT firstname, surname, idstaff, role FROM staff WHERE " + field + " = '" + match + "'");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                data.add(result.getString("firstname"));
                data.add(result.getString("surname"));
                data.add(result.getString("idstaff"));
                data.add(result.getString("role"));
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }
}
