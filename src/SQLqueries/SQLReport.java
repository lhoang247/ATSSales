package SQLqueries;

import Entities.Data;
import Entities.Data2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static SQLqueries.SQL.getConnection;

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
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }

    public static ObservableList<Data> getReport7(int type, int staffID) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data> table = FXCollections.observableArrayList();
            PreparedStatement statement;
            if (type == 0) {
                statement = con.prepareStatement("SELECT  blanks.idstaff, count(*),sum(sales.salesamount),sum(sales.tax) ,(sum(sales.salesamount) + sum(sales.tax))\n" +
                        "FROM atsdb.sales, atsdb.blanks where (sales.blanktype = 440 OR sales.blanktype = 444 OR sales.blanktype = 420  OR sales.blanktype = 451 OR sales.blanktype = 452) and sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber\n" +
                        "group by blanks.bundle;");
            } else {
                statement = con.prepareStatement("SELECT  sales.blanktype,sales.ticketnumber,salesamount/exchangerate,exchangerate, salesamount, tax, (salesamount+tax)\n" +
                        "FROM atsdb.sales , atsdb.blanks\n" +
                        "WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420  OR sales.blanktype = 451 OR sales.blanktype = 452) AND sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = "+ staffID +" AND blanks.status = 'sold' AND sales.refunded != 'y';");
            }

            ResultSet result = statement.executeQuery();
            Data data;
            while (result.next()) {
                if (type == 0) {
                    data = new Data(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            ""
                    );
                } else {
                    data = new Data(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6),
                            result.getString(7)
                    );
                }
                table.add(data);

            }
            return table;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Data2> getReport8(int type, int staffID) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement;
            if (type == 0) {
                statement = con.prepareStatement("SELECT sales.paymentmethod ,\n" +
                        "                        case when sales.paymentmethod = 'cash' then sum(amountPaid) else '' end as 'cash',\n" +
                        "                        case when sales.paymentmethod = 'card' then sum(amountPaid) else '' end as 'card', \n" +
                        "                        case when sales.paymentmethod = 'card' then \n" +
                        "\t\t\t\t\t\t(SELECT cardnumber \n" +
                        "                        FROM atsdb.creditcard \n" +
                        "                        WHERE creditcard.email = sales.customeremail and creditcard.ticketnumber = sales.ticketnumber) else ''\n" +
                        "                        end as 'Card number', \n" +
                        "                        sum(amountPaid)\n" +
                        "                        FROM atsdb.sales, atsdb.blanks\n" +
                        "                        WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420 OR sales.blanktype = 451 OR sales.blanktype = 452)  and sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber\n" +
                        "                        group by blanks.bundle;"
                );
            } else {
                statement = con.prepareStatement("SELECT sales.paymentmethod , case when paymentmethod = 'cash' then amountPaid else '' end as 'cash',\n" +
                        "                        case when paymentmethod = 'card' then amountPaid else '' end as 'card',\n" +
                        "                        case when paymentmethod = 'card' then \n" +
                        "                       (SELECT cardnumber \n" +
                        "                        FROM atsdb.creditcard \n" +
                        "                        WHERE creditcard.email = sales.customeremail and creditcard.ticketnumber = sales.ticketnumber) else ''\n" +
                        "                        end as 'Card number',\n" +
                        "                        amountPaid\n" +
                        "                        FROM atsdb.sales , atsdb.blanks\n" +
                        "                        WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420  OR sales.blanktype = 451 OR sales.blanktype = 452) AND sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = " + staffID + " AND blanks.status = 'sold' and sales.refunded != 'y';"
                );
            }

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
            return table;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> getUniqueCommissions(int type, int staffID) throws Exception {
        try {
            ArrayList<String> data = new ArrayList<>();
            Connection con = getConnection();

            PreparedStatement statement;
            if (type == 0) {
                statement = con.prepareStatement("SELECT DISTINCT(commissionrate)  \n" +
                        "FROM atsdb.sales WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420  OR sales.blanktype = 451 OR sales.blanktype = 452) ORDER BY commissionrate;");
            } else if (type == 1){
                statement = con.prepareStatement("SELECT DISTINCT(commissionrate)\n" +
                        "FROM atsdb.sales, atsdb.blanks WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420  OR sales.blanktype = 451 OR sales.blanktype = 452) AND\n" +
                        "sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = " + staffID + " AND blanks.status = 'sold' and sales.refunded != 'y' \n" +
                        " ORDER BY commissionrate;");
            } else if (type == 2) {
                statement = con.prepareStatement("SELECT DISTINCT(commissionrate)  \n" +
                        "FROM atsdb.sales WHERE (sales.blanktype = 201 OR sales.blanktype = 101) ORDER BY commissionrate;");
            } else {
                statement = con.prepareStatement("SELECT DISTINCT(commissionrate)\n" +
                        "FROM atsdb.sales, atsdb.blanks WHERE (sales.blanktype = 201 OR sales.blanktype = 101) AND\n" +
                        "sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = " + staffID + " AND blanks.status = 'sold' and sales.refunded != 'y' \n" +
                        " ORDER BY commissionrate;");
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                data.add(result.getString(1));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Data2> getReport9(int type, int staffID) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();

            //jank

            ArrayList<String> array = getUniqueCommissions(type, staffID);
            String sqlString;
            PreparedStatement statement;
            if (type == 0) {
                sqlString = "case when commissionrate = " + array.get(0) +  " then ROUND(sum(amountPaid) * (1 - (commissionrate/100)),2) end as 'example' ";
                for (int i = 1 ; i < array.size() ; i ++) {
                    sqlString += ", case when commissionrate = " + array.get(i) +  " then ROUND(sum(amountPaid) * (1 - (commissionrate/100)),2) end as 'example' ";
                }
                statement = con.prepareStatement("SELECT " + sqlString +" FROM atsdb.sales, atsdb.blanks" +
                        " WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420 OR sales.blanktype = 451 OR sales.blanktype = 452)  and sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber AND blanks.status = 'sold' and sales.refunded != 'y' " +
                        "group by blanks.bundle;");
            } else if (type == 1) {
                sqlString = "case when commissionrate = " + array.get(0) +  " then ROUND((amountPaid) * (1 - (commissionrate/100)), 2) end as 'example'";
                for (int i = 1 ; i < array.size() ; i ++) {
                    sqlString += ", case when commissionrate = " + array.get(i) +  " then ROUND((amountPaid) * (1 - (commissionrate/100)), 2) end as 'example' ";
                }
                statement = con.prepareStatement("SELECT " + sqlString +" FROM atsdb.sales,atsdb.blanks WHERE (sales.blanktype = 444 OR sales.blanktype = 440 OR sales.blanktype = 420  OR sales.blanktype = 451 OR sales.blanktype = 452) AND " +
                        "  sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = " + staffID + " AND blanks.status = 'sold' and sales.refunded != 'y';");
            } else if (type == 2) {
                sqlString = "case when commissionrate = " + array.get(0) +  " then ROUND(sum(amountPaid) * (1 - (commissionrate/100)),2) end as 'example'";
                for (int i = 1 ; i < array.size() ; i ++) {
                    sqlString += ", case when commissionrate = " + array.get(i) +  " then ROUND(sum(amountPaid) * (1 - (commissionrate/100)),2) end as 'example' ";
                }
                statement = con.prepareStatement("SELECT " + sqlString +" FROM atsdb.sales,atsdb.blanks WHERE (sales.blanktype = 201 OR sales.blanktype = 101) and sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber AND blanks.status = 'sold' and sales.refunded != 'y' " +
                        "group by blanks.bundle;");
            } else {
                sqlString = "case when commissionrate = " + array.get(0) +  " then ROUND((amountPaid) * (1 - (commissionrate/100)), 2) end as 'example'";
                for (int i = 1 ; i < array.size() ; i ++) {
                    sqlString += ", case when commissionrate = " + array.get(i) +  " then ROUND((amountPaid) * (1 - (commissionrate/100)), 2) end as 'example' ";
                }
                statement = con.prepareStatement("SELECT " + sqlString +" FROM atsdb.sales,atsdb.blanks WHERE (sales.blanktype = 201 OR sales.blanktype = 101) AND " +
                        "  sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = " + staffID + " AND blanks.status = 'sold' and sales.refunded != 'y';");
            }

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
            System.out.println("probably no for a table data");
            return null;
        }
    }

    public static ObservableList<Data2> getReport10(int type, int staffID) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement;
            if (type == 0) {
                statement = con.prepareStatement("SELECT  blanks.idstaff, count(*),sum(sales.salesamount),sales.tax\n" +
                        "FROM atsdb.sales, atsdb.blanks where (sales.blanktype = 201 OR sales.blanktype = 101) and sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber\n" +
                        "group by blanks.bundle;");
            } else {
                statement = con.prepareStatement("SELECT  sales.blanktype,sales.ticketnumber,sales.salesamount, sales.tax " +
                        "FROM atsdb.sales, atsdb.blanks " +
                        "WHERE (sales.blanktype = 201 OR sales.blanktype = 101) AND sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = "+ staffID +" AND blanks.status = 'sold';");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1),
                        String.format("%08d", Integer.parseInt(result.getString(2))),
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

    public static ObservableList<Data2> getReport11(int type, int staffID) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement;
            if (type == 0) {
                statement = con.prepareStatement("SELECT paymentmethod,\n" +
                        "case when paymentmethod = 'cash' then sum(amountPaid) else '' end as 'cash', \n" +
                        "case when paymentmethod = 'card' then sum(amountPaid) else '' end as 'card',\n" +
                        "case when paymentmethod = 'card' then\n" +
                        "     (SELECT cardnumber\n" +
                        "\tFROM atsdb.creditcard\n" +
                        "\tWHERE creditcard.email = sales.customeremail and creditcard.ticketnumber = sales.ticketnumber) else ''\n" +
                        "\tend as 'Card number', \n" +
                        "\tsum(amountPaid)\n" +
                        "\tFROM atsdb.sales, atsdb.blanks \n" +
                        "    WHERE (sales.blanktype = 201 OR sales.blanktype = 101) and sales.refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber " +
                        "group by blanks.bundle;");
            } else {
                statement = con.prepareStatement("SELECT paymentmethod,\n" +
                        "case when paymentmethod = 'cash' then amountPaid else '' end as 'cash', \n" +
                        "case when paymentmethod = 'card' then amountPaid else '' end as 'card',\n" +
                        "case when paymentmethod = 'card' then\n" +
                        "     (SELECT cardnumber\n" +
                        "\tFROM atsdb.creditcard\n" +
                        "\tWHERE creditcard.email = sales.customeremail and creditcard.ticketnumber = sales.ticketnumber) else ''\n" +
                        "\tend as 'Card number', \n" +
                        "\tamountPaid\n" +
                        "\tFROM atsdb.sales, atsdb.blanks" +
                        "    WHERE (sales.blanktype = 201 OR sales.blanktype = 101) AND refunded != 'y' AND sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = "+ staffID +" AND blanks.status = 'sold';");
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Data2 data = new Data2(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)
                );
                table.add(data);
            }
            return table;
        } catch (Exception e) {
            return null;
        }
    }


    public static ObservableList<Data2> getReport12(int staffID) throws Exception {
        try {
            Connection con = getConnection();
            ObservableList<Data2> table = FXCollections.observableArrayList();
            PreparedStatement statement;

            statement = con.prepareStatement("SELECT  sales.blanktype,sales.ticketnumber\n" +
                    "FROM atsdb.sales , atsdb.blanks\n" +
                    "WHERE sales.ticketnumber = blanks.ticketnumber AND blanks.idstaff = " + staffID + " AND blanks.status = 'sold' AND refunded = 'n';");


            ResultSet result = statement.executeQuery();
            Data2 data;
            while (result.next()) {

                    data = new Data2(
                            result.getString(1),
                            String.format("%08d", Integer.parseInt(result.getString(2)))
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
