package SQLqueries;

import Entities.Data2;
import General.ErrorBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static SQLqueries.SQL.getConnection;

//This class uses SQL queries that are associated with customers.

public class SQLCustomers {

    //This method allows the user to create a customer account.

    public static void addCustomer(String email,String firstname,String surname, String type) throws Exception {
        try {
            Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement("insert into atsdb.customerdetails (email,firstname,surname,customertype) " +
                        " values (?, ?, ?, ?);");
                statement.setString (1, email);
                statement.setString (2, firstname);
                statement.setString (3, surname);
                statement.setString (4, type);
                statement.execute();

        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }


    //This method returns a list of bands that are in the database.

    public static ObservableList<Data2> viewBands() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT idfband,fromBand,toBand  FROM atsdb.fband;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    //This method allows the user to create a flexible discount.

    public static void createFlexiblePlan() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into atsdb.discount (type) " +
                    " values (?);");
            statement.setString (1, "2");
            statement.execute();

            statement = con.prepareStatement("insert into atsdb.flexible (iddiscount) " +
                    " values (?);");
            statement.setString (1, getDiscountID());
            statement.execute();

        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    //This method allows the user to create a new band.

    public static void createFBand(String from,String to) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into atsdb.fband (fromBand,toBand) " +
                    " values (?,?);");
            statement.setString (1, from);
            statement.setString (2, to);
            statement.execute();

        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    //This method allows the user to create new fixed plan.

    public static void createFixedPlan(String discount) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into atsdb.discount (type) " +
                    " values (?);");
            statement.setString (1, "1");
            statement.execute();

            String max = null;
            statement = con.prepareStatement("SELECT MAX(iddiscount)  FROM atsdb.discount;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                max =  result.getString(1);
            }

            statement = con.prepareStatement("insert into atsdb.fixed (iddiscount,fixed_rate) " +
                    " values (?,?);");
            statement.setString (1, max);
            statement.setString (2, discount);
            statement.execute();

        } catch (Exception e) {
            ErrorBox.display("Miss Input", "Input was not valid.");
        }
    }

    //This method returns the id of a discount.

    public static String getDiscountID() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT MAX(iddiscount)  FROM atsdb.discount;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns a string.
    //The string is a flexible id that is requested by the user.

    public static String getFlexibleID() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT MAX(idflexible)  FROM atsdb.flexible;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //This method creates a new record in the FlexDiscBand table in MySQL.

    public static void createFlexDiscBand(String bID, String discount) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("insert into atsdb.flexdiscband (idflexible,idfband,discount) " +
                    " values (?,?,?);");
            statement.setString (1, getFlexibleID());
            statement.setString (2, bID);
            statement.setString (3, discount);
            statement.execute();
        } catch (Exception e) {
        }
    }

    //This method returns a list that contains the flexible discount plan.

    public static ObservableList<Data2> showFlexibleDiscount(String iddiscount) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT discount, fromBand, toBand\n" +
                    "FROM atsdb.discount,atsdb.flexible,atsdb.flexdiscband,atsdb.fband\n" +
                    "WHERE discount.iddiscount = flexible.iddiscount AND flexible.idflexible = flexdiscband.idflexible \n" +
                    "AND fband.idfband = flexdiscband.idfband\n" +
                    "AND discount.iddiscount = "+ iddiscount +";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    //This method shows the fixed discount that the user selected.

    public static ObservableList<Data2> showFixedDiscount(String iddiscount) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT fixed_rate FROM atsdb.fixed,atsdb.discount WHERE discount.iddiscount = fixed.iddiscount AND discount.iddiscount = "+ iddiscount +";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    //This method returns a list of customer emails and discount id.

    public static ObservableList<Data2> getCustomerEmails() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT email,iddiscount FROM atsdb.customerdetails WHERE customerType = 'valued';");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1),
                        result.getString(2)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    //This method returns a list of discount ids and their type.

    public static ObservableList<Data2> getDiscount() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT iddiscount,case when type = 1 then 'Fixed' else 'Flexible' end as 'cash' FROM atsdb.discount;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1),
                        result.getString(2)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns the string.
    //The string is associated with type of discount.

    public static String getDiscountType(String iddiscount) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT type FROM atsdb.discount WHERE iddiscount = " + iddiscount +";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //This method updates the discount plan for a customer.

    public static void updateDiscountPlanForCustomer(String discount, String email) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.customerdetails \n" +
                    "SET iddiscount = " + discount + "  " +
                    "WHERE email = '" + email + "';");
        } catch (Exception e) {
        }
    }


    //This method returns a string that is the type of discount.

    public static String getDiscountType2(String email) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT type \n" +
                    "FROM atsdb.discount,atsdb.customerdetails\n" +
                    "WHERE discount.iddiscount = customerdetails.iddiscount\n" +
                    "AND email = '" + email + "';");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //This method returns a list of late payments.

    public static ObservableList<Data2> getLatePayment(int idstaff) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT customeremail \n" +
                    "FROM atsdb.sales, atsdb.blanks \n" +
                    "WHERE paid = 'n' AND dateRecorded  <= CURRENT_DATE() - INTERVAL 1 MONTH AND sales.tid = blanks.tid;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    //This method returns a list of late payments with their associated tickets.

    public static ObservableList<Data2> getLatePaymentwithTicket(int idstaff) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT customeremail , sales.blanktype, sales.ticketnumber \n" +
                    "FROM atsdb.sales, atsdb.blanks \n" +
                    "WHERE paid = 'n' AND sales.tid = blanks.tid;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    //This method returns a string that is the full price of the sale.

    public static String getsalesamountandtax(String blanktype,String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT salesamount+tax FROM atsdb.sales WHERE ticketnumber = "+ ticketnumber +" AND blanktype = "+ blanktype +";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    //This method updates late payments.

    public static void updateLateSales(String paymentamount, String method, String date,String ticketnumber,String blanktype) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.sales " +
                    "SET amountpaid = " + paymentamount + ", paid = 'y' , paymentmethod = '"+ method +"', dateRecorded = '" + date + "' " +
                    "WHERE ticketnumber = " + ticketnumber + " AND blanktype = " + blanktype + ";");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //This method updates customer accounts.

    public static void updateCustomer(String email,String blanktype,String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.customerdetails " +
                    "SET iddiscount = NULL, customerType = 'regular' " +
                    "WHERE email = '" + email + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //This method returns the coupon destinations that correspond to the selected ticket.

    public static ObservableList<Data2> getCoupons(String blanktype, String ticketnumber) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT destination FROM atsdb.coupons WHERE ticketnumber = "+ ticketnumber +" AND blanktype = "+ blanktype +";");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
