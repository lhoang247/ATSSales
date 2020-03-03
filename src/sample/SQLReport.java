package sample;

import Entities.Data;
import Entities.Data2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
                Data data = new Data(result.getString("idstaff"),
                        result.getString("blanktype"),
                        result.getString("bundle"),
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

    public static ObservableList<Data> getReport6() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT idstaff, blanktype, MIN(ticketnumber) AS \"FROM\", MAX(ticketnumber) AS \"TO\", count(*) \n" +
                    "FROM atsdb.blanks \n" +
                    "WHERE status = \"assigned\" \n" +
                    "GROUP BY bundle;\n");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(
                        result.getString("idstaff"),
                        result.getString("blanktype"),
                        result.getString(3),
                        result.getString(4),
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

    public static ObservableList<Data> getReport7() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT  blanktype,ticketnumber,salesamount,exchangerate, salesamount*exchangerate, tax, (salesamount*exchangerate+tax) " +
                    "FROM sales " +
                    "WHERE blanktype = 444 OR blanktype = 440 OR blanktype = 420;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data data = new Data(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)
                );
                System.out.println();
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    public static ObservableList<Data2> getReport8() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement = con.prepareStatement("SELECT q.paymentmethod , " +
                    "case when paymentmethod = 'cash' then salesamount*exchangerate+tax else '' end as 'cash', " +
                    "case when paymentmethod = 'card' then salesamount*exchangerate+tax else '' end as 'card', " +
                    "case when paymentmethod = 'card' then " +
                        " (SELECT cardnumber " +
                        "FROM creditcard C " +
                        "WHERE c.email = q.customeremail and c.ticketnumber = q.ticketnumber) else '' " +
                        "end as 'Card number', " +
                    "salesamount*exchangerate+tax " +
                    "FROM sales q"
            );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)
                );
                System.out.println();
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            System.out.println("erroe");
            return null;
        }
    }

    public static ArrayList<String> getUniqueCommissions() throws Exception {
        try {
            ArrayList<String> data = new ArrayList<>();
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement("SELECT DISTINCT(commissionrate)  \n" +
                    "FROM atsdb.sales ORDER BY commissionrate;");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                data.add(result.getString(1));
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }


    public static ObservableList<Data2> getReport9() throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();

            //jank

            ArrayList<String> array = getUniqueCommissions();
            String sqlString = "case when commissionrate = " + array.get(0) +  " then (salesamount*exchangerate+tax) * (1 - (commissionrate/100)) end as 'example'";
            for (int i = 1 ; i < array.size() ; i ++) {
                sqlString += ", case when commissionrate = " + array.get(i) +  " then (salesamount*exchangerate+tax) * (1 - (commissionrate/100)) end as 'example' ";
            }

            PreparedStatement statement = con.prepareStatement("SELECT " + sqlString +" FROM atsdb.sales");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = null;
                if (array.size() == 1) {
                    data = new Data2(result.getString(1)
                    );
                } else if (array.size() == 2) {
                    data = new Data2(result.getString(1),
                            result.getString(2)
                    );
                } else if (array.size() == 3) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3)
                    );
                } else if (array.size() == 4) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4)
                    );
                } else if (array.size() == 5) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5)
                    );
                } else if (array.size() == 6) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6)
                    );
                } else if (array.size() == 7) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6),
                            result.getString(7)
                    );
                } else if (array.size() == 8) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6),
                            result.getString(7),
                            result.getString(8)
                    );
                } else if (array.size() == 9) {
                    data = new Data2(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6),
                            result.getString(7),
                            result.getString(8),
                            result.getString(9)
                    );
                }
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            System.out.println("error");
            return null;
        }
    }

}
