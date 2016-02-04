/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inv.util;

 
import com.inv.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author tharaka
 */
public class DBProcesses {
    
      
    private static PreparedStatement addRecordStatement = null;


   
    public static void insertHistoryRecord(int userid, String module, String operation, String message, String ip) throws Exception{
        Connection con=null;
         try {
             con=DBConnection.getConnection();
             con.setAutoCommit(false);
             String qury="INSERT INTO ic_history(USER_ID,MODULE_ID,OPERATION_ID,MESSAGE,IP) "
                     + "VALUES(?,?,?,?,?)"; 
             addRecordStatement=con.prepareStatement(qury);
          
             addRecordStatement.setInt(1, userid);
             addRecordStatement.setString(2, module);
             addRecordStatement.setString(3, operation);
             addRecordStatement.setString(4, message);
             addRecordStatement.setString(5, ip);
             
             addRecordStatement.executeUpdate();
             con.commit();
        } catch (Exception e) {             
            throw e;
        } finally {
            if(addRecordStatement!=null){
                    addRecordStatement.close();
             }
             
             if(con!=null){
                 con.close();
             }
        }
        
    }
    
}
