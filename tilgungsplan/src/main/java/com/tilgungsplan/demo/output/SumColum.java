package com.tilgungsplan.demo.output;

import java.sql.*;

public class SumColum {

    public void  sumElement() throws ClassNotFoundException, SQLException {
    double sum = 0;
    Class.forName("org.h2.Driver");
    Connection con = DriverManager.getConnection("jdbc:h2:~/test",
            "sa", "");
    Statement st = con.createStatement();
    ResultSet res = st.executeQuery("SELECT SUM(Zinsen) FROM TILGUNGSPLAN");
		while (res.next()) {
        double c = res.getInt(1);
        sum = sum + c;
    }
		System.out.println("Sum of column = " + sum);
    }
}
