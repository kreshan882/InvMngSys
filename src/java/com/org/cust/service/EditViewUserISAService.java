/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.cust.service;

import com.inv.db.DBConnection;
//import com.inv.init.AppType;
import com.inv.init.Status;


import com.org.cust.bean.EditViewUserISADataBean;
import com.org.cust.bean.EditUserISAInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tharaka
 */
public class EditViewUserISAService {

    public void loadinstituteList(EditUserISAInputBean bean) throws Exception {
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String insListQuery = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            insListQuery = "SELECT NAME,INSTITUTE_ID FROM E24OCM_INSTITUTE where INSTITUTE_ID !=? AND (STATUS= ? OR STATUS = ?)";
            prepSt = con.prepareStatement(insListQuery);
//            prepSt.setInt(1,UserCatagory.EPIC_ROOT_INSTITUTE_ID);
            prepSt.setString(2,Status.ACTIVE);
            prepSt.setString(3,Status.INACTIVE);
            res = prepSt.executeQuery();

            while (res.next()) {
                bean.getInstituteList().put(res.getInt("INSTITUTE_ID"), res.getString("NAME"));
            }
            con.commit();
        } catch (Exception e) {
            if(con!=null){con.rollback();}
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

    public List<EditViewUserISADataBean> loadUsersFromInstiute(EditUserISAInputBean bean, String orderBy, int max, int first) throws Exception {

        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<EditViewUserISADataBean> dataList = null;
        long totalCount = 0;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            
            String sqlCount = "select count(*) AS TOTAL FROM "
                    + "E24OCM_USER UA,E24OCM_STATUS ST WHERE UA.INSTITUTE_ID = ? "
                    + "AND UA.STATUS = ST.CODE AND UA.APP_TYPE = ? AND USERNAME LIKE ? AND (STATUS = ? OR STATUS = ?)";

                
            prepSt = con.prepareStatement(sqlCount);
            prepSt.setInt(1, bean.getInstituteID());
//            prepSt.setString(2, AppType.IAM);
            prepSt.setString(3, "%" + bean.getUsername() + "%");
            prepSt.setString(4, Status.ACTIVE);
            prepSt.setString(5, Status.INACTIVE);
            res = prepSt.executeQuery();
            while (res.next()) {

                totalCount = res.getLong("TOTAL");
            }

            
            if (prepSt != null) {  prepSt.close(); } if (res != null) { res.close(); }       
            
            
            getUsersListQuery = "SELECT   CAST(UA.REG_DATE AS CHAR)AS REG_DATE ,UA.STATUS AS STATUSCODE,UA.USERNAME AS USERNAME ,ST.DESCRIPTION AS STATUS,UA.COMPANY AS COMPANY FROM "
                    + "E24OCM_USER UA,E24OCM_STATUS ST WHERE UA.INSTITUTE_ID = ? "
                    + "AND UA.STATUS = ST.CODE AND UA.APP_TYPE = ? AND USERNAME LIKE ? AND (STATUS = ? OR STATUS = ?) " + orderBy + " LIMIT " + first + "," + max;

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setInt(1, bean.getInstituteID());
//            prepSt.setString(2, AppType.IAM);
            prepSt.setString(3, "%" + bean.getUsername() + "%");
            prepSt.setString(4, Status.ACTIVE);
            prepSt.setString(5, Status.INACTIVE);
            res = prepSt.executeQuery();
 
            dataList = new ArrayList<EditViewUserISADataBean>();

            while (res.next()) {

                EditViewUserISADataBean dataBean = new EditViewUserISADataBean();

                dataBean.setCompany(res.getString("COMPANY"));
                dataBean.setUsername(res.getString("USERNAME"));
                dataBean.setStatus(res.getString("STATUS"));
                dataBean.setStatusCode(res.getString("STATUSCODE"));  
                dataBean.setRegDate(res.getString("REG_DATE"));
                
                dataBean.setFullCount(totalCount);
                dataList.add(dataBean);
            }
            con.commit();

        } catch (Exception e) {
           if(con!=null){con.rollback();}
            e.printStackTrace();
            throw e;

        } finally {
            if (prepSt != null) {  prepSt.close(); } if (res != null) { res.close(); }  if (con != null) {con.close(); }

        }
        return dataList;
    }

    public void deleteUserISA(EditUserISAInputBean bean) throws Exception {

        PreparedStatement prepSt = null;
        Connection con = null;
        String deleteUser = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            deleteUser = "UPDATE E24OCM_USER SET STATUS =?  WHERE USERNAME= ? AND INSTITUTE_ID = ?";
            prepSt = con.prepareStatement(deleteUser);

            prepSt.setString(1,  Status.DELETED);
            prepSt.setString(2, bean.getUsername());            
            prepSt.setInt(3, bean.getInstituteID());
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

     public void findUser(EditUserISAInputBean bean) throws Exception {
        
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String getUsersListQuery = null;
        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            getUsersListQuery = "SELECT USERNAME,COMPANY,STATUS FROM E24OCM_USER WHERE USERNAME = ? AND INSTITUTE_ID = ? AND APP_TYPE = ?";

            prepSt = con.prepareStatement(getUsersListQuery);
            prepSt.setString(1,bean.getUsername());
            prepSt.setInt(2, bean.getInstituteID());
//            prepSt.setString(3, AppType.IAM);
            res = prepSt.executeQuery();

            while (res.next()) {

                bean.setUsername(res.getString("USERNAME"));
                bean.setCompany(res.getString("COMPANY"));
                bean.setStatusCode(res.getString("STATUS"));
                
            }
            bean.setInstituteID(bean.getInstituteID());
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

    public boolean updateUserISA(EditUserISAInputBean inputBean) throws Exception {

        
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
            prepSt.setString(1, inputBean.getUpstatus());
            prepSt.setString(2, inputBean.getUpcompany());
            prepSt.setString(3, inputBean.getUpusername2());
            prepSt.setInt(4,inputBean.getUpinstituteID());

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
    
    public void loadUserListFromInstitute(EditUserISAInputBean bean)throws Exception{
    
        PreparedStatement prepSt = null;
        ResultSet res = null;
        Connection con = null;
        String insListQuery = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            insListQuery = "SELECT USERNAME FROM E24OCM_USER WHERE INSTITUTE_ID=?";
            prepSt = con.prepareStatement(insListQuery);
            prepSt.setInt(1, bean.getInstituteID());
            res = prepSt.executeQuery();

            while (res.next()) {
              
               bean.getUsernameList().add(res.getString("USERNAME"));
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
    }

}
