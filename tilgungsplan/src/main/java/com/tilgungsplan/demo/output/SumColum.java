package com.tilgungsplan.demo.output;

import java.sql.*;

public class SumColum {

    public double sumElement(String column, Statement statement) throws  SQLException {
    double sum = 0;
    String sql = "SELECT round(SUM(" + column + "),2) FROM TILGUNGSPLAN";
    if (column == "RESTSCHULD"){
        sql = "SELECT TOP 1 RESTSCHULD FROM TILGUNGSPLAN ORDER BY id DESC";
    }
    ResultSet res = statement.executeQuery(sql);
		while (res.next()) {
        double c = res.getDouble(1);
        sum = sum + c;
    }
    if (column == "RATE" || column == "TILGUNG"){
        res = statement.executeQuery("SELECT " + column +" FROM TILGUNGSPLAN WHERE ID=1");
        while (res.next()) {
            double c = res.getDouble(1);
            sum = sum - c;
        }
    }
    return sum;
    }
}
