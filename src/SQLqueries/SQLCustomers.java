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

public class SQLCustomers {

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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

    public static ObservableList<Data2> getCustomerEmails() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT email FROM atsdb.customerdetails WHERE customerType = 'valued';");
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

    public static ObservableList<Data2> getDiscount() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT iddiscount FROM atsdb.discount;");
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

    public static void updateDiscountPlanForCustomer(String discount, String email) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.customerdetails \n" +
                    "SET iddiscount = " + discount + "  " +
                    "WHERE email = '" + email + "';");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

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
}
