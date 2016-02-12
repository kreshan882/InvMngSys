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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan
 */
public class AddSaleService {

    public void getNextInvoiceNumber(AddSaleInputBeen inputBean) throws Exception{
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

    public boolean checkItemQtyAvaliable(AddSaleInputBeen inputBean) throws Exception{
        boolean QtyAvaliable=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT COUNT FROM ic_stock where STOR_ID=? AND ITEM_NO=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getStorId()));
            perSt.setString(2, inputBean.getItemCode());
            res = perSt.executeQuery();

            if(res.next()) {
                if(res.getDouble("COUNT") >= Double.parseDouble(inputBean.getItemQut())){
                    QtyAvaliable=true;
                }
               
            }
            
        } catch (Exception ex) {
            QtyAvaliable=false;
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
        return QtyAvaliable;
    }

    public boolean submitInvoice(AddSaleInputBeen inputBean) throws Exception{
        boolean qtySucess=false;
        Connection con = null;
        double total=0;
        System.out.println("cal for strouck update..........."+inputBean.getInvoiceId()+":"+inputBean.getStorId());
        PreparedStatement perSt = null;
        ResultSet res = null;
        String sql=null;
        
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            //get total
            sql = "SELECT SUM(TOTAL_PRIZE) AS TOTAL_PRIZE  FROM ic_invoice_details where INV_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getInvoiceId()));
            res = perSt.executeQuery();

            if(res.next()) {
                total=res.getDouble("TOTAL_PRIZE");
            }
            //Upsate invoice table
            perSt = null;
            res = null;
            sql=null;
            sql = "update ic_invoice SET STATUS=?,TOTAL=? where INV_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setString(1, Status.ACTIVE);
            perSt.setDouble(2, total);
            perSt.setInt(3, Integer.parseInt(inputBean.getInvoiceId()));
            perSt.executeUpdate();
            con.commit();

            //update Stock
            perSt = null;
            res = null;
            sql=null;
//            select * from ic_stock st right join ic_invoice_details inv 
//            on st.STOR_ID=1 and inv.INV_ID='2' and st.ITEM_NO=inv.ITEM_NO;
            sql = "UPDATE ic_stock AS st INNER JOIN ic_invoice_details AS inv  ON st.ITEM_NO=inv.ITEM_NO "
                    + " SET st.COUNT=(st.COUNT - inv.COUNT) "
                    + " WHERE st.ITEM_NO=inv.ITEM_NO AND st.STOR_ID=? and inv.INV_ID=?";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getStorId()));
            perSt.setInt(2, Integer.parseInt(inputBean.getInvoiceId()));
            perSt.executeUpdate();
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

    public void setPdfParameters(AddSaleInputBeen inputBean) throws Exception{
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

    public void setPdfDataList(AddSaleInputBeen inputBean) throws Exception{
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

    public boolean checkItemalready(AddSaleInputBeen inputBean) throws Exception{
        boolean isAlready=false;
        PreparedStatement perSt = null;
        ResultSet res = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String sql = "SELECT * FROM ic_invoice_details where INV_ID=? AND ITEM_NO=?;";
            perSt = con.prepareStatement(sql);
            perSt.setInt(1, Integer.parseInt(inputBean.getInvoiceId()));
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
}
