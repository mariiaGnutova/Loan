package com.tilgungsplan.demo.output;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TableGeneration extends SumColum{
    private Statement statement;
    public void createTable() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:~/test",
                "sa", "");
        statement = con.createStatement();
        double zinsemSum = sumElement("ZINSEN", statement);
        double restschuldResult = sumElement("RESTSCHULD", statement);
        double tilgungSum = sumElement("TILGUNG", statement);
        double rateSum = sumElement("RATE", statement);
        System.out.printf("\n%s %27s %11s %30s %7s %n", "Date", "Restschuld", "Zinsen", "Tilgung(+)/Auszahlung(-)", "Rate");
        for (int i = 1; i < 4; i++){
            System.out.printf("\n%7s %20s %13s %20s %17s %n", executeDate(i),
                    executeStatment("RESTSCHULD", i), executeStatment("ZINSEN", i),
                    executeStatment("TILGUNG", i), executeStatment("RATE", i));
        }
        System.out.printf("\n%30s", "...");

        for (int y = lastID()-1; y < lastID() + 1; y++){
            System.out.printf("\n%7s %20s %13s %20s %17s %n", executeDate(y),
                    executeStatment("RESTSCHULD", y), executeStatment("ZINSEN", y),
                    executeStatment("TILGUNG", y), executeStatment("RATE", y));
        }

        System.out.printf("\n%7s %14s %14s %20s %17s %n", "Zinsbindungsende", restschuldResult, zinsemSum, tilgungSum, rateSum);

    }

    private double executeStatment (String column, int id) throws SQLException {
        double sum = 0.0;
        ResultSet res = statement.executeQuery("SELECT " + column + " FROM TILGUNGSPLAN WHERE ID=" + id);
        while (res.next()) {
            double c = res.getDouble(1);
            sum = sum + c;
        }
        return sum;
    }
    private String executeDate (int id) throws SQLException {
        double sum = 0.0;
        String date = new String();
        ResultSet res = statement.executeQuery("SELECT DATUM FROM TILGUNGSPLAN WHERE ID=" + id);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        while (res.next()) {
            Date c = res.getDate(1);
            date = dateFormat.format(c);

        }
        return date;
    }

    private int lastID () throws SQLException {
        ResultSet res = statement.executeQuery("SELECT MAX(id) FROM TILGUNGSPLAN");
        int id = 0;
        while (res.next()) {
            id = res.getInt(1);
        }
        return id;
    }

}
