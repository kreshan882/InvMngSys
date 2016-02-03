/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.cust.service;

import com.inv.db.DBConnection;
import com.org.cust.bean.ChangePasswordBean;
import com.inv.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author kreshan
 */
public class ChangePasswordService {

 
    
          public static boolean getOldPasswordFromDb(ChangePasswordBean cpb) throws Exception {
		PreparedStatement perSt = null;
		ResultSet res = null;
                Connection con = null;
		try {
                        con=DBConnection.getConnection();
                        con.setAutoCommit(false);
			String sql = "SELECT PASSWORD FROM E24OCM_USER where "
                                + "INSTITUTE_ID = ? AND USERNAME= ? ";
                        perSt = con.prepareStatement(sql);
                        perSt.setInt(1, cpb.getInstuteid());
                        perSt.setString(2, cpb.getUsername());
			res = perSt.executeQuery();

			while (res.next()) {                           
                                cpb.setPasswordDb(res.getString("PASSWORD"));
                                
			}
                        con.commit();
		} catch (Exception ex) {
                        con.rollback();
			throw ex;
		} finally {
			if (perSt != null) perSt.close();
			if (res != null) res.close();	
                        if (con != null) con.close();
		}
                return true;
		
	}

    public boolean checkPasswordMatchWithUserPass(ChangePasswordBean changePasswordBean) throws Exception {
        if (Util.generateHash(changePasswordBean.getPasswordOld()).equals(changePasswordBean.getPasswordDb())){
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkNewPasswordsMatch(ChangePasswordBean changePasswordBean) {
               
        if (changePasswordBean.getPasswordNew1().equals(changePasswordBean.getPasswordNew2())){
            return true;
        }
        else
        {
            return false;
        }
    }


          
        	
    public boolean updatePassword(ChangePasswordBean cpb) throws Exception {
		PreparedStatement ps = null;
                Connection con=null;
		try {
                        con= DBConnection.getConnection();
                        String sql = "UPDATE E24OCM_USER SET "
                                + "PASSWORD= ? WHERE INSTITUTE_ID= ? AND USERNAME= ? ";
                    
			
			ps = (PreparedStatement) con.prepareStatement(sql);
                        
                        ps.setString(1, Util.generateHash(cpb.getPasswordNew1()));
			ps.setInt(2, cpb.getInstuteid());
			ps.setString(3, cpb.getUsername());
			
			ps.executeUpdate();
                        
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (ps != null) ps.close();
                        if (con != null) con.close();
		}
                return true;
	}

    
}
