/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.service;

import com.inv.db.DBConnection;
import com.inv.init.Status;
import com.inv.util.Util;
import com.org.sap.bean.AddSaleInputBeen;
import com.org.sap.bean.SaleItem;
import java.sql.Connection;
import java.sql.Date;
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
            perSt.setInt(1, Integer.parseInt(inputBean.getInvoiceId())); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT INV_ID,ITEM_NO,COUNT,UNIT_PRIZE,TOTAL_PRIZE FROM ic_invoice_details WHERE INV_ID=?"
                    + " "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setInt(1, Integer.parseInt(inputBean.getInvoiceId())); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<SaleItem>();
             while (res.next()) {
                 
                 SaleItem been = new SaleItem();
                 been.setInvId(res.getString("INV_ID"));
                 been.setItemNo(res.getString("ITEM_NO"));
                 been.setCount(res.getString("COUNT"));
                 been.setUnitPrize(res.getString("UNIT_PRIZE"));
                 been.setTotalPrize(res.getString("TOTAL_PRIZE"));
                 
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



    public boolean checkInvoiceId(String invoiceId) throws Exception{
        boolean isAlready=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT * FROM ic_invoice where INV_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(invoiceId));
            res = perSt.executeQuery();

            if(res.next()) {
               isAlready=true;
            }
            
        } catch (Exception ex) {
            isAlready=false;
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
        return isAlready;
    }
    
    
       
    public boolean addInvoice(AddSaleInputBeen inputBean) throws Exception{
        PreparedStatement perSt = null;
        Connection con = null;
        boolean ok=false;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO ic_invoice(INV_ID,CUS_ID,STOR_ID,STATUS,DATE) "
                    + "VALUES(?,?,?,?,?)";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getInvoiceId()));
            perSt.setInt(2, Integer.parseInt(inputBean.getCustId()));
            perSt.setInt(3, Integer.parseInt(inputBean.getStorId()));
            perSt.setString(4, Status.PENDING);
            perSt.setDate(5, (Date) Util.getLocalDate());
            int n= perSt.executeUpdate();
            if(n >= 0){
                ok=true;
            }
            con.commit();
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (perSt != null) {
                perSt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ok;
    }

    public boolean addInvoiceDetail(AddSaleInputBeen inputBean) throws Exception{
        PreparedStatement perSt = null;
        Connection con = null;
        boolean ok=false;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO ic_invoice_details(INV_ID,ITEM_NO,COUNT,UNIT_PRIZE,TOTAL_PRIZE) "
                    + "VALUES(?,?,?,?,?)";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getInvoiceId()));
            perSt.setString(2, inputBean.getItemCode().trim());
            perSt.setDouble(3, Double.parseDouble(inputBean.getItemQut()));
            perSt.setDouble(4, Double.parseDouble(inputBean.getItemPrize()));
            perSt.setDouble(5, Double.parseDouble(inputBean.getItemQut()) * Double.parseDouble(inputBean.getItemPrize()) );
            int n= perSt.executeUpdate();
            if(n >= 0){
                ok=true;
            }
            con.commit();
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (perSt != null) {
                perSt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ok;
    }

    public void deleteSelectItem(AddSaleInputBeen inputBean) throws Exception{
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "DELETE  FROM ic_invoice_details where INV_ID=? and ITEM_NO=? ";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1,  Integer.parseInt(inputBean.getDinvoNo()));
            perSt.setString(2,  inputBean.getDitemNo().trim());
            perSt.execute();
            con.commit();
            
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
}
