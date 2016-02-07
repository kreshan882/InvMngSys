/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.supp.service;

import com.org.cust.service.*;
import com.inv.db.DBConnection;
import com.org.supp.bean.SupplierBeen;
import com.org.supp.bean.ViewSupplierInputBean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tharaka
 */
public class ViewSupplierService {
    
  
    public   List<SupplierBeen> loadUsers( ViewSupplierInputBean bean,String orderBy, int max, int first )throws Exception{
    
        
        List <SupplierBeen> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_supplier WHERE NAME LIKE ?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setString(1, "%"+bean.getSuppName()+"%"); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT SUP_ID,NAME,EMAIL,ADDRESS,TP_OFFICE,TP_MOBILE,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE "
                    + "FROM ic_supplier WHERE NAME LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+bean.getSuppName()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<SupplierBeen>();
             while (res.next()) {
                 
                 SupplierBeen been = new SupplierBeen();

                 been.setSuppId(res.getString("SUP_ID")); 
                 been.setSuppName(res.getString("NAME"));
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
