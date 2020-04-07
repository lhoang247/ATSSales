package SQLqueries;

import Entities.Data2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static SQLqueries.SQL.getConnection;

public class SQLAdmin {

    //This method add blanks to stocks.

    public static void addBlanksToStocks(String blanktype, String quantity) throws Exception {
        try {
            Connection con = getConnection();

            DateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            Calendar calobj = Calendar.getInstance();

            String data = null;
            /*PreparedStatement statement = con.prepareStatement("insert into atsdb.blanks (blanktype,status,receivedDate) " +
                    " values (?,?,?);");
            statement.setString(1, blanktype);
            statement.setString(2, "stock");
            statement.setString(3, df.format(calobj.getTime()));
            statement.execute();*/


            PreparedStatement statement = con.prepareStatement("SELECT MAX(ticketnumber) FROM atsdb.blanks WHERE blanktype = " + blanktype + ";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                data = result.getString(1);
            }

            if (data == null) {
                data = "0";
            }

            String data2 = String.format("%08d", Integer.parseInt(data) + 1) + " - " + String.format("%08d", Integer.parseInt(String.valueOf(Integer.parseInt(data) + Integer.parseInt(quantity))));


            statement = con.prepareStatement("insert into atsdb.blanks (ticketnumber,blanktype,status,bundle,receivedDate) " +
                    " values (?,?,?,?,?);");

            for (int i = Integer.parseInt(data) + 1; i <= Integer.parseInt(quantity) + Integer.parseInt(data); i++) {
                statement.setInt(1, i);
                statement.setString(2, blanktype);
                statement.setString(3, "stock");
                statement.setString(4, data2);
                statement.setString(5, df.format(calobj.getTime()));
                statement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method returns a table of data which contains staff details.

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

    //This method allows the user to add staff to the database.

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
        }
    }

    //This method updates the staff's details using SQL.

    public static void editStaff(String id,String firstname,String surname,String email) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.staff \n" +
                    "SET firstname = '" + firstname + "' , surname = '" + surname + "', email = '" + email + "' \n" +
                    "WHERE idstaff = '"+ id + "' ;");
        } catch (Exception e) {
        }
    }

    //This method fetches data from the database and returns a string.

    public static String getStaffUsername(String username) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT username FROM atsdb.staff WHERE username = '" + username + "';");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            con.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //This method deletes stocks from the database.

    public static void removeStocks(String blanktype,String from, String to) throws Exception {
        try {
            Connection con = getConnection();

            String query = "delete from atsdb.blanks where blanktype = " + blanktype + " AND ticketnumber  >= " + from + " AND ticketnumber <= " + to + ";";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //This method allows the user to search for specific blanks.

    public static ObservableList<Data2> getSearchStocks(String blanktype, String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT blanktype,null, ticketnumber,ticketnumber,idstaff,receivedDate FROM atsdb.blanks WHERE blanktype = "+ blanktype +" AND status != 'sold';");
            if (!ticketnumber.equals("")) {
                statement = con.prepareStatement("SELECT blanktype,null, ticketnumber,ticketnumber,idstaff,receivedDate FROM atsdb.blanks WHERE blanktype = "+ blanktype +" AND ticketnumber = "+ ticketnumber +";");
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                );
                table.add(data);
            }
            con.close();
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns a string.
    //The method returns the status of the blank in the blank table.

    public static String getStatus(String blanktype, String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT status FROM atsdb.blanks WHERE blanktype = "+ blanktype +" AND ticketnumber = "+ ticketnumber +";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            con.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}