/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.service;

import com.inv.db.DBConnection;
import com.org.sap.bean.EditViewSaleInputBean;
import com.org.sap.bean.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KRESHAN
 */
public class EditViewSaleService {


    public List<SaleItem> loadUsers(EditViewSaleInputBean inputBean, String orderBy, int max, int first )throws Exception{
    
        
        List <SaleItem> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_invoice WHERE INV_ID LIKE ?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setString(1, "%"+inputBean.getInvNo()+"%"); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT inv.INV_ID,cus.NAME AS CNAME,str.NAME AS SNAME,inv.STATUS,CAST(TOTAL AS CHAR) AS TOTAL ,CAST(DATE AS CHAR) AS SALE_DATE "
                    + "FROM ic_invoice inv,ic_customer cus,mt_store str "
                    + "where inv.CUS_ID=cus.CUS_ID AND inv.STOR_ID=str.STOR_ID AND inv.INV_ID LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+inputBean.getInvNo()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<SaleItem>();
             while (res.next()) {
                 
                 SaleItem been = new SaleItem();
                 been.setEvinvId(res.getString("INV_ID"));
                 been.setEvcusName(res.getString("CNAME"));
                 been.setEvstorName(res.getString("SNAME"));
                 been.setEvstatus(res.getString("STATUS"));
                 been.setEvtotal(res.getString("TOTAL"));
                 been.setEvsaleDate(res.getString("SALE_DATE"));
                 
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
