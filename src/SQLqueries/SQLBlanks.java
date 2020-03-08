package SQLqueries;

import Entities.Data;
import Entities.Data2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static SQLqueries.SQL.getConnection;

public class SQLBlanks {

    public static ObservableList<Data2> getReport1() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT  bundle ,min(ticketnumber), max(ticketnumber), blanktype, idstaff , receivedDate " +
                    "FROM atsdb.blanks where status = 'stock' or status = 'assigned' group by bundle;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(result.getString(4),
                        result.getString(1),
                        String.format("%08d", Integer.parseInt(result.getString(2))),
                        String.format("%08d", Integer.parseInt(result.getString(3))),
                        result.getString(5),
                        result.getString(6)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static ObservableList<Data2> getReport2() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT idstaff, email, firstname, surname from atsdb.staff where role = 'm' or role = 't';");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(String.format("%03d", Integer.parseInt(result.getString(1))),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static void assignBlank(String idstaff, String bundle) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                        "SET idstaff = '" + String.format("%03d", Integer.parseInt(idstaff)) + "' , status = 'assigned' \n" +
                        "WHERE bundle = '" + bundle + "' AND (status = 'stock' OR status = 'assigned') ;");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static void unassignBlank(String bundle) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET idstaff = '' , status = 'stock' \n" +
                    "WHERE bundle = '" + bundle + "' AND status = 'assigned';");
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    public static ObservableList<Data2> salesTable(String idstaff) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT blanktype, min(ticketnumber), max(ticketnumber) FROM atsdb.blanks WHERE status = 'assigned' and idstaff = " + idstaff + " GROUP BY bundle;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(result.getString(1),
                        String.format("%08d", Integer.parseInt(result.getString(2))),
                        String.format("%08d", Integer.parseInt(result.getString(3)))
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static ObservableList<Data2> getCustomerAccounts() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT email, firstname, surname, customerType from atsdb.customerdetails;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(String.format(result.getString(1)),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static void voidBlank(String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET status = 'void' \n" +
                    "WHERE ticketnumber = '" + ticketnumber + "' AND status = 'assigned';");
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
