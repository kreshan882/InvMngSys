/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sap.service;

import com.inv.db.DBConnection;
import com.inv.init.Status;
import com.org.sap.bean.EditViewPurchesInputBean;
import com.org.sap.bean.PurchesItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan88
 */
public class EditViewPurchesService {
    
    public List<PurchesItem> loadData(EditViewPurchesInputBean inputBean, String orderBy, int max, int first )throws Exception{
    
        
        List <PurchesItem> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_purchase WHERE PUR_ID LIKE ?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setString(1, "%"+inputBean.getPurNo()+"%"); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT pur.PUR_ID,sup.NAME AS SUPNAME,str.STOR_ID,str.NAME AS STRNAME,pur.STATUS,CAST(TOTAL AS CHAR) AS TOTAL ,CAST(DATE AS CHAR) AS PURCH_DATE  "
                    + "FROM ic_purchase pur,ic_supplier sup,mt_store str  "
                    + "where pur.SUP_ID=sup.SUP_ID AND pur.STOR_ID=str.STOR_ID AND pur.PUR_ID LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+inputBean.getPurNo()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<PurchesItem>();
             while (res.next()) {
                 
                 PurchesItem been = new PurchesItem();
                 been.setEvpurId(res.getString("PUR_ID"));
                 been.setEvsupName(res.getString("SUPNAME"));
                 been.setEvstorId(res.getString("STOR_ID"));
                 been.setEvstorName(res.getString("STRNAME"));
                 been.setEvstatus(res.getString("STATUS"));
                 been.setEvtotal(res.getString("TOTAL"));
                 been.setEvpurDate(res.getString("PURCH_DATE"));
                 
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
    
       public void setPdfParameters(EditViewPurchesInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            Map pdfParaMtr = new HashMap();
            String sql = "SELECT pur.PUR_ID,CAST(pur.DATE AS CHAR) AS PUR_DATE,pur.TOTAL,sup.NAME,sup.EMAIL,sup.ADDRESS "
                    + " FROM ic_purchase pur,ic_supplier sup where pur.SUP_ID=sup.SUP_ID AND pur.PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPdfpurchId()));
            res = perSt.executeQuery();

            if(res.next()) {
                pdfParaMtr.put("pdfInvNo", res.getString("PUR_ID"));
                pdfParaMtr.put("pdfInvDate", res.getString("PUR_DATE"));
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

    public void setPdfDataList(EditViewPurchesInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        PurchesItem saleItem=null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            List datalist = new ArrayList();
            String sql = "SELECT inv.ITEM_NO,ite.NAME,inv.COUNT,inv.UNIT_COST,inv.UNIT_PRIZE,inv.TOTAL_PRIZE "
                    + "FROM ic_purchase_details inv ,ic_items ite where inv.ITEM_NO=ite.ITEM_NO AND inv.PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPdfpurchId()));
            res = perSt.executeQuery();

            while(res.next()) {
                saleItem = new PurchesItem();
                    saleItem.setPdfItmNo(res.getString("ITEM_NO"));
                    saleItem.setPdfItmName(res.getString("NAME"));  
                    saleItem.setPdfItmQty(res.getString("COUNT"));
                    saleItem.setPdfItmUcost(res.getString("UNIT_COST"));
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

    public boolean rollbackStock(EditViewPurchesInputBean inputBean) throws Exception{
        boolean qtySucess=false;
        Connection con = null;
        double total=0;
        PreparedStatement perSt = null;
        ResultSet res = null;
        String sql=null;
        
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            sql = "update ic_purchase SET STATUS=? where PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, Status.RETURN);
            perSt.setInt(2, Integer.parseInt(inputBean.getPurNo()));
            perSt.executeUpdate();
            con.commit();

            //update Stock
            perSt = null;
            res = null;
            sql=null;

            sql = "UPDATE ic_stock AS st INNER JOIN ic_purchase_details AS pur  ON st.ITEM_NO=pur.ITEM_NO  "
                    + "SET st.COUNT=(st.COUNT - pur.COUNT) "
                    + "WHERE  st.STOR_ID=? and pur.PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getStorNo()));
            perSt.setInt(2, Integer.parseInt(inputBean.getPurNo()));
            int n= perSt.executeUpdate();
            if(n >= 0){
                qtySucess=true;
            }
            con.commit();
        } catch (Exception ex) {
            qtySucess=false;
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
        return qtySucess;
    }
}