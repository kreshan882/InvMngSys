/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.sap.service;

import com.inv.db.DBConnection;
import com.inv.init.Status;
import com.inv.util.Util;
import com.org.sap.bean.AddPurchesInputBean;
import com.org.sap.bean.PurchesItem;
import java.sql.Connection;
import java.sql.Date;
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
public class AddPurchesService {
    public void getNextPurchaseNumber(AddPurchesInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT max(PUR_ID)+1 AS NEXT_PUR_ID FROM ic_purchase";
            perSt = con.prepareStatement(sql);
            res = perSt.executeQuery();

            if(res.next()) {
               inputBean.setPurchaseId(res.getString("NEXT_PUR_ID"));
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

    public boolean getItemDetail(AddPurchesInputBean inputBean) throws Exception{
        boolean result=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        System.out.println("bbbbbb");
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT NAME FROM ic_items where ITEM_NO=?";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, inputBean.getItemCode().trim());
            res = perSt.executeQuery();

            if(res.next()) {
               inputBean.setItemName(res.getString("NAME"));
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

    public List<PurchesItem> loadTableData(AddPurchesInputBean inputBean, String orderBy, int max, int first) throws Exception{
        
        List <PurchesItem> dataList = null;       
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        String quary = null;
        long totalCount = 0;

        try {            
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String sqlCount = "SELECT COUNT(*) AS TOTAL FROM ic_purchase_details WHERE PUR_ID=?";
            
            perSt = con.prepareStatement(sqlCount);  
            perSt.setInt(1, Integer.parseInt(inputBean.getPurchaseId())); 
            res = perSt.executeQuery();
            
            while (res.next()) {
                totalCount = res.getLong("TOTAL");                
            }          
       
            quary ="SELECT PUR_ID,ITEM_NO,COUNT,UNIT_COST,UNIT_PRIZE,TOTAL_PRIZE FROM ic_purchase_details WHERE PUR_ID=?"
                    + " "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setInt(1, Integer.parseInt(inputBean.getPurchaseId())); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<PurchesItem>();
             while (res.next()) {
                 
                 PurchesItem been = new PurchesItem();
                 been.setPurId(res.getString("PUR_ID"));
                 been.setItemNo(res.getString("ITEM_NO"));
                 been.setCount(res.getString("COUNT"));
                 been.setUnitCost(res.getString("UNIT_COST"));
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



    public boolean checkPurcheaseId(String purId) throws Exception{
        boolean isAlready=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT * FROM ic_purchase where PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(purId));
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
    
    
       
    public boolean addInvoice(AddPurchesInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        Connection con = null;
        boolean ok=false;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO ic_purchase(PUR_ID,SUP_ID,STOR_ID,STATUS,DATE) "
                    + "VALUES(?,?,?,?,?)";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPurchaseId()));
            perSt.setInt(2, Integer.parseInt(inputBean.getSuppId()));
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

    public boolean addInvoiceDetail(AddPurchesInputBean inputBean) throws Exception{
        PreparedStatement perSt = null;
        Connection con = null;
        boolean ok=false;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO ic_purchase_details(PUR_ID,ITEM_NO,COUNT,UNIT_COST,UNIT_PRIZE,TOTAL_PRIZE) "
                    + "VALUES(?,?,?,?,?,?)";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPurchaseId()));
            perSt.setString(2, inputBean.getItemCode().trim());
            perSt.setDouble(3, Double.parseDouble(inputBean.getItemQut()));
            perSt.setDouble(4, Double.parseDouble(inputBean.getItemCost()));
            perSt.setDouble(5, Double.parseDouble(inputBean.getItemPrize()));
            perSt.setDouble(6, Double.parseDouble(inputBean.getItemQut()) * (Double.parseDouble(inputBean.getItemPrize())+Double.parseDouble(inputBean.getItemCost())) );
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

    public void deleteSelectItem(AddPurchesInputBean inputBean) throws Exception{
        
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "DELETE  FROM ic_purchase_details where PUR_ID=? and ITEM_NO=? ";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1,  Integer.parseInt(inputBean.getDpurNo()));
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

//    public boolean checkItemQtyAvaliable(AddPurchesInputBean inputBean) throws Exception{
//        boolean QtyAvaliable=false;
//        PreparedStatement perSt = null;
//        ResultSet res = null;
//        Connection con = null;
//        try {
//            con = DBConnection.getConnection();
//            con.setAutoCommit(false);
//
//            String sql = "SELECT COUNT FROM ic_stock where STOR_ID=? AND ITEM_NO=?";
//            perSt = con.prepareStatement(sql);
//            perSt.setInt(1, Integer.parseInt(inputBean.getStorId()));
//            perSt.setString(2, inputBean.getItemCode());
//            res = perSt.executeQuery();
//
//            if(res.next()) {
//                if(res.getDouble("COUNT") >= Double.parseDouble(inputBean.getItemQut())){
//                    QtyAvaliable=true;
//                }
//               
//            }
//            
//        } catch (Exception ex) {
//            QtyAvaliable=false;
//            throw ex;
//        } finally {
//            if (perSt != null) {
//                perSt.close();
//            }
//            if (res != null) {
//                res.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return QtyAvaliable;
//    }

    public boolean submitPurch(AddPurchesInputBean inputBean) throws Exception{
        boolean qtySucess=false;
        Connection con = null;
        double total=0;
        PreparedStatement perSt = null;
        ResultSet res = null;
        String sql=null;
        
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            //get total
            sql = "SELECT SUM(TOTAL_PRIZE) AS TOTAL_PRIZE  FROM ic_purchase_details where PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPurchaseId()));
            res = perSt.executeQuery();

            if(res.next()) {
                total=res.getDouble("TOTAL_PRIZE");
            }

            //Upsate invoice table
            perSt = null;
            res = null;
            sql=null;
            sql = "update ic_purchase SET STATUS=?,TOTAL=? where PUR_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, Status.ACTIVE);
            perSt.setDouble(2, total);
            perSt.setInt(3, Integer.parseInt(inputBean.getPurchaseId()));
            perSt.executeUpdate();
            con.commit();

            //update Stock
            perSt = null;
            res = null;
            sql=null;

            sql = "UPDATE ic_stock AS st INNER JOIN ic_purchase_details AS inv  ON st.ITEM_NO=inv.ITEM_NO "
                    + " SET st.COUNT=(st.COUNT + inv.COUNT) "
                    + " WHERE  st.STOR_ID=? and inv.PUR_ID=? ";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getStorId()));
            perSt.setInt(2, Integer.parseInt(inputBean.getPurchaseId()));
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

    public void setPdfParameters(AddPurchesInputBean inputBean) throws Exception{
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
            perSt.setInt(1, Integer.parseInt(inputBean.getPdfpurchaseId()));
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

    public void setPdfDataList(AddPurchesInputBean inputBean) throws Exception{
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
            perSt.setInt(1, Integer.parseInt(inputBean.getPdfpurchaseId()));
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

    public boolean checkItemalready(AddPurchesInputBean inputBean) throws Exception{
        boolean isAlready=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT * FROM ic_purchase_details where PUR_ID=? AND ITEM_NO=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getPurchaseId()));
            perSt.setInt(2, Integer.parseInt(inputBean.getItemCode()));
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

    public boolean addItemUnitTotalCost(AddPurchesInputBean inputBean) throws Exception {

        
        boolean ok = false;
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
           
            sql = "update ic_items SET UNIT_TCOST=? where ITEM_NO=?";
            prepSt = con.prepareStatement(sql);
            prepSt.setDouble(1, (Double.parseDouble(inputBean.getItemPrize())+Double.parseDouble(inputBean.getItemCost())) );
            prepSt.setString(2, inputBean.getItemCode());

            int n= prepSt.executeUpdate();
            if(n>0){
                ok = true;
            }
            con.commit();

        } catch (Exception e) {
            
            con.rollback();
            throw e;

        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }

        }
           return ok;
    }
}
