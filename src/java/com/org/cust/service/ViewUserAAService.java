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
import com.org.cust.bean.ViewUserAADataBean;
import com.org.cust.bean.ViewUserAAInputBean;
import com.inv.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;


/**
 *
 * @author tharaka
 */
public class ViewUserAAService {
    
    
     
    
//    public   void loaduserProfileList(ViewUserAAInputBean bean)throws Exception{
//        
//        HttpSession session = ServletActionContext.getRequest().getSession(false);
//        SessionUserBean su = (SessionUserBean)session.getAttribute("SessionObject");
//        PreparedStatement perSt = null;
//        ResultSet res = null;
//        Connection con=null;
//        String sql = null;
//        try {
//            con= DBConnection.getConnection();
//            con.setAutoCommit(false);
//            if(su.getApptype().equals(AppType.IAM)){
//                sql="SELECT PROFILE_ID,NAME FROM E24OCM_USER_PROFILE where INSTITUTE_ID =? AND STATUS!=?";
//
//                perSt = con.prepareStatement(sql);
//                perSt.setInt(1, su.getInstituteid());
//                perSt.setString(2, Status.DELETED);
//                res = perSt.executeQuery();
//            }else{
//                sql="SELECT PROFILE_ID,NAME FROM E24OCM_USER_PROFILE where INSTITUTE_ID =? AND APP_TYPE=? AND APP_ID=? AND STATUS!=?";
//
//                perSt = con.prepareStatement(sql);
//                perSt.setInt(1, su.getInstituteid());
//                perSt.setString(2, su.getApptype());
//                perSt.setInt(3, su.getAppid());
//                perSt.setString(4, Status.DELETED);
//                res = perSt.executeQuery();
//            }
//            while (res.next()) {
//                 bean.getUserProfileList().put(res.getInt("PROFILE_ID"), res.getString("NAME"));
//            }
//            
//            con.commit();
//        } catch (Exception ex) {
//            if(con!=null){con.rollback();}
//            
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
//    
//    }
    
    public   List<ViewUserAADataBean> loadUsers( ViewUserAAInputBean bean,String orderBy, int max, int first )throws Exception{
    
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        String getUsersListQuery = null;
        List<ViewUserAADataBean> dataList = null;
        String whereClause = "";
        long totalCount = 0;
        try {
             HttpSession session = ServletActionContext.getRequest().getSession(false);
            SessionUserBean sub = (SessionUserBean)session.getAttribute("SessionObject");
            
            con=DBConnection.getConnection();
            con.setAutoCommit(false);
            
            if (bean.getUserProfileID()!=-1) {
                whereClause += " AND USER_PROFILE = ?";
            }
            if(bean.getUserCategory()!=-1){
                whereClause += " AND USER_CATEGORY = ?" ;
            }
            
            String sqlCount ="select count(*) AS TOTAL FROM E24OCM_USER "
                    + "WHERE (STATUS = ? OR  STATUS = ?) AND USERNAME LIKE ? AND INSTITUTE_ID = ? AND APP_TYPE = ? AND APP_ID = ?"+whereClause;
            
            ps = con.prepareStatement(sqlCount);      
                  
            ps.setString(1, Status.ACTIVE);
            ps.setString(2, Status.INACTIVE);
            ps.setString(3, "%"+bean.getUsername()+"%");
//            ps.setInt(4, sub.getInstituteid());
//            ps.setString(5, sub.getApptype());
//            ps.setInt(6, sub.getAppid());
            
           if(bean.getUserProfileID()!=-1 && bean.getUserCategory()==-1){
                ps.setInt(7, bean.getUserProfileID());
            }else if(bean.getUserProfileID()==-1 && bean.getUserCategory()!=-1){
                ps.setInt(7, bean.getUserCategory());
            }else if(bean.getUserProfileID()!=-1 && bean.getUserCategory()!=-1){
                ps.setInt(7, bean.getUserProfileID());
                ps.setInt(8, bean.getUserCategory());
            }
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                totalCount = rs.getLong("TOTAL");
            }
            
            
            if (ps != null) {
                ps.close(); 
            } 
            if (rs != null) {
                rs.close(); 
            }     
             
            whereClause = "";
            
            if (bean.getUserProfileID()!=-1) {
                whereClause += " AND UA.USER_PROFILE = ?";
            }
            if(bean.getUserCategory()!=-1){
                whereClause += " AND UA.USER_CATEGORY = ?" ;
            }
            
           getUsersListQuery = "SELECT UA.USERNAME AS USERNAME,UA.EMAIL AS EMAIL,UA.TEL_NUMBER AS TELEPHONE,UA.NIC AS NIC,UA.LOCATION AS LOCATION,UA.COMPANY AS COMPANY,CAT.DESCRIPTION AS CATEGORY,PR.NAME AS PROFILE, AT.DESCRIPTION AS APP_TYPE,UA.STATUS AS STATUSCODE,ST.DESCRIPTION AS STATUS,UA.REG_DATE AS REG_DATE "
                   + "FROM E24OCM_USER UA,E24OCM_STATUS ST,E24OCM_USER_CATEGORY CAT,E24OCM_APP_TYPE AT,E24OCM_USER_PROFILE PR "
                   + "WHERE UA.STATUS=ST.CODE AND UA.USER_CATEGORY=CAT.CODE AND UA.USER_PROFILE=PR.PROFILE_ID  AND UA.APP_TYPE=AT.CODE  AND (UA.STATUS = ? OR  UA.STATUS = ?) AND UA.USERNAME LIKE ? AND UA.INSTITUTE_ID = ?  AND UA.APP_TYPE = ? AND UA.APP_ID = ?"+whereClause + orderBy + " LIMIT " + first + "," + max;
            
            ps = con.prepareStatement(getUsersListQuery);            
            ps.setString(1, Status.ACTIVE);
            ps.setString(2, Status.INACTIVE);
            ps.setString(3, "%"+bean.getUsername()+"%");
//            ps.setInt(4, sub.getInstituteid());
//            ps.setString(5, sub.getApptype());
//            ps.setInt(6, sub.getAppid());

            if(bean.getUserProfileID()!=-1 && bean.getUserCategory()==-1){
                ps.setInt(7, bean.getUserProfileID());
            }else if(bean.getUserProfileID()==-1 && bean.getUserCategory()!=-1){
                ps.setInt(7, bean.getUserCategory());
            }else if(bean.getUserProfileID()!=-1 && bean.getUserCategory()!=-1){
                ps.setInt(7, bean.getUserProfileID());
                ps.setInt(8, bean.getUserCategory());
            }

            rs = ps.executeQuery();

            dataList = new ArrayList<ViewUserAADataBean>();
            
            ViewUserAADataBean dataBean = null;
            while (rs.next()) {  
                
                dataBean =  new ViewUserAADataBean();
                dataBean.setUsername(rs.getString("USERNAME"));
                dataBean.setEmail(rs.getString("EMAIL"));                 
                dataBean.setStatusCode(rs.getString("STATUSCODE"));
                dataBean.setTelephonenumber(rs.getString("TELEPHONE"));
                dataBean.setNic(rs.getString("NIC"));
                dataBean.setLocation(rs.getString("LOCATION"));
                dataBean.setCompany(rs.getString("COMPANY"));
                dataBean.setCategory(rs.getString("CATEGORY"));
                dataBean.setProfile(rs.getString("PROFILE"));
                dataBean.setAppType(rs.getString("APP_TYPE"));
                dataBean.setRegistrationDate(rs.getString("REG_DATE"));
                dataBean.setFullCount(totalCount);
                dataList.add(dataBean);
                
            }
            con.commit();
        } catch (Exception e) {
            if(con!=null){con.rollback();}
             
            throw e;

        } finally {
			if (ps != null) ps.close();
			if (rs != null) rs.close();	
                        if (con != null) con.close();

        }
        return dataList;
    }
    
    
}
