package SQLqueries;

import Entities.Data2;
import General.ErrorBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static SQLqueries.SQL.getConnection;

public class SQLAdmin {

    public static void createFlexDiscBand(String blanktype, String quantity) throws Exception {
        try {
            Connection con = getConnection();

            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Calendar calobj = Calendar.getInstance();

            String data = null;
            PreparedStatement statement = con.prepareStatement("insert into atsdb.blanks (blanktype,status,receivedDate) " +
                    " values (?,?,?);");
            statement.setString(1, blanktype);
            statement.setString(2, "stock");
            statement.setString(3, df.format(calobj.getTime()));
            statement.execute();


            statement = con.prepareStatement("SELECT MAX(ticketnumber) FROM atsdb.blanks;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                data = result.getString(1);
            }

            String data2 = String.format("%08d", Integer.parseInt(data)) + " - " + String.format("%08d", Integer.parseInt(String.valueOf(Integer.parseInt(data) + Integer.parseInt(quantity) - 1)));


            statement = con.prepareStatement("insert into atsdb.blanks (blanktype,status,bundle,receivedDate) " +
                    " values (?,?,?,?);");

            for (int i = 0; i < Integer.parseInt(quantity) - 1; i++) {
                statement.setString(1, blanktype);
                statement.setString(2, "stock");
                statement.setString(3, data2);
                statement.setString(4, df.format(calobj.getTime()));
                statement.execute();
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET bundle = '" + data2 + "' \n" +
                    "WHERE ticketnumber = " + data + ";");
        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    public static ObservableList<Data2> getStaffDetails() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT idstaff,firstname,surname,role,email FROM atsdb.staff;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)
                );
                table.add(data);
            }
            con.close();
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static void addStaff(String firstname,String surname,String username,String password,String role,String email) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into atsdb.staff (firstname,surname,username,password,role,email) " +
                    " values (?,?,?,?,?,?);");
            statement.setString(1, firstname);
            statement.setString(2, surname);
            statement.setString(3, username);
            if (role.equals("office manager")) {
                statement.setString(4, "m");
            } else if (role.equals("travel advisor")) {
                statement.setString(4, "t");
            } else if (role.equals("admin")) {
                statement.setString(4, "a");
            }

            statement.setString(5, role);
            statement.setString(6, email);
            statement.execute();


        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    public static void editStaff(String id,String firstname,String surname,String email) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.staff \n" +
                    "SET firstname = '" + firstname + "' , surname = '" + surname + "', email = '" + email + "' \n" +
                    "WHERE idstaff = '"+ id + "' ;");
        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }
}