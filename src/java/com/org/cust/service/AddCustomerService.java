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

    public boolean insertISADetails(CustomerBeen userBean ,  SessionUserBean sessionUserBean) throws Exception{
        Connection connection = null;
        String query;
        PreparedStatement addUserStatement=null;
        boolean ok=false;
        
        
        try {
            connection = DBConnection.getConnection();
            query="INSERT INTO E24OCM_USER (USER_CATEGORY,USER_PROFILE,INSTITUTE_ID,STATUS,APP_TYPE,APP_ID,USERNAME,PASSWORD,EMAIL,TEL_NUMBER,NIC,LOCATION,COMPANY,GENDER,REG_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            addUserStatement = connection.prepareStatement(query);
                                
            

            addUserStatement.setInt(3, Integer.parseInt(userBean.getInstitute()));
            addUserStatement.setString(4,Status.ACTIVE);
//            addUserStatement.setString(5,AppType.IAM);
            addUserStatement.setInt(6,0);
            addUserStatement.setString(7, userBean.getUserId());
            addUserStatement.setString(8, Util.generateHash(userBean.getPassword()));//should be insert increpted password
            addUserStatement.setString(9, userBean.getEmail());
            addUserStatement.setString(10, userBean.getTelephone());
            addUserStatement.setString(11, userBean.getNic());
            addUserStatement.setString(12, userBean.getLocation());
            addUserStatement.setString(13, userBean.getCompany());
            addUserStatement.setString(14, userBean.getGender());
            addUserStatement.setDate(15, (Date) Util.getLocalDate());
            
            
            
           int n= addUserStatement.executeUpdate();
           if(n >= 0){
               ok=true;
           }

            
            
        } catch (Exception e) {
            throw  e;
        }finally{
            if(addUserStatement != null){
                addUserStatement.close();
            }
            if(connection != null){
                DBConnection.dbConnectionClose(connection);
            }
            
            
        }
        return ok;
      
    }
    
    public void getInstituteList(CustomerBeen userBean) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT INSTITUTE_ID,NAME FROM E24OCM_INSTITUTE where INSTITUTE_ID !=? AND STATUS=?";
            ps = connection.prepareStatement(sql);
//            ps.setInt(1,UserCatagory.EPIC_ROOT_INSTITUTE_ID);
            ps.setString(2, Status.ACTIVE);
            result = ps.executeQuery();

            while (result.next()) {
                userBean.getInstituteList().put(result.getInt("INSTITUTE_ID"), result.getString("NAME"));
            }
            connection.commit();
           
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
                DBConnection.dbConnectionClose(connection);
            }
        }
        
        }

    
    
     public boolean checkUserName(CustomerBeen userBean) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        boolean ok=false;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT * FROM E24OCM_USER WHERE INSTITUTE_ID=? AND USERNAME=?  AND STATUS!=? ";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(userBean.getInstitute()));
            ps.setString(2, userBean.getUserId());
            ps.setString(3, Status.DELETED);
            result = ps.executeQuery();

            if (result.next()) {
                ok = true;
            }
            connection.commit();
           
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
