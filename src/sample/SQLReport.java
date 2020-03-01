package sample;

import Entities.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static sample.SQL.getConnection;

public class SQLReport {

    public static ObservableList<Data> getReport1() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT DISTINCT (bundle), blanktype, COUNT(*) FROM blanks WHERE receivedDate >= '2020-02-01' GROUP BY bundle");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(result.getString("bundle"),
                        result.getString("blanktype"),
                        result.getString("COUNT(*)")
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static ObservableList<Data> getReport2() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT DISTINCT (bundle), blanktype,idstaff, COUNT(*) FROM blanks WHERE receivedDate >= '2020-02-01' AND status != 'stock' GROUP BY bundle");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(result.getString("bundle"),
                        result.getString("blanktype"),
                        result.getString("idstaff"),
                        result.getString("COUNT(*)")
                );
                System.out.println();
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    public static ObservableList<Data> getReport3() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT DISTINCT bundle, blanktype, idstaff, COUNT(*)" +
                    "FROM blanks " +
                    "WHERE status != 'stock'" +
                    "GROUP BY bundle;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(result.getString("idstaff"),
                        result.getString("blanktype"),
                        result.getString(1),
                        result.getString("COUNT(*)")
                );
                System.out.println();
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static ObservableList<Data> getReport4() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT blanktype, MIN(ticketnumber), MAX(ticketnumber), count(*) " +
                    "FROM blanks " +
                    "WHERE status = 'sold' " +
                    "GROUP BY bundle;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(result.getString("blanktype"),
                        result.getString(2),
                        result.getString(3),
                        result.getString("COUNT(*)")
                );
                System.out.println();
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static ObservableList<Data> getReport5() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT blanktype, MIN(ticketnumber), MAX(ticketnumber), count(*) \n" +
                    "FROM atsdb.blanks \n" +
                    "WHERE status != 'sold' \n" +
                    "GROUP BY bundle;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(result.getString("blanktype"),
                        result.getString(2),
                        result.getString(3),
                        result.getString("COUNT(*)")
                );
                System.out.println();
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    public static ObservableList<Data> getReport20() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT q.ticketnumber,q.paymentmethod , " +
                    "case when paymentmethod = 'cash' then salesamount*exchangerate+tax end as 'cash', " +
                    "case when paymentmethod = 'card' then salesamount*exchangerate+tax end as 'card', " +
                    "case when paymentmethod = 'card' then " +
                        "(SELECT cardnumber " +
                        "FROM creditcard C " +
                        "WHERE c.email = q.customeremail and c.ticketnumber = q.ticketnumber) " +
                        "end as 'Card number', salesamount*exchangerate+tax AS 'TOTAL AMOUNT PAID' " +
                    "FROM sales q"
            );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(result.getString("cash"),
                        result.getString("card"),
                        result.getString("Card number"),
                        result.getString("TOTAL AMOUNT PAID")
                );
                System.out.println(result.getString("cash"));
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }
}
