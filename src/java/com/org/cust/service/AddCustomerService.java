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

    public boolean addData(CustomerBeen userBean ,  SessionUserBean sessionUserBean) throws Exception{
        Connection con = null;
        String query;
        PreparedStatement preStat=null;
        boolean ok=false;
        
        
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            query="insert into ic_customer(NAME,EMAIL,ADDRESS,TP_MOBILE,TP_OFFICE,STATUS,REG_DATE) "
                    + "VALUES(?,?,?,?,  ?,?,?)";
            
            preStat = con.prepareStatement(query);

            preStat.setString(1, userBean.getCustName());
            preStat.setString(2, userBean.getEmail());
            preStat.setString(3, userBean.getAddress());
            
            preStat.setString(4, userBean.getTpMobile());
            preStat.setString(5, userBean.getTpOffice());
            preStat.setString(6, Status.ACTIVE);
            preStat.setDate(7, (Date) Util.getLocalDate());

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
