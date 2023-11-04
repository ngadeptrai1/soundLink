/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.services;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;

public class Connection {
    private static final String DB_SERVER = "localhost";
    private static final String DB_NAME = "DUAN1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "123";

    private static java.sql.Connection conn;

    private Connection() {
    }

    public static java.sql.Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String strConn = String.format("jdbc:sqlserver://%s;DatabaseName=%s;TrustServerCertificate=true;",
                    DB_SERVER, DB_NAME);
            conn = DriverManager.getConnection(strConn, DB_USERNAME, DB_PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
            conn = null;
        }

        return conn;
    }
    public static void main(String[] args) {
        System.out.println(Connection.getConnection());
    }
}
