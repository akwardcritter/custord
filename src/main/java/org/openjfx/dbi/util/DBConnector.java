package org.openjfx.dbi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String url = "jdbc:mysql://localhost:3306/classicmodels?user=annie&password=1234";
    // jdbc:mysql://[host][,failoverhost...]
    // [:port]/[database]
    // [?propertyName1][=propertyValue1]
    // [&propertyName2][=propertyValue2]

    public static Connection connect() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url);

        System.out.println("Connected to database");
        return conn;
    }
}
