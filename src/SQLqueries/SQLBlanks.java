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
import java.util.ArrayList;
import java.util.Calendar;

import static SQLqueries.SQL.getConnection;

public class SQLBlanks {

    //This method returns the blanks that are in stock.
    //The method uses union.

    public static ObservableList<Data2> getReport1() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT  bundle ,min(ticketnumber), max(ticketnumber),blanktype, idstaff , DATE_FORMAT(receivedDate, \"%d-%m-%Y\"), DATE_FORMAT(assignedDate, \"%d-%m-%Y\")\n" +
                    "FROM atsdb.blanks where status = 'stock' group by (receivedDate)\n" +
                    "UNION\n" +
                    "SELECT  bundle,min(ticketnumber), max(ticketnumber),blanktype , idstaff , DATE_FORMAT(receivedDate, \"%d-%m-%Y\"), DATE_FORMAT(assignedDate, \"%d-%m-%Y\")\n" +
                    "FROM atsdb.blanks where status = 'assigned' group by (assignedDate);");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(result.getString(4),
                        result.getString(1),
                        String.format("%08d", Integer.parseInt(result.getString(2))),
                        String.format("%08d", Integer.parseInt(result.getString(3))),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)
                );
                table.add(data);
            }
            con.close();
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns the staff members who are either a manager or a travel advisor.

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

    //This method allows the user to assign blanks to the other staff members.
    //This uses a update query.

    public static void assignBlank(String idstaff, String bundle, int from, int to, String time) throws Exception {
        try {
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            Calendar calobj = Calendar.getInstance();
            Connection con = getConnection();
            Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                        "SET idstaff = '" + String.format("%03d", Integer.parseInt(idstaff)) + "' , status = 'assigned' , assignedDate = '"+ df.format(calobj.getTime()) +"' \n" +
                        "WHERE bundle = '" + bundle + "' AND (status = 'stock' OR status = 'assigned') AND  ticketnumber >= " + from + " AND ticketnumber < " + to + " AND blanktype = " + time + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method unassigns blanks from the staff.

    public static void unassignBlank(String bundle, String blanktype, String from, String to) throws Exception {
        try {
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            Calendar calobj = Calendar.getInstance();
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET idstaff = '' , status = 'stock', assignedDate = NULL \n" +
                    "WHERE bundle = '" + bundle + "' AND blanktype = " + blanktype + " AND ticketnumber >= " + from + " AND ticketnumber <= " + to + " AND status = 'assigned';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //This table returns the blanks that are assigned to the staff member who requested it.

    public static ObservableList<Data2> salesTable(String idstaff) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT blanktype, min(ticketnumber), max(ticketnumber) FROM atsdb.blanks WHERE status = 'assigned' and idstaff = " + idstaff + " GROUP BY receivedDate;");
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


    //This method returns customer details.

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


    //This method voids blanks.
    //This uses a update query.

    public static void voidBlank(String ticketnumber, String blanktype) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET status = 'void' \n" +
                    "WHERE ticketnumber = '" + ticketnumber + "' AND blanktype = " + blanktype + " AND status = 'assigned';");
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    //This method allows the user to report a sale.

public static void reportSales(String ticketnumber,String blanktype,String salesAmount, String paid, String tax,String exchangerate, String customeremail, String amountPaid, String paymentMethod, String daterecorded) throws Exception {
        try {

            //Get connection to the database.

            Connection con = getConnection();

            //This statement gets the current commission rate of the blank type.

            PreparedStatement statement1 = con.prepareStatement("SELECT commissionrate FROM atsdb.commissions WHERE blanktype = '"+ blanktype +"';");
            ResultSet result = statement1.executeQuery();
            String commissionrate = "0";

            //Storing the current commission rate of the blank type as a variable.

            while (result.next()) {
                commissionrate =  result.getString(1);
            }

            //Getting the ticket id of the blank.

            statement1 = con.prepareStatement("SELECT tid FROM atsdb.blanks WHERE blanktype = '"+ blanktype +"' AND ticketnumber = "+ ticketnumber +";");
            result = statement1.executeQuery();
            String tid = "0";

            //Storing the ticket id as a variable.

            while (result.next()) {
                tid =  result.getString(1);
            }


            //Adding the ticket to the database with the information provided by the user.

            //This if statement is used for interline tickets as they have exchange rates that need to be added.

            if (blanktype.equals("440") || blanktype.equals("420") || blanktype.equals("444") || blanktype.equals("451") || blanktype.equals("452")) {
                PreparedStatement statement = con.prepareStatement("insert into atsdb.sales (ticketnumber, blanktype, salesamount, paid, refunded, tax, exchangerate,customeremail,amountPaid,paymentMethod,commissionrate,daterecorded,tid) " +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
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
                statement.setString (13, tid);
                statement.execute();
            } else {

                //This else statement is used for domestic sales.
                //We do not need to store the current exchange rate in the database for a domestic sale record.


                PreparedStatement statement = con.prepareStatement("insert into atsdb.sales (ticketnumber, blanktype, salesamount, paid, refunded, tax,customeremail,amountPaid,paymentMethod,commissionrate,daterecorded,tid) " +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");
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
                statement.setString (12, tid);
                statement.execute();
            }

        } catch (Exception e) {
        }
    }


    //This method allows the user to add credit card details to the database.

    public static void createCreditcard(String email,String cardnumber,String ticketnumber,String blanktype) throws Exception {
        try {

            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT tid FROM atsdb.blanks WHERE blanktype = '"+ blanktype +"' AND ticketnumber = "+ ticketnumber +";");
            ResultSet result = statement.executeQuery();
            String tid = "0";
            while (result.next()) {
                tid =  result.getString(1);
            }

            statement = con.prepareStatement("insert into atsdb.creditcard (email,cardnumber,ticketnumber) " +
                    " values (?, ?, ?);");
            statement.setString (1, email);
            statement.setString (2, cardnumber);
            statement.setString (3, tid);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //This updates the blanks status.
    //The status changes from assigned to sold.

    public static void soldBlank(String ticketnumber,String blanktype) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.blanks \n" +
                    "SET status = 'sold' \n" +
                    "WHERE ticketnumber = '" + ticketnumber + "' AND status = 'assigned' and blanktype = '"+ blanktype +"';");
        } catch (Exception e) {
        }
    }


    //This method updates the sale to being refunded.

    public static void refundSale(String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.sales \n" +
                    "SET refunded = 'y' \n" +
                    "WHERE ticketnumber = '" + ticketnumber + "';");
        } catch (Exception e) {
        }
    }


    //This method fetches the fixed discount that is associated with the customer detail that is a parameter.

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


    //This method fetches the flexible discount that is associated with the customer detail that is a parameter.

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


    //This method updates the exchange rate in the database.
    //Record a new exchange rate.

    public static void updateExchangeRate(String exchangerate) throws Exception {
        try {
            Connection con = getConnection();
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            Calendar calobj = Calendar.getInstance();

            PreparedStatement statement = con.prepareStatement("insert into atsdb.exchangerate (exchangerate) " +
                    " values (?);");
            statement.setString (1, exchangerate);
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method fetches the exchange rate.

    public static String getExchangeRate() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT idexchangerate,exchangerate " +
                    "    FROM atsdb.exchangerate ORDER BY idexchangerate DESC " +
                    "    ;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(2);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    //This method adds the destination of each coupon to the ticket.

    public static void addCoupons(ArrayList<String> destinations, String blanktype, String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("insert into atsdb.coupons (blanktype,ticketnumber,destination) " +
                    " values (?,?,?);");

            for (int i = 0 ; i < destinations.size(); i++) {
                if (!destinations.get(i).equals("")) {
                    statement.setString (1, blanktype);
                    statement.setString (2, ticketnumber);
                    statement.setString (3, destinations.get(i));
                    statement.execute();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
