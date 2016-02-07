/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.supp.service;


import com.inv.db.DBConnection;
//import com.inv.init.AppType;
import com.inv.init.Status;
import com.org.supp.bean.EditViewSupplierInputBean;
import com.org.supp.bean.SupplierBeen;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tharaka
 */
public class EditViewSupplierService {


    public List<SupplierBeen> loadTableData(EditViewSupplierInputBean bean, String orderBy, int max, int first) throws Exception {

        
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
    
    

    public boolean deleteData(EditViewSupplierInputBean bean) throws Exception {

        boolean result=false;
        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "delete from ic_supplier where SUP_ID=?";
            prepSt = con.prepareStatement(deleteUser);

            prepSt.setInt(1,  Integer.parseInt(bean.getDsuppId()));
            prepSt.execute();
            con.commit();
            result=true;

        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            result=false;
            throw e;

        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
//Added on 22nd Aug to find user.

     public void findForUpdate(EditViewSupplierInputBean bean) throws Exception {
        
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {

            con = DBConnection.getConnection();
            sql = "SELECT SUP_ID,NAME,EMAIL,ADDRESS,TP_OFFICE,TP_MOBILE,STATUS "
                    + "FROM ic_supplier WHERE SUP_ID=?";

            prepSt = con.prepareStatement(sql);
            prepSt.setString(1,bean.getUpsuppId());

            res = prepSt.executeQuery();

            if (res.next()) {
                bean.setUpsuppId(res.getString("SUP_ID"));
                bean.setUpsuppName(res.getString("NAME"));
                bean.setUpemail(res.getString("EMAIL"));       
                
                bean.setUpaddress(res.getString("ADDRESS")); 
                bean.setUptpOffice(res.getString("TP_OFFICE")); 
                bean.setUptpMobile(res.getString("TP_MOBILE")); 
                bean.setUpstatus(res.getString("STATUS")); 
            }
            
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

    }

    public boolean updateData(EditViewSupplierInputBean inputBean) throws Exception {

        
        boolean ok = false;
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String sql = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
           
            sql = "update ic_supplier SET EMAIL=?,ADDRESS=?,TP_OFFICE=?,TP_MOBILE=?,STATUS=? where SUP_ID=?";
            prepSt = con.prepareStatement(sql);
            prepSt.setString(1, inputBean.getUpemail());
            prepSt.setString(2, inputBean.getUpaddress());
            prepSt.setString(3, inputBean.getUptpOffice());
            prepSt.setString(4, inputBean.getUptpMobile());
            prepSt.setString(5, inputBean.getUpstatus());
            prepSt.setString(6, inputBean.getUpsuppId());

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
