/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.conn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author viquy
 */
public class ConnectionUntils {
    public static Connection getConnection() 
              throws ClassNotFoundException, SQLException {
        return DBConnection.getDBConnection();        
    }
     
    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
 
    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
