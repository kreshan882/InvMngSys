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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public void setPdfParameters(EditViewSaleInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            Map pdfParaMtr = new HashMap();
            String sql = "SELECT inv.INV_ID,CAST(inv.DATE AS CHAR) AS INV_DATE,inv.TOTAL,cus.NAME,cus.EMAIL,cus.ADDRESS "
                    + "FROM ic_invoice inv,ic_customer cus where inv.CUS_ID=cus.CUS_ID AND inv.INV_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPdfinvoiceId()));
            res = perSt.executeQuery();

            if(res.next()) {
                pdfParaMtr.put("pdfInvNo", res.getString("INV_ID"));
                pdfParaMtr.put("pdfInvDate", res.getString("INV_DATE"));
                pdfParaMtr.put("pdfTotal", res.getString("TOTAL"));
                pdfParaMtr.put("pdfCusName", res.getString("NAME"));
                pdfParaMtr.put("pdfCusEmail", res.getString("EMAIL"));
                pdfParaMtr.put("pdfCusAdd", res.getString("ADDRESS"));
                inputBean.setParameterMap(pdfParaMtr);
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

    public void setPdfDataList(EditViewSaleInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        SaleItem saleItem=null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            List datalist = new ArrayList();
            String sql = "SELECT inv.ITEM_NO,ite.NAME,inv.COUNT,inv.UNIT_PRIZE,inv.TOTAL_PRIZE "
                    + "FROM ic_invoice_details inv ,ic_items ite where inv.ITEM_NO=ite.ITEM_NO AND inv.INV_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPdfinvoiceId()));
            res = perSt.executeQuery();

            while(res.next()) {
                saleItem = new SaleItem();
                    saleItem.setPdfItmNo(res.getString("ITEM_NO"));
                    saleItem.setPdfItmName(res.getString("NAME"));  
                    saleItem.setPdfItmQty(res.getString("COUNT"));
                    saleItem.setPdfItmUprize(res.getString("UNIT_PRIZE"));
                    saleItem.setPdfItmTprize(res.getString("TOTAL_PRIZE"));
                    datalist.add(saleItem);
            }
            inputBean.setReportdatalist(datalist);
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
