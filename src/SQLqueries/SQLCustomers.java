package SQLqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
