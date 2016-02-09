/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.service;

import com.inv.db.DBConnection;
import com.inv.init.Status;
import com.org.sap.bean.AddSaleInputBeen;
import com.org.sap.bean.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kreshan
 */
public class AddSaleService {

    public void getInvoiceNumber(AddSaleInputBeen inputBean) throws Exception{
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT max(INV_ID)+1 AS NEXT_INV_ID FROM ic_invoice";
            perSt = con.prepareStatement(sql);
            res = perSt.executeQuery();

            if(res.next()) {
               inputBean.setInvoiceId(res.getString("NEXT_INV_ID"));
            }
            
        } catch (Exception ex) {
            throw ex;
        } finally {
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
    }

    public boolean getItemDetail(AddSaleInputBeen inputBean) throws Exception{
        boolean result=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT NAME,UNIT_PRIZE FROM ic_items where ITEM_NO=?";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, inputBean.getItemCode().trim());
            res = perSt.executeQuery();

            if(res.next()) {
               inputBean.setItemName(res.getString("NAME"));
               inputBean.setItemPrize(res.getString("UNIT_PRIZE"));
               result=true;
            }
            
        } catch (Exception ex) {
            result=false;
            throw ex;
        } finally {
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
        return result;
    }

    public List<SaleItem> loadTableData(AddSaleInputBeen inputBean, String orderBy, int max, int first) throws Exception{
        
        List <SaleItem> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_invoice_details WHERE INV_ID=?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setInt(1, 1); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT ITEM_NO,COUNT,UNIT_PRIZE,TOTAL_PRIZE FROM ic_invoice_details WHERE INV_ID=?"
                    + " "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setInt(1, 1); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<SaleItem>();
             while (res.next()) {
                 
                 SaleItem been = new SaleItem();

                 been.setItemNo(res.getString("ITEM_NO"));
                 been.setCount(res.getString("COUNT"));
                 been.setUnitPrize(res.getString("UNIT_PRIZE"));
                 been.setTotalPrize(res.getString("UNIT_PRIZE"));
                 
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
