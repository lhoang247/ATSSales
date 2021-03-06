package SQLqueries;

import Entities.Data2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static SQLqueries.SQL.getConnection;

//This class is used for the SQL queries that involve commissions.

public class SQLCommission {

    //This method returns the blank type and commissions that are associated with a specific travel agent.

    public static ObservableList<Data2> getTypeAndCommission(String travel) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM atsdb.commissions,atsdb.travelagents WHERE commissions.idtravelagents = travelagents.idtravelagents AND travelagentsName = '"+ travel +"';");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(String.format(result.getString(2)),
                        result.getString(3)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    //This returns a ComboBox that contains all the travel agents that are associated with the company.

    public static ComboBox<String> getTravelAgents() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM atsdb.travelagents;");
            ComboBox agentBox = new ComboBox();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                agentBox.getItems().add(result.getString(2));
            }
            return agentBox;
        } catch (Exception e) {
            return null;
        }
    }

    //This method updates commission rates.

    public static void updateCommission(String type, String commission, String travelagent) throws Exception {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE atsdb.commissions \n" +
                    "SET commissionrate = " + commission + "  " +
                    "WHERE blanktype = '" + type + "' AND idtravelagents = (SELECT idtravelagents FROM atsdb.travelagents WHERE travelagentsName = '"+ travelagent +"') ;");
        } catch (Exception e) {
        }
    }
}
