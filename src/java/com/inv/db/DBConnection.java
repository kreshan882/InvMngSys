/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inv.db;

import com.inv.init.InitConfigValue;
import com.inv.util.LogFileCreator;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import snaq.db.ConnectionPool;

/**
 *
 * @author tharaka
 */
public class DBConnection {

    public static ConnectionPool pool = null;

    public static synchronized Connection getConnection() throws Exception {
        Connection conn = null;

        try {
            conn = pool.getConnection(InitConfigValue.DBCONNECTIONTIMEOUT);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return conn;
    }

    /**
     * Close the aquired database connection
     *
     * @param conn - Database connection
     * @throws Exception
     */
    public static synchronized void dbConnectionClose(Connection conn) throws Exception {
        try {
            conn.close();
            conn=null;
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            throw ex;
        }
    }

    public static void createDbPool() throws Exception {

        Driver driver = (Driver) Class.forName(InitConfigValue.DBDRIVER).newInstance();
        DriverManager.registerDriver(driver);

        pool = new ConnectionPool("MySql",
                InitConfigValue.MINPOOL,
                InitConfigValue.MAXPOOL,
                InitConfigValue.MAXCON,
                InitConfigValue.DBCONEXPIRTIMEOUT,
                InitConfigValue.DBURL,
                InitConfigValue.DBUSERNAME,
                InitConfigValue.DBPASSWORD);

        System.out.println("Establish the database connection.... ");
        Connection con = getConnection();
        dbConnectionClose(con);
        System.out.println("Database connection is ....... passed ");

    }

}
