package SQLqueries;

import Entities.Data;
import Entities.Data2;
import General.ErrorBox;
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
            con.close();
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
            con.close();
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
            ErrorBox.display("Miss Input", "Input was not valid.");
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
            ErrorBox.display("Miss Input", "Input was not valid.");
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



public static void reportSales(String ticketnumber,String blanktype,String salesAmount, String paid, String tax,String exchangerate, String customeremail, String amountPaid, String paymentMethod, String daterecorded) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement1 = con.prepareStatement("SELECT commissionrate FROM atsdb.commissions WHERE blanktype = '"+ blanktype +"';");
            ResultSet result = statement1.executeQuery();
            String commissionrate = "0";
            while (result.next()) {
                commissionrate =  result.getString(1);
            }

            if (blanktype.equals("440") || blanktype.equals("420") || blanktype.equals("444") || blanktype.equals("451") || blanktype.equals("452")) {
                PreparedStatement statement = con.prepareStatement("insert into atsdb.sales (ticketnumber, blanktype, salesamount, paid, refunded, tax, exchangerate,customeremail,amountPaid,paymentMethod,commissionrate,daterecorded) " +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                statement.setString (1, ticketnumber);
                statement.setString (2, blanktype);
                statement.setString (3, salesAmount);
                statement.setString (4, paid);
                statement.setString (5, "n");
                statement.setString (6, tax);
                statement.setString (7, exchangerate);
                statement.setString (8, customeremail);
                statement.setString (9, amountPaid);
                statement.setString (10, paymentMethod);
                statement.setString (11, commissionrate);
                statement.setString (12, daterecorded);
                statement.execute();
            } else {
                PreparedStatement statement = con.prepareStatement("insert into atsdb.sales (ticketnumber, blanktype, salesamount, paid, refunded, tax,customeremail,amountPaid,paymentMethod,commissionrate,daterecorded) " +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                statement.setString (1, ticketnumber);
                statement.setString (2, blanktype);
                statement.setString (3, salesAmount);
                statement.setString (4, paid);
                statement.setString (5, "n");
                statement.setString (6, tax);
                statement.setString (7, customeremail);
                statement.setString (8, amountPaid);
                statement.setString (9, paymentMethod);
                statement.setString (10, commissionrate);
                statement.setString (11, daterecorded);
                statement.execute();
            }

        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    public static void createCreditcard(String email,String cardnumber,String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into atsdb.creditcard (email,cardnumber,ticketnumber) " +
                    " values (?, ?, ?);");
            statement.setString (1, email);
            statement.setString (2, cardnumber);
            statement.setString (3, ticketnumber);
            statement.execute();

        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }



    public static void soldBlank(String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET status = 'sold' \n" +
                    "WHERE ticketnumber = '" + ticketnumber + "' AND status = 'assigned';");
        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    public static void refundSale(String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.sales \n" +
                    "SET refunded = 'y' \n" +
                    "WHERE ticketnumber = '" + ticketnumber + "';");
        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }


    public static String getFixedDiscount(String customeremail) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT fixed_rate " +
                    "    FROM atsdb.customerdetails, atsdb.discount, atsdb.fixed\n" +
                    "    WHERE customerdetails.iddiscount = discount.iddiscount AND fixed.iddiscount = discount.iddiscount AND customerdetails.email = '"+ customeremail +"';");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFlexibleDiscount(String customeremail, String ticketPrice) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT MAX(flexdiscband.discount) " +
                    "FROM atsdb.customerdetails,atsdb.discount,atsdb.flexible,atsdb.flexdiscband,atsdb.fband " +
                    "WHERE customerdetails.iddiscount = discount.iddiscount " +
                    "AND discount.iddiscount = flexible.iddiscount " +
                    "AND flexible.idflexible = flexdiscband.idflexible " +
                    "AND flexdiscband.idfband = fband.idfband " +
                    "AND customerdetails.email = '"+ customeremail +"' " +
                    "AND fband.fromBand < " + ticketPrice + " " +
                    "AND fband.toBand > " + ticketPrice + ";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getString(1));
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
