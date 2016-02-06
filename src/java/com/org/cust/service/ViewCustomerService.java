/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.cust.service;

import com.inv.db.DBConnection;
//import com.inv.init.AppType;
import com.org.cust.bean.ViewCustomerInputBean;
import com.org.cust.bean.CustomerBeen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tharaka
 */
public class ViewCustomerService {
    
  
    public   List<CustomerBeen> loadUsers( ViewCustomerInputBean bean,String orderBy, int max, int first )throws Exception{
    
        
        List <CustomerBeen> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_customer WHERE NAME LIKE ?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setString(1, "%"+bean.getCustName()+"%"); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT CUS_ID,NAME,EMAIL,ADDRESS,TP_OFFICE,TP_MOBILE,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE "
                    + "FROM ic_customer WHERE NAME LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+bean.getCustName()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<CustomerBeen>();
             while (res.next()) {
                 
                 CustomerBeen been = new CustomerBeen();

                 been.setCustId(res.getString("CUS_ID")); 
                 been.setCustName(res.getString("NAME"));
                 been.setEmail(res.getString("EMAIL"));
                 been.setAddress(res.getString("ADDRESS"));
                 been.setTpOffice(res.getString("TP_OFFICE"));
                 been.setTpMobile(res.getString("TP_MOBILE"));
                 been.setStatusCode(res.getString("STATUS"));
                 been.setRegDate(res.getString("REG_DATE"));             
                 
                 been.setFullCount(totalCount);                 
                 dataList.add(been);
                      
                }
            con.commit();
         
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally{
            if (perSt != null) {
                perSt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return dataList;
    }
    
        
    
}
