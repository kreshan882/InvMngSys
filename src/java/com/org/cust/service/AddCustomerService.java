/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.cust.service;

import com.inv.db.DBConnection;
//import com.inv.init.AppType;
import com.inv.init.Status;
import com.org.login.bean.SessionUserBean;
import com.org.cust.bean.CustomerBeen;
import com.inv.util.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 * Title       : InstituteSuperUserService
 * Description : Add Institute super User to DB
 * Company     : Epic Lanka (pvt) Ltd
 * @author sadun
 *
 */
public class AddCustomerService {

    public boolean insertCustomerDetails(CustomerBeen userBean ,  SessionUserBean sessionUserBean) throws Exception{
        Connection con = null;
        String query;
        PreparedStatement preStat=null;
        boolean ok=false;
        
        
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            query="insert into ic_customer(NAME,COMPANY_NAME,EMAIL,ADDRESS,TP_MOBILE,TP_OFFICE,STATUS,REG_DATE) "
                    + "VALUES(?,?,?,?,  ?,?,?,?)";
            
            preStat = con.prepareStatement(query);

            preStat.setString(1, userBean.getCustName());
            preStat.setString(2, userBean.getCompanyName());
            preStat.setString(3, userBean.getEmail());
            preStat.setString(4, userBean.getAddress());
            
            preStat.setString(5, userBean.getTpMobile());
            preStat.setString(6, userBean.getTpOffice());
            preStat.setString(7, Status.ACTIVE);
            preStat.setDate(8, (Date) Util.getLocalDate());

           int n= preStat.executeUpdate();
           if(n >= 0){
               ok=true;
           }
           con.commit();

        } catch (Exception e) {
            throw  e;
        }finally{
            if(preStat != null){
                preStat.close();
            }
            if(con != null){
                DBConnection.dbConnectionClose(con);
            }
        }
        return ok;
      
    }
    
//    public void getInstituteList(CustomerBeen userBean) throws Exception {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet result = null;
//
//        try {
//            connection = DBConnection.getConnection();
//            connection.setAutoCommit(false);
//            String sql = "SELECT INSTITUTE_ID,NAME FROM E24OCM_INSTITUTE where INSTITUTE_ID !=? AND STATUS=?";
//            ps = connection.prepareStatement(sql);
////            ps.setInt(1,UserCatagory.EPIC_ROOT_INSTITUTE_ID);
//            ps.setString(2, Status.ACTIVE);
//            result = ps.executeQuery();
//
//            while (result.next()) {
//                userBean.getInstituteList().put(result.getInt("INSTITUTE_ID"), result.getString("NAME"));
//            }
//            connection.commit();
//           
//        }
//        catch (Exception ex) {
//            throw ex;
//	}finally {
//            if (ps != null) {
//                ps.close();
//            }
//            if (result != null) {
//                result.close();
//            }
//            if (connection != null) {
//                DBConnection.dbConnectionClose(connection);
//            }
//        }
//        
//        }

    
    
     public boolean checkCusName(String cusname) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        boolean ok=false;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM ic_customer WHERE NAME=? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cusname);
            result = ps.executeQuery();

            if (result.next()) {
                ok = true;
            }
           
        }
        catch (Exception ex) {
            throw ex;
	}finally {
            if (ps != null) {
                ps.close();
            }
            if (result != null) {
                result.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return  ok;
        
        }
  
    
    
  
    
}
