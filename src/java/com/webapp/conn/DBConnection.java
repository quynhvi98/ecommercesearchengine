/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author viquy
 */
public class DBConnection {
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        
        String hostName = "localhost";
        String sqlInstanceName = "MSSQLSERVER";
        String database = "mytest";
        String userName = "sa";
        String password = "123456";
 
        return getDBConnection(hostName, sqlInstanceName, database, userName, password);
    }

    private static Connection getDBConnection(String hostName,String sqlInstanceName, String database, String userName, String password)//
            throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" //
                + ";instance=" + sqlInstanceName + ";databaseName=" + database;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
