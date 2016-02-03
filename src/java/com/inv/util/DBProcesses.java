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
    private static ResultSet ResultSet_DateTime = null; 
    
    public static void  insertHistoryRecord(int instituteID,int userID,String appType,int appID,String module,String operation,String description,String ip)throws Exception{
        Connection con=null;
         try {
             con=DBConnection.getConnection();
             String insertHistoryRecordQuery="INSERT INTO E24OCM_HISTORY(INSTITUTE_ID,USER_ID,APP_TYPE,APP_ID,MODULE,OPERATION,DESCRIPTION,IP) VALUES (?,?,?,?,?,?,?,?)"; 
             addRecordStatement=con.prepareStatement(insertHistoryRecordQuery);
          
             addRecordStatement.setInt(1, instituteID);
             addRecordStatement.setInt(2, userID);
             addRecordStatement.setString(3, appType);
             addRecordStatement.setInt(4, appID);
             addRecordStatement.setString(5, module);
             addRecordStatement.setString(6, operation);
             addRecordStatement.setString(7, description);
             addRecordStatement.setString(8, ip);
             
             addRecordStatement.executeUpdate();
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

    public static void  insertRequestHistoryRecord_k(int sub_instituteID,String sub_appType,int sub_appID,int reqID,String reqAppType,String status,String description)throws Exception{
        Connection con=null;
         try {
             con=DBConnection.getConnection();
             String insertRequestHistoryRecordQuery="INSERT INTO E24OCM_REQUEST_HISTORY(INSTITUTE_ID,APP_TYPE,APP_ID,REQ_ID,REQ_APPTYPE,STATUS,DESCRIPTION) VALUES (?,?,?,?,?,?,?)"; 
             addRecordStatement=con.prepareStatement(insertRequestHistoryRecordQuery);
          
             addRecordStatement.setInt(1, sub_instituteID);
             addRecordStatement.setString(2, sub_appType);
             addRecordStatement.setInt(3, sub_appID);
             addRecordStatement.setInt(4, reqID);
             addRecordStatement.setString(5, reqAppType);
             addRecordStatement.setString(6, status);
             addRecordStatement.setString(7, description);
             
             addRecordStatement.executeUpdate();
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
