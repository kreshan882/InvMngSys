/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.cust.service;

import com.inv.db.DBConnection;
//import com.inv.init.AppType;
import com.inv.init.Status;
import com.org.cust.bean.CustomerBeen;



import com.org.cust.bean.EditViewCustomerInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tharaka
 */
public class EditViewCustomerService {


    public List<CustomerBeen> loadUsersFromInstiute(EditViewCustomerInputBean bean, String orderBy, int max, int first) throws Exception {

        
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
       
            quary ="SELECT CUS_ID,NAME,COMPANY_NAME,EMAIL,ADDRESS,TP_OFFICE,TP_MOBILE,STATUS,CAST(REG_DATE AS CHAR) AS REG_DATE "
                    + "FROM ic_customer WHERE NAME LIKE ? "+orderBy+ " LIMIT " + first + "," + max;
            
            perSt = con.prepareStatement(quary); 
            perSt.setString(1, "%"+bean.getCustName()+"%"); 
            res = perSt.executeQuery();             
            
            dataList = new ArrayList<CustomerBeen>();
             while (res.next()) {
                 
                 CustomerBeen been = new CustomerBeen();

                 been.setCustId(res.getString("CUS_ID")); 
                 been.setCustName(res.getString("NAME"));
                 been.setCompanyName(res.getString("COMPANY_NAME"));
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
    
    

    public void deleteUserISA(EditViewCustomerInputBean bean) throws Exception {

        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "UPDATE E24OCM_USER SET STATUS =?  WHERE USERNAME= ? AND INSTITUTE_ID = ?";
            prepSt = con.prepareStatement(deleteUser);

            prepSt.setString(1,  Status.DELETED);
//            prepSt.setString(2, bean.getUsername());            
       
            prepSt.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;

        } finally {
            if (prepSt != null) {
                prepSt.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
//Added on 22nd Aug to find user.

     public void findUser(EditViewCustomerInputBean bean) throws Exception {
        
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            getUsersListQuery = "SELECT USERNAME,COMPANY,STATUS FROM E24OCM_USER WHERE USERNAME = ? AND INSTITUTE_ID = ? AND APP_TYPE = ?";

            prepSt = con.prepareStatement(getUsersListQuery);
//            prepSt.setString(1,bean.getUsername());

            res = prepSt.executeQuery();

            while (res.next()) {

//                bean.setUsername(res.getString("USERNAME"));
//                bean.setCompany(res.getString("COMPANY"));
//                bean.setStatusCode(res.getString("STATUS"));
                
            }
//            bean.setInstituteID(bean.getInstituteID());
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

    }

    public boolean updateUserISA(EditViewCustomerInputBean inputBean) throws Exception {

        
        boolean ok = false;
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
           
            getUsersListQuery = "UPDATE E24OCM_USER SET STATUS =? , COMPANY = ?  WHERE USERNAME= ? AND INSTITUTE_ID = ? ";
            prepSt = con.prepareStatement(getUsersListQuery);
//            prepSt.setString(1, inputBean.getUpstatus());
//            prepSt.setString(2, inputBean.getUpcompany());
//            prepSt.setString(3, inputBean.getUpusername2());
//            prepSt.setInt(4,inputBean.getUpinstituteID());

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
